/*
 * Modify the Pair class from Code Fragment 2.17 on page 92 so that it provides
 * a natural definition for both the equals() and hashCode() methods.
 */
public class Pair<A,B>{
    A first;
    B second;

    public Pair(A a, B b){
        first = a;
        second = b;
    }

    public A getFirst(){
        return first;
    }

    public B getSecond(){
        return second;
    }

    public boolean equals(Object o){
        if(getClass() != o.getClass()){
            return false;
        }
        Pair other = (Pair) o;
        if(getFirst().equals(o.getFirst()) && getSecond().equals(o.getSecond())){
            return true
        }else{
            return false;
        }
    }

    public int hashCode(){
        return (int)getFirst() ^ (int)getSecond();
    }
}
