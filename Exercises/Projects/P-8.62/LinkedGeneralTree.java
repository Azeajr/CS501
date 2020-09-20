import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class LinkedGeneralTree<E> extends AbstractTree<E>{
    protected static class Node<E> implements Position<E>{
        private E element;
        private Node<E> parent;
        private List<Position<E>> children;


        public Node(E e, Node<E> above){
            element = e;
            parent = above;
            children = new ArrayList<>();
        }

        public E getElement(){
            return element;
        }

        public Node<E> getParent(){
            return parent;
        }

        public void setElement(E e){
            element = e;
        }

        public void setParent(Node<E> parentNode){
            parent = parentNode;
        }

        public void addChild(Node<E> child){
            children.add(child);
        }
    }

    protected Node<E> createNode(E e, Node<E> parent){
        return new Node<E>(e, parent);
    }

    protected Node<E> root = null;
    private int size = 0;

    public LinkedGeneralTree(){

    }

    protected Node<E> validate(Position<E> p){
        if(!(p instanceof Node)){
            throw new IllegalArgumentException("Not a valid position type");
        }
        Node<E> node = (Node<E>) p;
        if(node.getParent() == node){
            throw new IllegalArgumentException("p is no longer in the tree");
        }
        return node;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Position<E> root(){
        return root;
    }

    public Position<E> parent(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return node.getParent();
    }

    public boolean isRoot(Position<E> p){
        /*
         * Someone needs to explain to me why i shoud get at the root pointer
         * using a function instead of using the actual pointer.
         */
        return p == root();
    }

    public boolean isInternal(Position<E> p){
        return numChildren(p) > 0;
    }

    public boolean isExternal(Position<E> p){
        return numChildren(p) == 0;
    }

    public int numChildren(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return node.children.size();
    }

    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return node.children;
    }

    public int depth(Position<E> p){
        if(isRoot(p)){
            return 0;
        }else{
            return depth(parent(p));
        }
    }

    public int height(Position<E> p){
        int h = 0;
        for(Position<E> c : children(p)){
            h = Math.max(h, 1 + height(c));
        }
        return h;
    }

    public Position<E> addRoot(E e) throws IllegalStateException{
        if(!isEmpty()){
            throw new IllegalStateException("Tree is not empty");
        }
        root = createNode(e,null);
        size++;
        return root;
    }

    public Position<E> addChild(E e, Position<E> p){
        Node<E> parent = validate(p);
        Node<E> child = createNode(e, parent);
        parent.addChild(child);
        size++;
        return child;

    }

    public String toString(){
        StringBuilder sb = new StringBuilder("(");
        for(Position<E> p : positions()){
            sb.append(p.getElement());
            sb.append(",");
        }

        sb.setCharAt(sb.length()-1,')');
        return sb.toString();
    }

    public static void main(String[] args){
        LinkedGeneralTree<String> tree = new LinkedGeneralTree<>();

        tree.addRoot("E");
        Position<String> temp1 = tree.addChild("X", tree.root());
        tree.addChild("N", tree.root());
        Position<String> temp2 = tree.addChild("A", temp1);
        tree.addChild("U", temp1);
        tree.addChild("M", temp2);
        tree.addChild("F", temp2);




        System.out.println(tree);
    }
}
