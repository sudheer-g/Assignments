import com.work.assignments.FileIO.FileWordOccurances;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.*;
import org.apache.logging.log4j.*;
public class FileWordOccurancesTest {

    private static Logger logger = LogManager.getLogger();

    @DataProvider(name = "FileWordOccurances")
    public static Object[][] fileWordOccurances() {
        Map<String, List<Integer>> assertMap = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertMap.put("Have", list);

        return new Object[][] {new Object[] {"sampleInput", assertMap}};
    }

    @Test(dataProvider = "FileWordOccurances")
    public void wordOccuranceCoordinatesInFile(String fileName, Map<String, List<Integer>> assertMap) {
        Map<String, List<Integer>> hashMap = new HashMap<>();
        FileWordOccurances fo = new FileWordOccurances();
        hashMap = fo.getFileWordOccurances(fileName);
        for (Map.Entry<String, List<Integer>> entry : assertMap.entrySet()) {
            Assert.assertEquals(hashMap.get(entry.getKey()), entry.getValue());
        }

    }

}
