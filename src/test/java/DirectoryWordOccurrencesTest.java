import com.work.assignments.FileIO.DirectoryWordOccurrences;
import com.work.assignments.FileIO.Result;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


import java.util.*;

public class DirectoryWordOccurrencesTest {

    private Logger logger = LogManager.getLogger(DirectoryWordOccurrencesTest.class);

    @DataProvider(name = "DirectoryWordOccurrences")
    public static Object[][] directoryWordOccurrences() {
        List<Result> positionList = new ArrayList<>();
        String fileName2 = "sampleDirectory/sampleInput2";
        Result r10 = new Result(1, 0, fileName2);
        Result r11 = new Result(1, 23, fileName2);
        //Result r12 = new Result(1, 65, fileName2);
        Result r13 = new Result(2, 2, fileName2);
        Result r14 = new Result(2, 26, fileName2);
        //Result r15 = new Result(2, 68, fileName2);
        Result r16 = new Result(3, 2, fileName2);
        Result r17 = new Result(3, 25, fileName2);
        //Result r18 = new Result(3, 67, fileName2);
        positionList.add(r10);
        positionList.add(r11);
        //positionList.add(r12);
        positionList.add(r13);
        positionList.add(r14);
        //positionList.add(r15);
        positionList.add(r16);
        positionList.add(r17);
        //positionList.add(r18);
        String fileName = "sampleDirectory/sampleInput";
        Result r1 = new Result(1, 0, fileName);
        Result r2 = new Result(1, 23, fileName);
        //Result r3 = new Result(1, 65, fileName);
        Result r4 = new Result(2, 2, fileName);
        Result r5 = new Result(2, 26, fileName);
        //Result r6 = new Result(2, 68, fileName);
        Result r7 = new Result(3, 2, fileName);
        Result r8 = new Result(3, 25, fileName);
        //Result r9 = new Result(3, 67, fileName);
        positionList.add(r1);
        positionList.add(r2);
        //positionList.add(r3);
        positionList.add(r4);
        positionList.add(r5);
        //positionList.add(r6);
        positionList.add(r7);
        positionList.add(r8);
        //positionList.add(r9);

        return new Object[][]{new Object[]{"sampleDirectory", "This is", positionList, true}};
    }

    @Test(dataProvider = "DirectoryWordOccurrences")
    public void folderWordOccurences(String folderName, String word, List<Result> assertList, boolean recursive) {
        DirectoryWordOccurrences fo = new DirectoryWordOccurrences();
        List<Result> resultList = fo.getDirectoryWordOccurrences(folderName, word, recursive);
        Iterator iterator = resultList.iterator();
        for (Result result : assertList) {
            Assert.assertEquals(Objects.equals(result, iterator.next()), true);
        }
    }
}
