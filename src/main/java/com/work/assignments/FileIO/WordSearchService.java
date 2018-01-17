package com.work.assignments.FileIO;

import java.io.IOException;
import java.util.List;

public interface WordSearchService {
    List<Result> search(Query query);
}
