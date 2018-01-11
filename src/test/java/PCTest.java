import com.work.assignments.FileIO.Consumer;
import com.work.assignments.FileIO.PCController;
import com.work.assignments.FileIO.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class PCTest {
    private Logger logger = LogManager.getLogger();
    @Test
    public void testPCController() {
        logger.info("\n");
        List<Query> queryList = new ArrayList<>();
        queryList.add(new Query("sampleDirectory", "This is", false));
        queryList.add(new Query("sampleDirectory/innerDirectory", "This is", false));
        PCController pcController = new PCController();
        pcController.startThreads(queryList);
    }
}
