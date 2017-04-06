import java.util.*;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n; //Number of moves
    public int[][] tiles;
    private int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    public HashMap<Integer, int[]> Positions;

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        n = 0;
        tiles = b;
        Positions = new HashMap<Integer, int[]>();
        for(int i = 0; i < goal.length; i++){
            for(int j = 0; j < goal[0].length; j++) {
//                System.out.print(i);
//                System.out.println(j);
//                System.out.println(goal[i][j]);
                Positions.put(goal[i][j], new int[]{i,j});
            }
//            System.out.println("  ");
        }
    }

    /*
     * Size of the board (equal to 3 for 8 puzzle, but in theory the Board
     * class should  work for any puzzle size)
     */
    private int size() {
        return tiles.length;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     * Estimated cost from the current node to the goal for A* (h(n))
     */
    public int manhattan() {
        int total = 0;
		for(int i = 0; i < tiles.length; i++){
		    for(int j = 0; j < tiles[0].length; j++) {
		        int[] ideal = Positions.get(tiles[i][j]);
                if(tiles[i][j] != 0) total += Math.abs(ideal[0]- i) +  Math.abs(ideal[1]- j);
            }
        }
        return total;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        return (manhattan()==0);
    }

    /*
     * Returns true if the board is solvable
     */
    public boolean solvable() {
        int[] invertArray = new int[tiles.length * tiles.length];
        int k = 0;
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[0].length; j++) {
                invertArray[k] = tiles[i][j];
                k ++;
            }
        }
        int numInvert = 0;
        for(int j = 1; j < invertArray.length; j ++){
            if(invertArray[j]< invertArray[j-1]){
                numInvert++;
            }
        }

        if(numInvert % 2 == 1){
            return true;
        }
        return false;
    }

    /*
     * Return the neighboring boards in the state tree
     * One possible method: Create a duplicate board state, try moving the
     * blank space up, down, left, and right, and if the resulting board state
     * is valid, add it to an accumulator.
     */
    public Iterable<Board> neighbors() {
        ArrayList<Board> answer = new ArrayList<Board>();
        int[] pos = new int[2];
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[0].length; j++) {
                if(tiles[i][j] == 0) {
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        for(int i = 0; i < 2; i++){
            if(pos[i] > 0){
                int[] move = pos.clone();
                move[i] -= 1;
                answer.add(new Board(examineSwitch(move, pos)));

            }
            if(pos[i] < tiles.length-1){
                int[] move = pos.clone();
                move[i] += 1;
                answer.add(new Board(examineSwitch(move, pos)));
            }
        }
        return answer;
    }


    public int[][] examineSwitch(int[] move, int[] zero){
        int[][] newTiles = new int[tiles.length][];
        for(int i = 0; i < tiles.length; i++)
            newTiles[i] = tiles[i].clone();
        newTiles[zero[0]][zero[1]] = tiles[move[0]][move[1]];
        newTiles[move[0]][move[1]] = tiles[zero[0]][zero[1]];
        return newTiles;
    }

    /*
     * Prints out the board state nicely for debugging purposes
     */
    public void printBoard() {
        for (int[] tile : tiles) {
            for (int aTile : tile) System.out.print(aTile + " ");
            System.out.println();
        }
        System.out.println();
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        board.printBoard();
        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
        for (Board b : it)
            b.printBoard();
    }
}
