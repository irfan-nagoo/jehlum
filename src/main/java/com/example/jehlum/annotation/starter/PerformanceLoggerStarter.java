package com.example.jehlum.annotation.starter;

import com.example.jehlum.annotation.service.DepartmentService;
import com.example.jehlum.annotation.service.DepartmentServiceImpl;
import com.example.jehlum.annotation.service.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author irfan.nagoo
 */
public class PerformanceLoggerStarter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceLoggerStarter.class);

    public static void main(String[] args) {
        // CGLIB proxy
        SampleService sampleService = SampleService.getInstance();
        LOGGER.info("Max : {}", sampleService.getMax(Arrays.asList(10, -20, 30, 40)));
        LOGGER.info("Min : {}", sampleService.getMin(Arrays.asList(-200, 100, 456, 987, 500)));

        // Dynamic proxy
        DepartmentService departmentService = DepartmentServiceImpl.getInstance();
        LOGGER.info("Department: {}", departmentService.printDepartmentName("Software Engineering"));
    }


}
