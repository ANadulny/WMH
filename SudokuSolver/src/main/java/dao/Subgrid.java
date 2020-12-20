package dao;

import java.util.Arrays;

// TODO improve implementation or remove from project if not necessary
public class Subgrid {
    // matrix 3x3
    private Cell [][] grid;

    public Subgrid(){
        Cell[][] matrix = new Cell[3][3];
        for (Cell[] row: matrix)
            Arrays.fill(row, new Cell(0));
        grid = matrix;
    }

    public Subgrid(Cell [][] initialGrid){
        grid = initialGrid;
    }

    @Override
    public String toString() {
        return "Subgrid{" +
                "grid=" + Arrays.toString(grid) +
                '}';
    }
}
