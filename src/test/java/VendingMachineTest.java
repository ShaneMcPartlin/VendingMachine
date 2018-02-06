package VendingMachineKata;

import org.junit.Test;

import VendingMachineKata.VendingMachine;

import static org.junit.Assert.*;

import java.beans.Transient;
import java.lang.String;

import javax.accessibility.AccessibleAttributeSequence;

public class VendingMachineTest {

    @Test public void VendingMachingRejectsPennies() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin("penny");
        assertEquals(vm.getMachineMessage(), "INSERT COIN");
    }

    @Test
    public void VendingMachineAcceptsNickels() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin("nickel");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.05");
    }

    @Test
    public void VendingMachineAcceptsDimes() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin("dime");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.10");
    }

    @Test
    public void VendingMachineAcceptsQuarters() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin("quarter");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.25");
    }

    @Test
    public void VendingMachineCorrectlyAddsQuarterNickelAndDimeToTotalValue() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin("quarter");
        vm.addCoin("nickel");
        vm.addCoin("dime");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.40");
    }

    @Test
    public void VendingMachineCorrectlyAddsQuarterNickelDimeAndPennyToTotalValue() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin("quarter");
        vm.addCoin("nickel");
        vm.addCoin("dime");
        vm.addCoin("penny");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.40");
    }
    
    @Test
    public void VendingMachineHasMessageINSERTCOIN() {
        VendingMachine vm = new VendingMachine();
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
        vm.addCoin("quarter");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.25");
    }

    @Test
    public void SelectColaAndCheckMessageThenInsertThreeQuartersAndCheckMessageAgain() {
        VendingMachine vm = new VendingMachine();
        vm.selectItem(vm.COLA);
        vm.getMachineMessage();
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.75");
    }

    @Test
    public void InsertFourQuartersAndSelectColaAndCheckMessage() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.selectItem(vm.COLA);
        assertEquals(vm.getMachineMessage(), "THANK YOU");
    }

    @Test
    public void InsertFourQuartersAndSelectColaAndCheckMessageTwice() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.selectItem(vm.COLA);
        vm.getMachineMessage();
        assertEquals(vm.getMachineMessage(), "INSERT COIN");
    }
    
    @Test
    public void BuyAColaWithFiveQuartersAndCheckCoinReturn() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.selectItem(vm.COLA);
        assertEquals(vm.checkCoinReturn(), "COIN RETURN: $0.25");
    }

    @Test
    public void InsertFiveQuartersAndUseCoinReturn() {
        VendingMachine vm = new VendingMachine();
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.addCoin("quarter");
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

    @Test
    public void EnableExactChangeOnlyMode() {
        VendingMachine vm = new VendingMachine();
        vm.setExactChangeMode(true);
        assertEquals(vm.getMachineMessage(), "EXACT CHANGE ONLY");
    }

    @Test
    public void TestExactChangeOnlyModeWorksWithCoinsAsWell() {
        VendingMachine vm = new VendingMachine();
        vm.setExactChangeMode(true);
        vm.addCoin("quarter");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.25");
    }

    @Test
    public void TestExactChangeOnlyModeWorksWithAPurchaseOfColaAndChipsAndCandyAndEverythingInBetween() {
        VendingMachine vm = new VendingMachine();
        vm.setExactChangeMode(true);
        vm.selectItem(vm.COLA);
        assertEquals(vm.getMachineMessage(), "PRICE: $1.00");
        vm.addCoin("quarter");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.25");
        vm.addCoin("quarter");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.50");
        vm.addCoin("quarter");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.75");
        vm.addCoin("quarter");
        assertEquals(vm.getMachineMessage(), "THANK YOU");
        assertEquals(vm.checkCoinReturn(), "COIN RETURN: $0.00");
        assertEquals(vm.getMachineMessage(), "EXACT CHANGE ONLY");        
        vm.addCoin("quarter");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.25");
        vm.addCoin("quarter");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.50");
        vm.selectItem(vm.CHIPS);
        assertEquals(vm.getMachineMessage(), "THANK YOU");
        vm.addCoin("nickel");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.05");
        vm.addCoin("dime");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.15");
        vm.addCoin("quarter");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.40");
        vm.addCoin("quarter");
        assertEquals(vm.getMachineMessage(), "CREDIT: $0.65");
        vm.selectItem(vm.CANDY);
        assertEquals(vm.getMachineMessage(), "THANK YOU");
        assertEquals(vm.checkCoinReturn(), "COIN RETURN: $0.00");
        assertEquals(vm.getMachineMessage(), "EXACT CHANGE ONLY");
    }

    @Test
    public void AllItemsSoldOut() {
        VendingMachine vm = new VendingMachine();
        vm.makeItemSoldOut(vm.COLA);
        vm.makeItemSoldOut(vm.CHIPS);
        vm.makeItemSoldOut(vm.CANDY);
        vm.selectItem(vm.COLA);
        assertEquals(vm.getMachineMessage(), "SOLD OUT");
        assertEquals(vm.getMachineMessage(), "INSERT COIN");
        vm.selectItem(vm.CHIPS);
        assertEquals(vm.getMachineMessage(), "SOLD OUT");
        assertEquals(vm.getMachineMessage(), "INSERT COIN");
        vm.selectItem(vm.CANDY);
        assertEquals(vm.getMachineMessage(), "SOLD OUT");
        assertEquals(vm.getMachineMessage(), "INSERT COIN");
    }

    @Test
    public void AllItemsSoldOutAndUserTriesBuyingThem() {
        VendingMachine vm = new VendingMachine();
        vm.makeItemSoldOut(vm.COLA);
        vm.makeItemSoldOut(vm.CHIPS);
        vm.makeItemSoldOut(vm.CANDY);
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.addCoin("quarter");
        vm.selectItem(vm.COLA);
        assertEquals(vm.getMachineMessage(), "SOLD OUT");
        assertEquals(vm.getMachineMessage(), "CREDIT: $1.00");
        vm.selectItem(vm.CHIPS);
        assertEquals(vm.getMachineMessage(), "SOLD OUT");
        assertEquals(vm.getMachineMessage(), "CREDIT: $1.00");
        vm.selectItem(vm.CANDY);
        assertEquals(vm.getMachineMessage(), "SOLD OUT");
        assertEquals(vm.getMachineMessage(), "CREDIT: $1.00");
        vm.returnCoins();
        assertEquals(vm.checkCoinReturn(), "COIN RETURN: $1.00");
    }
}
