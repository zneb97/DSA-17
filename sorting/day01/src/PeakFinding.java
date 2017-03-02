import java.util.NoSuchElementException;

public class PeakFinding {

    // Return -1 is left is higher, 1 if right is higher, 0 if peak
    private static int peak(int i, int[] nums) {
        if (i>0 && nums[i] < nums[i-1])
            return -1;
        if (i<nums.length-1 && nums[i] < nums[i+1])
            return 1;
        return 0;
    }

    // Return -1 is left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x>0 && nums[y][x] < nums[y][x-1])
            return -1;
        if (x<nums[0].length-1 && nums[y][x] < nums[y][x+1])
            return 1;
        return 0;
    }

    // Return -1 is up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y>0 && nums[y][x] < nums[y-1][x])
            return -1;
        if (y<nums.length-1 && nums[y][x] < nums[y+1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }
    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums){
        if(nums.length == 0){
            throw new NoSuchElementException();
        }
        if(nums.length == 1){
            return -1;
        }
        if(nums[0]>nums[1]){
            return 0;
        }
        if(nums[nums.length-1] > nums[nums.length-2]){
            return nums.length-1;
        }
        if(peak(nums.length/2,nums) == 0){
            return nums.length/2;
        }
        else{
            int[]left = breakArray(nums,0,nums.length/2);
            int[] right = breakArray(nums,0,nums.length/2);
            int leftPeak = findOneDPeak(left);
            int rightPeak = findOneDPeak(right);
            if(leftPeak == -1){
                return rightPeak;
            }
            else{
                return leftPeak;
            }
        }
    }

    public static int[] breakArray(int[] nums, int start, int end){
        int[] result = new int[end-start+1];
        System.arraycopy(nums,start,result,0,result.length);
        return result;
    }

    public static int[] findTwoDPeak(int[][] nums){
//        //Get start values
////        int[] result = {0,0};
////        int xPos = nums[0].length/2;
////        int yPos= nums.length/2;
////        int half = nums.length/2;
////        int horizontal = peakX(xPos, yPos, nums);
////        int vertical = peakY(xPos, yPos, nums);
//
//        if(horizontal == 0 && vertical == 0){
//            result[0] = xPos;
//            result[1]= yPos;
//            return result;
//        }
//
//        while((horizontal != 0) || (vertical != 0)) {
//            //Reach end of one side of array
//            if(half/2 > 1){
//                half = half/2;
//            }
//            else{
//                half = 1;
//            }
//            //Update
//            //Move in direction of highest number
//            xPos += horizontal * half;
//            yPos += vertical * half;
//            horizontal = peakX(xPos, yPos, nums);
//            vertical = peakY(xPos, yPos, nums);
//        }
//
//        result[0] = xPos;
//        result[1] = yPos;
//        return result;
        //////////////////////
        int lowerX = 0;
        int upperX = nums[0].length;
        int lowerY = 0;
        int upperY = nums.length;

        while(upperY > lowerY && upperX > lowerX) {

                //Find column max
                int xPos = (upperX + lowerX)/2;
                int maxY = maxYIndex(xPos, lowerY, upperY, nums);
                int horizontal = peakX(xPos, maxY, nums);
                if (horizontal == 0) {
                    int[] peak = {maxY, xPos};
                    return peak;
                }
                //Decide which way to look
                else if (horizontal == -1) {
                    upperX = xPos;
                } else {
                   lowerX = xPos+1;
                }


                //Find row max
                int yPos = (upperY + lowerY)/2;
                int maxX = maxXIndex(yPos, lowerX, upperX, nums);
                int vertical = peakY(maxX, yPos, nums);
                if (vertical == 0) {
                    int[] peak= {yPos,maxX};
                    return peak;
                }
                //Decide which way to look
                else if (vertical == -1) {
                    upperY = yPos;
                } else {
                    lowerY = yPos+1;
                }
            }

        return null;
    }

}

