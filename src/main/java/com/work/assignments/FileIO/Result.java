package com.work.assignments.FileIO;

public class Result {
    private int lineNumber;
    private int positionNumber;
    private String fileName;

    public Result(int x, int y, String fileName) {
        this.lineNumber = x;
        this.positionNumber = y;
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "(" + lineNumber + ", " + positionNumber + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof Result)) {
            return false;
        }

        Result result = (Result) obj;
        return this.lineNumber == result.lineNumber && this.positionNumber == result.positionNumber && this.fileName.equals(result.fileName);
    }

    @Override
    public int hashCode() {
        return this.fileName.hashCode() ^ this.positionNumber ^ this.lineNumber;
    }
}
