public class Useless{
    public static void main( String[] args){
        /*
         * According to Java Language Specification,
         * The conditional expression of If block will
         * no be evaluated at compile time to decide the
         * unreachable code. That’s why you will not get
         * compile time error in case of if block. 
         */
        if(false){
            System.out.println("THis will never run.");
        }
    }
}
