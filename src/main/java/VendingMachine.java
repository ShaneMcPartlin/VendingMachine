package VendingMachineKata;

import java.lang.String;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

public class VendingMachine {

    private float totalCredit = 0f;
    private float coinReturn = 0f;

    private boolean exactChangeMode = false;
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
        handleBuyingSelection();
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
        if (isNotDefaultMessage(messageToReturn)) {

            return messageToReturn;
        }
        return machineMessage;
    }

    private boolean isNotDefaultMessage(String message) {
        if (message.charAt(0) == 'C' ||
            message == "INSERT COIN" ||
            message == "EXACT CHANGE ONLY") {
                return false;
            }
            return true;
    }

    private void setMachineMessageToDefaults() {
        if (totalCredit == NO_CREDIT) {
            if (exactChangeMode) {
                machineMessage = "EXACT CHANGE ONLY";
                return;
            }
            machineMessage = "INSERT COIN";
        } else if (totalCredit > NO_CREDIT) {
            machineMessage = "CREDIT:" + printFloatLikeMoney(totalCredit);
        }
    }

    private String printFloatLikeMoney(float floatToParse) {
        return " $" + String.format("%.2f", floatToParse);
    }

    public void selectItem(InventoryItem item) {
        if (item.soldOut) {
            machineMessage = "SOLD OUT";
            return;
        }
        selectedItem = item;
        if (totalCredit >= item.cost) {
            buySelection();
        }
        else if (totalCredit < item.cost) {
            machineMessage = "PRICE:" + printFloatLikeMoney(item.cost);
        }
    }

    public String getSelectedItemName() {
        return selectedItem.name;
    }

    void handleBuyingSelection() {
        if (totalCredit >= selectedItem.cost && selectedItem != NO_ITEM) {
            buySelection();
        }
    }

    private void buySelection() {
        makeChange();
        selectedItem = NO_ITEM;
        machineMessage = "THANK YOU";
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

    public void makeItemSoldOut(InventoryItem item) {
        item.setSoldOut(true);
    }

    public void setExactChangeMode(boolean setting) {
        exactChangeMode = setting;
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