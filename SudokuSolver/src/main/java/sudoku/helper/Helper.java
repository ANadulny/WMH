package sudoku.helper;

import dao.Cell;
import dao.CellsList;
import dao.Movement;
import dao.Position;

import java.util.LinkedList;
import java.util.List;

public class Helper {

    private Helper(){}

    static public List<Movement> movementListForSubgrid(int xOffset, int yOffset){
        List<Position> positionList = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                positionList.add(new Position(i+xOffset, j+yOffset));
            }
        }

        List<Movement> listForSubgrid = new LinkedList<>();
        for (int i = 0; i < positionList.size() - 1; i++) {
            for (int j = i + 1; j < positionList.size(); j++) {
                listForSubgrid.add(new Movement(positionList.get(i), positionList.get(j)));
            }
        }
        return listForSubgrid;
    }

    public static boolean hasCorrectNumberPosition(CellsList testList, CellsList solutionList) {
        List<Cell> test = testList.getCells();
        List<Cell> solution = solutionList.getCells();
        if (test.size() != solution.size()) {
            return false;
        }

        for(int i = 0; i < test.size(); i++) {
            if (test.get(i).getValue() != 0 && test.get(i).getValue() != solution.get(i).getValue()) {
                return false;
            }
        }
        return true;
    }
}
