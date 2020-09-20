import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPositionalList<E> implements PositionalList<E>{
    private static class Node<E> implements Position<E>{
        private E element;
        private Node<E> prev;
        private Node<E> next;
        public Node(E e, Node<E> p, Node<E> n){
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() throws IllegalStateException{
            if(next == null){
                throw new IllegalStateException("Position no longer valid");
            }
            return element;
        }

        public Node<E> getPrev(){
            return prev;
        }

        public Node<E> getNext(){
            return next;
        }

        public void setElement(E e){
            element = e;
        }

        public void setPrev(Node<E> p){
            prev = p;
        }

        public void setNext(Node<E> n){
            next = n;
        }
    }

    private class PositionIterator implements Iterator<Position<E>>{
        private Position<E> cursor =  first();
        private Position<E> recent = null;

        public boolean hasNext(){
            return (cursor != null);
        }

        public Position<E> next() throws NoSuchElementException{
            if(cursor == null){
                throw new NoSuchElementException("nothing left");
            }
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }

        public void remove() throws IllegalStateException{
            if(recent == null){
                throw new IllegalStateException("nothing to remove");
            }
            LinkedPositionalList.this.remove(recent);
            recent = null;
        }
    }

    private class PositionIterable implements Iterable<Position<E>>{
        public Iterator<Position<E>> iterator(){
            return new PositionIterator();
        }
    }

    public Iterable<Position<E>> positions(){
        return new PositionIterable();
    }

    private class ElementIterator implements Iterator<E>{
        Iterator<Position<E>> posIterator = new PositionIterator();

        public boolean hasNext(){
            return posIterator.hasNext();
        }

        public E next(){
            return posIterator.next().getElement();
        }

        public void remove(){
            posIterator.remove();
        }
    }

    public Iterator<E> iterator(){
        return new ElementIterator();
    }

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    public LinkedPositionalList(){
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header,null);
        header.setNext(trailer);
    }

    /*
     *  ReimplementtheLinkedPositionalListclass so that an invalid position is
     *  reported in a scenario such as the one described in Exercise R-7.14.
     *
     * This method checks that the header node found by traversing in reverse is
     * the same as the header node of LinkedPositionalList.
     */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException{
        if(!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
        Node<E> node = (Node<E>)p;
        if(node.getNext() == null){
            throw new IllegalArgumentException("p is no longer in the list");
        }
        Node<E> walk = node;
        while (walk.getPrev() != null) {
            walk = walk.getPrev();
        }
        if(walk == header){
            return node;

        } else{
            throw new IllegalArgumentException("p is no longer in the list");
        }
    }

    private Position<E> position(Node<E> node){
        if(node == header || node == trailer){
            return null;
        }
        return node;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Position<E> first(){
        return position(header.getNext());
    }

    public Position<E> last(){
        return position(trailer.getPrev());
    }

    public Position<E> before(Position<E> p) throws IllegalArgumentException{
        Node<E> node =  validate(p);
        return position(node.getPrev());
    }

    public Position<E> after(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ){
        Node<E> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    public Position<E> addFirst(E e){
        return addBetween(e, header, header.getNext());
    }

    public Position<E> addLast(E e){
        return addBetween(e, trailer.getPrev(), trailer);
    }

    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    public E remove(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        E answer = node.getElement();
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = header.getNext();
        while (walk != trailer) {
            sb.append(walk.getElement());
            walk = walk.getNext();
            if (walk != trailer)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }
}