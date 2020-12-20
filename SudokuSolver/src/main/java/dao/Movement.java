package dao;

public class Movement {
    private Position firstCell;
    private Position secondCell;

    public Movement(Position firstCell, Position secondCell){
        this.firstCell = firstCell;
        this.secondCell = secondCell;
    }

    public Position getFirstPos() {
        return this.firstCell;
    }

    public Position getSecondPos(){
        return this.secondCell;
    }

    @Override
    public String toString() {
        return firstCell + " -> " + secondCell;
    }
}
