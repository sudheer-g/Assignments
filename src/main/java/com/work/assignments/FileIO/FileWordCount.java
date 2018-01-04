package com.work.assignments.FileIO;


import java.io.*;
import java.util.*;

public class FileWordCount {

    private FileReader openFile(String fileName) {
        try {
            return new FileReader(fileName);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }
    }

    private void closeFile(Reader fr)
    {
        if (fr != null) {
            try {
                fr.close();
            } catch (IOException e) {
                System.out.println("Failed to close");
            }
        }
    }

    public Map<String, Integer> countWordsInAFile(String fileName) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        BufferedReader bufferedReader = null;
        try {
            FileReader fr = openFile(fileName);
            bufferedReader = new BufferedReader(fr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                wordCount(wordCountMap, line);
            }
            return wordCountMap;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        } finally {
            closeFile(bufferedReader);
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
