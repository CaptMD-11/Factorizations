import java.util.ArrayList;
import java.util.Arrays;

public class Factorizations {

    private int n;
    private ArrayList<Integer> primeFactorization;
    private ArrayList<ArrayList<Integer>> allDistinctFactorizations;
    private ArrayList<ArrayList<Integer>> firstLevelFactorizations; 

    public Factorizations(int inputN) {
        n = inputN;
        primeFactorization = new ArrayList<Integer>();
        primeFactorization = getPrimeFactorization(n); 
        allDistinctFactorizations = new ArrayList<ArrayList<Integer>>(); 
        firstLevelFactorizations = new ArrayList<ArrayList<Integer>>(); 
        ArrayList<ArrayList<Integer>> prime_combinationsNoDuplicates = new ArrayList<ArrayList<Integer>>(); 
        prime_combinationsNoDuplicates = getCombinationsNoDuplicates(primeFactorization); 
        ArrayList<Integer> prime_products = new ArrayList<Integer>(); 
        for (int i = 0; i < prime_combinationsNoDuplicates.size(); i++) {
            prime_products.add(product(prime_combinationsNoDuplicates.get(i))); 
        }
        // now, prime_products represents an ArrayList<Integer> containing the products of the individual distinct combinations of pf(n)
        for (int i = 0; i < prime_products.size(); i++) {
            ArrayList<Integer> list = new ArrayList<Integer>(); 
            for (int j = 0; j < primeFactorization.size(); j++) {
                list.add(primeFactorization.get(j)); 
            }
            firstLevelFactorizations.add(list); 
        }
        // now, prime_products and firstLevelFactorizations both contain the same number of elements (although they contain different data types)
        for (int i = 0; i < firstLevelFactorizations.size(); i++) {
            // System.out.println(firstLevelFactorizations.get(i)); 
            // System.out.println(prime_products.get(i)); 
            firstLevelFactorizations.get(i).add(prime_products.get(i)); 
            System.out.println(firstLevelFactorizations.get(i)); 
        }
        // now, added each product to each respective element of firstLevelFactorizations
        for (int i = 0; i < firstLevelFactorizations.size(); i++) {
            for (int j = firstLevelFactorizations.get(i).size()-1; j > -1; j--) {
                for (int k = 0; k < prime_combinationsNoDuplicates.size(); k++) {
                    for (int l = 0; l < prime_combinationsNoDuplicates.get(k).size(); l++) {
                        
                    }
                }
            }
        }
        
    }

    // // returns number of distinct factorizations of n (prime and composite)
    // public int numFactorizations(int n) {
    //     ArrayList<ArrayList<Integer>> distinctFactorizations = new ArrayList<ArrayList<Integer>>(0); 
    //     distinctFactorizations = getAllDistinctFactorizations();
    //     return distinctFactorizations.size();
    // }

    // public ArrayList<ArrayList<Integer>> getAllDistinctFactorizations() {
    //     ArrayList<ArrayList<Integer>> duplicateFactorizations = new ArrayList<ArrayList<Integer>>(); 
    //     duplicateFactorizations = getAllFactorizationsWithDuplicates();
    //     ArrayList<ArrayList<Integer>> distinctFactorizations = new ArrayList<ArrayList<Integer>>(); 
    //     distinctFactorizations.add(duplicateFactorizations.get(0)); 
    //     for (int i = 1; i < duplicateFactorizations.size(); i++) {
    //         if (firstListExistsInSecondList(duplicateFactorizations.get(i), distinctFactorizations) == false)
    //             distinctFactorizations.add(duplicateFactorizations.get(i)); 
    //     }
    //     return distinctFactorizations;
    // }

    // /*
    //  * returns all possible distinct factorizations of n (no duplicates)
    //  * returns the member variable allDistinctFactorizations
    //  */
    // public ArrayList<ArrayList<Integer>> getAllFactorizationsWithDuplicates() {
    //     for (int i = 0; i < firstLevelFactorizations.size(); i++) {
    //         allDistinctFactorizations.add(firstLevelFactorizations.get(i)); 
    //     }
    //     if (firstLevelFactorizations.get(0).size() == 2) {
    //         allDistinctFactorizations.add(getPrimeFactorization(n));
    //         return allDistinctFactorizations;
    //     } else {
    //         ArrayList<ArrayList<ArrayList<Integer>>> kLevelCombinations = new ArrayList<ArrayList<ArrayList<Integer>>>(); 
    //         for (int i = 0; i < firstLevelFactorizations.size(); i++) {
    //             kLevelCombinations.add(getCombinationsNoDuplicates(firstLevelFactorizations.get(i))); 
    //         }
    //         // need to remove duplicates inside kLevelCombinations
    //         for (int i = 0; i < kLevelCombinations.size(); i++) {
    //             ArrayList<ArrayList<Integer>> combinationsIthIndexNoDuplicates = new ArrayList<ArrayList<Integer>>(); 
    //             combinationsIthIndexNoDuplicates.add(kLevelCombinations.get(i).get(0)); 
    //             for (int j = 1; j < kLevelCombinations.get(i).size(); j++) {
    //                 if (firstListExistsInSecondList(kLevelCombinations.get(i).get(j), combinationsIthIndexNoDuplicates) == false)
    //                     combinationsIthIndexNoDuplicates.add(kLevelCombinations.get(i).get(j)); 
    //             }
    //             kLevelCombinations.set(i, combinationsIthIndexNoDuplicates); 
    //         } // now, duplicate combinations should be removed and only distinct combinations should exist inside kLevelCombinations
    //         for (int i = 0; i < kLevelCombinations.size(); i++) {
    //             ArrayList<Integer> jIndexProducts = new ArrayList<Integer>(); 
    //             for (int j = 0; j < kLevelCombinations.get(i).size(); j++) {
    //                 jIndexProducts.add(product(kLevelCombinations.get(i).get(j))); 
    //             }
    //             for (int j = 0; j < kLevelCombinations.get(i).size(); j++) {
    //                 kLevelCombinations.get(i).get(j).clear();
    //                 kLevelCombinations.get(i).get(j).add(jIndexProducts.get(j)); 
    //             }
    //         } // now, products are inside kLevelCombinations, where each product is inside its own ArrayList
    //         ArrayList<ArrayList<Integer>> kLevelFactorizationCombinationProduct = new ArrayList<ArrayList<Integer>>(); 
    //         for (int i = 0; i < kLevelCombinations.size(); i++) {
    //             ArrayList<Integer> list = new ArrayList<Integer>(); 
    //             for (int j = 0; j < kLevelCombinations.get(i).size(); j++) {
    //                 list.add(kLevelCombinations.get(i).get(j).get(0)); 
    //             }
    //             kLevelFactorizationCombinationProduct.add(list); 
    //         }
    //         // now, completed with first paragraph on 8th page of handwritten paper 
    //     }
    // }

    /*
     * helper method
     * STEP 1 OF ALGORITHM
     * returns all combinations (nCr; grouped into pairs of 2) of a factorization with no duplicates 
     */
    public ArrayList<ArrayList<Integer>> getCombinationsNoDuplicates(ArrayList<Integer> factorization) {
        ArrayList<ArrayList<Integer>> res1 = new ArrayList<ArrayList<Integer>>(); 
        int numFactors = factorization.size();
            for (int j = 0; j < numFactors-1; j++) {
                for (int k = j+1; k < numFactors; k++) {
                    ArrayList<Integer> pair = new ArrayList<Integer>();
                    pair.add(factorization.get(j));
                    pair.add(factorization.get(k));
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
