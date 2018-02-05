import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.String;

public class VendingMachineTest {

    private final float marginOfErrorForComparingFloats = 0;
    private final String PENNY = "penny";
    private final String NICKEL = "nickel";
    private final String DIME = "dime";
    private final String QUARTER = "quarter";

    @Test public void vendingMachingRejectsPennies() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin(PENNY);
        assertEquals(vm.getTotalCredit(), 0f, marginOfErrorForComparingFloats);
    }

    @Test
    public void vendingMachineAcceptsNickels() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin(NICKEL);
        assertEquals(vm.getTotalCredit(), 0.05f, marginOfErrorForComparingFloats);
    }

    @Test
    public void vendingMachineAcceptsDimes() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin(DIME);
        assertEquals(vm.getTotalCredit(), 0.1f, marginOfErrorForComparingFloats);
    }

    @Test
    public void vendingMachineAcceptsQuarters() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin(QUARTER);
        assertEquals(vm.getTotalCredit(), 0.25f, marginOfErrorForComparingFloats);
    }

    @Test
    public void vendingMachineCorrectlyAddsQuarterNickelAndDimeToTotalValue() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin(QUARTER);
        vm.addCoin(NICKEL);
        vm.addCoin(DIME);
        assertEquals(vm.getTotalCredit(), 0.40f, marginOfErrorForComparingFloats);
    }
}
