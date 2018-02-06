package VendingMachineKata;

import java.lang.String;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

public class VendingMachine {

    private float totalCredit = 0f;
    private float coinReturn = 0f;

    private String machineMessage = "INSERT COIN";

    public final float NICKEL_VALUE = 0.05f;
    public final float DIME_VALUE = 0.1f;
    public final float QUARTER_VALUE = 0.25f;
    public final float UNACCEPTED_COIN_VALUE = 0f;

    public InventoryItem COLA = new InventoryItem("cola", 1f);
    public InventoryItem CHIPS = new InventoryItem("chips", 0.5f);
    public InventoryItem CANDY = new InventoryItem("candy", 0.65f);
    public InventoryItem NO_ITEM = new InventoryItem("", 0f);
    private InventoryItem selectedItem = NO_ITEM;
    private final float NO_CREDIT = 0f;

    public void addCoin(String coin) {
        totalCredit += getCoinValue(coin);
        return;
    }

    private float getCoinValue(String coin) {
        if (coin == "nickel") {
            return NICKEL_VALUE;
        }
        else if (coin == "dime") {
            return DIME_VALUE;
        }
        else if (coin == "quarter") {
            return QUARTER_VALUE;
        }
        else {
            return UNACCEPTED_COIN_VALUE;
        }
    }

    public String getMachineMessage() {
        return setMachineMessageToDisplayBetweenDefaultAndSpecialMessages();
    }

    private String setMachineMessageToDisplayBetweenDefaultAndSpecialMessages() {
        String messageToReturn = machineMessage;
        setMachineMessageToDefaults();
        if (messageToReturn != "CREDIT:" + printFloatLikeMoney(totalCredit) && messageToReturn != "INSERT COIN") {
            return messageToReturn;
        }
        return machineMessage;
    }

    public void selectItem(InventoryItem item) {
        selectedItem = item;
        if (item.soldOut) {
            machineMessage = "SOLD OUT";
            return;
        }
        if (totalCredit >= item.cost) {
            machineMessage = "THANK YOU";
            buySelection();
        }
        else if (totalCredit < item.cost) {
            machineMessage = "PRICE:" + printFloatLikeMoney(item.cost);
        }
    }

    private void setMachineMessageToDefaults() {
        if (totalCredit == NO_CREDIT) {
            machineMessage = "INSERT COIN";
        }
        else if (totalCredit > NO_CREDIT) {
            machineMessage = "CREDIT:" + printFloatLikeMoney(totalCredit);
        }
    }

    private String printFloatLikeMoney(float floatToParse) {
        return " $" + String.format("%.2f", floatToParse);
    }

    private void buySelection() {
        makeChange();
        selectedItem = NO_ITEM;
    }

    public void returnCoins() {
        coinReturn = totalCredit;
        totalCredit = NO_CREDIT;
        selectedItem = NO_ITEM;
        setMachineMessageToDefaults();
    }

    private void makeChange() {
        coinReturn = totalCredit - selectedItem.cost;
        totalCredit = NO_CREDIT;
    }

    public String checkCoinReturn() {
        return "COIN RETURN:" + printFloatLikeMoney(coinReturn);
    }

    public String getSelectedItemName() {
        return selectedItem.name;
    }

    public void makeItemSoldOut(InventoryItem item) {
        item.setSoldOut(true);
    }

    public class InventoryItem {
        private String name;
        private float cost;
        private boolean soldOut = false; 
        InventoryItem(String name, float cost) {
            this.name = name;
            this.cost = cost;
        }

        public void setSoldOut(boolean setting) {
            soldOut = setting;
        }
    }
}