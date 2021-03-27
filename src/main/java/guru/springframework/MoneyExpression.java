package guru.springframework;

public interface MoneyExpression {
    MoneyExpression plus(MoneyExpression addend);

    Money reduce(Bank bank, String to);

    MoneyExpression times(int i);
}
