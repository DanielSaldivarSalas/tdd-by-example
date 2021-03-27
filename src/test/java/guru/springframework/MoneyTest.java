package guru.springframework;

import org.junit.jupiter.api.Test;


import java.beans.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
public class MoneyTest {

    @Test
    void multiplicationDollarTest(){
        Money five = Money.dollar(5);

        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    void equalityMoneyTest(){
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(6));
        assertNotEquals(Money.dollar(5), Money.franc(5));
        assertEquals(Money.franc(5), Money.franc(5));
        assertNotEquals(Money.franc(5), Money.franc(6));
    }

    @Test
    void multiplicationFrancTest(){
        Money five = Money.franc(5);
        assertEquals(Money.franc(10), five.times(2));
    }

    @Test
    void currencyTest(){
        assertEquals("USD", Money.dollar(1).getCurrency());
        assertEquals("CHF", Money.franc(1).getCurrency());
    }


    @Test
    void simpleAdditionTest(){
        Money five = Money.dollar(5);
        MoneyExpression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    void plusReturnsSumTest(){
        Money five = Money.dollar(5);
        MoneyExpression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augmend);
        assertEquals(five, sum.addmend);
    }

    @Test
    void reduceSumTest(){
        MoneyExpression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank =  new Bank();
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), result);
    }

    @Test
    void testReduceMoney(){
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertEquals(Money.dollar(1), result);
    }

    @Test
    void reduceMoneyDifferentCurrency(){
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        assertEquals(Money.dollar(1), result);
    }

    @Test
    void identityRateTest(){
        assertEquals(1, new Bank().rate("USD", "USD"));
        assertEquals(1, new Bank().rate("CHF","CHF"));
    }

    @Test
    void mixedAdditionTest(){
        MoneyExpression fivebucks = Money.dollar(5);
        MoneyExpression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        MoneyExpression result = bank.reduce(fivebucks.plus(tenFrancs), "USD");
        assertEquals(Money.dollar(10), result);
    }

    @Test
    void usingSumPlusMoneyTest(){
        MoneyExpression fivebucks = Money.dollar(5);
        MoneyExpression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        MoneyExpression sum = new Sum(fivebucks, tenFrancs).plus(fivebucks);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(15), result);
    }

    @Test
    void sumTimesTest(){
        MoneyExpression fivebucks = Money.dollar(5);
        MoneyExpression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        MoneyExpression sum = new Sum(fivebucks, tenFrancs).times(2);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(20), result);
    }
}

