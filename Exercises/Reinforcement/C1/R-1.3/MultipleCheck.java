import java.util.Random;

public class MultipleCheck{
    boolean isMultiple(long n, long m){
        if(n%m==0){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args){
        MultipleCheck check = new MultipleCheck();
        Random r = new Random();
        int n = r.nextInt(100)+40, m = r.nextInt(10)+1;

        System.out.print(n + " is ");
        if(check.isMultiple(n,m)){

        }else{
            System.out.print("not ");
        }
        System.out.print("a factor of " + m + "\n");
    }
}
