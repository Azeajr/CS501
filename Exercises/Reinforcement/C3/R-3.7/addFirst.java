/*
 * Consider the implementation of CircularlyLinkedList.addFirst, in Code
 * Fragment 3.16. The else body at lines 39 and 40 of that method relies
 * on a locally declared variable, newest.  Redesign that clause to avoid
 * use of any local variable.
 */

public void addFirst(E e) {                // adds element e to the front of the list
    if (size == 0) {
        tail = new Node<>(e, null);
        tail.setNext(tail);                     // link to itself circularly
    } else {
        //Node<E> newest = new Node<>(e, tail.getNext());
        //tail.setNext(newest);

        /*
         * Not sure if this is what they mean.
         */
        tail.setNext(new Node<>(e, tail.getNext());
    }
    size++;
}
