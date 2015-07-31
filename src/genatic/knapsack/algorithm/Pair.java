package genatic.knapsack.algorithm;

public class Pair {

    int benefit ;
    int weight ;

    public Pair() {
    }

    public Pair(int first, int second) {
        this.benefit = second;
        this.weight = first;
    }
    @Override
    public String toString()
    {
        return weight+" "+benefit ;
    }
}
