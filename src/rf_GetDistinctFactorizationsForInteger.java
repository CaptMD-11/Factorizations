import java.util.ArrayList;

public class rf_GetDistinctFactorizationsForInteger {

    public static void main(String[] args) {

        // input composite integer in the below line
        int inputN = 50 * (int) (Math.pow(3, 5));
        Factorizations obj = new Factorizations(inputN);
        ArrayList<ArrayList<Integer>> distinctFactorizations = new ArrayList<ArrayList<Integer>>();
        distinctFactorizations = obj.getDistinctFactorizations();

        // IMPORTANT prints the distinct factorizations of inputN
        for (int i = 0; i < distinctFactorizations.size(); i++) {
            System.out.println(distinctFactorizations.get(i));
        }

        System.out.println("Number of distinct factorizations for " + inputN + " is: " + distinctFactorizations.size());

    }

}