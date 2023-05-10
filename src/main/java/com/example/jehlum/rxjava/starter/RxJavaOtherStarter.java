package com.example.jehlum.rxjava.starter;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @author irfan.nagoo
 */
public class RxJavaOtherStarter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RxJavaOtherStarter.class);

    public static void main(String[] args) {
        Observable<Number> digits = Observable.just(5, 6, 7);
        Observable<String> alpha = Observable.just("X", "Y", "Z");

        // zip alpha and numbers together
        Disposable d1 = Observable.zip(digits, alpha, (a, b) -> a + b)
                .subscribe(LOGGER::info);

        // zip overloaded
        Disposable d2 = Observable.range(1, 10)
                .zipWith(Observable.just("A", "B"), (a, b) -> a + b)
                .subscribe(LOGGER::info);

        // merge two observables
        Disposable d3 = Observable.merge(Observable.just(10), Observable.just(20))
                .subscribe(x -> LOGGER.info(x.toString()));

        // dispose off resources
        destroy(Arrays.asList(d1, d2, d3));
    }

    private static void destroy(List<Disposable> list) {
        list.forEach(Disposable::dispose);
    }

}
