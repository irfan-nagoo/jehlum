package com.example.jehlum.opennlp;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * This Sentence detector uses preexisting modal to analyse the input
 * and returns the extract sentences out of it
 *
 * @author irfan.nagoo
 */
public class SentenceDetector {

    private final SentenceDetectorME sentenceDetectorME;

    public SentenceDetector(String modelFilePath) throws IOException {
        this.sentenceDetectorME = new SentenceDetectorME(new SentenceModel(getInputStream(modelFilePath)));
    }

    public String[] detectSentences(String sentence) {
        return sentenceDetectorME.sentDetect(sentence);
    }

    public Span[] detectSpans(String sentence) {
        return sentenceDetectorME.sentPosDetect(sentence);
    }

    private static InputStream getInputStream(String modelFilePath) throws IOException {
        return Files.newInputStream(new File(modelFilePath).toPath());
    }

}
