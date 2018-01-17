import com.work.assignments.FileIO.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PCTest {
    private Logger logger = LogManager.getLogger();

    @DataProvider(name = "ProducerInput")
    public static Object[][] producerInput() {
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
        return new Object[][]{new Object[]{new Query("sampleDirectory", "This is", true), resultList}};
    }

    @Test(dataProvider = "ProducerInput")
    public void testPCController(Query query, List<Result> assertList) {
        MultiThreadedWordSearchService getWords = new MultiThreadedWordSearchService();
        try {
            List<Result> resultList = getWords.search(query);
            Collections.sort(resultList);
            Collections.sort(assertList);
            logger.info(resultList.size() + " " + assertList.size());
            Assert.assertEquals(resultList.size() == assertList.size(), true);
            Iterator<Result> resultIterator = resultList.iterator();
            for (Result result : assertList) {
                Assert.assertEquals(Objects.equals(result, resultIterator.next()), true);
            }
        }
        catch (Exception e) {
            logger.error("Unexpected Exception: ",e);
        }

    }
}
