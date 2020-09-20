/*
 * Give a simple adapter that implements the queue ADT while using an instance
 * of a deque for storage.
 */
public class queueDeque<E> implements Queue<E>{
    private Deque<E> deck;

    public void enqueue(E e){
        deck.addFirst(e);
    }
    public E dequeue(){
        return deck.removeFirst();
    }
    public E first(){
        return deck.first();
    }

    public int size(){
        return deck.size();
    }

    public boolean isEmpty(){
        return deck.isEmpty();
    }


}
