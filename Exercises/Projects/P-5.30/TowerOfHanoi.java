import java.util.Arrays;

public class TowerOfHanoi{
    private static class Stack{
        private static final int CAPACITY = 1000;
        private int[] data;
        private int index;

        public Stack(){
            this(CAPACITY);
        }

        public Stack(int capacity){
            data = new int[capacity];
            index = -1;
        }

        public int size(){
            return index + 1;
        }

        public boolean isEmpty(){
            return index == -1;
        }

        public void push(int e){
            if(size() == data.length){
                throw new IllegalStateException("Stack is full.");
            }
            data[++index] = e;
        }

        public int top(){
            if(isEmpty()){
                return 0;
            }
            return data[index];
        }

        public int pop(){
            if(isEmpty()){
                return 0;
            }
            int answer = data[index];
            data[index] = 0;
            index--;
            return answer;
        }

        public String toString(){
            return Arrays.toString(data);
        }
    }

    private Stack start;
    private Stack helper;
    private Stack end;
    private static final int defaultSize = 10;

    public TowerOfHanoi(){
        this(defaultSize);
    }

    public TowerOfHanoi(int n){
        start = new Stack(n);
        helper = new Stack(n);
        end = new Stack(n);


        for(int i = n; i > 0; i--){
            start.push(i);
        }

    }



    public void solve(int n, Stack start, Stack help, Stack end){
        if(n==1){
            end.push(start.pop());
        }else{
            solve(n-1,start,end,help);
            end.push(start.pop());
            solve(n-1,help,start,end);
        }
    }

    public static void main(String[] args){
        /*int n = 10;
        Stack start = new Stack(n);
        Stack helper = new Stack(n);
        Stack end = new Stack(n);


        for(int i = n; i > 0; i--){
            start.push(i);
        }

        System.out.println(start + "\n" + helper + "\n" + end + "\n");

        TowerOfHanoi.tHanoi(n,start, helper, end);

        System.out.println(start + "\n" + helper + "\n" + end + "\n");*/
        TowerOfHanoi Tower = new TowerOfHanoi(3);
        Tower.solve(3,Tower.start,Tower.helper,Tower.end);

    }
}
