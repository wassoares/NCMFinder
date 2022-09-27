package org.soarescontabil.finder.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Repository {
    
    private final Map<String, File> files;
    
    public Repository(String path) {
        this.files = new HashMap<>();
        File directory = new File(path);
        File[] found = directory.listFiles();
        for (File file : found) {
            this.files.put(file.getName(), file);
        }
    }

    public Map<String, File> getFiles() {
        return files;
    }

    public File findBy(String fileName) {
        return this.files.get(fileName);
    }
    
}
