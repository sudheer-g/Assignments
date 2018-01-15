import com.work.assignments.FileIO.FileIO;
import com.work.assignments.FileIO.PCController;
import com.work.assignments.FileIO.Query;
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
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PCTest {
    private Logger logger = LogManager.getLogger();

    @DataProvider(name = "ProducerInput")
    public static Object[] producerInput() {
        BufferedReader bufferedReader = null;
        List<Result> resultList = new ArrayList<>();
        String fileName = "";
        Result result;
        try {
            FileReader fr = FileIO.openFile("src/test/testCasesWCThreads");
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
        List<Query> queryList = new ArrayList<>();
        queryList.add(new Query("sampleDirectory", "This is", true));
        queryList.add(new Query("sampleDirectory2", "This is", false));
        queryList.add(new Query("sampleDirectory3", "This is", false));
        queryList.add(new Query("sampleDirectory4", "This is", false));
        queryList.add(new Query("sampleDirectory5", "This is", false));
        queryList.add(new Query("sampleInput", "This is", false));
        return new Object[][]{new Object[]{queryList, resultList}};
    }

    @Test(dataProvider = "ProducerInput")
    public void testPCController(List<Query> queryList, List<Result> assertList) {
        PCController pcController = new PCController();
        List<Result> resultList = pcController.wordSearch(queryList);
        Collections.sort(resultList);
        Collections.sort(assertList);
        Assert.assertEquals(resultList.size() == assertList.size(), true);
        logger.info(resultList.size() + " " + assertList.size());
        Iterator<Result> resultIterator = resultList.iterator();
        for (Result result : assertList) {
            Assert.assertEquals(Objects.equals(result, resultIterator.next()), true);
        }
    }
}
