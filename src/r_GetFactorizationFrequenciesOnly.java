import java.util.ArrayList;

public class r_GetFactorizationFrequenciesOnly {

    public static void main(String[] args) {

        // input upper bound in below line
        int upperBound = 1000;
        ArrayList<Integer> factorizationFrequenciesOnly = new ArrayList<Integer>();
        factorizationFrequenciesOnly = getFactorizationFrequenciesOnly(upperBound);
        System.out.println(factorizationFrequenciesOnly);

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static ArrayList<Integer> getFactorizationFrequenciesOnly(int upperBound) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        ArrayList<Integer> compositeIntegers = new ArrayList<Integer>();
        compositeIntegers = getCompositeIntegers(upperBound);
        for (int i = 0; i < compositeIntegers.size(); i++) {
            Factorizations test = new Factorizations(compositeIntegers.get(i));
            res.add(test.getNumberOfDistinctFactorizations());
        }
        return res;
    }

    public static ArrayList<Integer> getCompositeIntegers(int upperBound) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 4; i <= upperBound; i++) {
            if (Factorizations.isPrime(i) == false)
                res.add(i);
        }
        return res;
    }

}