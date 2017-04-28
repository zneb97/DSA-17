import java.util.ArrayList;
import java.util.Collections;

public class ReviewProblem {

    public static int minimumLengthSubArray(int[] A, int desiredSum) {
        int left=0;
        int right=0;
        int sum=0;
        int length = Integer.MAX_VALUE;

        while(right<A.length){
            if(sum<desiredSum){
                sum += A[right];
                right++;
            }
            else{
                if(right-left < length){
                    length = right-left;
                }
                sum -=A[left];
                left++;
            }
        }

        while(sum>=desiredSum){
            if(right-left < length){
                length = right-left;
            }

            sum -=A[left++];
        }

        return length;
    }




}
