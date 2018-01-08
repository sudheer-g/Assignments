package com.work.assignments.FileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.*;


public class DirectoryWordOccurrences {

    private void countWordOccurencesInLine(String line, int lineCounter, String word, String fileName, Word wordObj) {
        int wordIndex;
        wordIndex = line.indexOf(word);
        while(wordIndex >= 0) {
            wordObj.putWordsInLine(lineCounter, wordIndex, fileName);
            wordIndex = line.indexOf(word, wordIndex + word.length());
        }
    }

    private List<Result> getFileWordOccurances(String fileName, String word) {
        BufferedReader bufferedReader = null;
        Word wordObj = new Word();
        int lineCounter = 1;
        try {
            FileReader fr = FileIO.openFile(fileName);
            bufferedReader = new BufferedReader(fr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                countWordOccurencesInLine(line, lineCounter, word, fileName, wordObj);
                lineCounter++;
            }
            return wordObj.getWordList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read File.", e);
        } finally {
            FileIO.closeFile(bufferedReader);
        }
    }

    public List<Result> getDirectoryWordOccurrences(String directoryName, String word) {
        java.io.File folder = new java.io.File(directoryName);
        java.io.File[] listOfFiles = folder.listFiles();
        List<Result> resultList = new ArrayList<>();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    List<Result> list = getFileWordOccurances(directoryName + "/" + file.getName(), word);
                    resultList.addAll(list);
                }
            }
        }
        return resultList;
    }
}
