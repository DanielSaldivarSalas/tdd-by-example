package guru.springframework;

import java.util.HashMap;

public class Bank {
    private HashMap<Pair, Integer> rateMap = new HashMap<>();


    Money reduce(MoneyExpression source, String toCurrency){

        return source.reduce(this, toCurrency);
    }

    public int rate(String from, String to) {
        if (from.equals(to)) return 1;
        else {
            Pair pair = new Pair(from, to);
            int rate = this.rateMap.get(pair);
            return rate;
        }
    }

    public void addRate(String from, String to, int rate) {

        Pair pair = new Pair(from, to);
        this.rateMap.put(pair, rate);
    }
}
