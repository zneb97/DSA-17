import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        BinarySearchTree<Integer> balanced = new BinarySearchTree<>();
        Collections.sort(values);
        balance(values, balanced);
        return balanced;
    }

    public static void balance(List<Integer> values, BinarySearchTree<Integer> balanced){

        //Base
        if(values.size() <=1){
            balanced.add(values.get(0));
        }

        //Recurse
        balanced.add(values.get(values.size()/2));
        if(values.size() > 1){
            List<Integer> left = values.subList(0, values.size()/2);
            List<Integer> right = values.subList(values.size()/2 +1, values.size());
            balance(left, balanced);
            if(values.size() > 2) {
                balance(right, balanced);
            }
        }

    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        //Root Case
        if(n1.parent ==  null){
            if(n1.key != n2.key){
                return false;
            }
        }

        //Leaf Case
        if (((n1 == null) && (n2 == null)) || ((n1.isLeaf() && n2.isLeaf()))){
            return true;
        }

        //Branch case
        else{
            //Same side case
            if ((n1.leftChild.key == n2.leftChild.key) && (n1.rightChild.key == n2.rightChild.key)){
                boolean left = isIsomorphic(n1.leftChild, n2.leftChild);
                boolean right = isIsomorphic(n1.rightChild, n2.rightChild);
                return left && right;
            }

            //Switch side case
            if ((n1.rightChild.key == n2.leftChild.key) && (n1.leftChild.key == n2.rightChild.key)){
                boolean left = isIsomorphic(n1.rightChild, n2.leftChild);
                boolean right = isIsomorphic(n1.leftChild, n2.rightChild);
                return left && right;
            }
        }

        return false;
    }
}
