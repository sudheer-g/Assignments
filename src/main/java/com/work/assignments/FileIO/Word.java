package com.work.assignments.FileIO;
import java.util.*;
public class Word{
    private String wordName;
    private List<Result> list = new ArrayList<>();

    Word(String wordName) {
        this.wordName = wordName;
    }

    public void putwordsInLine(int lineCount, int wordOccurance, String fileName) {
        Result result = new Result(lineCount, wordOccurance, fileName);
        list.add(result);
    }

    public List<Result> getwordList() {
        return list;
    }

}
