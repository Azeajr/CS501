/*
 * CS501 Projects-3.38
 * Antonio Zea Jr.
 * 01302020
 *
 * Perform the previous project, but use a doubly linked list. Moreover, your
 * implementation of remove(i) should make the fewest number of pointer hops to
 * get to the game entry at index i.
 */
public class Scoreboard{
    //---------------- beginning of nested DoublyLinkedList class --------------
    private static class DoublyLinkedList<E extends Comparable>{
        /*
         * Node class is written this way since DoublyLinkedList is the only
         * class that needs access to it(Encapsulation).
         * Need some clarification on on the use of static nested classes.
         */
        //---------------------------- beginning of nested Node class ----------
        private class Node<E>{
            private E element;
            private Node<E> prev;
            private Node<E> next;

            public Node(E e, Node<E> p, Node<E> n) {
                element = e;
                prev = p;
                next = n;
            }

            public E getElement(){
                return element;
            }

            public Node<E> getPrev(){
                return prev;
            }

            public Node<E> getNext(){
                return next;
            }

            public void setPrev(Node<E> p){
                prev = p;
            }

            public void setNext(Node<E> n){
                next = n;
            }
        }
        //---------------------------- end of nested Node class ----------------

        /*
         * header and trailer pointer for the beginning and end of DoublyLinkedList.
         * size keeps track of how many nodes there are in the list
         */
        private Node<E> header;
        private Node<E> trailer;
        private int size = 0;

        public DoublyLinkedList(){
            /*
             * Each pointer in the header node is set to point to null.  While
                 * the trailer node will have its prev pointer set to header.
             */
            header = new Node<>(null, null, null);
            trailer = new Node<>(null, header, null);
            header.setNext(trailer);

        }
        /*
         * This method is used in case the size varible needs to accesed outside
         * of the DoublyLinkedList class
         */
        public int size(){
            return size;
        }
        /*
         * This method is used to determine if the list is empty
         */
        public boolean isEmpty(){
            return size == 0;
        }
        /*
         * Returns the element located within the first node of the list
         */
        public E first(){
            if(size == 0){
                return null;
            }
            return header.getNext().getElement();
        }
        /*
        * Returns the element located within the last node of the list
         */
        public E last(){
            if(size == 0){
                return null;
            }
            return trailer.getPrev().getElement();
        }

        public void addFirst(E e){
            addBetween(e,header,header.getNext());
        }

        public void addLast(E e){
            addBetween(e,trailer.getPrev(),trailer);
        }

        public E removeFirst(){
            if(size == 0){
                return null;
            }
            return remove(header.getNext());
        }

        public E removeLast(){
            if(size == 0){
                return null;
            }
            return remove(trailer.getPrev());
        }

        private void addBetween(E e, Node<E> predecessor, Node<E> successor){
            Node<E> newest = new Node<>(e, predecessor, successor);
            predecessor.setNext(newest);
            successor.setPrev(newest);
            size++;
        }
        /*
         * DoublyLinkedList makes this method very easy to set up.  Do not need
         * to traverse the list using a while loop to find the node right before
         * the target node.
         */
        private E remove(Node<E> node){
            Node<E> predecessor = node.getPrev();
            Node<E> successor = node.getNext();
            predecessor.setNext(successor);
            successor.setPrev(predecessor);
            size--;
            return node.getElement();
        }
        /*
         * Throws IndexOutOfBoundsException if the index falls outside of the
         * indeces contained in the list.
         *
         * If the index is smaller than the halfway point(size/2) then I
         * traverse the list starting at the header.  Otherwise I start at the
         * trailer and work my way backwards.  This minimizes the number of hops
         * necessary to find the node in question.
         */
        public E removeAt(int index) throws IndexOutOfBoundsException{
            E answer = null;
            if(index < 0 || index > size-1){
                throw new IndexOutOfBoundsException("Invalid index: " + index);
            }else if( index < size/2 ){

                Node<E> header_search = header;
                for(int i = 0; i < index+1; i++){
                    header_search=header_search.getNext();
                }
                return remove(header_search);
            }else{
                Node<E> trailer_search = trailer;
                for(int i = 0; i < size - index; i++){
                    trailer_search=trailer_search.getPrev();
                }
                return remove(trailer_search);
            }

        }
        /*
         * This method is responsible for adding a new node while keepint the
         * DoublyLinkedList in order.
         */
        public void orderedInsertion(E e){
            Node<E> header_search=header;
            Node<E> trailer_search=trailer;
            if(size == 0){
                addFirst(e);
            }else{
                /*
                 * This loop searches from both sides of the DoublyLinkedList
                 * until either the header_search's element is bigger than the
                 * target element or trailer_search's element is smaller that
                 * the target element. The moment either these case occurs then
                 * I have found where I should insert the current element.
                 */
                while(header_search.getNext().getElement().compareTo(e) < 0 &&
                      trailer_search.getPrev().getElement().compareTo(e) > 0){
                    header_search=header_search.getNext();
                    trailer_search=trailer_search.getPrev();
                }
                /*
                 * This if statement checks which logic statement failed, then
                 * inserts the Node either after the found node in the case of
                 * header_search or before the found node in the case of
                 * trailer_search.
                 */
                if(header_search.getNext().getElement().compareTo(e) >= 0){
                    addBetween(e,header_search,header_search.getNext());
                }else if(trailer_search.getPrev().getElement().compareTo(e) <= 0){
                    addBetween(e,trailer_search.getPrev(),trailer_search);
                }
            }


        }
        /*
         * StringBuilder object constructs string much faster than using
         * concatenation.
         */
        public String toString() {
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
    //---------------- end of nested DoublyLinkedList class --------------------

    /*
     * numEntries keeps track of how many scores are currently stored in
     * scoreboard.
     * boardsize will specify the maximum number of values to be kept on the
     * scoreboard.
     * board is the actual DoublyLinkedList
     */
    private int numEntries = 0;
    private int boardSize;
    DoublyLinkedList<GameEntry> board;

    /*
     * Constructor sets the boardsize and initializes the DoublyLinkedList
     */
    public Scoreboard(int boardSize){
        this.boardSize = boardSize;
        board = new DoublyLinkedList<>();
    }
    /*
     * Method adds GameEntry object to DoublyLinkedList object.
     */
    public void add(GameEntry e){
        int newScore = e.getScore();
        /*
         * There are two reasons to call orderedInsertion.  Either the board has
         * not reached its limit. Or the newScore is larger than the smallest
         * value on the board.
         */
        if(numEntries < boardSize || board.first() != null && newScore > board.first().getScore()){
            board.orderedInsertion(e);
            numEntries++;
            /*
             * If the board's limit has been reached then the smallest element
             * needs to be removed.
             */
            if(numEntries > boardSize){
                board.removeFirst();
                numEntries--;
            }
        }
    }
    /*
     * Method removes a GameEntry object from DoublyLinkedList object located at
     * index.  Throws an index IndexOutOfBoundsException if it does not fall
     * between front and end of the Scoreboard.  This is redundant as removeAt()
     * also throws this error.
     */
    public void remove( int i) throws IndexOutOfBoundsException{
        if(i < 0 || i >= numEntries){
            throw new IndexOutOfBoundsException("Invalid index: " + i);
        }
        board.removeAt(i);
        numEntries--;
    }
    /*
     * Without this method, the DoublyLinkedList object is printed as an address
     * refrence.
     */
    public String toString(){
        return board.toString();
    }
    /*
     * Test the functionalilty of the Scoreboard class.
     */
    public static void main(String[] args){
        Scoreboard highscores = new Scoreboard(10);
        String[] names = {"Rob", "Mike", "Rose", "Jill", "Jack",
                          "Anna", "Paul", "Bob", "Katey", "Martha",
                          "Josh", "Jose", "Tony", "Roman", "Dwayne"};
        int[] scores = {750, 1105, 590, 740, 510,
                        660, 720, 400, 750, 1105,
                        200, 805, 800, 600, 1000};

        for (int i=0; i < names.length; i++) {
            GameEntry gE = new GameEntry(names[i], scores[i]);
            System.out.println("Adding " + gE);
            highscores.add(gE);
            System.out.println(" Scoreboard: " + highscores);
        }



        System.out.println("Removing score at index " + 9);
        highscores.remove(9);
        System.out.println(" Scoreboard: " + highscores);

        System.out.println("Removing score at index " + 7);
        highscores.remove(7);
        System.out.println(" Scoreboard: " + highscores);

        System.out.println("Removing score at index " + 1);
        highscores.remove(1);
        System.out.println(" Scoreboard: " + highscores);

        System.out.println("Removing score at index " + 0);
        highscores.remove(0);
        System.out.println(" Scoreboard: " + highscores);

    }
}
