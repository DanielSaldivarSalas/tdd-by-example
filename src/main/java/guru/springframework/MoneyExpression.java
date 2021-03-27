package guru.springframework;

public interface MoneyExpression {
    Money reduce(Bank bank,String to);
}
