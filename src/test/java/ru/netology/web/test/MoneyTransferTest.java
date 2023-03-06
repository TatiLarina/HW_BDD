package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        String amount = "1000";
        var card002 = DataHelper.getCard002();
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int firstBalance = dashboardPage.getCardBalance(0);
        dashboardPage.Replenish001(amount, card002.getNumber());

        int actual = dashboardPage.getCardBalance(0);
        int expected = firstBalance + Integer.parseInt(amount);

        assertEquals(expected, actual);

    }

}