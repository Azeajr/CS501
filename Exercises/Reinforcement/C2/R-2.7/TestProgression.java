public class TestProgression{
    public static void main(String[] args){
        Progression prog = new ArithmeticProgression();
        System.out.println("Arithmetic progression with start value 2 and 2: ");
        prog = new ArithmeticProgression(128,0);


        /*
         * long.MAX_VALUE/128 is maximum number of calls
         * 9,223,372,036,854,775,808/128
         * 72057594037927935 function calls before the size of current gets overflowed.
         *
         * long literal need to end with L
         */
        prog.printProgression(72057594037927935L);

    }
}
