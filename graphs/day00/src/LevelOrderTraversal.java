import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    public static List<Integer> levelOrderTraversal(TreeNode<Integer> n) {
        ArrayList<Integer> ordered = new ArrayList<>();
        breadthFirstSearch(n, ordered);
        return ordered;
    }

    public static void breadthFirstSearch(TreeNode<Integer> n, ArrayList<Integer> ordered){
        Queue<TreeNode<Integer>> path = new LinkedList<>();
        path.add(n);
        while(!path.isEmpty()){
            n = path.remove();
            ordered.add(n.key);
            if(n.hasLeftChild()){
                path.add(n.leftChild);
            }
            if(n.hasRightChild()){
                path.add(n.rightChild);
            }

        }
    }
}
