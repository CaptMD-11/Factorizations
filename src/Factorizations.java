import java.util.ArrayList;
import java.util.Arrays;

public class Factorizations {

    private int n;
    private ArrayList<Integer> primeFactorization;
    private ArrayList<ArrayList<Integer>> allDistinctFactorizations;

    public Factorizations(int inputN) {
        n = inputN;
        primeFactorization = new ArrayList<Integer>();
        primeFactorization = getPrimeFactorization(n); 
        allDistinctFactorizations = new ArrayList<ArrayList<Integer>>(); 
    }

    

    // // returns number of distinct factorizations of n (prime and composite)
    // public int numFactorizations(int n) {
        
    // }

    /*
     * returns all possible distinct factorizations of n (no duplicates)
     * returns the member variable allDistinctFactorizations
     * input is an ArrayList<ArrayList<Integer>> that is a list that contains a list that represents the prime factorization of n
     */
    public ArrayList<ArrayList<Integer>> getAllFactorizationsWithDuplicates(ArrayList<ArrayList<Integer>> factorizations) {
        allDistinctFactorizations.add(factorization); 
        if (factorizations.get(0).size() == 1) {
            return allDistinctFactorizations;
        } else {
            ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>>();
            combinations = getCombinationsNoDuplicates();
            ArrayList<Integer> nextLevelFactors = new ArrayList<Integer>();
            for (int i = 0; i < combinations.size(); i++) {
                nextLevelFactors.add(product(combinations.get(i))); 
            } // now, 4, 10, and 25 are stored inside nextLevelFactors
            ArrayList<ArrayList<Integer>> nextLevelFactorizations = new ArrayList<ArrayList<Integer>>(); 
            for (int i = 0; i < nextLevelFactors.size(); i++) {
                ArrayList<Integer> nextLevelFactorization1 = new ArrayList<Integer>();
                nextLevelFactorization1 = getPrimeFactorization(n / nextLevelFactors.get(i));
                nextLevelFactorization1.add(nextLevelFactors.get(i)); 
                nextLevelFactorization1 = sort(nextLevelFactorization1);
                nextLevelFactorizations.add(nextLevelFactorization1);
            }
            // now, nextLevelFactorizations contain {4,5,5}, {2,5,10}, and {2,2,25}
            for (int i = 0; i < nextLevelFactorizations.size(); i++) {
                return getAllFactorizationsWithDuplicates(nextLevelFactorizations.get(i)); 
            }
        }

    }

    /*
     * helper method
     * STEP 1 OF ALGORITHM
     * returns all combinations (grouped into pairs of 2) of a factorization with no duplicates 
     */
    public ArrayList<ArrayList<Integer>> getCombinationsNoDuplicates() {
        ArrayList<ArrayList<Integer>> res1 = new ArrayList<ArrayList<Integer>>(); 
        int numFactors = primeFactorization.size();
            for (int j = 0; j < numFactors-1; j++) {
                for (int k = j+1; k < numFactors; k++) {
                    ArrayList<Integer> pair = new ArrayList<Integer>();
                    pair.add(primeFactorization.get(j));
                    pair.add(primeFactorization.get(k));
                    res1.add(pair);
                }
            }
        ArrayList<ArrayList<Integer>> res2 = new ArrayList<ArrayList<Integer>>(); 
        res2.add(res1.get(0)); 
        for (int i = 1; i < res1.size(); i++) {
            if (firstListExistsInSecondList(res1.get(i), res2) == false)
                res2.add(res1.get(i)); 
        }
        return res2;
    }

    /*
     * helper method
     * returns true if list1 is found in list2 and false otherwise 
     */
    public boolean firstListExistsInSecondList(ArrayList<Integer> list1, ArrayList<ArrayList<Integer>> list2) {
        String list1Str = "";
        for (int i = 0; i < list1.size(); i++) {
            list1Str += list1.get(i) + " "; 
        }
        list1Str = list1Str.trim();
        for (int i = 0; i < list2.size(); i++) {
            String tempStr = ""; 
            for (int j = 0; j < list2.get(i).size(); j++) {
                tempStr += list2.get(i).get(j) + " "; 
            }
            tempStr = tempStr.trim(); 
            if (list1Str.equals(tempStr))
                return true;
        }
        return false; 
    }

    /*
     * helper method
     * returns true if both the lists are the same (doesn't matter if they are sorted or not) and false otherwise 
     */
    public boolean areSameList(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        list1 = sort(list1); 
        list2 = sort(list2); 
        if (list1.size() != list2.size()) 
            return false; 
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i))
                return false;
        }
        return true; 
    }

    /*
     * helper method
     * returns a sorted version of the input ArrayList in increasing order 
     */
    public ArrayList<Integer> sort(ArrayList<Integer> list) {
        int[] arr = new int[list.size()]; 
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i); 
        }
        Arrays.sort(arr); 
        for (int i = 0; i < arr.length; i++) {
            list.set(i, arr[i]); 
        }
        return list; 
    }

    /*
     * helper method
     * returns the product of all the integers in a list 
     */
    public int product(ArrayList<Integer> factorization) {
        int res = 1;
        for (int i = 0; i < factorization.size(); i++) {
            res *= factorization.get(i);
        }
        return res; 
    }

    /*
     * helper method
     * returns an ArrayList<Integer> that represents the prime factorization of n
     */
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

    /*
     * helper method
     * returns true if n is prime and false otherwise
     */
    public boolean isPrime(int n) {
        for (int i = 2; i <= n/2; i++) {
            if (n % i == 0)
                return false;
        }
        return true; 
    }

    /*
     * helper method
     * returns least factor of n
     */
    public int getLeastFactor(int n) {
        for (int i = 2; i <= n/2; i++) {
            if (n % i == 0)
                return i;
        }
        return 1; 
    }    

}
