import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class DungeonTest {

    @Test
    public void testMinInitialHealth1() {
        int[][] map = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        assertThat(DungeonGame.minInitialHealth(map), is(7));
    }

    @Test
    public void testMinInitialHealth2() {
        int[][] map = {{-2,-3,-4,-1,-3},{3,0,-4,0,0},{0,1,-4,-1,0},{-5,4,1,-4,-1},{2,-5,-3,-5,-4}};
        assertThat(DungeonGame.minInitialHealth(map), is(3));
    }

    @Test
    public void testMinInitialHealth3() {
        int[][] map = {{-8,-15,-9,5,-6},{-10,-13,-4,-1,-4},{-14,4,-5,-9,-3},{-7,-10,-7,-12,-3},{-2,1,-3,5,-9}};
        assertThat(DungeonGame.minInitialHealth(map), is(44));
    }
    
    // TODO: Add more test cases here

}
