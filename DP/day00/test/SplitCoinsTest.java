import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SplitCoinsTest {

    /**
     * Test method for {@link SplitCoins#splitCoins(int[])}.
     */
    @Test
    public void testSplitCoins() throws FileNotFoundException {
        for(int i = 1; i <= 12; i++){
            Scanner input = new Scanner(new File(
                    "testcases/divgold." + i + ".in"));
            Scanner output = new Scanner(new File(
                    "testcases/divgold." + i + ".out"));

            int n = input.nextInt();
            int[] coins = new int[n];
            for(int j = 0; j < n; j++){
                coins[j] = input.nextInt();
            }

            assertEquals(output.nextInt(),new SplitCoins().splitCoins(coins));

            input.close();
            output.close();
        }
    }
}
