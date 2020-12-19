package dao;

// TODO use it in project ?
public class Cell {
    private int value;
    private boolean isOriginal;

    public Cell(){
        this(0);
    }

    public Cell(int value){
        this.value = value;
    }

    public Cell(int value, boolean isOriginal){
        this.value = value;
        this.isOriginal = isOriginal;
    }


    public int get() {
        return value;
    }

    public void set(final int number){
        value = number;
    }

    public boolean isOriginal(){
        return this.isOriginal;
    }

    public void clear() {
        value = 0;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "value=" + value +
                ", isOriginal=" + isOriginal +
                '}';
    }
}
