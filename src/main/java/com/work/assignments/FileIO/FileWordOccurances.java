package com.work.assignments.FileIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.*;


public class FileWordOccurances {

    public List<Tuple<Integer, Integer>> getFileWordOccurances(String fileName, String word) {
        BufferedReader bufferedReader = null;
        File file = new File(fileName);
        try {
            FileReader fr = FileIO.openFile(fileName);
            bufferedReader = new BufferedReader(fr);
            String line;
            int lineCounter = 1;
            while((line = bufferedReader.readLine())!= null) {
                countWordOccurancesInLine(file, line, lineCounter);
                lineCounter++;
            }
            return getLineWordPositions(file,word);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Failed to read File.", e);
        }
        finally {
            FileIO.closeFile(bufferedReader);
        }

    }

    private void countWordOccurancesInLine(File file, String line, int lineCounter) {
        List<String> list = Arrays.asList(line.split(" "));
        int wordPosition = 1;
        for (String s : list) {
            if(file.containsWord(s)) {
                Word word =  file.getWord(s);
                word.putwordsInLine(lineCounter, wordPosition++);
            }
            else {
                Word word = new Word(s);
                word.putwordsInLine(lineCounter, wordPosition++);
                file.putWord(s, word);
            }
        }
    }

    private List<Tuple<Integer, Integer>> getLineWordPositions(File file, String word) {
        Word obj = file.getWord(word);
        return obj.getwordList();
    }
}
