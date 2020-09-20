/*
 *  CS501 Projects-6.38
 *  Antonio Zea Jr.
 *  02252020
 *
 *  The introduction of Section 6.1 notes that stacks are often used to provide
 *  "undo" support in applications like a Web browser or text editor. While
 *  support for undo can be implemented with an unbounded stack, many
 *  applications provide only limited support for such an undo history, with a
 *  fixed-capacity stack. When push is invoked with the stack at full capacity,
 *  rather than throwing an exception, a more typical semantic is to accept the
 *  pushed element at the top while "leaking" the oldest element from the bottom
 *  of the stack to make room. Give an implementation of such a LeakyStack
 *  abstraction, using a circular array.
 */
import java.util.Arrays;

public class LeakyStack<E>{
    /*
     * Default size of the LeakyStack(ie the data array)
     */
    private static final int CAPACITY = 10;
    private E[] data;
    /*
     * t is the index of top of the stack. sz is size of the stack.
     */
    private int t = -1;
    private int sz = 0;

    public LeakyStack(){
        this(CAPACITY);
    }

    public LeakyStack(int capacity){
        data = (E[]) new Object[capacity];
    }

    /*
     * This method returns the current size of the stack.
     */
    public int size(){
        return sz;
    }

    /*
     * This method returns true if the stack is empty and false otherwise.
     */
    public boolean isEmpty(){
        return sz == 0;
    }

    /*
     * This method pushes new elements to the top of the stack.
     */
    public void push(E e){
        /*
         * The way I increment t makes this array behave as a cirular array.  Even
         * if the stack is full, the push command should run, overwriting the
         * element at the bottom of the stack.  This gives this class it's "leaky"
         * property.
         */
        t=(t+1)%data.length;
        data[t] = e;
        sz++;
    }

    /*
     * This method removes and returns elements at the top of the stack.
     */
    public E pop(){
        if(sz == 0){
            return null;
        }
        E answer = data[t];
        data[t] = null;

        /*
         * I was trying to get this to work with % operator until I did some
         * research to find that the operator is really the remainder operator
         * which is not the same as the modulus arithmetic I expected.
         *
         * I finally found a solution.  This one is the implementation suggested
         * by the textbook.
         */
        t=(t-1+data.length)%data.length;
        /*
         * My original solution below.  I think my soution will take up less
         * resources though.
         */
        /*
        if(t < 0){
            t = t + data.length;
        }*/
        sz--;
        return answer;
    }

    /*
     * This method returns the element at the top of the stack without removing
     * it.
     */
    public E top(){
        if(sz == 0){
            return null;
        }
        return data[t];
    }

    public String toString(){
        return Arrays.toString(data);
    }

    public static void main(String[] args){
        LeakyStack<Integer> Lstack = new LeakyStack<>();

        System.out.println(Lstack);

        for(int i = 0; i < 12; i++){
            Lstack.push(i*3+2);
            System.out.print(Lstack);
            System.out.println("\tTop: " + Lstack.top());

        }
        Lstack.pop();
        Lstack.pop();
        System.out.print(Lstack);
        System.out.println("\tTop: " + Lstack.top());
        Lstack.pop();
        Lstack.pop();
        System.out.print(Lstack);
        System.out.println("\tTop: " + Lstack.top());
    }
}
