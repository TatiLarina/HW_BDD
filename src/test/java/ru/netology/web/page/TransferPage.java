package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement errorMessage = $("[data-test-id=error-notification]");

    public DashboardPage transfer(String amount, String numberCard) {
        amountField.setValue(amount);
        fromField.setValue(numberCard);
        transferButton.click();
        return new DashboardPage();
    }

    public TransferPage transferError() {
        errorMessage.shouldBe(Condition.visible);
        return new TransferPage();
    }
}
