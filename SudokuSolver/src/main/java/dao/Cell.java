package dao;

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
}
