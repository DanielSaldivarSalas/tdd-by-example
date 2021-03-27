package guru.springframework;

import java.beans.Expression;

public class Sum implements MoneyExpression{
    MoneyExpression augmend;
    MoneyExpression addmend;

    public Sum(MoneyExpression augmend, MoneyExpression addmend) {
        this.augmend = augmend;
        this.addmend = addmend;
    }

    @Override
    public Money reduce(Bank bank, String to) {
        int amount = augmend.reduce(bank, to).amount + addmend.reduce(bank,to).amount;
        return new Money(amount, to);
    }

    @Override
    public MoneyExpression plus(MoneyExpression addend) {
        //TODO Determine if this is needed
        return null;
    }
}
