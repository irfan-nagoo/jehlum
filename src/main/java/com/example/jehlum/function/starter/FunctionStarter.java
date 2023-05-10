package com.example.jehlum.function.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

/**
 * @author irfan.nagoo
 */

public class FunctionStarter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionStarter.class);

    public static void main(String[] args) {
        // just function with single arg
        Function<Integer, Integer> mod = a -> a % 2;
        LOGGER.info("Function Result: {}", mod.apply(13));

        // bi function with two args
        BiFunction<Integer, Integer, Integer> multiple = (a, b) -> a * b;
        BiFunction<Integer, Integer, Integer> multipleThenModOfTwo = multiple.andThen(a -> a / 2);
        LOGGER.info("BiFunction with Function Result: {}", multipleThenModOfTwo.apply(10, 20));

        // predicate for boolean
        Predicate<Integer> isEven = a -> a % 2 == 0;
        LOGGER.info("Predicate Result: {}", isEven.test(5));

        // consumer to process single arg
        Consumer<Integer> consumer = a -> LOGGER.info("Consumer Result: {}", a);
        consumer.accept(90);
        // bi consumer to process two args
        BiConsumer<Integer, Integer> biConsumer = (a, b) -> LOGGER.info("BiConsumer Result: {}", (a + b));
        biConsumer.accept(20, 30);

        // supplier to return a value
        Supplier<String> supplier = () -> "300";
        LOGGER.info("Supplier Result: {}", supplier.get());

        // binary-operator to accept same type of args and return same type of value
        BinaryOperator<Integer> summation = Integer::sum;
        LOGGER.info("BinaryOperator Result: {}", summation.apply(70, 30));

        // reduce function
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        LOGGER.info("Summation: {}", list.stream().reduce(summation).get());

        // an example of bi function in a separate method
        LOGGER.info("Add and Get: {}", addAndGetString().apply(10, 12));
    }

    private static BiFunction<Integer, Integer, String> addAndGetString() {
        return (x, y) -> String.valueOf((x + y));
    }

}
