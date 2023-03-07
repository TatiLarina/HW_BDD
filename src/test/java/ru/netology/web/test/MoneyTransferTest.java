package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @Test
        //пополнение карты 001
    void shouldTransferMoneyBetweenWith002on001() {
        String amount = "1000";
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int firstBalance = dashboardPage.getCardBalance(0);
        int secondBalance = dashboardPage.getCardBalance(1);
        dashboardPage.Replenish001(amount, DataHelper.getCard002().getNumber());

        int actual001 = dashboardPage.getCardBalance(0);
        int expected001 = firstBalance + Integer.parseInt(amount);

        assertEquals(expected001, actual001);

        int actual002 = dashboardPage.getCardBalance(1);
        int expected002 = secondBalance - Integer.parseInt(amount);

        assertEquals(expected002, actual002);

    }

    @Test
        //Пополнение карты 002
    void shouldTransferMoneyBetweenWith001on002() {
        String amount = "1000";
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int firstBalance = dashboardPage.getCardBalance(0);
        int secondBalance = dashboardPage.getCardBalance(1);
        dashboardPage.Replenish002(amount, DataHelper.getCard001().getNumber());

        int actual001 = dashboardPage.getCardBalance(0);
        int expected001 = firstBalance - Integer.parseInt(amount);

        assertEquals(expected001, actual001);

        int actual002 = dashboardPage.getCardBalance(1);
        int expected002 = secondBalance + Integer.parseInt(amount);

        assertEquals(expected002, actual002);

    }

    @Test
        //Попытка перевести больше чем текущий баланс второй карты
    void shouldTransferMoneyMoreThenBalance002() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int firstBalance = dashboardPage.getCardBalance(0);
        int secondBalance = dashboardPage.getCardBalance(1);
        String amount = Integer.toString(secondBalance + 1000);
        dashboardPage.Replenish001(amount, DataHelper.getCard002().getNumber());

        int actual001 = dashboardPage.getCardBalance(0);
        int expected001 = firstBalance + secondBalance;

        assertEquals(expected001, actual001);

        int actual002 = dashboardPage.getCardBalance(1);
        int expected002 = 0;

        assertEquals(expected002, actual002);
    }


}