package com.example.jehlum.rxjava.retriever;

import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WikiContentRetrieverTest {

    private WikiContentRetriever contentRetriever;

    @Test
    void retrieve() {
        // this will retrieve actual content from the wiki site mock the data source is generally
        // considered a better practice though. Also actual observable thread pool is used, in a
        // mocked scenario, we can use TestScheduler class
        contentRetriever = new WikiContentRetriever("https://en.wikipedia.org/wiki/");
        TestObserver<String> testObserver = contentRetriever
                .retrieve(new String[]{"Apache", "SourceForge"})
                .observeOn(Schedulers.io())
                .test();

        testObserver.assertValueAt(0, input -> input.contains("Apache"));
        testObserver.assertValueAt(1, input -> input.contains("SourceForge"));
    }

    @Test
    void retrieve_Invalid() {
        contentRetriever = new WikiContentRetriever("https://en.wikipedia.org/wiki/");
        TestObserver<String> testObserver = contentRetriever
                .retrieve(new String[]{"invalid"})
                .observeOn(Schedulers.io())
                .test();

        testObserver.assertNoValues();
    }
}