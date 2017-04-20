import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class DungeonGame {
    //O(nm) board size, memo for each space
    public static int minInitialHealth(int[][] map) {
        //Creat memo
        //Health needed at coordinate
        int memo[][] = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                memo[i][j] = Integer.MIN_VALUE; //Not -1 because that can be attack value
            }
        }

        return recurse(map, 0, 0, memo);
    }

    private static int recurse(int[][] map, int i, int j, int[][] memo) {
        int health = 0;

        //Base
        if ((i == map.length-1) && (j == map[0].length-1)) {
            return max(-map[i][j]+1, 1);
        }

        //Memo
        else if (memo[i][j] != Integer.MIN_VALUE) {
            return memo[i][j];
        }

        //Ran out of board
        if (i == map.length-1) {
            health = max(-map[i][j] + recurse(map, i, j+1, memo), 1);
        }
        //Ran out of board
        else if (j == map[0].length-1) {
            health = max(-map[i][j] + recurse(map, i+1, j, memo), 1);
        }

        //Guess
        else {
            int right = recurse(map, i + 1, j, memo);
            int down = recurse(map, i, j + 1, memo);
            int choice = Math.min(right, down);
            health = max(-map[i][j] + choice,1);
        }

        memo[i][j] = health;
        return health;

    }
}