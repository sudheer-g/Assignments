import com.work.assignments.FileIO.Query;
import com.work.assignments.FileIO.Result;
import com.work.assignments.FileIO.SimpleWordSearchService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class simpleWSSWriteToFile {
    private Logger logger = LogManager.getLogger(simpleWSSWriteToFile.class);
    @Test
    public void writeResultsToFile() {
        SimpleWordSearchService service = new SimpleWordSearchService();
        Query query = new Query("src/test/100","This is", true);
        List<Result> resultList = service.search(query);

        JSONArray resultArray = new JSONArray();
        for(Result result: resultList) {
            JSONObject obj = new JSONObject();
            obj.put("lineNumber",result.getLineNumber());
            obj.put("positionNumber", result.getPositionNumber());
            obj.put("fileName",result.getFileName());
            resultArray.add(obj);
        }

        FileWriter fr = null;
        try {
            fr = new FileWriter("src/test/resources/wordSearchResults.json");
            fr.write(resultArray.toJSONString());
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
