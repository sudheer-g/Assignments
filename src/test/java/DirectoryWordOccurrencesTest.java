import com.work.assignments.FileIO.DirectoryWordOccurrences;
import com.work.assignments.FileIO.FileIO;
import com.work.assignments.FileIO.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DirectoryWordOccurrencesTest {

    private Logger logger = LogManager.getLogger(DirectoryWordOccurrencesTest.class);

    @DataProvider(name = "DirectoryWordOccurrences")
    public static Object[][] directoryWordOccurrences() {
        BufferedReader bufferedReader = null;
        List<Result> resultList = new ArrayList<>();
        String fileName = "";
        Result result;
        try {
            FileReader fr = FileIO.openFile("src/test/testCasesWordOccurrences");
            bufferedReader = new BufferedReader(fr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.matches(".*[a-zA-Z].*")) {
                    fileName = line;
                } else {
                    List<String> list = Arrays.asList(line.split(","));
                    result = new Result(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(1)), fileName);
                    resultList.add(result);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read File.", e);
        } finally {
            FileIO.closeFile(bufferedReader);
        }

        return new Object[][]{new Object[]{"sampleDirectory", "This is", resultList, true}};
    }

    @Test(dataProvider = "DirectoryWordOccurrences")
    public void folderWordOccurences(String folderName, String word, List<Result> assertList, boolean recursive) {
        DirectoryWordOccurrences fo = new DirectoryWordOccurrences();
        List<Result> resultList = fo.getDirectoryWordOccurrences(folderName, word, recursive);
        Collections.sort(resultList);
        Collections.sort(assertList);
        Iterator iterator = resultList.iterator();
        for (Result result : assertList) {
            Assert.assertEquals(Objects.equals(result, iterator.next()), true);
        }
    }
}
