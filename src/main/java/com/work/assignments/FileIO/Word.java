package com.work.assignments.FileIO;

import java.util.*;

public class Word {
    private List<Result> list = new ArrayList<>();

    public void putWordsInLine(int lineCount, int wordOccurance, String fileName) {
        Result result = new Result(lineCount, wordOccurance, fileName);
        list.add(result);
    }

    public List<Result> getWordList() {
        return list;
    }

}
