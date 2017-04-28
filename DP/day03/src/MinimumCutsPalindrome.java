import org.junit.internal.builders.NullBuilder;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class MinimumCutsPalindrome {

    // Return true if s[lo:hi] is a palindrome (inclusive, exclusive)
    private boolean isPalindrome(String s, int lo, int hi) {
        assert lo <= hi;
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi-1))
                return false;
            lo++; hi--;
        }
        return true;
    }

    /**
     * the minimum number of cuts needed to partition s into palindromes. For example: "aabcacb" -> "aa","bcacb".
     * Calculated using a bottom-up dynamic programming approach.
     */
    public int minCutBU(String s) {
        int length = s.length();
        int[] memo = new int[length+1];
        memo[length] = -1; //Solves problem of last cut
        int[] nextCuts = new int[length];
        nextCuts[length-1] = -1; //No next cut

        //Iteration
        for (int i = length-1; i >= 0; i--) {
            int cuts = Integer.MAX_VALUE;
            int nextCut = -1;
            for (int j = i+1; j <= length; j++) {
                if(isPalindrome(s, i, j)){
                    int newCuts = memo[j] +1;
                    if(newCuts < cuts){
                        cuts = newCuts;
                        nextCut = j;
                    }
                }
            }
            nextCuts[i] = nextCut;
            memo[i] = cuts;
        }
        List<Integer> allCuts = new LinkedList<>();
        int cutLocation = nextCuts[0];
        while(cutLocation < length){
            allCuts.add(cutLocation);
            cutLocation = nextCuts[cutLocation];
        }
        System.out.println(cutLocation);
        return memo[0];
    }

    /**
//     * Calculated using a top-down dynamic programming approach.
//     */
//    public int minCutTD(String s) {
//        int length = s.length();
//        int[] memo = new int[length+1];
//        for (int i = 0; i < memo.length; i++) {
//            memo[i] = Integer.MAX_VALUE;
//        }
//        memo[length] = -1; //Solves problem of last cut
//
//        return minCutRecursive(s, memo, length-1);
//
//
//    }
//
//    private int minCutRecursive(String s, int[] memo, int i){
//        if(memo[i] != Integer.MIN_VALUE){
//            return memo[i];
//        }
//        int N = s.length();
//        int cuts = Integer.MAX_VALUE;
//        for (int j = i+1; j <= N; j++) {
//            if(isPalindrome(s,i,j)){
//                int newCuts = minCutRecursive(s, memo, j) +1;
//            }
//        }
//    }

    public static void main(String[] args) {
        System.out.println((new MinimumCutsPalindrome()).minCutBU("aabcacb")); // should return 1 "aa,bcacb"
       // System.out.println((new MinimumCutsPalindrome()).minCutTD("aabcacb")); // should return 1 "aa,bcacb"
        System.out.println((new MinimumCutsPalindrome()).minCutBU("aabacacb")); // should return 3 "a,aba,cac,b"
        //System.out.println((new MinimumCutsPalindrome()).minCutTD("aabacacb")); // should return 3 "a,aba,cac,b"
    }


}
