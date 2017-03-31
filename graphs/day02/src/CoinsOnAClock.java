import java.util.ArrayList;
import java.util.List;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        //Keep track of covered
        int[] covered = new int[hoursInDay];
        for (int i = 0; i < covered.length; i++) {
            covered[i] = 0;
        }

        char[] clock = new char[hoursInDay];
        ArrayList<char[]> solutions = new ArrayList();
        return setCoins(pennies, nickels, dimes, 0, clock, covered, solutions);
    }

    private static List<char[]> setCoins(int pennies, int nickels, int dimes, int currentHour, char[] clock, int[] covered, ArrayList solutions){
        if((pennies == 0) && (nickels == 0) && (dimes == 0)){
            char[] finale = copyOf(clock);
            solutions.add(finale);
            return solutions;
        }

        else if ((clock[currentHour] == 'p') || (clock[currentHour] == 'n') || (clock[currentHour] == 'd')) {
            return solutions;
        }

        if(pennies > 0){
            int nextHour = addTime(1, currentHour, clock);
            if(valid(nextHour, covered, pennies, nickels, dimes)){
                covered[nextHour] = 1;
                clock[currentHour] = 'p';
                setCoins(pennies-1, nickels, dimes, nextHour, clock, covered, solutions);
                clock[currentHour] = ' ';
                covered[nextHour] = 0; //Reset
            }
        }

        if(nickels > 0){
            int nextHour = addTime(5, currentHour, clock);
            if(valid(nextHour, covered, pennies, nickels, dimes)){
                covered[nextHour] = 1;
                clock[currentHour] = 'n';
                setCoins(pennies, nickels-1, dimes, nextHour, clock, covered, solutions);
                clock[currentHour] = ' ';
                covered[nextHour] = 0; //Reset
            }
        }

        if(dimes > 0){
            int nextHour = addTime(10, currentHour, clock);
            if(valid(nextHour, covered, pennies, nickels, dimes)){
                covered[nextHour] = 1;
                clock[currentHour] = 'd';
                setCoins(pennies, nickels, dimes-1, nextHour, clock, covered, solutions);
                clock[currentHour] = ' '; //Reset
                covered[nextHour] = 0; //Reset
            }
        }

        return solutions;
    }


    //Add choosen coin to get new hour
    private static int addTime(int value, int currentHour, char[] clock){
        int nextHour = currentHour + value;
        if(nextHour >= clock.length){
            nextHour = nextHour % clock.length;
        }
        return nextHour;
    }

    private static char[] copyOf(char[] A) {
        char[] B = new char[A.length];
        for (int i = 0; i < A.length; i++)
            B[i] = A[i];
        return B;
    }

    //Checks to see no duplicate coins
    private static Boolean valid(int nextHour, int[] covered, int pennies, int nickels, int dimes){
        if(covered[nextHour] == 1){}

        return true;
    }
}
