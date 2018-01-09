import com.work.assignments.FileIO.PCController;
import org.testng.annotations.Test;

public class PCTest {

    @Test
    public void testPCController() {
        PCController pcController = new PCController();
        pcController.startThreads();
    }

}
