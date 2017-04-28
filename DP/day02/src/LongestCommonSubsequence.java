import java.util.Arrays;

public class LongestCommonSubsequence {

    public static int longestCommonSubsequence(String S, String T) {
        int[][] memo = new int[S.length()+1][T.length()+1];
        for(int i = 0; i < memo.length;i++){
            for(int j = 0; j < memo[0].length;j++){
                memo[i][j] = -1;
            }
        }
        return recurse(S.toCharArray(),T.toCharArray(),memo);
    }

    public static int recurse(char[] string1, char[] string2, int[][] memo) {

        //Base
        if(string1.length == 0){
            return 0;
        }else if(string2.length == 0){
            return 0;
        }

        //Memo
        if(memo[string1.length][string2.length] != -1){
            return memo[string1.length][string2.length];
        }


        char[] s1Save = string1;
        char[] s2Save = string2;

        //Drop both
        if(s1Save[s1Save.length-1] == s2Save[s2Save.length-1]){
            char[] cut1 = Arrays.copyOfRange(s1Save, 0, s1Save.length-1);
            char[] cut2 = Arrays.copyOfRange(s2Save, 0, s2Save.length-1);
            //Memoize
            memo[string1.length][string2.length] = recurse(cut1, cut2,memo) + 1;
            return memo[string1.length][string2.length];
        }
        
        //Guess
        else{
            char[] cut1 = Arrays.copyOfRange(s1Save, 0, s1Save.length-1);
            char[] cut2 = Arrays.copyOfRange(s2Save, 0, s2Save.length);
            int drop1 =  recurse(cut1, cut2,memo);
            cut1 = Arrays.copyOfRange(s1Save, 0, s1Save.length);
            cut2 = Arrays.copyOfRange(s2Save, 0, s2Save.length-1);
            int drop2 = recurse(cut1, cut2, memo);
            memo[string1.length][string2.length] = Math.max(drop1, drop2);
            return Math.max(drop1, drop2);
        }
    }

}