package dao;

public class Cell {
    private int value;
    private boolean filled;

    public Cell(){
        this(0);
    }

    public Cell(int value){
        filled = false;
        this.value = value;
    }

    public boolean isFilled() {
        return filled;
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int number){
        filled = true;
        value = number;
    }

    public void clear() {
        value = 0;
        filled = false;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
