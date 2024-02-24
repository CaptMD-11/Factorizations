import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class r_GetNumberOfFactorizationLevelFrequenciesForIntegers {

    public static void main(String[] args) {

        // input composite integer bounds in the below 2 lines
        int lowerBound = 100;
        int upperBound = 200;

        try {
            File myObj = new File("NumberOfFactorizationLevelFrequenciesPerInteger.txt");
            myObj.createNewFile();
            FileWriter fileWriter = new FileWriter("NumberOfFactorizationLevelFrequenciesPerInteger.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = lowerBound; i <= upperBound; i += 2) {
                Factorizations obj = new Factorizations(i);
                ArrayList<Integer> primeFactorization = new ArrayList<Integer>();
                primeFactorization = obj.getPrimeFactorization(i);
                int numberOfFactorizationLevels = primeFactorization.size() / 2;
                String entry = "(" + i + ", " + numberOfFactorizationLevels + ")";
                bufferedWriter.write(entry);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}