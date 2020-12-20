package dao;

public class NeighbourState {
    private Board state;
    private Movement movementToState;
    private int conflictedPositions;

    public NeighbourState(Board state, Movement movementToState, int conflictedPositions) {
        this.state = state;
        this.movementToState = movementToState;
        this.conflictedPositions = conflictedPositions;
    }

    public Board getState() {
        return state;
    }

    public Movement getMovementToState() {
        return movementToState;
    }

    public int getConflictedPositions() {
        return conflictedPositions;
    }

    @Override
    public String toString() {
        return "NeighbourState{" +
                "\nstate=" + state +
                "\nmovementToState=" + movementToState +
                "\nconflictedPositions=" + conflictedPositions +
                "\n}";
    }
}
