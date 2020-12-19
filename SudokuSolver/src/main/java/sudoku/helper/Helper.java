package sudoku.helper;

import dao.Movement;
import dao.Position;

import java.util.LinkedList;
import java.util.List;


public class Helper {

    private Helper(){}

    static public List<Movement> movementListForSubgrid(int xOffset, int yOffset){
        List<Movement> listForSubgrid = new LinkedList<>();
        listForSubgrid.add(new Movement(new Position(0+xOffset,0+yOffset), new Position(0+xOffset,1+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,0+yOffset), new Position(0+xOffset,2+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,0+yOffset), new Position(1+xOffset,0+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,0+yOffset), new Position(1+xOffset,1+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,0+yOffset), new Position(1+xOffset,2+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,0+yOffset), new Position(2+xOffset,0+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,0+yOffset), new Position(2+xOffset,1+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,0+yOffset), new Position(2+xOffset,2+yOffset)));

        listForSubgrid.add(new Movement(new Position(0+xOffset,1+yOffset), new Position(0+xOffset,2+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,1+yOffset), new Position(1+xOffset,0+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,1+yOffset), new Position(1+xOffset,1+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,1+yOffset), new Position(1+xOffset,2+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,1+yOffset), new Position(2+xOffset,0+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,1+yOffset), new Position(2+xOffset,1+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,1+yOffset), new Position(2+xOffset,2+yOffset)));

        listForSubgrid.add(new Movement(new Position(0+xOffset,2+yOffset), new Position(1+xOffset,0+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,2+yOffset), new Position(1+xOffset,1+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,2+yOffset), new Position(1+xOffset,2+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,2+yOffset), new Position(2+xOffset,0+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,2+yOffset), new Position(2+xOffset,1+yOffset)));
        listForSubgrid.add(new Movement(new Position(0+xOffset,2+yOffset), new Position(2+xOffset,2+yOffset)));

        listForSubgrid.add(new Movement(new Position(1+xOffset,0+yOffset), new Position(1+xOffset,1+yOffset)));
        listForSubgrid.add(new Movement(new Position(1+xOffset,0+yOffset), new Position(1+xOffset,2+yOffset)));
        listForSubgrid.add(new Movement(new Position(1+xOffset,0+yOffset), new Position(2+xOffset,0+yOffset)));
        listForSubgrid.add(new Movement(new Position(1+xOffset,0+yOffset), new Position(2+xOffset,1+yOffset)));
        listForSubgrid.add(new Movement(new Position(1+xOffset,0+yOffset), new Position(2+xOffset,2+yOffset)));

        listForSubgrid.add(new Movement(new Position(1+xOffset,1+yOffset), new Position(1+xOffset,2+yOffset)));
        listForSubgrid.add(new Movement(new Position(1+xOffset,1+yOffset), new Position(2+xOffset,0+yOffset)));
        listForSubgrid.add(new Movement(new Position(1+xOffset,1+yOffset), new Position(2+xOffset,1+yOffset)));
        listForSubgrid.add(new Movement(new Position(1+xOffset,1+yOffset), new Position(2+xOffset,2+yOffset)));

        listForSubgrid.add(new Movement(new Position(1+xOffset,2+yOffset), new Position(2+xOffset,0+yOffset)));
        listForSubgrid.add(new Movement(new Position(1+xOffset,2+yOffset), new Position(2+xOffset,1+yOffset)));
        listForSubgrid.add(new Movement(new Position(1+xOffset,2+yOffset), new Position(2+xOffset,2+yOffset)));

        listForSubgrid.add(new Movement(new Position(2+xOffset,0+yOffset), new Position(2+xOffset,1+yOffset)));
        listForSubgrid.add(new Movement(new Position(2+xOffset,0+yOffset), new Position(2+xOffset,2+yOffset)));

        listForSubgrid.add(new Movement(new Position(2+xOffset,1+yOffset), new Position(2+xOffset,2+yOffset)));

        return listForSubgrid;
    }
}
