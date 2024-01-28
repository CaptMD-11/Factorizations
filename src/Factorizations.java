import java.util.ArrayList;
import java.util.Arrays;

public class Factorizations {

    private int m_n;
    private ArrayList<Integer> m_primeFactorization;
    private ArrayList<ArrayList<Integer>> m_allDistinctFactorizations;
    private ArrayList<ArrayList<Integer>> m_firstLevelFactorizations;

    public Factorizations(int inputN) {
        m_n = inputN;
        m_primeFactorization = new ArrayList<Integer>();
        m_primeFactorization = getPrimeFactorization(m_n);
        m_allDistinctFactorizations = new ArrayList<ArrayList<Integer>>();
        m_firstLevelFactorizations = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> prime_combinationsNoDuplicates = new ArrayList<ArrayList<Integer>>();
        prime_combinationsNoDuplicates = getCombinationsNoDuplicates(m_primeFactorization);
        ArrayList<Integer> prime_products = new ArrayList<Integer>();
        for (int i = 0; i < prime_combinationsNoDuplicates.size(); i++) {
            prime_products.add(product(prime_combinationsNoDuplicates.get(i)));
        }
        // now, prime_products represents an ArrayList<Integer> containing the products
        // of the individual distinct combinations of pf(n)
        for (int i = 0; i < prime_products.size(); i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < m_primeFactorization.size(); j++) {
                list.add(m_primeFactorization.get(j));
            }
            m_firstLevelFactorizations.add(list);
        }
        // now, prime_products and m_firstLevelFactorizations both contain the same
        // number
        // of elements (although they contain different data types)
        for (int i = 0; i < m_firstLevelFactorizations.size(); i++) {
            m_firstLevelFactorizations.get(i).add(prime_products.get(i));
        }
        // now, added each product to each respective element of
        // m_firstLevelFactorizations
        // m_firstLevelFactorizations and prime_combinationsNoDuplicates have the same
        // size
        for (int i = 0; i < m_firstLevelFactorizations.size(); i++) {
            ArrayList<Integer> compliment = new ArrayList<Integer>();
            compliment = getSubsetCompliment(prime_combinationsNoDuplicates.get(i), m_primeFactorization);
            for (int j = 0; j < compliment.size(); j++) {
                m_firstLevelFactorizations.get(i).add(compliment.get(j));
            }
            for (int j = m_primeFactorization.size() - 1; j > -1; j--) {
                m_firstLevelFactorizations.get(i).remove(j);
            }
        }
    }

    public int getNumberOfDistinctFactorizations() {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        list = getDistinctFactorizations();
        return list.size();
    }

    /*
     * returns all distinct factorizations of n
     */
    public ArrayList<ArrayList<Integer>> getDistinctFactorizations() {
        ArrayList<ArrayList<Integer>> factorizationWithDuplicates = new ArrayList<ArrayList<Integer>>();
        factorizationWithDuplicates = getAllFactorizationsWithDuplicates(m_firstLevelFactorizations);
        for (int i = 0; i < factorizationWithDuplicates.size(); i++) {
            factorizationWithDuplicates.set(i, sort(factorizationWithDuplicates.get(i)));
        }
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        res.add(factorizationWithDuplicates.get(0));
        for (int i = 1; i < factorizationWithDuplicates.size(); i++) {
            if (firstListExistsInSecondList(factorizationWithDuplicates.get(i), res) == false)
                res.add(factorizationWithDuplicates.get(i));
        }
        m_allDistinctFactorizations = res;
        return res;
    }

    /*
     * returns all possible factorizations of n (yes duplicates)
     * original input is m_firstLevelFactorizations
     * subsequent inputs are kth level factorizations
     */
    public ArrayList<ArrayList<Integer>> getAllFactorizationsWithDuplicates(
            ArrayList<ArrayList<Integer>> factorizations) {
        // need to add current level factorizations to allDistinctFactorizations
        for (int i = 0; i < factorizations.size(); i++) {
            m_allDistinctFactorizations.add(factorizations.get(i));
        }
        if (factorizations.get(0).size() == 2) {
            m_allDistinctFactorizations.add(m_primeFactorization);
            return m_allDistinctFactorizations;
        } else {
            ArrayList<ArrayList<Integer>> nextLevelFactorizations = new ArrayList<ArrayList<Integer>>();
            for (int a = 0; a < factorizations.size(); a++) {
                ArrayList<ArrayList<Integer>> factor_combinationsNoDuplicates = new ArrayList<ArrayList<Integer>>();
                factor_combinationsNoDuplicates = getCombinationsNoDuplicates(factorizations.get(a));
                ArrayList<Integer> factor_products = new ArrayList<Integer>();
                for (int i = 0; i < factor_combinationsNoDuplicates.size(); i++) {
                    factor_products.add(product(factor_combinationsNoDuplicates.get(i)));
                }
                // now, prime_products represents an ArrayList<Integer> containing the products
                // of the individual distinct combinations of pf(n)

                ArrayList<ArrayList<Integer>> nextLevelFactorizationChildNodes = new ArrayList<ArrayList<Integer>>();

                for (int i = 0; i < factor_products.size(); i++) {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    for (int j = 0; j < factorizations.get(a).size(); j++) {
                        list.add(factorizations.get(a).get(j));
                    }
                    nextLevelFactorizationChildNodes.add(list);
                }
                // now, prime_products and nextLevelFactorizationChildNodes both contain the
                // same number
                // of elements (although they contain different data types)
                for (int i = 0; i < nextLevelFactorizationChildNodes.size(); i++) {
                    nextLevelFactorizationChildNodes.get(i).add(factor_products.get(i));
                }
                // now, added each product to each respective element of
                // m_firstLevelFactorizations
                // m_firstLevelFactorizations and prime_combinationsNoDuplicates have the same
                // size
                for (int i = 0; i < nextLevelFactorizationChildNodes.size(); i++) {
                    ArrayList<Integer> compliment = new ArrayList<Integer>();
                    compliment = getSubsetCompliment(factor_combinationsNoDuplicates.get(i), factorizations.get(a));
                    for (int j = 0; j < compliment.size(); j++) {
                        nextLevelFactorizationChildNodes.get(i).add(compliment.get(j));
                    }
                    for (int j = factorizations.get(a).size() - 1; j > -1; j--) {
                        nextLevelFactorizationChildNodes.get(i).remove(j);

                    }
                }
                // the loop starting on the next line adds all the child nodes to the
                // nextLevelFactorizations
                for (int i = 0; i < nextLevelFactorizationChildNodes.size(); i++) {
                    nextLevelFactorizations.add(nextLevelFactorizationChildNodes.get(i));
                }
            }
            return getAllFactorizationsWithDuplicates(nextLevelFactorizations);
        }
    }

    /*
     * helper method
     * returns the firstLevelFactorization of pf(n)
     * getter
     */
    public ArrayList<ArrayList<Integer>> getFirstLevelFactorizations() {
        return m_firstLevelFactorizations;
    }

    /*
     * helper method
     * returns the compliment of the subset of the set
     */
    public ArrayList<Integer> getSubsetCompliment(ArrayList<Integer> smallList, ArrayList<Integer> bigList) {
        ArrayList<Integer> bigListCopy = new ArrayList<Integer>();
        for (int i = 0; i < bigList.size(); i++) {
            bigListCopy.add(bigList.get(i));
        }
        for (int i = 0; i < smallList.size(); i++) {
            for (int j = bigListCopy.size() - 1; j > -1; j--) {
                if (smallList.get(i) == bigListCopy.get(j)) {
                    bigListCopy.remove(j);
                    break;
                }
            }
        }
        return bigListCopy;
    }

    /*
     * helper method
     * STEP 1 OF ALGORITHM
     * returns all combinations (nCr; grouped into pairs of 2) of a factorization
     * with no duplicates
     */
    public ArrayList<ArrayList<Integer>> getCombinationsNoDuplicates(ArrayList<Integer> factorization) {
        ArrayList<ArrayList<Integer>> res1 = new ArrayList<ArrayList<Integer>>();
        int numFactors = factorization.size();
        for (int j = 0; j < numFactors - 1; j++) {
            for (int k = j + 1; k < numFactors; k++) {
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
     * returns true if both the lists are the same (doesn't matter if they are
     * sorted or not) and false otherwise
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
        if (n == 1) {
            return m_primeFactorization;
        } else if (isPrime(n)) {
            m_primeFactorization.add(n);
            return m_primeFactorization;
        } else {
            int leastFactor = getLeastFactor(n);
            m_primeFactorization.add(leastFactor);
            return getPrimeFactorization(n / leastFactor);
        }

    }

    /*
     * helper method
     * returns true if n is prime and false otherwise
     */
    public boolean isPrime(int n) {
        for (int i = 2; i <= n / 2; i++) {
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
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0)
                return i;
        }
        return 1;
    }

}