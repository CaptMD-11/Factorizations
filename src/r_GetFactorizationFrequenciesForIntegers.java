import java.util.ArrayList;

public class r_GetFactorizationFrequenciesForIntegers {

    public static void main(String[] args) {

        // input upper bound in below line
        int upperBound = 500;
        ArrayList<ArrayList<Integer>> factorizationFrequenciesPerNumber = new ArrayList<ArrayList<Integer>>();
        factorizationFrequenciesPerNumber = getFactorizationFrequenciesPerNumber(upperBound);

        // prints out ArrayLists, where each ArrayList = [c,f]
        // c is the composite integer of interest
        // f is the number of distinct factorizations of c
        for (int i = 0; i < factorizationFrequenciesPerNumber.size(); i++) {
            System.out.println(factorizationFrequenciesPerNumber.get(i));
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static ArrayList<ArrayList<Integer>> getFactorizationFrequenciesPerNumber(int upperBound) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> compositeIntegers = new ArrayList<Integer>();
        compositeIntegers = getCompositeIntegers(upperBound);
        for (int i = 0; i < compositeIntegers.size(); i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Factorizations test = new Factorizations(compositeIntegers.get(i));
            list.add(compositeIntegers.get(i));
            list.add(test.getNumberOfDistinctFactorizations());
            res.add(list);
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