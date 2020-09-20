/*
 * Describe an implementation of the positional list methods
 * addLast and addBefore realized by using methods in the set
 * {isEmpty, first, last, before, after, addAfter, addFirst}
 */
public Position<E> addLast(E e){
    addAfter(last(),e);
}

public Position<E> addBefore(Position<E> p, E e){
    addAfter(p.before(),e);
}
