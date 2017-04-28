import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SplitCoins {

    public int splitCoins(int[] coins) {
        int total = 0;
        for (int i = 0; i < coins.length; i++) {
            total += coins[i];
        }

        //Make memo
        int[][] memo = new int[total+1][coins.length];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
            }
        }

        return recurse(total, 0 , 0, coins, memo);
    }

    private int recurse(int total, int used, int pile, int[] coins, int[][] memo){

        //Base, used up all coins
        if(coins.length == used){
            return Math.abs(pile*2-total);
        }

        //Exists
        else if(memo[pile][used] != -1){
            return memo[pile][used];
        }

        //Continue
        int left = recurse(total, used+1, pile + coins[used], coins, memo);
        int right = recurse(total,used+1, pile, coins, memo);
        int min = Math.min(left,right);
        memo[pile][used] = min;
        return min;
    }
}