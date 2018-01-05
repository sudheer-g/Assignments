package com.work.assignments.FileIO;
import java.util.*;
public class Word {
    private String wordName;
    private List<Tuple<Integer, Integer>> list = new ArrayList<>();

    Word(String wordName) {
        this.wordName = wordName;
    }

    public void putwordsInLine(int lineCount, int wordOccurance) {
        Tuple<Integer, Integer> tuple = new Tuple<>(lineCount, wordOccurance);
        list.add(tuple);
    }

    public List<Tuple<Integer, Integer>> getwordList() {
        return list;
    }

}
