/*
 * CS501 Projects-7.58
 * Antonio Zea Jr.
 * 03092020
 *
 * Develop an experiment, using techniques similar to those in Section 4.1, to
 * test the efficiency of n successive calls to the add method of an ArrayList,
 * for various n, under each of the following three scenarios: a. Each add takes
 * place at index 0. b. Each add takes place at index size()/2. c. Each add
 * takes place at index size(). Analyze your empirical results.
 */
import java.util.Arrays;

public class ArrayListTest<E> implements List<E>{
    private static final int CAPACITY = 16;
    private E[] data;
    private int size = 0;

    /*
     * Default constructor
     */
    public ArrayListTest(){
        this(CAPACITY);
    }
    /*
     * Constructor makes an ArrayList of user defined size.
     */
    public ArrayListTest(int capacity){
        data = (E[]) new Object[capacity];
    }
    /*
     * Returns the current size of the ArrayList.
     */
    public int size(){
        return size;
    }
    /*
     * Returns true if the ArrayList is empty.
     */
    public boolean isEmpty(){
        return size == 0;
    }
    /*
     * Returns the element located at index i.  If index i is not within the
     * size of the ArrayList then IndexOutOfBoundsException is thrown.
     */
    public E get(int i) throws IndexOutOfBoundsException{
        checkIndex(i,size);
        return data[i];
    }
    /*
     * Changes the element located at index i to the element e.  Returns the
     * previous value located at i. If index i is not within the size of the
     * ArrayList then IndexOutOfBoundsException is thrown.
     */
    public E set(int i, E e) throws IndexOutOfBoundsException{
        checkIndex(i,size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }
    /*
     * Adds element e to ArrayList at index i. Shifts all indeces starting at i
     * until the end of the ArrayList up(right) by 1. If ArrayList has reached
     * capacity then the size is doubled using resize().
     */
    public void add(int i, E e) throws IndexOutOfBoundsException{
        checkIndex(i, size + 1);
        if(size == data.length){
            resize(2*data.length);
            /*throw new IllegalStateException("Array is full.");*/
        }
        for(int k = size - 1; k >= i; k--){
            data[k+1] = data[k];
        }
        data[i] = e;
        size++;
    }
    /*
     *  Removes element located at index i.  Returns the element removed. Shifts
     *  the array down(left) by 1 for values after index i.
     */
    public E remove(int i) throws IndexOutOfBoundsException{
        checkIndex(i, size);
        E temp = data[i];
        for(int k = i; k < size - 1; k++){
            data[k] = data[k+1];
        }
        data[size-1] = null;
        size--;
        return temp;
    }
    /*
     * Helper method.  Checks if the index is valid or not.
     */
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException{
        if(i < 0 || i >=n){
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    }
    /*
     * Helper method.  Resizes the ArrayList by creating an array with the
     * desired size.  Values are copied to this new array, then data is set to
     * point to this new array.
     */
    protected void resize(int capacity){
        E[] temp = (E[]) new Object[capacity];
        for(int i = 0; i < size; i++){
            temp[i] = data[i];
        }
        data = temp;
    }

    public String toString(){
        return Arrays.toString(data);
    }

    public static void main(String[] args){
        int size = 100000;
        long startTime, endTime, elapsed;

        for(int i = 10; i <= size; i=i*10){
            ArrayListTest<Integer> arrList = new ArrayListTest<>(i);
            /*
             * Using nanoTime() generally lead to inconsistencies, especially
             * the first experiment(i.e. N=10) of each set.
             */
            startTime = System.nanoTime();

            for(int j = 0 ; j < i; j++){
                /*
                 * This tests adds an element at the end of the ArrayList.
                 */
                arrList.add(arrList.size(),j);
                /*
                 * This adds an element to the middle of the ArrayList.
                 * arrList.add(arrList.size()/2,j);
                 */
                /*
                 * This adds an element to the front of the ArrayList.
                 * arrList.add(0,j);
                 */
            }
            endTime = System.nanoTime();

            elapsed = endTime - startTime;

            System.out.println("N: " + i + "\tNanoSec: " + elapsed);
        }


    }
}
