public class TestProgression{
    public static void main(String[] args){
        Progression prog = new FibonacciProgression();
        System.out.println("Fibonacci progression with default start values: ");
        prog.printProgression(10);
        System.out.println("Fibonacci progression with start value 2 and 2: ");
        prog = new FibonacciProgression(2,2);
        prog.printProgression(8);
    }
}
