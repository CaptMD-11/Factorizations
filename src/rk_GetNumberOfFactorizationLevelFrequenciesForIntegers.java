import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class rk_GetNumberOfFactorizationLevelFrequenciesForIntegers {

    // CREATES NEW FILE
    public static void main(String[] args) {

        // input composite integer bounds in the below 2 lines
        int lowerBound = 2;
        int upperBound = 1000000;

        ArrayList<Integer> compositeIntegers = new ArrayList<Integer>();
        compositeIntegers = getCompositeIntegers(lowerBound, upperBound);

        // all points are stored in the points ArrayList<ArrayList<Integer>>
        ArrayList<ArrayList<Integer>> points = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < compositeIntegers.size(); i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Factorizations obj = new Factorizations(compositeIntegers.get(i));
            ArrayList<Integer> primeFactorization = new ArrayList<Integer>();
            primeFactorization = obj.getPrimeFactorization(compositeIntegers.get(i));
            int numberOfFactorizationLevels = primeFactorization.size() / 2;
            list.add(compositeIntegers.get(i));
            list.add(numberOfFactorizationLevels);
            points.add(list);
        }

        int maxNumberOfFactorizationLevels = points.get(0).get(1);
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).get(1) > maxNumberOfFactorizationLevels)
                maxNumberOfFactorizationLevels = points.get(i).get(1);
        }
        System.out.println(maxNumberOfFactorizationLevels);

        // // mac grapher input
        // try {
        // File myObj = new File("NumberOfFactorizationLevelFrequenciesPerInteger.txt");
        // myObj.createNewFile();
        // FileWriter fileWriter = new
        // FileWriter("NumberOfFactorizationLevelFrequenciesPerInteger.txt");
        // BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // for (int i = 0; i < compositeIntegers.size(); i++) {
        // Factorizations obj = new Factorizations(compositeIntegers.get(i));
        // ArrayList<Integer> primeFactorization = new ArrayList<Integer>();
        // primeFactorization = obj.getPrimeFactorization(compositeIntegers.get(i));
        // int numberOfFactorizationLevels = primeFactorization.size() / 2;
        // String entry = compositeIntegers.get(i) + " " + numberOfFactorizationLevels +
        // " " + 0;
        // bufferedWriter.write(entry);
        // bufferedWriter.newLine();
        // }
        // bufferedWriter.close();
        // System.out.println("Printed from " + lowerBound + " to " + upperBound);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        // good
        // try {
        // File myObj = new File("NumberOfFactorizationLevelFrequenciesPerInteger.txt");
        // myObj.createNewFile();
        // FileWriter fileWriter = new
        // FileWriter("NumberOfFactorizationLevelFrequenciesPerInteger.txt");
        // BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // for (int i = 0; i < compositeIntegers.size(); i++) {
        // Factorizations obj = new Factorizations(compositeIntegers.get(i));
        // ArrayList<Integer> primeFactorization = new ArrayList<Integer>();
        // primeFactorization = obj.getPrimeFactorization(compositeIntegers.get(i));
        // int numberOfFactorizationLevels = primeFactorization.size() / 2;
        // String entry = "(" + compositeIntegers.get(i) + ", " +
        // numberOfFactorizationLevels + ")";
        // bufferedWriter.write(entry);
        // bufferedWriter.newLine();
        // }
        // bufferedWriter.close();
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static ArrayList<Integer> getCompositeIntegers(int lowerBound, int upperBound) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = lowerBound; i <= upperBound; i++) {
            if (Factorizations.isPrime(i) == false)
                res.add(i);
        }
        return res;
    }

}