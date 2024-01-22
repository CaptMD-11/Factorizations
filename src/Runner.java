import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) throws Exception {

        Factorizations obj = new Factorizations(100);
         System.out.println(obj.isPrime(659));
        // System.out.println(obj.getPrimeFactorization(102));
        ArrayList<Integer> factorization = new ArrayList<Integer>(); 
        factorization.add(2);
        factorization.add(5);
        factorization.add(7);
        factorization.add(9); 

        System.out.println(obj.getCombinationsNoDuplicates(factorization));

    }
}