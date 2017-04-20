import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    //O(n^2) n for cost finding, n for memo
    public static List<Integer> justifyText(String[] w, int width) {
        ArrayList<Integer> result = new ArrayList<>();

        //Create memo
        int[] memo = new int[w.length+1]; //At each word break, check currentCost
        int[] breakPoint = new int[w.length];
        for (int i = 0; i < w.length; i++) {
            breakPoint[i] = -1;
            memo[i] = -1;
        }

        //
        recurse(w, breakPoint, width, 0, memo);

        //Create word list
        int i = 0;
        while(i < w.length && i >= 0) {
            result.add(i);
            i = breakPoint[i];
        }

        return result;
    }


    private static int recurse (String[] w, int[] breakPoint, int width, int index, int[] memo){
        int totalCost = 0;
        int costIndex = index+1;
        int minCost = Integer.MAX_VALUE;

        //Memo
        if(memo[index] != -1){
            return memo[index];
        }

        //Recurse
        for (int j = index+1; j <= w.length; j++) {
            int depthCost = recurse(w, breakPoint, width, j , memo);
            int currentCost = getCost(w,index,j,width);

            //End path
            if(currentCost == Integer.MAX_VALUE){
                totalCost = Integer.MAX_VALUE;
            }
            //New cost
            else {
                totalCost = depthCost+currentCost;
            }

            if(minCost > totalCost){
                minCost = totalCost;
                costIndex = j;
            }
        }
        memo[index] = minCost;
        breakPoint[index] = costIndex;
        return minCost;

    }

    private static int getCost(String[] w, int index, int end, int width){
        int totalLength = -1;
        for(int i = index; i < end; i++){
            totalLength += w[i].length();
            totalLength++; //Spaces
        }
        //Overflow
        if(totalLength > width){
            return Integer.MAX_VALUE;
        }
        //Calculated Cost
        return (int)Math.pow(width - totalLength,3);
    }

}