import java.util.Iterator;

public interface PositionalList<E> extends Iterable<E>{
    public int size();
    public boolean isEmpty();
    public Position<E> first();
    public Position<E> last();
    public Position<E> before(Position<E> p) throws IllegalArgumentException;
    public Position<E> after(Position<E> p) throws IllegalArgumentException;
    public Position<E> addFirst(E e);
    public Position<E> addLast(E e);
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;
    public E set(Position<E> p, E e) throws IllegalArgumentException;
    public E remove(Position<E> p) throws IllegalArgumentException;
    public Iterator<E> iterator();
    public Iterable<Position<E>> positions();
}
