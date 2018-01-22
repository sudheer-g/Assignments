import com.work.assignments.FileIO.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PCTest {
    private Logger logger = LogManager.getLogger();

    @DataProvider(name = "ProducerInput")
    public static Object[][] producerInput() {
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        List<Result> assertList = new ArrayList<>();
        try {
            fr = FileIO.openFile("src/test/resources/wordSearchResults.json");
            Object obj = parser.parse(fr);
            JSONArray jsonArray = (JSONArray) obj;
            for (Object object : jsonArray) {
                JSONObject o = (JSONObject) object;
                Result result = new Result(((Long)o.get("lineNumber")).intValue(), ((Long)o.get("positionNumber")).intValue(), (String)o.get("fileName"));
                assertList.add(result);
            }
            Collections.sort(assertList);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read File.", e);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to Parse", e);
        }
        finally {
            FileIO.closeFile(fr);
        }
        return new Object[][]{new Object[]{new Query("100", "This is", true), assertList}};
    }

    @Test(dataProvider = "ProducerInput")
    public void testPCController(Query query, List<Result> assertList) {
        MultiThreadedWordSearchService getWords = new MultiThreadedWordSearchService();
        try {
            List<Result> resultList = getWords.search(query);
            Iterator iterator = assertList.iterator();
            for(Result result : resultList) {
                Assert.assertEquals(true, Objects.equals(result, iterator.next()));
            }
        }
        catch (Exception e) {
            logger.error("Unexpected Exception: ",e);
        }

    }
}
