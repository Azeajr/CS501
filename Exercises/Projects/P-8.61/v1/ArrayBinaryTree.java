public class ArrayBinaryTree<E> extends AbstractBinaryTree<E>{

    private class Node<E> implements Position<E>{
        private E element;
        private int index;

        public Index(E e, int i){
            element = e;
            index = i;
        }

        public E getElement(){
            return element;
        }

        public int getIndex(){
            return index;
        }

        public void setElement(E e){
            element = e;
        }

        public void setIndex(int i){
            index = i;
        }
    }

    private Position<E>[] data;
    private int size = 0;
    private static final int CAPACITY = 16;


    public ArrayBinaryTree(){
        this(CAPACITY);
    }

    public ArrayBinaryTree(int capacity){
        data = (E[]) new Object[capacity];
    }

    protected Node<E> validate(Position<E> p){
        if(!(p instanceof Node)){
            throw new IllegalArgumentException("Not valid position type");
        }
        Node<E> node = (Node<E>) p;
        return node;
    }

    public int size(){
        return size;
    }

    public Position<E> root(){
        return data[0];
    }

    public Position<E> parent(Position<E> p) throws IllegalArgumentException{
        return data[(p.getIndex() - 1)/2];
    }

    public Position<E> left(Position<E>)
    right

    addRoot
    addLeft
    addRight
    set
    attach
    remove

}
