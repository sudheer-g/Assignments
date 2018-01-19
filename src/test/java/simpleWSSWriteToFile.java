import com.work.assignments.FileIO.Query;
import com.work.assignments.FileIO.SimpleWordSearchService;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class simpleWSSWriteToFile {
    private Logger logger = LogManager.getLogger(simpleWSSWriteToFile.class);
    @Test
    public void writeResultsToFile() {
        SimpleWordSearchService service = new SimpleWordSearchService();
        Query query = new Query("100","This is", true);
        JSONObject obj = new JSONObject();
        obj.put(query.word, service.search(query).toString());
        FileWriter fr = null;
        try {
            fr = new FileWriter("src/test/wordSearchResults.json");
            fr.write(obj.toJSONString());
            fr.flush();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(fr!=null) {
                try {
                    fr.close();
                }
                catch (IOException e) {
                    logger.error("Failed to close", e);
                }
            }
        }
    }
}
