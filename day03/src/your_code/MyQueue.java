package your_code;

import ADTs.QueueADT;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * An implementation of the Queue interface.
 */
public class MyQueue implements QueueADT<Integer> {
    LinkedList list;

    public MyQueue(){
        list = new LinkedList();
    }

    @Override
    public void enqueue(Integer item) {
        list.addLast(item);
    }

    @Override
    public Integer dequeue() {
        if(list.size() == 0){
            throw new NoSuchElementException("Stack is empyty");
        }
        int save = (int)list.getFirst();
        list.removeFirst();
        return save;
    }

    @Override
    public boolean isEmpty() {
       if(list.size()==0){
           return true;
       }
       return false;
    }

    @Override
    public Integer front() {
        if(list.size() == 0){
            throw new NoSuchElementException("Stack is empyty");
        }
        return (int)list.getFirst();
    }
}