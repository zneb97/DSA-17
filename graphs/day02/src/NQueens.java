import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NQueens {

    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    public static List<char[][]> nQueensSolutions(int n) {
        ArrayList solutions = new ArrayList();
        char[][] board = setBoard(n);
        List<char[][]> result = setQueens(n, board, solutions, 0, 0);
        return result;
    }

    public static List<char[][]> setQueens(int n, char[][] board, List<char[][]> allBoards, int row, int col) {
        //Base
        if (n == 0) {
            char[][] save = copyOf(board);
            allBoards.add(save);
            return allBoards;
        }
        
        //Backtrack
        for (int i = row; i < board.length; i++) {
            for (int j = col; j < board.length; j++) {
                if (safe(board, i, j)) {
                    board[i][j] = 'Q';
                    setQueens(n-1, board, allBoards, i, j);
                    board[i][j] = '.';
                }
                //Reset
                col = 0;
            }
        }
        return allBoards;
    }

    public static Boolean safe(char[][] board, int row, int col) {
        //Check Straights
        //Check Columns
        for (char[] r: board) {
            char colItem = r[col];
            if (colItem == 'Q') {
                return false;
            }
        }
        //Check Rows
        for (char rowItem: board[row]) {
            if (rowItem == 'Q') {
                return false;
            }
        }

        //Check Diagonals

        //Diag 1
        int rCount = row;
        int cCount = col;
        for (int i = row; i > 0; i--) {
            if(cCount <= 0){
                break;
            }
            cCount--; 
            rCount--;
        }
        for (int j = rCount; j < board.length; j++) {
            if(cCount >= board.length){
                break;
            }
            if (board[rCount][cCount] == 'Q') {
                return false;
            }
            cCount++;
            rCount++;
        }

        //Diag 2
        int rowCount = row;
        int colCount = col;
        for (int i = row; i > 0; i--) {
            if(colCount >= board.length - 1){
                break;
            }
            colCount++;
            rowCount--;
        }

        for (int j = rowCount; j < board.length; j++) {
            if(colCount <= 0){
                break;
            }
            if (board[rowCount][colCount] == 'Q') {
                return false;
            }
            colCount--;
            rowCount++;
        }

        return true;
    }

    private static char[][] setBoard(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        return board;
    }
}