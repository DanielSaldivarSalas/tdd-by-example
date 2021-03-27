package guru.springframework;

public class Money implements MoneyExpression {
    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }


    public String getCurrency() {
        return this.currency;
    }

    public static Money dollar(int amount){
        return new Money(amount,"USD");
    }

    public static Money franc(int amount){
        return new Money(amount, "CHF");
    }


    public boolean equals(Object object){
        Money money = (Money) object;
        return amount == money.amount
                && this.currency.equals(money.currency);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }


    public MoneyExpression times(int multiplier){
        return new Money(this.amount * multiplier, this.currency);
    }

    @Override
    public MoneyExpression plus(MoneyExpression addend){
        return new Sum(this, addend);
    }

    @Override
    public Money reduce(Bank bank,String to){
        int rate = bank.rate(this.currency, to);
        return new Money( amount/ rate, to);
    }
}
