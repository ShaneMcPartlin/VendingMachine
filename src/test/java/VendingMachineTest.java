import org.junit.Test;
import static org.junit.Assert.*;

import java.beans.Transient;
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

    @Test
    public void vendingMachineCorrectlyAddsQuarterNickelDimeAndPennyToTotalValue() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin(QUARTER);
        vm.addCoin(NICKEL);
        vm.addCoin(DIME);
        vm.addCoin(PENNY);
        assertEquals(vm.getTotalCredit(), 0.40f, marginOfErrorForComparingFloats);
    }
    
    @Test
    public void vendingMachineHasMessageINSERTCOIN() {
        VendingMachine vm = new VendingMachine();
        assertEquals(vm.getMachineMessage(), "INSERT COIN");
    }

    @Test
    public void vendingMachineHasMessageINSERTCOINAfterAddingCoins() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin(QUARTER);
        assertEquals(vm.getMachineMessage(), "INSERT COIN");
    }

    @Test
    public void SelectColaAndCheckSelection() {
        VendingMachine vm = new VendingMachine();
        vm.selectItem(vm.COLA);
        assertEquals(vm.getSelectedItemName(), "cola");
    }

    @Test
    public void SelectColaAndCheckMessageWithNoCoins() {
        VendingMachine vm = new VendingMachine();
        vm.selectItem(vm.COLA);
        assertEquals(vm.getMachineMessage(), "PRICE: $1.00");
    }

    @Test
    public void SelectChipsAndCheckMessageWithNoCoins() {
        VendingMachine vm = new VendingMachine();
        vm.selectItem(vm.CHIPS);
        assertEquals(vm.getMachineMessage(), "PRICE: $0.50");
    }

    @Test
    public void SelectCandyAndCheckMessageWithNoCoins() {
        VendingMachine vm = new VendingMachine();
        vm.selectItem(vm.CANDY);
        assertEquals(vm.getMachineMessage(), "PRICE: $0.65");
    }

    @Test
    public void SelectColaAndCheckMessageTwice() {
        VendingMachine vm = new VendingMachine();
        vm.selectItem(vm.COLA);
        vm.getMachineMessage();
        assertEquals(vm.getMachineMessage(), "INSERT COIN");
    }

    @Test
    public void SelectColaAndCheckMessageThenInsertAQuarterAndCheckMessageAgain() {
        VendingMachine vm = new VendingMachine();
        vm.selectItem(vm.COLA);
        vm.getMachineMessage();
        vm.addCoin(QUARTER);
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.25");
    }

    @Test
    public void SelectColaAndCheckMessageThenInsertThreeQuartersAndCheckMessageAgain() {
        VendingMachine vm = new VendingMachine();
        vm.selectItem(vm.COLA);
        vm.getMachineMessage();
        vm.addCoin(QUARTER);
        vm.addCoin(QUARTER);
        vm.addCoin(QUARTER);
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.75");
    }

    @Test
    public void InsertFourQuartersAndSelectColaAndCheckMessage() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin(QUARTER);
        vm.addCoin(QUARTER);
        vm.addCoin(QUARTER);
        vm.addCoin(QUARTER);
        vm.selectItem(vm.COLA);
        assertEquals(vm.getMachineMessage(), "THANK YOU");
    }

    @Test
    public void InsertFourQuartersAndSelectColaAndCheckMessageTwice() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin(QUARTER);
        vm.addCoin(QUARTER);
        vm.addCoin(QUARTER);
        vm.addCoin(QUARTER);
        vm.selectItem(vm.COLA);
        vm.getMachineMessage();
        assertEquals(vm.getMachineMessage(), "INSERT COIN");
    }
    
    @Test
    public void BuyAColaWithFiveQuartersAndCheckCoinReturn() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin(QUARTER);
        vm.addCoin(QUARTER);
        vm.addCoin(QUARTER);
        vm.addCoin(QUARTER);
        vm.addCoin(QUARTER);
        vm.selectItem(vm.COLA);
        vm.buySelection();
        assertEquals(vm.checkCoinReturn(), "COIN RETURN: $0.25");
    }

    @Test
    public void InsertFiveQuartersAndUseCoinReturn() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin(QUARTER);
        vm.addCoin(QUARTER);
        vm.addCoin(QUARTER);
        vm.addCoin(QUARTER);
        vm.addCoin(QUARTER);
        vm.returnCoins();
        assertEquals(vm.checkCoinReturn(), "COIN RETURN: $1.25");
        assertEquals(vm.getMachineMessage(), "INSERT COIN");
    }

    @Test
    public void SelectASoldOutItemAndCheckMessage() {
        VendingMachine vm = new VendingMachine();
        vm.makeItemSoldOut(vm.COLA);
        vm.selectItem(vm.COLA);
        assertEquals(vm.getMachineMessage(), "SOLD OUT");
    }
}
