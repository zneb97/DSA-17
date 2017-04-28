public class EditDistance {
    //O(nm) memo is string 1 by string 2 with costs at each comparision
    public static int minEditDist(String a, String b) {
        //Create Memo
        //Stores cost between each letter pointer
        int[][] memo = new int[a.length()+1][b.length()+1];
        for (int i = 0; i <a.length()+1; i++) {
            for (int j = 0; j <b.length()+1 ; j++) {
                memo[i][j] = -1;
            }
        }
        for (int i = 0; i <a.length()+1; i++) {
            memo[i][0] = i;
        }
        for (int j = 0; j <b.length()+1 ; j++) {
            memo[0][j] = j;
        }

        //Costs
        for (int i = 1; i < a.length()+1 ; i++) {
            for (int j = 1; j < b.length()+1; j++) {
                //No cost
                if (a.charAt(i-1) == b.charAt(j-1)){
                    memo[i][j] = 0;
                }

                //Guess
                if(memo[i][j] == -1){
                    int save = Math.min(memo[i-1][j-1], memo[i][j-1]);
                    memo[i][j] = Math.min(save, memo[i-1][j]) +1;
                }

                //Update
                if(memo[i][j] == 0){
                    memo[i][j] = memo[i-1][j-1];
                }
            }
        }
        return memo[a.length()][b.length()];
    }

}