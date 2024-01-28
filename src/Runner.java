import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) throws Exception {

        Factorizations obj = new Factorizations(100);
        System.out.println(obj.getDistinctFactorizations());
        System.out.println(obj.getNumberOfDistinctFactorizations());

    }
}