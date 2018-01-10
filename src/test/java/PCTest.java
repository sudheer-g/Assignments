import com.work.assignments.FileIO.PCController;
import com.work.assignments.FileIO.Query;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class PCTest {

    @Test
    public void testPCController() {
        List<Query> queryList = new ArrayList<>();
        queryList.add(new Query("sampleDirectory", "This is", false));
        queryList.add(new Query("sampleDirectory/innerDirectory", "This is", false));
        PCController pcController = new PCController();
        pcController.startThreads(queryList);
    }
}
