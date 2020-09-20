/*
 * CS501 Projects-3.36
 * Antonio Zea Jr.
 * 01292020
 *
 * Write a Java program for a matrix class that can add and multiply arbitrary
 * two-dimensional arrays of integers.
 */

import java.util.Random;

public class Matrix{
    /*
     * Convers a particular matrix's column into a one dimensional array to
     * faciliate processing.
     */
    public int[] columnToRow(int[][] matrix, int column){
        int[] columnRow = new int[matrix.length];
        for(int i = 0; i < matrix.length; i++){
            columnRow[i] = matrix[i][column];
        }
        return columnRow;
    }
    /*
     * Technically, the method multiplies corresponding array elements and sums
     * those products.  This ends up emulating the algorithm used to multiply
     * matrixes using dot product(row multiplied by columns).
     */
    public int multiplyRowColumn(int[] row, int[] column){
        if(row.length != column.length){
            /*
             * Dot product is only defined when the columns of the first matirix
             * equal the rows of the second matrix.  This check is redundant as
             * this state is also checked in the dotProduct() method.
             */
            throw new IllegalArgumentException("Rows and columns do not match.");
        }
        int temp = 0;

        for(int i = 0; i < row.length; i++){
            temp+=row[i]*column[i];
        }
        return temp;
    }
    /*
     * This method calls columnToRow() on the second matrix to make one of it's
     * columns easier to process.  multiplyRowColumn() is called on the a given
     * row of the first matrix and our converted column of the second matrix.
     */
    public int[][] dotProduct(int[][] matrix1, int [][] matrix2){
        if(matrix1[0].length != matrix2.length){
            throw new IllegalArgumentException("Undefined due to dimensional mismatch.");
        }

        int[][] result = new int[matrix1.length][matrix2[0].length];

        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix2[0].length; j++){
                result[i][j] = multiplyRowColumn(matrix1[i],columnToRow(matrix2,j));
            }
        }

        return result;
    }
    /*
     * Prints 2D array
     */
    public void print2DArray(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
    /*
     * Creates and populates a 2D array with random integers and then return it.
     */
    public int[][] TwoDimArrayMaker(int rows, int columns){
        Random rand = new Random();
        int[][] matrix = new int[rows][columns];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                matrix[i][j] = rand.nextInt(8)+1;
            }
        }
        return matrix;
    }
    /*
     * Adds two arrays by adding the corresponding array values.
     */
    public int[][] add2DArrays(int[][] matrix1, int[][] matrix2){
        if(matrix1[0].length != matrix2[0].length && matrix1.length != matrix2.length){
            throw new IllegalArgumentException("Undefined due to dimensional mismatch.");
        }

        int[][] result = new int[matrix1.length][matrix1[0].length];

        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix1[0].length; j++){
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        return result;
    }

    public static void main(String[] args){
        Matrix m = new Matrix();

        int[][] matrix1 = m.TwoDimArrayMaker(2,3);
        int[][] matrix2 = m.TwoDimArrayMaker(3,1);


        System.out.println("Matrix 1:");
        m.print2DArray(matrix1);

        System.out.println("Matrix 2:");
        m.print2DArray(matrix2);

        System.out.println("Dot Product Matrix:");
        m.print2DArray(m.dotProduct(matrix1, matrix2));

        matrix2 = m.TwoDimArrayMaker(2,3);
        System.out.println("Matrix 2:");
        m.print2DArray(matrix2);

        System.out.println("Sum Matrix:");
        m.print2DArray(m.add2DArrays(matrix1, matrix2));

    }
}
