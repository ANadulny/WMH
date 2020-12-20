package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BoarderCellsList {
    List<Cell> cells;

    public BoarderCellsList(List<Cell> cells) {
        this.cells = cells;
    }

    public boolean hasDiffNumbers() {
        List<Integer> numbers = cells.stream().map(cell -> cell.getValue()).collect(Collectors.toList());
        Set<Integer> set = new HashSet<>(numbers);
        return set.size() == cells.size();
    }

    public int countDifferentElements(){
        int numOfDifferentVals = 0;
        ArrayList<Integer> diffNum = new ArrayList<>();
        for (Cell cell : this.cells) {
            if (!diffNum.contains(cell.getValue())) {
                diffNum.add(cell.getValue());
            }
        }

        if(diffNum.size()==1){
            numOfDifferentVals = 0;
        }
        else{
            numOfDifferentVals = diffNum.size();
        }

        return numOfDifferentVals;
    }

    public List<Cell> getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return "BoarderCellsList{" +
                "numbers=" + cells +
                '}';
    }
}
