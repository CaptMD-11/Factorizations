import java.util.ArrayList;

public class r_GetDistinctFactorizationsForInteger {

    public static void main(String[] args) {

        // input composite integer in below line
        int inputN = 100;
        Factorizations obj = new Factorizations(inputN);
        ArrayList<ArrayList<Integer>> distinctFactorizations = new ArrayList<ArrayList<Integer>>();
        distinctFactorizations = obj.getDistinctFactorizations();

        // prints the distinct factorizations of inputN
        for (int i = 0; i < distinctFactorizations.size(); i++) {
            System.out.println(distinctFactorizations.get(i));
        }

    }

}