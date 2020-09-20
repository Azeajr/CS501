/*
 * CS501 Projects-6.37
 * Antonio Zea Jr.
 * 02122020
 *
 * Design an ADT for a two-color, double-stack ADT that consists of two stacks
 * -- one for "red" and one for "blue" -- and has as its operations color-coded
 * versions of the regular stack ADT opertations. For example, this ADT should
 * support both a redPush operation and a bluePush operation. Give an efficient
 * implementation of this ADT using a single array whose capacity is set at some
 * value of N that is assumed to always be larger than the sizes of the red and
 * blue stacks combined.
 */

import java.util.Arrays;

public class DoubleStackADT<E>{
    public static final int CAPACITY = 1000;

    private E[] data;
    /*
     * blueIndex can be set immediatly because it will always start at -1
     * regardless of the size of the array.  redIndex can not be set as we do
     * not yet know the size of the array that will be used.
     */
    private int blueIndex = -1;
    private int redIndex;
    /*
     * Default constructor sets the size of the array to the CAPACITY variable.
     */
    public DoubleStackADT(){
        this(CAPACITY);
    }

    public DoubleStackADT(int capacity){
        data = (E[])new Object[capacity];
        /*
         * redIndex is set to capacity because that particular stack is working
         * it's way from the end towards the front of the array.  This can not
         * be done till a DoubleStackADT is instantiated because of its
         * dependency on the size of the array.
         */
        redIndex = capacity;
    }
    /*
     * Since blueIndex starts at -1 when the stack is empty, 1 is added to
     * correctly report the size.
     */
    public int blueSize(){
        return blueIndex + 1;
    }
    /*
     * If the red stack is empty then redIndex=data.length which makes this
     * method correctly return 0.
     */
    public int redSize(){
        return data.length - redIndex;
    }
    /*
     * This method returns true or false depending on whether the blue stack is
     * empty or not
     */
    public boolean blueIsEmpty(){
        return blueIndex == -1;
    }
    /*
     * This method returns true or false depending on whether the red stack is
     * empty or not
     */
    public boolean redIsEmpty(){
        return redIndex == data.length;
    }
    /*
     * This method adds element e to the blue stack.  Throws
     * IllegalStateException error if the there is no space left in the array.
     */
    public void bluePush(E e){
        /*
         * If the sizes of the two stacks combined is bigger than the length of
         * the array then an error is thrown.
         */
        if((blueSize() + redSize()) == data.length){
            throw new IllegalStateException("Stack is full.");
        }
        /*
         * Since blueIndex is keeping track of the current top, it needs to be
         * incremented first, then used as an index for the element about to be
         * pushed.
         */
        data[++blueIndex] = e;
    }
    /*
     * This method adds element e to the red stack.  Throws
     * IllegalStateException error if the there is no space left in the array.
     */
    public void redPush(E e){
        if((blueSize() + redSize()) == data.length){
            throw new IllegalStateException("Stack is full.");
        }
        /*
         * This is basically the inverse of bluePush because the red stack is
         * building from the opposite end of the array
         */
        data[--redIndex] = e;
    }
    /*
     * This method returns the element at the top of the blue stack without
     * removing it.
     */
    public E blueTop(){
        if(blueIsEmpty()){
            return null;
        }
        return data[blueIndex];
    }
    /*
     * This method returns the element at the top of the red stack without
     * removing it.
     */
    public E redTop(){
        if(redIsEmpty()){
            return null;
        }
        return data[redIndex];
    }
    /*
     * Removes the top element of the blue stack. Element is first saved, the
     * value at that location in the array is set to null, and blueIndex is
     * decremented.
     */
    public E bluePop(){
        if(blueIsEmpty()){
            return null;
        }
        E answer = data[blueIndex];
        data[blueIndex] = null;
        blueIndex--;
        return answer;
    }
    /*
     * Removes the "top" element of the red stack. Element is first saved, the
     * value at that location in the array is set to null, and redIndex is
     * incremented(red stack works in the reverse of the blue stack).
     */
    public E redPop(){
        if(redIsEmpty()){
            return null;
        }
        E answer = data[redIndex];
        data[redIndex] = null;
        redIndex++;
        return answer;
    }

    public String toString(){
        return Arrays.toString(data);
    }

    public static void main(String[] args){
        DoubleStackADT<Integer> S = new DoubleStackADT<>(10);
        S.redPush(5);
        S.redPush(3);
        S.redPush(11);

        S.bluePush(1);
        S.bluePush(10);
        System.out.println(S);

        System.out.println("Blue top: " + S.blueTop() + "\tRed top: " + S.redTop());

        S.redPop();
        System.out.println(S);

        for(int i = 1; i < 7; i++){
            S.redPush(i);
        }

        System.out.println(S);
        /*
         * Meant to test how the full stack will react to an extra element.
         */
        S.bluePush(22);
    }
}
