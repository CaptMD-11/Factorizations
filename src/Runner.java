import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) throws Exception {

        // Factorizations obj = new Factorizations(110);
        // System.out.println(obj.getDistinctFactorizations());
        // System.out.println(obj.getNumberOfDistinctFactorizations());

        ArrayList<Integer> compositeIntegers = new ArrayList<Integer>();
        int upperBound = 1000;
        for (int i = 4; i <= upperBound; i++) {
            if (Factorizations.isPrime(i) == false)
                compositeIntegers.add(i);
        }
        // now, all composite integers from 1 to upperBound (inclusive) have been added
        // to compositeIntegers
        ArrayList<Integer> factorizationFrequencies = new ArrayList<Integer>();
        for (int i = 0; i < compositeIntegers.size(); i++) {
            Factorizations test = new Factorizations(compositeIntegers.get(i));
            factorizationFrequencies.add(test.getNumberOfDistinctFactorizations());
        }

        System.out.println(factorizationFrequencies);

    }
}