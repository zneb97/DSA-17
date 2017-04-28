import java.util.Arrays;

public class BillsNeeded {

    public int billsNeeded(int N, int[] billDenominations) {
        //Create and initialize memo
        int[] memo = new int[N + 1];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1;
        }

        //Begin recursion
        return recurse(N, billDenominations, memo);
    }

    private int recurse(int N, int[] billDenominations, int[] memo){
        //Found solution
        if(N == 0){
            return 0;
        }

        //Overshoot
        else if(N < 0){
            return Integer.MAX_VALUE-1;
        }

        //Already found
        else if(memo[N] != -1){
            return memo[N];
        }

        else{
            int[] bills = new int[billDenominations.length];

            for (int i = 0; i < billDenominations.length; i++) {
                bills[i] = recurse(N-billDenominations[i], billDenominations, memo) + 1;
            }
            
            Arrays.sort(bills);
            memo[N] = bills[0];
            return bills[0];
        }
    }

}