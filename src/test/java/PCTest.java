import com.work.assignments.FileIO.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class PCTest {
    private Logger logger = LogManager.getLogger();

    @DataProvider(name = "ProducerInput")
    public static Object[][] producerInput() {
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        JSONObject jsonObject;
        try {
            fr = FileIO.openFile("src/test/wordSearchResults.json");
            Object obj = parser.parse(fr);
            jsonObject = (JSONObject) obj;

        } catch (IOException e) {
            throw new RuntimeException("Failed to read File.", e);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to Parse", e);
        }
        finally {
            FileIO.closeFile(fr);
        }
        return new Object[][]{new Object[]{new Query("100", "This is", true), jsonObject}};
    }

    @Test(dataProvider = "ProducerInput")
    public void testPCController(Query query, JSONObject jsonObject) {
        MultiThreadedWordSearchService getWords = new MultiThreadedWordSearchService();
        try {
            JSONObject obj = new JSONObject();
            obj.put(query.word, getWords.search(query).toString());
            Assert.assertEquals(true, Objects.equals(obj, jsonObject));
        }
        catch (Exception e) {
            logger.error("Unexpected Exception: ",e);
        }

    }
}
