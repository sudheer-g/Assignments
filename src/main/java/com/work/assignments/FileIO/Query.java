package com.work.assignments.FileIO;

public class Query {
    public String directoryName;
    public String word;
    public boolean recursive;

    public Query(String directoryName, String word, boolean recursive) {
        this.directoryName = directoryName;
        this.word = word;
        this.recursive = recursive;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
