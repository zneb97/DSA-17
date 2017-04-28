package your_code;

import ADTs.StackADT;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {
    ArrayList<Integer> list;
    ArrayList<Integer> maxed;
    int size;

    public MyStack(){
        size = 0;
        list = new ArrayList<>();
        maxed = new ArrayList<>();
        maxed.add((int)Double.NEGATIVE_INFINITY);
    }

    @Override
    public void push(Integer e) {
        list.add(e);
        size++;
        if(e > maxed.get(maxed.size()-1)){
            maxed.add(e);
        }
    }

    @Override
    public Integer pop() {
        if(size == 0){
            throw new NoSuchElementException("Stack is empyty");
        }
        int save = list.get(size-1);
        if(save == maxed.get(maxed.size()-1)){
            maxed.remove(maxed.size()-1);
        }
        list.remove(size-1);
        size--;
        return save;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public Integer peek() {
        if(size == 0){
            throw new NoSuchElementException("Stack is empyty");
        }
        return list.get(size-1);
    }

    public Integer maxElement() {
        return maxed.get(maxed.size()-1);
    }
}
