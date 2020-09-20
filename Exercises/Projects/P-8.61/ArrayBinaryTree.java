import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayBinaryTree<E>{
    private E[] data;
    private int size = 0;
    private static final int CAPACITY = 10;


    public ArrayBinaryTree(){
        this(CAPACITY);
    }

    public ArrayBinaryTree(int capacity){
        data = (E[]) new Object[capacity];
    }

    /*getElement(): Returns the element stored at this position.
    root(): Returns the position of the root of the tree (or null if empty).
    parent(p): Returns the position of the parent of position p (or null if p is the root).
    children(p): Returns an iterable collection containing the children of position p (if any).
    numChildren(p): Returns the number of children of position p.
    isInternal(p): Returns true if position p has at least one child.
    isExternal(p): Returns true if position p does not have any children.
    isRoot(p): Returns true if position p is the root of the tree.
    size(): Returns the number of positions (and hence elements) that are contained in the tree.
    isEmpty(): Returns true if the tree does not contain any positions (and thus no elements).
    iterator(): Returns an iterator for all elements in the tree (so that the tree itself is Iterable).
    positions(): Returns an iterable collection of all positions of the tree.
    height
    left(p): Returns the position of the left child of p (or null if p has no left child).
    right(p): Returns the position of the right child of p (or null if p has no right child).
    sibling(p): Returns the position of the sibling of p (or null if p has no sibling).

    addRoot(e): Creates a root for an empty tree, storing e as the element, and returns the position of that root; an error occurs if the tree is not empty.
    addLeft(p, e): Creates a left child of position p, storing element e, and returns the position of the new node; an error occurs if p already has a left child.
    addRight(p, e): Creates a right child of position p, storing element e, and returns the position of the new node; an error occurs if p already has a right child.
    set(p, e): Replaces the element stored at position p with element e, and returns the previously stored element.
    attach(p, T1, T2): Attaches the internal structure of trees T1 and T2 as the respective left and right subtrees of leaf position p and resets T1 and T2 to empty trees; an error condition occurs if p is not a leaf.
    remove(p): Removes the node at position p, replacing it with its child (if any), and returns the element that had been stored at p; an error occurs if p has two children.*/

    public E getElement(int i){
        return data[i];
    }
    /*
     * Returns the location of the root of the tree
     */
    public int root(){
        return 0;
    }
    /*
     * Returns the location of the parent of index
     */
    public int parent(int index){
        return (index - 1)/2;
    }
    /*
     * Returns the location of the left child of index
     */
    public int left(int index){
        return 2 * index + 1;
    }
    /*
     * Returns the location of the right child of index
     */
    public int right(int index){
        return 2 * index + 2;
    }
    /*
     * Returns the location of the sibling of index
     */
    public int sibling(int index){
        int parent = parent(index);
        if(data[parent] == null){
            return -1;
        }
        if(index == left(parent)){
            return right(parent);
        }else{
            return left(parent);
        }
    }
    /*
     * Returns the number of children of index
     */
    public int numChildren(int index){
        int count = 0;
        if(data[left(index)] != null){
            count ++;
        }
        if(data[right(index)] != null){
            count++;
        }
        return count;
    }
    /*
     * Returns an iterable object containing the locations of children of index
     */
    public Iterable<Integer> children(int i){
        List<Integer> snapshot = new ArrayList<>(2);
        if(data[left(i)] != null){
            snapshot.add(left(i));
        }
        if(data[right(i)] != null){
            snapshot.add(right(i));
        }
        return snapshot;
    }
    /*
     * Returns true if index has at least one child
     */
    public boolean isInternal(int index){
        return numChildren(index) > 0;
    }
    /*
     * Returns true if index does not have any children
     */
    public boolean isExternal(int index){
        return numChildren(index) == 0;
    }
    /*
     * Returns true if index is the root of the tree
     */
    public boolean isRoot(int index){
        return index == 0;
    }
    /*
     * Returns true if the tree does not contain any elements
     */
    public boolean isEmpty(){
        return size() == 0;
    }
    /*
     * Returns the number of ancestors of index
     */
    public int depth(int index){
        if(isRoot(index)){
            return 0;
        }else{
            return 1 + depth(parent(index));
        }
    }
    /*
     * Returns the maximum of the depths of every position in the tree
     * Returns the height of the tree
     */
    public int heightBad(){
        int h = 0;
        for(int i: positions()){
            if(isExternal(i)){
                h = Math.max(h, depth(i));
            }
        }
        return h;
    }
    /*
     * Returns the height of the subtree rooted at index
     */
    public int height(int index){
        int h = 0;
        for(int c: children(index)){
            h = Math.max(h, 1 + height(c));
        }
        return h;
    }
    /*
     *
     */
    private class ElementIterator implements Iterator<Integer>{
        Iterator<Integer> posIterator = positions().iterator();
        public boolean hasNext(){
            return posIterator.hasNext();
        }

        public Integer next(){
            return posIterator.next();
        }

        public void remove(){
            posIterator.remove();
        }
    }

    public Iterator<Integer> iterator(){
        return new ElementIterator();
    }

    /*public Iterable<Integer> positions(){
        return preorder();
    }

    private void preorderSubtree(int index, List<Integer> snapshot){
        snapshot.add(index);
        for(int c : children(index)){
            preorderSubtree(c, snapshot);
        }
    }

    public Iterable<Integer> preorder(){
        List<Integer> snapshot = new ArrayList<>();
        if(!isEmpty()){
            preorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    private void postorderSubtree(int index, List<Integer> snapshot){
        for(int c : children(index)){
            postorderSubtree(root(), snapshot);
        }
        snapshot.add(i)
    }

    public Iterable<Integer> postorder(){
        List<Integer> snapshot = new ArrayList<>();
        if(!isEmpty()){
            postorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    public Iterable<Integer> breadthfirst(){
        List<Integer> snapshot = new ArrayList<>();
        if(!isEmpty()){
            Queue<Integer> fringe = new LinkedQueue<>()
            fringe.enqueue(root());
            while(!fringe.isEmpty()){
                int index = fringe.dequeue();
                snapshot.add(index);
                for(int c : children(index)){
                    fringe.enqueue(c);
                }
            }
        }
        return snapshot;
    }*/

    private void inorderSubtree(int index, List<Integer> snapshot){
        if(data[left(index)] != null){
            inorderSubtree(left(index), snapshot);
        }
        snapshot.add(index);
        if(data[right(index)] != null){
            inorderSubtree(right(index), snapshot);
        }
    }

    public Iterable<Integer> inorder(){
        List<Integer> snapshot = new ArrayList<>();
        if(!isEmpty()){
            inorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    public Iterable<Integer> positions(){
        return inorder();
    }

    public int size(){
        return size;
    }

    public void addRoot(E e) throws IllegalStateException{
        if(size() == 0){
            data[0] = e;
        }else{
            throw new IllegalStateException("Tree is not empty");
        }
    }

    public void addLeft(int i, E e){
        data[left(i)] = e;
    }

    public void addRight(int i, E e){
        data[right(i)] = e;
    }

    public E set(int i, E e){
        E temp = data[i];
        data[i] = e;
        return temp;
    }

    public String toString(){
        return Arrays.toString(data);
    }

    public static void main(String args[]){
        ArrayBinaryTree<Integer> tree = new ArrayBinaryTree<>(10);

        for(int i = 0; i < 10; i++){
            if(i == 0){
                tree.addRoot(i*2+10);
            }else{
                tree.addRight(i,i*2+10);
            }
        }

        System.out.println(tree);
    }
}
