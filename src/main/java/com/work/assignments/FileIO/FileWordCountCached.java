package com.work.assignments.FileIO;

import java.util.HashMap;
import java.util.Map;

public class FileWordCountCached {

    private Map<String, HashMap<String, Integer>> fileWordCache = new HashMap<>();

    private FileWordCount fileWordCount = new FileWordCount();

    public Map<String, Integer> createOrReadFileWordCache(String fileName) {
        if (!fileWordCache.containsKey(fileName)) {
            fileWordCache.put(fileName, (HashMap<String, Integer>) fileWordCount.countWordsInAFile(fileName));
        }
        return fileWordCache.get(fileName);
    }
}
