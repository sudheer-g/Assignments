package com.work.assignments.FileIO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class SimpleWordSearchService implements WordSearchService {
    private List<Result> resultList = new ArrayList<>();

    public List<Result> getDirectoryWordOccurrences(String directoryName, String word, boolean recursive) {
        File folder = new File(directoryName);
        File[] listOfFiles = folder.listFiles();
        FileWordOccurrences fo = new FileWordOccurrences();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    List<Result> list = fo.getFileWordOccurrences(directoryName + '/' + file.getName(), word);
                    resultList.addAll(list);
                }
                if (file.isDirectory() && recursive) {
                    resultList = getDirectoryWordOccurrences(directoryName + '/' + file.getName(), word, true);
                }
            }
        }
        return resultList;
    }


    @Override
    public List<Result> search(Query query) {
        File file = new File(query.fileName);
        FileWordOccurrences fo = new FileWordOccurrences();
        if (file.isFile()) {
            return fo.getFileWordOccurrences(query.fileName, query.word);
        } else {
            return getDirectoryWordOccurrences(query.fileName, query.word, query.recursive);
        }
    }
}
