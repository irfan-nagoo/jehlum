package com.example.jehlum.opennlp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DocumentCategorizerTest {

    @Test
    void findCategory_Device_Categorization() throws IOException {
        DocumentCategorizer documentCategorizer = new DocumentCategorizer("src/test/resources/opennlp/en-token.bin",
                "src/test/resources/opennlp/en-maxent-train1");
        String category = documentCategorizer.findCategory("It is cold during winter");
        assertEquals("Clothes", category);

        category = documentCategorizer.findCategory("I did download pdf from internet");
        assertEquals("Laptop", category);

        category = documentCategorizer.findCategory("In installed More apps on my mobile device");
        assertEquals("Mobile", category);
    }

    @Test
    void findCategory_Sentence_Categorization() throws IOException {
        DocumentCategorizer documentCategorizer = new DocumentCategorizer("src/test/resources/opennlp/en-token.bin",
                "src/test/resources/opennlp/en-maxent-train2");

        String category = documentCategorizer.findCategory("The share prices movement of ACME was upward today");
        assertEquals("GMIncrease", category);

        category = documentCategorizer.findCategory("There was a major decline in the temperature yesterday");
        assertEquals("GMDecrease", category);
    }

}