package com.example.jehlum.opennlp;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * This Entity finder uses preexisting modal to analyse the input
 * and returns person/location names
 *
 * @author irfan.nagoo
 */
public class EntityFinder {

    private final TokenizerME tokenizerME;
    private final NameFinderME nameFinderME;

    public EntityFinder(String tokenModelPath, String objectModelPath) throws IOException {
        this.tokenizerME = new TokenizerME(new TokenizerModel(getInputStream(tokenModelPath)));
        this.nameFinderME = new NameFinderME(new TokenNameFinderModel(getInputStream(objectModelPath)));
    }

    public String[] findEntity(String input) {
        String[] tokens = tokenizerME.tokenize(input);
        Span[] spans = nameFinderME.find(tokens);
        return Arrays.stream(spans)
                .map(s -> String.join(" ", Arrays.asList(tokens)
                        .subList(s.getStart(), s.getEnd())))
                .toArray(String[]::new);
    }

    private static InputStream getInputStream(String modelFilePath) throws IOException {
        return Files.newInputStream(new File(modelFilePath).toPath());
    }

}
