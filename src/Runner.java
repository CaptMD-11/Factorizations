import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) throws Exception {

        Factorizations obj = new Factorizations();
        // System.out.println(obj.isPrime(659));
        // System.out.println(obj.getPrimeFactorization(102));
        ArrayList<Integer> list = new ArrayList<Integer>(); 
        list.add(2);
        list.add(3);
        list.add(17);
        System.out.println(obj.product(list));

    }
}