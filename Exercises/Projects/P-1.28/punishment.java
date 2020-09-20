import java.util.Random;

public class punishment{
    public static void main(String[] args) {
        Random rand = new Random();
        String promise = "I will never spam my friends again";
        int n;


        for(int i = 0; i < 100; i++){
            n = rand.nextInt(promise.length());
            System.out.println(promise.substring(0,n) + promise.substring(n+1,promise.length()));
        }

        //System.out.println(promise.length());
        //System.out.println(promise.substring(0,promise.length()));
    }
}
