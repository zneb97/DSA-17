package your_code;

import ADTs.StackADT;

import java.util.NoSuchElementException;

public class PsetProblems {

    public static int longestValidSubstring(String s) {
        int count = 0;
        int length = 0;
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            length++;
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')' && count > 0) {
                count--;
                if (length > max) {
                    max = length;
                }
            } else {
                length = 0;
            }
        }
        return max;
    }

    public static StackADT<Integer> sortStackLimitedMemory(StackADT<Integer> s) {
        StackADT<Integer> temp = new MyStack();
        int save;

        while (!s.isEmpty()) {
            //Move current element of s to temp
            save = s.peek();
            s.pop();
            //Rebuild s from bottom up using value stored in temp
            while (!temp.isEmpty() && save < temp.peek()) {
                s.push(temp.peek());
                temp.pop();
            }
            temp.push(save);
        }

        //Repopulate s with in order temp
        while (!temp.isEmpty()) {
            s.push(temp.peek());
            temp.pop();
        }

        return s;

    }
}
