public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        long badVersion = helper(0, n, isBadVersion);
        return badVersion;
    }

    public static long  helper(long start, long end, IsFailingVersion isBadVersion){

        long badVersion;

        //List gets too short base case
        if(end - start < 0){
            if(isBadVersion.isFailingVersion(start)){
                return start;
            }
            else{
                return end;
            }
        }

        long halve = (start+end)/2;
        //Chose a side
        if(isBadVersion.isFailingVersion(halve)){
            if(isBadVersion.isFailingVersion(halve-1)){
                badVersion = helper(start, halve-1,isBadVersion);
            }
            else{
                return halve;
            }
        }
        else{
            badVersion = helper(halve+1, end, isBadVersion);
        }
        return badVersion;
    }
}
