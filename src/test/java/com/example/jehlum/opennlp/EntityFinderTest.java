package com.example.jehlum.opennlp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EntityFinderTest {

    private EntityFinder entityFinder;

    @Test
    void findName_Person() throws IOException {
        entityFinder = new EntityFinder("src/test/resources/opennlp/en-token.bin",
                "src/test/resources/opennlp/en-ner-person.bin");
        String[] names = entityFinder.findEntity("Tom doing well Mike");
        assertEquals(2, names.length);
        assertEquals("Tom", names[0]);
        assertEquals("Mike", names[1]);
    }

    @Test
    void findName_Location() throws IOException {
        entityFinder = new EntityFinder("src/test/resources/opennlp/en-token.bin",
                "src/test/resources/opennlp/en-ner-location.bin");
        String[] locations = entityFinder.findEntity("Im going to New York this summer");
        assertEquals(1, locations.length);
        assertEquals("New York", locations[0]);
    }
}