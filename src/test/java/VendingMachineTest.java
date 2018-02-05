import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.String;

public class VendingMachineTest {

    private final float marginOfErrorForComparingFloats = 0;
    private final String PENNY = "penny";
    private final String NICKEL = "nickel";

    @Test public void vendingMachingRejectsPennies() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin("penny");
        assertEquals(vm.getTotalCredit(), 0f, marginOfErrorForComparingFloats);
    }

    @Test
    public void vendingMachineAcceptsNickels() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin("nickel");
        assertEquals(vm.getTotalCredit(), 0.05f, marginOfErrorForComparingFloats);
    }
}
