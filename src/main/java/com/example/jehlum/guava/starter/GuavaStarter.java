package com.example.jehlum.guava.starter;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.MutableClassToInstanceMap;
import com.google.common.collect.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @author irfan.nagoo
 */
public class GuavaStarter {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaStarter.class);

    public static void main(String[] args) {
        // creates a table and inserts records at certain row and column
        Table<Integer, Integer, BigDecimal> table = HashBasedTable.create();
        table.put(0, 0, BigDecimal.ONE);
        table.put(0, 1, BigDecimal.TEN);
        LOGGER.info(table.column(1).toString());

        // create class type, instance (value) map
        ClassToInstanceMap<Number> classToInstanceMap = MutableClassToInstanceMap.create();
        classToInstanceMap.putInstance(Integer.class, 100);
        LOGGER.info(classToInstanceMap.getInstance(Integer.class).toString());
    }

}
