package com.work.assignments.FileIO;

public class Query {
    public String directoryOrFile;
    public String word;
    public boolean recursive;

    public Query(String directoryOrFile, String word, boolean recursive) {
        this.directoryOrFile = directoryOrFile;
        this.word = word;
        this.recursive = recursive;
    }

    public Query() {
    }

    @Override
    public String toString() {
        return "(" + this.directoryOrFile + ", " + this.word + ", " + this.recursive + ")";
    }
}
