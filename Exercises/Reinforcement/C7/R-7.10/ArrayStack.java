/*
 * Reimplement the ArrayStack class, from Section 6.1.2, using dynamic arrays to support unlimited capacity.
 */


public class ArrayStack<E> implements Stack<E>{
    public static final int CAPACITY = 1000;
    private E[] data;
    private int t = -1;

    public ArrayStack(){
        this(CAPACITY);
    }

    public ArrayStack(int capacity){
        data = (E[]) new Object[capacity];
    }

    public int size(){
        return t + 1;
    }

    public boolean isEmpty(){
        return t == -1;
    }

    public void push(E e) throws IllegalStateException{
        if(size() == data.length){
            resize()


            /*throw new IllegalStateException("Stack is full");*/
        }
        data[++t] = e;
    }

    protected void resize(){
        E[] temp = (E[]) new Object[data.length * 2];
        for(int i = 0; i < data.length; i++){
            temp[i]=data[i];
        }
        data = temp;
    }

    public E top(){
        if(isEmpty()){
            return null;
        }
        return data[t];
    }

    public E pop(){
        if(isEmpty()){
            return null;
        }
        E answer = data[t];
        data[t] = null;
        t--;
        return answer;
    }
}
