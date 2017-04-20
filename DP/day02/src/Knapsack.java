public class Knapsack {

    public static int maxValue(int knapsackSize, int[] S, int[] V) {
        int[][] memo = new int[V.length+1][knapsackSize+1];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
            }
        }
        return valueDP(0, knapsackSize, V, S, memo);
    }

    private static int valueDP(int itemIndex, int space, int[] items, int[] values, int[][] memo){

        //Base
        if(itemIndex == items.length-1){
            return 0;
        }

        //Check memo
        if(memo[itemIndex][space] != -1){
            return memo[itemIndex][space];
        }

        //Continue recusion
        //Current item too big
        if(items[itemIndex] > space){
            valueDP(itemIndex+1, space, items, values, memo);
        }

        //In or out
        int in = valueDP(itemIndex+1, space-items[itemIndex], items, values, memo)+values[itemIndex];
        int out = valueDP(itemIndex+1, space, items, values, memo);
        memo[itemIndex][space] = Math.max(in, out);
        return memo[itemIndex][space];
    }

}
