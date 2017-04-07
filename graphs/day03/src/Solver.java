/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;
import java.util.Arrays.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;
    private State root;


    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State implements Comparable<State>{
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves;
        public int cost;
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            cost = this.moves + this.board.manhattan();
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }

        /*
         * Return the cost difference between two states
         */
        @Override
        public int compareTo(State s) {
            return this.cost - s.cost;
        }

    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        while(state.prev != null){
            state = state.prev;
        }
        return state;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        //Check if even worth trying
        root = new State(initial, 0, null);
        if(initial.solvable()) {
            //Setup
            PriorityQueue<State> open = new PriorityQueue<>();
            Set<String> visited = new HashSet<>();

            open.add(root);

            while (!open.isEmpty()) {
                State current = open.poll();
                visited.add(convertBoard(current.board));
                for (Board neighbor : current.board.neighbors()) {
                    State next = new State(neighbor, current.moves + 1, current);

                    boolean skip = false;
                    //Done
                    if (neighbor.isGoal()) {
                        if(minMoves == -1 || current.moves < minMoves) {
                            skip = true;
                            solutionState = next;
                            minMoves = solutionState.moves;
                            open.remove(solutionState);

                            State cs = solutionState;
                            int i = 0;
//                            while(cs.prev != null){
//                                System.out.println(i);
//                                cs.board.printBoard();
//                                cs = cs.prev;
//                                i++;
//                            }
                            open.clear();
                            break;
                        }

                    }

                    //Check significance in path
                    for (State state : open) {
                        if (state.board.equals(next.board)) {
                            if(next.cost >= state.cost){
                                skip = true;
                            }else{
                                open.remove(state);
                            }
                        }
                    }
//                    for (String hash : visited) {
//                        if (hash.equals(next.board)) {
//                            skip = true;
//                        }
//                    }
                    if(visited.contains(convertBoard(next.board))) skip = true;

                    if (!skip) {
                        open.add(next);
                    }
                } //End of current state neighbors

            } //No more states
            solved = true;
        }
    }

    /*
     * Is the input board a solvable state
     */
    public boolean isSolvable() {
    	return root.board.solvable();
    }

    private int findMin(ArrayList<State> states){
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < states.size(); i++) {
            if(states.get(i).cost < min){
                min = states.get(i).cost;
                index = i;
            }
        }
        return min;
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        List<Board> solution = new ArrayList<>();
        while (solutionState.prev != null) {
            solution.add(0, solutionState.board);
            solutionState = solutionState.prev;
        }
        return solution;
    }

    /*
    * Converts board tiles to easily hashable string
     */
    public static String convertBoard(Board b){
        String hash = "";
        for (int i = 0; i < b.tiles.length; i++) {
            for (int j = 0; j < b.tiles[0].length; j++) {
                hash += Integer.toString(b.tiles[i][j]);
            }
        }
        return hash;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space: Your solution can have whatever output you find useful
     * Optional challenge: create a command line interaction for users to input game
     * states
     */
    public static void main(String[] args) {
        PriorityQueue<Integer> test = new PriorityQueue<>(Collections.reverseOrder());
        test.add(3);
        test.add(4);
        test.add(1);
        test.add(8);
        test.add(3);
        test.add(1);
        for (int i = 0; i < 5; i++) {
            System.out.println(test.poll());
        }

//        int[][] initState = {{8, 6, 7}, {2, 5, 4}, {3, 0, 1}};
//        int[][] initState2 = {{8, 6, 7}, {2, 5, 4}, {3, 0, 1}};
//        Board initial = new Board(initState);
//
//        String hash = "";
//        for (int i = 0; i < initState.length; i++) {
//            for (int j = 0; j < initState[0].length; j++) {
//                hash += Integer.toString(initState[i][j]);
//            }
//        }
//        System.out.println(hash);

//        // Solve the puzzle
//        Solver solver = new Solver(initial);
//        if (!solver.isSolvable())
//            System.out.println("No solution");
//        else {
//            for (Board board : solver.solution()) {
//                board.printBoard();
//            }
//            System.out.println(solver.minMoves);
//        }
    }


}
