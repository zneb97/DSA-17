# Introduction to Dynamic Programming

If you want a refresher on today's lecture, check out this [MIT lecture](https://www.youtube.com/watch?v=OQ5jsbhAv_M), which also goes over a brief introduction to dynamic programming. We have included the general DP template, but do not feel compelled to use if you would rather not.

## Bills

We want to make change for `N` dollars. GIven the denominations of all dollar bills, what is the least number of bills you need to make `N` dollars? For example, if you want to make 49 dollars using 1, 15, and 25 dollar bills, the answer is 7 (15, 15, 15, 1, 1, 1, 1).

## Making Profit

The Abrahams Merry Milk Makers company is interested in making money by investing in the milk stock exchange. You are given the price of the milk stock as a list of integers in an array. Each element in the array represents the price of the stock for that day. You must buy the stock exactly once and sell the stock (after buying it) exactly once. What is the maximum profit you can make?

You are given

* `prices`, an array of the prices of the stock per day.

You will be filling out the function `public int maxProfit(int[] prices)` in `Stocks.java` and run tests in `StocksTest.java`

For example, you are given the array of prices `[5,8,1,2,6,8,7]`. If you bought on day 0 (for 5 dollars) and sold on day 4 (for 6 dollars), you would make a profit of 1 dollar. The optimal solution here is to buy on day 2 and sell on day 5 for a profit of 7 dollars.

You can buy and then sell a stock on the same day, so your function should never return a negative number.

## Splitting coins

Katie has a stack of coins and would like to split them up as evenly as possible with David. She is given a set of `N` coins. Each of these coins has a given value. She must place each of these coins in one of two piles. Output the smallest possible difference between the total values of these two piles. 

You are given
* `coins`, an array of all the coins Katie must split

You will be filling out the function `public int splitCoins(int[] coins)` in `Coins.java` and run tests in `CoinsTest.java`

For example, if she had the coins `{3,1,1,2,5,7}`, she can try to split them up into `{3,5}` and `{1,1,2,7}` and the difference between these two piles would be `3`, (note that this is not the best possible splitting; the best answer is `1`).