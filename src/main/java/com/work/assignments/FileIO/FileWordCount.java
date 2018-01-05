package com.work.assignments.FileIO;


import java.io.*;
import java.util.*;

public class FileWordCount {

    public Map<String, Integer> countWordsInAFile(String fileName) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        BufferedReader bufferedReader = null;
        try {
            FileReader fr = FileIO.openFile(fileName);
            bufferedReader = new BufferedReader(fr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                wordCount(wordCountMap, line);
            }
            return wordCountMap;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        } finally {
            FileIO.closeFile(bufferedReader);
        }
    }

    private void wordCount(Map<String, Integer> wordCountMap, String line) {
        List<String> list = Arrays.asList(line.split(" "));
        for (String s : list) {
            s = s.replaceAll("[-+.^:,@*!#${}&()]","");
            if (wordCountMap.containsKey(s)) {
                int wc = wordCountMap.get(s);
                wordCountMap.put(s, ++wc);
            } else {
                wordCountMap.put(s, 1);
            }
        }
    }

}
