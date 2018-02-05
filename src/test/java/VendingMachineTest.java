import org.junit.Test;
import static org.junit.Assert.*;

public class VendingMachineTest {
    public final float marginOfErrorForComparingFloats = 0;
    @Test public void vendingMachingRejectsPennies() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin("penny");
        assertEquals(vm.getTotalCredit(), 0f, marginOfErrorForComparingFloats);
    }
}
