package com.work.assignments.FileIO;

import java.io.IOException;
import java.util.List;

public interface WordSearchService {
    public List<Result> search(Query query);
}
