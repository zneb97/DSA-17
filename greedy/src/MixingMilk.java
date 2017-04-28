import java.util.ArrayList;

public class MixingMilk {
    public static int solve(int M, int N, int[] price, int[] units) {
        int cost = 0;
        int unitsPurchased = 0;
        int cheapestIndex = -1;
        int cheaptestCost = Integer.MAX_VALUE;
        ArrayList<Integer> visited = new ArrayList<>();

        while(unitsPurchased < M) {

            //Find next cheapest
            for (int i = 0; i < price.length; i++) {
                //Check if seen
                if (!visited.contains(i)) {
                    //Find cheapest of nonvisted
                    if (price[i] < cheaptestCost){
                        cheaptestCost = price[i];
                        cheapestIndex = i;
                    }
                }
            }

            visited.add(cheapestIndex);
            cheaptestCost = Integer.MAX_VALUE; //Reset for next run through

            //Add units
            int tempUnits = 0;
            while((tempUnits < units[cheapestIndex]) && (unitsPurchased < M)){
                tempUnits++;
                unitsPurchased++;
                cost += price[cheapestIndex];
            }

        }

        return cost;
    }
}
