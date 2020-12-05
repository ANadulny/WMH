package dao;

import java.util.Arrays;

// TODO improve implementation or remove from project if not necessary
public class Subgrid {
    // matrix 3x3
    private int [][] grid;

    public Subgrid(){
        int[][] matrix = new int[3][3];
        for (int[] row: matrix)
            Arrays.fill(row, 0);

        grid = matrix;
    }

    public Subgrid(int [][] initialGrid){
        grid = initialGrid;
    }

    @Override
    public String toString() {
        return "Subgrid{" +
                "grid=" + Arrays.toString(grid) +
                '}';
    }
}
