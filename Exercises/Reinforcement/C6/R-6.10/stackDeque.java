/*
 * Give a simple adapter that implements the stack ADT while using an instance
 * of a deque for storage.
 */
public class stackDeque<E> implements Stack<E>{
    private Deque<E> deck;

    public int size(){
        deck.size();
    }

    public boolean isEmpty(){
        deck.isEmpty();
    }

    public void push(E e){
        deck.addFirst(e)
    }

    public E pop(){
        deck.removeFirst();
    }

    public E top(){
        deck.first();
    }
}
