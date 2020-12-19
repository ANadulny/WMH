package sudoku.helper;

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
}
