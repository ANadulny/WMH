package dao;

// TODO use it in project ?
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

    public int get() {
        return value;
    }

    public void set(final int number){
        filled = true;
        value = number;
    }

    public void clear() {
        value = 0;
        filled = false;
    }
}
