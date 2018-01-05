import com.work.assignments.FileIO.FileWordOccurances;
import com.work.assignments.FileIO.Tuple;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.*;
public class FileWordOccurancesTest {

    @DataProvider(name = "FileWordOccurances")
    public static Object[][] fileWordOccurances() {
        List<Tuple<Integer, Integer>> positionList = new ArrayList<>();
        Tuple<Integer, Integer> t1 = new Tuple<>(1,1);
        Tuple<Integer, Integer> t2 = new Tuple<>(1,6);
        Tuple<Integer, Integer> t3 = new Tuple<>(1,14);
        Tuple<Integer, Integer> t4 = new Tuple<>(2,2);
        Tuple<Integer, Integer> t5 = new Tuple<>(2,7);
        Tuple<Integer, Integer> t6 = new Tuple<>(2,15);
        Tuple<Integer, Integer> t7 = new Tuple<>(3,2);
        Tuple<Integer, Integer> t8 = new Tuple<>(3,7);
        Tuple<Integer, Integer> t9 = new Tuple<>(3,15);
        positionList.add(t1);
        positionList.add(t2);
        positionList.add(t3);
        positionList.add(t4);
        positionList.add(t5);
        positionList.add(t6);
        positionList.add(t7);
        positionList.add(t8);
        positionList.add(t9);
        return new Object[][] {new Object[] {"sampleInput","This", positionList}};
    }

    @Test(dataProvider = "FileWordOccurances")
    public void test(String fileName, String word, List<Tuple<Integer, Integer>> assertList) {
        FileWordOccurances fo = new FileWordOccurances();
        List<Tuple<Integer, Integer>> list  = fo.getFileWordOccurances(fileName, word);
        Iterator iterator = list.iterator();
        for(Tuple<Integer,Integer> tuple : assertList) {
            if(!iterator.hasNext()) {
                break;
            }
            Assert.assertEquals(true, Objects.equals(tuple, iterator.next()));
        }
    }



}
