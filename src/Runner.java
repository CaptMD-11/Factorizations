import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) throws Exception {

        Factorizations obj = new Factorizations(100);
         System.out.println(obj.isPrime(659));
        // System.out.println(obj.getPrimeFactorization(102));
        

        System.out.println(obj.getCombinationsNoDuplicates());

    }
}