import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class r_GetNumberOfFactorizationLevelsForInteger {

    public static void main(String[] args) {

        // input composite integer in the below line
        int inputN = 210;
        Factorizations obj = new Factorizations(inputN);
        ArrayList<Integer> primeFactorization = new ArrayList<Integer>();
        primeFactorization = obj.getPrimeFactorization(inputN);

        // prints the number of factorization levels for inputN
        System.out.println(primeFactorization.size() / 2 - 1);

    }

}