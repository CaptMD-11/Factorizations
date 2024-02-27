import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class rf_GetFactorizationFrequenciesForIntegers {

    public static void main(String[] args) {

        // input lower bound and upper bound in below lines
        int lowerBound = 100;
        int upperBound = 200;
        ArrayList<ArrayList<Integer>> factorizationFrequenciesPerNumber = new ArrayList<ArrayList<Integer>>();
        factorizationFrequenciesPerNumber = getFactorizationFrequenciesPerNumber(lowerBound, upperBound);

        // prints out ArrayLists, where each ArrayList = [c,f]
        // c is the composite integer of interest
        // f is the number of distinct factorizations of c
        try {
            ArrayList<String> factorizationFrequenciesPerNumberStrList = new ArrayList<String>();
            for (int i = 0; i < factorizationFrequenciesPerNumber.size(); i++) {
                factorizationFrequenciesPerNumberStrList.add(factorizationFrequenciesPerNumber.get(i) + "");
            }
            File myObj = new File("distinctFactorizationFrequenciesPerInteger.txt");
            myObj.createNewFile();
            FileWriter fileWriter = new FileWriter("distinctFactorizationFrequenciesPerInteger.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < factorizationFrequenciesPerNumberStrList.size(); i++) {
                String entry = factorizationFrequenciesPerNumberStrList.get(i);
                String str = "";
                str += "(";
                for (int j = 1; j < entry.length() - 1; j++) {
                    str += entry.substring(j, j + 1);
                }
                str += ")";
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < factorizationFrequenciesPerNumber.size(); i++) {
            System.out.println(factorizationFrequenciesPerNumber.get(i));
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static ArrayList<ArrayList<Integer>> getFactorizationFrequenciesPerNumber(int lowerBound, int upperBound) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> compositeIntegers = new ArrayList<Integer>();
        compositeIntegers = getCompositeIntegers(lowerBound, upperBound);
        for (int i = 0; i < compositeIntegers.size(); i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Factorizations test = new Factorizations(compositeIntegers.get(i));
            list.add(compositeIntegers.get(i));
            list.add(test.getNumberOfDistinctFactorizations());
            res.add(list);
        }
        return res;
    }

    public static ArrayList<Integer> getCompositeIntegers(int lowerBound, int upperBound) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = lowerBound; i <= upperBound; i++) {
            if (Factorizations.isPrime(i) == false)
                res.add(i);
        }
        return res;
    }

}