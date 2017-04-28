import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        ArrayList combo = new ArrayList();
        ArrayList perms = new ArrayList();
        ArrayList<Integer> listA = new ArrayList();
        for (int i = 0; i < A.size(); i++) {
            listA.add(A.get(i));
        }
        return backtrack(listA, combo, perms);
    }

    public static List<List<Integer>> backtrack(ArrayList<Integer> A, ArrayList combo, List<List<Integer>> perms) {

        //Base
        if (A.size() == 0) {
            perms.add(combo);
            return perms;
        }

        //Backtrack
        for (Integer toUse: A) {
            //Break off into new combos
            ArrayList<Integer> removeA = new ArrayList<>(A);
            ArrayList addCombo = new ArrayList<>(combo);
            //Can't use again
            removeA.remove(toUse);
            addCombo.add(toUse);
            perms = backtrack(removeA, addCombo, perms);
        }

        return perms;
    }
}