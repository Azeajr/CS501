/*
 * CS501 Projects-3.37
 * Antonio Zea Jr.
 * 01302020
 *
 * Write a class that maintains the top ten scores for a game application,
 * implementing the add and remove methods of Section 3.1.1, but using a singly
 * linked list instead of an array.
 */
public class Scoreboard{
    //---------------- beginning of nested SinglyLinkedList class --------------
    private static class SinglyLinkedList<E extends Comparable>{
        /*
         * Node class is written this way since SinglyLinkedList is the only
         * class that needs access to it(Encapsulation).
         * Need some clarification on on the use of static nested classes.
         */
        //---------------------------- beginning of nested Node class ----------
        private class Node<E>{
            private E element;
            private Node<E> next;

            public Node(E e, Node<E> n){
                element = e;
                next = n;
            }

            public E getElement(){
                return element;
            }

            public Node<E> getNext(){
                return next;
            }

            public void setNext(Node<E> n){
                next = n;
            }
        }
        //---------------------------- end of nested Node class ----------------

        /*
         * head and tail pointer for the beginning and end of SinglyLinkedList.
         * size keeps track of how many nodes there are in the list
         */
        private Node<E> head = null;
        private Node<E> tail = null;
        private int size = 0;

        public SinglyLinkedList(){

        }
        /*
         * This method is used in case the size varible needs to accesed outside
         * of the SinglyLinkedList class
         */
        public int size(){
            return size;
        }
        /*
         * This method is used to determine if the list is empty
         */
        public boolean isEmpty(){
            return size==0;
        }
        /*
         * Returns the element located within the first node of the list
         */
        public E first(){
            if(size == 0){
                return null;
            }
            return head.getElement();
        }
        /*
        * Returns the element located within the last node of the list
         */
        public E last(){
            if(size == 0){
                return null;
            }
            return tail.getElement();
        }
        /*
         * Add a node to the front of the list containing the specified element
         */
        public void addFirst(E e){
            head = new Node<>(e, head);
            /*
             * head is set to point to a new Node that points the node that
             * head originally pointed to.  If the list was empty then tail is
             * set to point to this first node as well.
             */
            if(size == 0){
                tail = head;
            }
            size++;
        }
        /*
         * Add a node to the end of the list containing the specified element
         */
        public void addLast(E e){
            Node<E> newest = new Node<>(e, null);
            /*
             * If list is empty head and tail pointers will both be set to
             * newest node.  Otherwise, the current node tail points to will be
             * set to lead to newest node.  At the end, tail pointer always
             * points to this new node in either case.
             */
            if(size == 0){
                head = newest;
            } else {
                tail.setNext(newest);
            }
            tail = newest;
            size++;
        }
        /*
         * Removes the first node of the list. Returns that node's element.
         */
        public E removeFirst(){
            if(size == 0){
                return null;
            }
            /*
             * The element that is about to be removed is saved in answer.
             * head pointer is incremented which effectively removes the node
             * from the list.  If the list is emptied then tail is set to null
             * as is the case for an empty list.
             */
            E answer = head.getElement();
            head = head.getNext();
            size--;
            if(size == 0){
                tail = null;
            }
            return answer;
        }
        /*
         * Remove the last node of list. Returns that node's element.
         */
        public E removeLast(){
            if(size == 0){
                return null;
            }

            Node<E> previous = null;
            Node<E> current = head;
            /*
             * This loop will stop when current equals the tail.  At that point
             * previous is the node that needs to be changed so that it points
             * to null.
             * Origninal conditional: current.getNext()!=null
             */
            while(current != tail){
                previous=current;
                current=current.getNext();
            }

            E answer = current.getElement();
            previous.setNext(null);
            size--;
            /*
             * tail is either set to null when the list is empty or it is set
             * to the new tail which is the previous node.
             */
            if(size == 0){
                tail = null;
            } else {
                tail = previous;
            }

            return answer;
        }
        /*
         * Inserts a new node containing e between the first and second nodes.
         */
        public void insertBetween(E e, Node<E> first, Node<E> second){
            Node<E> between = new Node<> (e,second);
            first.setNext(between);
            size++;
        }
        /*
         * Method takes for granted that element e implements comparable
         * interface. Elements are inserted into list in order.
         */
        public void orderedInsertion(E e){
            Node<E> previous = null;
            Node<E> current = head;
            /*
             * Loop traverses list and stops when it finds a node whose element
             * is bigger or equal to element in question e.
             */
            while(current!=null && current.getElement().compareTo(e) < 0){
                previous=current;
                current=current.getNext();
            }
            /*
             * This method does not increment the size variable itself.  Because
             * it calls either addFirst(), addLast(), or insertBetween().  All
             * of those methods take care of increment the size of the list.
             */
            if(current==head){
                /*
                 * If the element needs to be inserted at the beginning then
                 * addFirst() is used.  If null is reached then the element
                 * needs to be added to end of the list. Otherwise, the element
                 * needs to be insertBetween() previous and current pointers.
                 */
                addFirst(e);
            }else if(current == null){
                addLast(e);
            } else {
                insertBetween(e,previous,current);
            }
        }
        /*
         * Method removes Node located at index.  This method is zero indexed
         * like arrays. Throws IndexOutOfBoundsException if the index provided
         * does not fall within the values in the list.
         */
        public E removeAt(int index) throws IndexOutOfBoundsException{
            E answer = null;
            /*
             * If index is 0 then removeFirst() is appropriate. If index refers
             * to the last element (size-1) then removeLast() is used. If the
             * index falls between the beginning and the end then a for loop is
             * used to traverse the list and get the needed pointers to
             * facilitate removal.
             * Once we have the node we want to remove(current) and the node
             * right before this one(previous) we set prvious to point at the
             * node that comes after current.
             * Finally if the index fall outside of the previous values then an
             * error is thrown.
             */
            if(index==0){
                return removeFirst();
            } else if(index==size-1){
                return removeLast();
            } else if(index > 0 && index < size-1){
                Node<E> previous = null;
                Node<E> current = head;

                for(int j = 0; j < index;j++){
                    previous=current;
                    current=current.getNext();
                }

                answer = current.getElement();
                previous.setNext(current.getNext());

                size--;
                if(size == 0){
                    tail = null;
                }
            }else{
                throw new IndexOutOfBoundsException("Invalid index: " + index);
            }
            return answer;
        }
        /*
         *  toString method is necessary, otherwise an address is returned
         *  instead of a properly formated socreboard.
         */
        public String toString(){
            /*
             * Original attemp at the toString method.  In my research,
             * StringBuilder is more efficient due to Strings being immutable.
             * More string objects are created with standard concatenation.
             */
            /*String temp = "(";
            Node<E> current = head;
            while (current != null) {
                temp += current.getElement();
                if (current != tail){
                    temp += ", ";
                }
                current = current.getNext();
            }
            temp+=")";

            return temp;*/

            /*
             * Second attempt using StringBuilder object.
             */
            StringBuilder sll = new StringBuilder("(");
            Node<E> current = head;
            while (current != null) {
                sll.append(current.getElement());
                if (current != tail){
                    sll.append(", ");
                }
                current = current.getNext();
            }
            sll.append(")");
            return sll.toString();
        }
    }
    //---------------- end of nested SinglyLinkedList class --------------------

    /*
     * numEntries keeps track of how many scores are currently stored in
     * scoreboard.
     * boardsize will specify the maximum number of values to be kept on the
     * scoreboard.
     * board is the actual linked list
     */
    private int numEntries = 0;
    private int boardSize;
    SinglyLinkedList<GameEntry> board;

    /*
     * Constructor sets the boardsize and initializes the SinglyLinkedList
     */
    public Scoreboard(int boardSize){
        this.boardSize = boardSize;
        board = new SinglyLinkedList<>();
    }
    /*
     * Method adds GameEntry object to SinglyLinkedList object.
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
     * Method removes a GameEntry object from SinglyLinkedList object located at
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
     * Without this method, the SinglyLinkedList object is printed and an address refrence.
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
