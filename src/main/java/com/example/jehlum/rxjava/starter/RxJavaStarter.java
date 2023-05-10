package com.example.jehlum.rxjava.starter;

import com.example.jehlum.rxjava.retriever.WikiContentRetriever;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @author irfan.nagoo
 */
public class RxJavaStarter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RxJavaStarter.class);

    public static void main(String[] args) throws InterruptedException {
        // execute, subscribe and print callable
        Disposable d1 = Flowable.fromCallable(() -> "Hello RxJava")
                .subscribe(LOGGER::info);

        // parallel execute with callable
        Disposable d2 = Flowable.fromCallable(() -> {
                    Thread.sleep(1000);
                    return "Completed";
                })
                // parallel execution on consumer side
                .subscribeOn(Schedulers.io())
                // parallel execution on producer/creator side
                .observeOn(Schedulers.single())
                .subscribe(LOGGER::info, e -> LOGGER.error("Error occurred: ", e));

        // parallel execute with arrays
        Disposable d3 = Flowable.fromArray(new String[]{"Hello World", "Hello RxJava"})
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .subscribe(LOGGER::info);


        // simple observable processing
        Disposable d4 = getUpperCase(new String[]{"jan", "feb", "mar"})
                .skip(1)
                .take(2)
                .subscribe(LOGGER::info);

        // advanced observable processing
        Disposable d5 = new WikiContentRetriever("https://en.wikipedia.org/wiki/")
                .retrieve(new String[]{"Apache", "SourceForge"})
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(article -> System.out.println(article.substring(0, 10)));

        // time lap to let the tasks finish
        Thread.sleep(3000);

        // dispose all resources
        destroy(Arrays.asList(d1, d2, d3, d4, d5));
    }

    private static Observable<String> getUpperCase(String[] args) {
        return Observable.create(emitter -> {
            for (String input : args) {
                emitter.onNext(input.toUpperCase());
            }
            emitter.onComplete();
        });
    }

    private static void destroy(List<Disposable> list) {
        list.forEach(Disposable::dispose);
    }

}
