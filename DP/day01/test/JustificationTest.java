import org.junit.Test;
import org.junit.Before;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class JustificationTest {

    @Test
    public void testJustifyText1() {
        List<Integer> soln = Arrays.asList(0, 3, 6);
        int m = 16;
        String[] w = {"This", "is", "an", "example", "of", "text", "justification"};
        assertThat(TextJustification.justifyText(w, m), is(soln));
    }

    @Test
    public void testJustifyText2() {
        List<Integer> soln = Arrays.asList(0, 2, 4);
        int m = 15;
        String[] w = {"blah", "blah", "blah", "blah", "reallylongword"};
        assertThat(TextJustification.justifyText(w, m), is(soln));
    }


    @Test
    public void testJustifyText3() {
        List<Integer> soln = Arrays.asList(0, 2, 4, 5, 6, 7, 9);
        int m = 12;
        String[] w = {"hieu", "and", "sidd", "are", "really", "really", "really", "good", "at", "rocketleague"};
        assertThat(TextJustification.justifyText(w, m), is(soln));
    }


    @Test
    public void testJustifyText4() {
        List<Integer> soln = Arrays.asList(0, 3, 5, 7, 9, 10, 11, 14, 16, 18, 19, 21, 22, 24, 26, 28, 29);
        int m = 12;
        String[] w = {"ACT", "I", "SCENE", "I.", "Elsinore.", "A", "platform", "before", "the", "castle.", "FRANCISCO", "at", "his", "post.", "Enter", "to", "him", "BERNARDO", "BERNARDO", "Who's", "there?", "FRANCISCO", "Nay,", "answer", "me:", "stand,", "and", "unfold", "yourself.", "BERNARDO"};
        assertThat(TextJustification.justifyText(w, m), is(soln));
    }

    // TODO: add more test cases here
}
