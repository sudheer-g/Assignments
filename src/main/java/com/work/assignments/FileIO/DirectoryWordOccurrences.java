package com.work.assignments.FileIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DirectoryWordOccurrences {
    private List<Result> resultList = new ArrayList<>();
    private Logger logger = LogManager.getLogger(DirectoryWordOccurrences.class);


    private void countWordOccurrencesInLine(String line, int lineCounter, String word, String fileName, List<Result> resultList) {
        int wordIndex;
        wordIndex = line.indexOf(word);
        Result result;
        while (wordIndex >= 0) {
            result = new Result(lineCounter, wordIndex, fileName);
            resultList.add(result);
            wordIndex = line.indexOf(word, wordIndex + word.length());
        }
    }

    private List<Result> getFileWordOccurances(String fileName, String word) {
        BufferedReader bufferedReader = null;
        List<Result> resultList = new ArrayList<>();
        int lineCounter = 1;
        try {
            FileReader fr = FileIO.openFile(fileName);
            bufferedReader = new BufferedReader(fr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                countWordOccurrencesInLine(line, lineCounter, word, fileName, resultList);
                lineCounter++;
            }
            return resultList;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read File.", e);
        } finally {
            FileIO.closeFile(bufferedReader);
        }
    }

    public List<Result> getDirectoryWordOccurrences(String directoryName, String word, boolean recursive) {
        File folder = new File(directoryName);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    List<Result> list = getFileWordOccurances(directoryName + "/" + file.getName(), word);
                    resultList.addAll(list);
                }
                if (file.isDirectory() && recursive) {
                    resultList = getDirectoryWordOccurrences(directoryName + '/' + file.getName(), word, true);
                }
            }
        }
        return resultList;
    }

    public List<Result> getWords(Query query) {
        File file = new File(query.directoryOrFile);
        if (file.isFile()) {
            return getFileWordOccurances(query.directoryOrFile, query.word);
        } else {
            return getDirectoryWordOccurrences(query.directoryOrFile, query.word, query.recursive);
        }
    }
}
