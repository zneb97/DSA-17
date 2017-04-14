import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class JustificationTest {

    /**
     * Test method for int[] justifyText(String[] w, int m).
     */
    @Test
    public void testJustifyText() {
        int[] soln = {0, 3, 6};
        int m = 16;
        String[] w = {"This", "is", "an", "example", "of", "text", "justification"};
        assertThat(TextJustification.justifyText(w, m), is(soln));
    }

    // TODO: add more test cases here
}
