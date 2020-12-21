package dao;

import java.util.Objects;

public class Cell implements Cloneable{
    private final boolean isOriginal;
    private int value;

    public Cell(){
        this(0);
    }

    public Cell(int value){
        this.value = value;
        this.isOriginal = false;
    }

    public Cell(int value, boolean isOriginal){
        this.value = value;
        this.isOriginal = isOriginal;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(final int value){
        this.value = value;
    }

    public boolean isOriginal() {
        return this.isOriginal;
    }

    @Override
    public String toString() {
        return value + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return isOriginal == cell.isOriginal &&
                value == cell.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOriginal, value);
    }
}
