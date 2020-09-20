/*import java.util.Scanner;
import java.io.*;

public class AlphaFreq{
    public static void main(String[] args){
        Scanner file = new Scanner( new File("test.txt"));
        while(file.hasNext()){

        }
    }
}*/


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AlphaFreq{
    public static void main(String[] args) throws IOException{
        int alphaCount[] = new int[52];
        File f=new File("input.txt");     //Creation of File Descriptor for input file
        FileReader fr=new FileReader(f);   //Creation of File Reader object
        BufferedReader br=new BufferedReader(fr);  //Creation of BufferedReader object
        int c = 0;
        while((c = br.read()) != -1){     //Read char by Cha
            if(c>=65 && c<=90){
                alphaCount[c-65]++;
            }else if(c>=97 && c<=122){
                alphaCount[c-71]++;
            }
        }

        for(int i = 0; i<52;i++){
            if(i<=25){
                System.out.println((char)(i+65)+ " : " + alphaCount[i]);
            }else {
                System.out.println((char)(i+71)+ " : " + alphaCount[i]);
            }
        }
    }
}
