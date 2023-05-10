package com.example.jehlum.annotation.service;

import com.example.jehlum.annotation.PerformanceLogger;

/**
 * @author irfan.nagoo
 */
public interface DepartmentService {

    @PerformanceLogger
    String printDepartmentName(String name);
}
