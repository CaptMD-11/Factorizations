import java.util.ArrayList;

public class Factorizations {

    private ArrayList<Integer> primeFactorization;
    private ArrayList<ArrayList<Integer>> allFactorizationsWithDuplicates;

    public Factorizations() {
        primeFactorization = new ArrayList<Integer>(); 
        allFactorizationsWithDuplicates = new ArrayList<ArrayList<Integer>>(); 
    }

    

    // // returns number of distinct factorizations of n (prime and composite)
    // public int numFactorizations(int n) {
        
    // }

    // // returns all possible factorizations of n (includes duplicates)
    // // input is an ArrayList<Integer> that represents the prime factorization of n
    // public ArrayList<ArrayList<Integer>> getAllFactorizationsWithDuplicates(ArrayList<Integer> primeFactorization) {
    //     if ()

    // }

    // helper method
    // returns the product of all the integers in a list 
    public int product(ArrayList<Integer> factorization) {
        int res = 1;
        for (int i = 0; i < factorization.size(); i++) {
            res *= factorization.get(i);
        }
        return res; 
    }

    // helper method
    // returns an ArrayList<Integer> that represents the prime factorization of n
    public ArrayList<Integer> getPrimeFactorization(int n) {
        if (n==1) {
            return primeFactorization;
        } else if (isPrime(n)) {
            primeFactorization.add(n);
            return primeFactorization;
        } else {
            int leastFactor = getLeastFactor(n);
            primeFactorization.add(leastFactor);
            return getPrimeFactorization(n / leastFactor);
        }
        
    }

    // helper method
    // returns true if n is prime and false otherwise 
    public boolean isPrime(int n) {
        for (int i = 2; i <= n/2; i++) {
            if (n % i == 0)
                return false;
        }
        return true; 
    }

    // helper method
    // returns least factor of n
    public int getLeastFactor(int n) {
        for (int i = 2; i <= n/2; i++) {
            if (n % i == 0)
                return i;
        }
        return 1; 
    }    

}
