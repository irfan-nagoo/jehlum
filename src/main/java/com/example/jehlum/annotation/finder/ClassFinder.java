package com.example.jehlum.annotation.finder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Find all the classes in the given package and sub packages recursively
 *
 * @author irfan.nagoo
 */
public class ClassFinder {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassFinder.class);

    private final String packageName;

    public ClassFinder(String packageName) {
        this.packageName = packageName;
    }

    public List<Class<?>> find() {
        String path = packageName.replace(".", "/");
        URL url = ClassFinder.class.getClassLoader().getResource(path);
        if (url == null) {
            throw new IllegalArgumentException("Package name is invalid");
        }
        File directory = new File(url.getPath());
        return findAllClasses(new ArrayList<>(), packageName, directory.listFiles());
    }

    private List<Class<?>> findAllClasses(List<Class<?>> classList, String packageName, File[] directory) {
        try {
            for (File file : Objects.requireNonNull(directory)) {
                if (file.isFile()) {
                    String fileWithPkg = new StringBuilder().append(packageName).append(".")
                            .append(file.getName().replace(".class", "")).toString();
                    Class<?> clazz = Class.forName(fileWithPkg);
                    classList.add(clazz);
                } else {
                    String dirPath = file.getPath();
                    String pkgName = dirPath.substring(dirPath.indexOf("com"))
                            .replace("\\", ".");
                    findAllClasses(classList, pkgName, file.listFiles());
                }
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error("Error occurred while finding classes: ", e);
            throw new RuntimeException(e);
        }
        return classList;
    }

}
