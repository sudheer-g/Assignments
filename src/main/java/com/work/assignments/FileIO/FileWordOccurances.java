package com.work.assignments.FileIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileWordOccurances {
    Map<String, List<Integer>> hashMap = new HashMap<>();
    public Map<String ,List<Integer>> getFileWordOccurances(String fileName) {
        BufferedReader bufferedReader = null;
        try {

            FileReader fr = FileIO.openFile(fileName);
            bufferedReader = new BufferedReader(fr);
            String line;
            while((line = bufferedReader.readLine())!= null) {
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("Failed to read File.", e);
        }
        finally {
            FileIO.closeFile(bufferedReader);
        }
        return hashMap;
    }
}
