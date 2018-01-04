import com.work.assignments.FileIO.FileWordCount;
import com.work.assignments.FileIO.FileWordCountCached;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import org.apache.logging.log4j.*;

@Test
public class FileWordCountTest {

    private static Logger logger = LogManager.getLogger();
    @DataProvider(name = "sampleFileData")
    public static Object[][] wordCountData() {
        Map<String, Integer> assertMap = new HashMap<>();
        assertMap.put("a", 13);
        assertMap.put("This", 9);
        assertMap.put("sample", 3);
        assertMap.put("ipsum", 8);
        return new Object[][] {new Object[] {"sampleInput", assertMap}};
    }


    @Test(dataProvider = "sampleFileData")
    public void testReadFile(String fileName, Map<String, Integer> assertMap) {
        logger.debug("Test Statement");
        FileWordCount fc = new FileWordCount();
        Map<String, Integer> hashMap;
        hashMap = fc.countWordsInAFile(fileName);
        for (Map.Entry<String, Integer> entry : assertMap.entrySet()) {
            Assert.assertEquals(hashMap.get(entry.getKey()), entry.getValue());
        }
    }


    @Test(dataProvider = "sampleFileData")
    public void testReadFileCached(String fileName, Map<String, Integer> assertMap) {
        FileWordCountCached fc = new FileWordCountCached();
        Map<String, Integer> hashMap;
        int i = 0;
        long startTime, endTime, totalTime;
        long[] timeArr = new long[20];
        while(i < 2){
            startTime = System.currentTimeMillis();
            hashMap = fc.createOrReadFileWordCache(fileName);
            endTime = System.currentTimeMillis();
            totalTime = endTime - startTime;
            timeArr[i] = totalTime;
            logger.info("Total time for call to return: " + totalTime);
            for (Map.Entry<String, Integer> entry : assertMap.entrySet()) {
                Assert.assertEquals(hashMap.get(entry.getKey()), entry.getValue());
            }
            i++;
        }
        Assert.assertEquals(true, timeArr[1] < timeArr[0] );
    }
}
