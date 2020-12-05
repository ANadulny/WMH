package dao;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("<");
        result.append(x);
        result.append(",");
        result.append(y);
        result.append(">");
        return result.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Position))return false;
        Position otherMyClass = (Position)other;
        return x == otherMyClass.x && y == otherMyClass.y;
    }
}
