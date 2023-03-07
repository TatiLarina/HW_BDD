package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement buttonCard001 = $$("[data-test-id=action-deposit]").get(0);
    private SelenideElement buttonCard002 = $$("[data-test-id=action-deposit]").get(1);

    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance(int id) {
        val text = cards.get(id).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public DashboardPage Replenish001(String amount, String numberCard) {
        buttonCard001.click();
        amountField.setValue(amount);
        fromField.setValue(numberCard);
        transferButton.click();
        return new DashboardPage();
    }

    public DashboardPage Replenish002(String amount, String numberCard) {
        buttonCard002.click();
        amountField.setValue(amount);
        fromField.setValue(numberCard);
        transferButton.click();
        return new DashboardPage();
    }
}