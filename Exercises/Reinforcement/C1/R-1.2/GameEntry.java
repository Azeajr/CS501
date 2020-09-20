import java.util.Random;

public class GameEntry{
    int score;

    public static void main(String[] args){
        Random r = new Random();
        GameEntry[] A = new GameEntry[10];
        for(int i=0;i<A.length;i++){
            /*
             * Otherwise NullPointerExceptionis thrown because the defualt
             * value of an object refrence is Null.
             */
            A[i] = new GameEntry();
            A[i].score=r.nextInt()%90+10;
        }

        GameEntry[] B = A;

        A[4].score = 550;

        /*
         * A[] and B[] are aliases of each other.
         */
        System.out.println("B[4].score: " + B[4].score);

        for(GameEntry x: A){
            System.out.println(x.score);
        }
    }
}
