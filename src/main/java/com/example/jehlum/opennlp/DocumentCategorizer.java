package com.example.jehlum.opennlp;

import opennlp.tools.doccat.*;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * @author irfan.nagoo
 */

public class DocumentCategorizer {

    private final TokenizerME tokenizerME;

    private final DoccatModel doccatModel;

    public DocumentCategorizer(String tokenizerPath, String trainFilePath) throws IOException {
        this.tokenizerME = new TokenizerME(new TokenizerModel(getInputStream(tokenizerPath)));
        this.doccatModel = getDoccatModel(trainFilePath);
    }

    private DoccatModel getDoccatModel(String trainFilePath) throws IOException {
        // read plain text train input file
        InputStreamFactory isf = () -> getInputStream(trainFilePath);
        try (ObjectStream<DocumentSample> objStream = new DocumentSampleStream(new PlainTextByLineStream(isf,
                Charset.defaultCharset()))) {

            // customize doc category factory with feature generators for
            // processing words in a given input
            FeatureGenerator[] featureGenerators = new FeatureGenerator[]{new NGramFeatureGenerator(1, 1),
                    new NGramFeatureGenerator(2, 3), new BagOfWordsFeatureGenerator()};

            // Training parameters-
            //   CUTOFF_PARAM - Minimum number of words to in a given category
            //   ITERATIONS_PARAM - Number of iteration to perform
            TrainingParameters trainingParameters = TrainingParameters.defaultParams();
            trainingParameters.put(TrainingParameters.CUTOFF_PARAM, 1);
            trainingParameters.put(TrainingParameters.ITERATIONS_PARAM, 10);
            return DocumentCategorizerME.train("en", objStream, trainingParameters, new DoccatFactory(featureGenerators));
        }
    }

    public String findCategory(String input) {
        String[] tokens = tokenizerME.tokenize(input);
        DocumentCategorizerME documentCategorizerME = new DocumentCategorizerME(doccatModel);
        double[] result = documentCategorizerME.categorize(tokens);
        return documentCategorizerME.getBestCategory(result);
    }

    private static InputStream getInputStream(String modelFilePath) throws IOException {
        return Files.newInputStream(new File(modelFilePath).toPath());
    }

}
