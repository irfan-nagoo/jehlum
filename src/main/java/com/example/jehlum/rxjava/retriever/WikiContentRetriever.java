package com.example.jehlum.rxjava.retriever;


import io.reactivex.rxjava3.core.Observable;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class enables retrieval of content from given website by name
 *
 * @author irfan.nagoo
 */
public class WikiContentRetriever implements ContentRetriever<String[], Observable<String>> {

    private final String wikiBaseUrl;

    public WikiContentRetriever(String wikiBaseUrl) {
        this.wikiBaseUrl = wikiBaseUrl;
    }

    @Override
    public Observable<String> retrieve(String[] articleNames) {
        return Observable.create(subscriber -> {
            Arrays.stream(articleNames).forEach(articleName -> {
                try {
                    // emit event for each article
                    subscriber.onNext(new Scanner(new URL(wikiBaseUrl + articleName).openStream(),
                            StandardCharsets.UTF_8.name()).useDelimiter("\\A").next());
                } catch (IOException e) {
                    // report error is any
                    subscriber.onError(e);
                }
            });
            // complete once all articles done
            subscriber.onComplete();
        });
    }
}
