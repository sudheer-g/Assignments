package com.work.assignments.FileIO;

import java.util.List;

public class MultiThreadedWordSearchService implements WordSearchService {

    @Override
    public List<Result> search(Query query) {
        PCController pcController = new PCController();
        return pcController.wordSearch(query);
    }
}
