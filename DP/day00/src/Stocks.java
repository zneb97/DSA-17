public class Stocks {


    public static int maxProfit(int[] prices) {
        //Bare Minimum
        int difference = 0;
        int profit = 0;

        for (int i = 1; i < prices.length; ++i) {
            difference += prices[i] - prices[i-1];
            //Update
            profit = Math.max(profit, difference);
            difference = Math.max(0, difference);
        }

        return profit;
    }
}