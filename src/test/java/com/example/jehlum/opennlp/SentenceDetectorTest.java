package com.example.jehlum.opennlp;

import opennlp.tools.util.Span;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class SentenceDetectorTest {

    private SentenceDetector sentenceDetector;

    @Test
    void detectSentences() throws IOException {
        sentenceDetector = new SentenceDetector("src/test/resources/opennlp/en-sent.bin");
        String[] output = sentenceDetector.detectSentences("Hello, How are you doing? Today is Tuesday");
        assertEquals(2, output.length);
        assertEquals("Hello, How are you doing?", output[0]);
    }

    @Test
    void detectSpans() throws IOException {
        sentenceDetector = new SentenceDetector("src/test/resources/opennlp/en-sent.bin");
        Span[] output = sentenceDetector.detectSpans("Hello, How are you doing? Today is Tuesday");
        assertEquals(2, output.length);
        assertEquals("[26..42)", output[1].toString());
    }
}