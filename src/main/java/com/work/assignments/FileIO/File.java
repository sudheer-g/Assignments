package com.work.assignments.FileIO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class File {
    public String name;
    private Map<String, Word> wordHash = new HashMap<>();

    File(String name) {
        this.name = name;
    }

    public Word getWord(String word) {
        return wordHash.get(word);
    }

    public boolean containsWord(String word) {
        return wordHash.containsKey(word);
    }

    public void putWord(String word, Word obj) {
        wordHash.put(word, obj);
    }

}
