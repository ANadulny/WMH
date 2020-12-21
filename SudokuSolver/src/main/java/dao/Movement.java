package dao;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return Objects.equals(firstCell, movement.firstCell) &&
                Objects.equals(secondCell, movement.secondCell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstCell, secondCell);
    }
}
