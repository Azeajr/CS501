/*
 * To better model a FIFO queue in which entries may be deleted before reaching
 * the front, design a LinkedPositionalQueue class that supports the complete
 * queue ADT, yet with enqueue returning a position instance and support for a
 * new method, remove(p), that removes the element associated with position p
 * from the queue. You may use the adapter design pattern (Section 6.1.3),
 * using a LinkedPositionalList as your storage.
 */
public class LinkedPositionalQueue<E> implements Queue<E>, Position<E>{
    LinkedPositionalList<E> lpl = new LinkedPositionalList<>();


    public int size(){
        return lpl.size();
    }

    public boolean isEmpty(){
        return lpl.isEmpty();
    }

    public Position<E> enqueue(E e){
        return lpl.addFirst(e);
    }

    public E first(){
        return lpl.first();
    }

    public E dequeue(){
        return lpl.remove(lpl.last());
    }

    public E remove(Position<E> p){
        return lpl.remove(p);
    }
}
