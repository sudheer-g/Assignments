import com.work.assignments.FileIO.FileWordOccurances;
import com.work.assignments.FileIO.Result;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class FileWordOccurancesTest {

    @DataProvider(name = "FileWordOccurances")
    public static Object[][] fileWordOccurances() {
        List<Result> positionList = new ArrayList<>();
        String fileName = "sampleInput";
        Result r1 = new Result(1, 1, fileName);
        Result r2 = new Result(1, 6, fileName);
        Result r3 = new Result(1, 14, fileName);
        Result r4 = new Result(2, 2, fileName);
        Result r5 = new Result(2, 7, fileName);
        Result r6 = new Result(2, 15, fileName);
        Result r7 = new Result(3, 2, fileName);
        Result r8 = new Result(3, 7, fileName);
        Result r9 = new Result(3, 15, fileName);

        positionList.add(r1);
        positionList.add(r2);
        positionList.add(r3);
        positionList.add(r4);
        positionList.add(r5);
        positionList.add(r6);
        positionList.add(r7);
        positionList.add(r8);
        positionList.add(r9);
        return new Object[][]{new Object[]{fileName, "This", positionList}};
    }

    @Test(dataProvider = "FileWordOccurances")
    public void test(String fileName, String word, List<Result> assertList) {
        FileWordOccurances fo = new FileWordOccurances();
        List<Result> list = fo.getFileWordOccurances(fileName, word);
        Iterator iterator = list.iterator();
        for (Result result : assertList) {
            Assert.assertEquals(true, Objects.equals(result, iterator.next()));
        }
    }
}
