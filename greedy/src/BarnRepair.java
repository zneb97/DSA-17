import com.sun.org.apache.xpath.internal.SourceTree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class BarnRepair {
    public static int solve(int M, int S, int C, int[] occupied) {
        PriorityQueue<Integer> gaps = new PriorityQueue<>();
        Arrays.sort(occupied);

        int continous = 0;

        //Get gap sizes
        for (int i = 0; i < occupied.length-1; i++) {
            //Gap start
            if((occupied[i+1] - occupied[i]) > 1 ){
                int gapSize = occupied[i+1] - occupied[i]-1;
                gaps.add(occupied[i+1] - occupied[i]-1);

            }
            else{
                continous++;
            }
        }

        int boardsUsed = C - continous;
        int blocked = C;

        //Fill smallest gaps
        while((boardsUsed > M) && (!gaps.isEmpty())){
            blocked += gaps.poll();
            boardsUsed--;
        }

        System.out.println(blocked);
        return blocked;

    }
}
