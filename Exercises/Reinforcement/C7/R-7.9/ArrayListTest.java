/*
 * The add method for a dynamic array, as described in Code Fragment 7.5, has
 * the following inefficiency. In the case when a resize occurs, the resize
 * operation takes time to copy all the elements from the old array to a new
 * array, and then the subsequent loop in the body of add shifts some of them
 * to make room for a new element. Give an improved implementation of the add
 * method, so that, in the case of a resize, the elements are copied into their
 * final place in the new array (that is, no shifting is done).

 */

import java.util.Arrays;

public class ArrayListTest<E> implements List<E>{
    private static final int CAPACITY = 16;
    private E[] data;
    private int size = 0;

    public ArrayListTest(){
        this(CAPACITY);
    }

    public ArrayListTest(int capacity){
        data = (E[]) new Object[capacity];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E get(int i) throws IndexOutOfBoundsException{
        checkIndex(i,size);
        return data[i];
    }

    public E set(int i, E e) throws IndexOutOfBoundsException{
        checkIndex(i,size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }

    public void add(int i, E e) throws IndexOutOfBoundsException{
        /*checkIndex(i, size + 1);*/
        if(size == data.length){
            resize(i, e, 2*data.length);
            size++;
            /*throw new IllegalStateException("Array is full.");*/
        }else{
            for(int k = size - 1; k >= i; k--){
                data[k+1] = data[k];
            }
            data[i] = e;
            size++;
        }

    }

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

    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException{
        if(i < 0 || i >=n){
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    }

    protected void resize(int i, E e, int capacity){
        E[] temp = (E[]) new Object[capacity];


        for(int j = 0; j < i; j++){
            temp[j] = data[j];
        }
        temp[i] = e;
        for(int j = i+1; j < data.length; j++){
            temp[j] = data[j];
        }
        data = temp;
    }

    public String toString(){
        return Arrays.toString(data);
    }

    public static void main(String[] args){
        ArrayListTest<Integer> arrList = new ArrayListTest<>(5);
        for(int j = 0 ; j < 5; j++){
            arrList.add(arrList.size(),j);
        }

        System.out.println(arrList);

        for(int j = 0 ; j < 10; j++){
            arrList.add(arrList.size(),j);
        }
        System.out.println(arrList);

    }
}
