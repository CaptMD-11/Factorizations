import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) throws Exception {

        Factorizations obj = new Factorizations(100);//
        ArrayList<ArrayList<Integer>> factorizations = obj.getFirstLevelFactorizations();
        System.out.println(obj.getAllFactorizationsWithDuplicates(factorizations));

    }
}