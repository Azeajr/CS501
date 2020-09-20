/*
 * Give an implementation of the stack ADT using an array list for storage.
 */
import java.util.Arrays;

public class ArrayListStack<E> implements Stack<E>{
    private class ArrayList<E> implements List<E>{
        private static final int CAPACITY = 16;
        private E[] data;
        private int size = 0;

        public ArrayList(){
            this(CAPACITY);
        }

        public ArrayList(int capacity){
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
    }

    ArrayList<E> arrList;
    private static final int CAPACITY = 16;


    public ArrayListStack(){
        this(CAPACITY);
    }

    public ArrayListStack(int capacity){
        arrList = new ArrayList<>(capacity);
    }

    public int size(){
        return arrList.size();
    }

    public boolean isEmpty(){
        return arrList.isEmpty();
    }

    public void push(E e){
        arrList.add(arrList.size(),e);
    }

    public E top(){
        return arrList.get(arrList.size() - 1);
    }

    public E pop(){
        return arrList.remove(arrList.size() - 1);
    }

    public String toString(){
        return arrList.toString();
    }

    public static void main(String[] args){
        ArrayListStack<Integer> arrListStack = new ArrayListStack<>(4);

        System.out.println(arrListStack);

        for(int i = 0; i < 15; i++){
            arrListStack.push(i*2+12);
            System.out.println(arrListStack);
        }

    }
}
