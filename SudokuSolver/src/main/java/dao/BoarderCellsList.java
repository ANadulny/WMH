package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoarderCellsList {
    List<Cell> numbers;

    public BoarderCellsList(List<Cell> numbers) {
        this.numbers = numbers;
    }

    public boolean hasDiffNumbers() {
        Set<Cell> set = new HashSet<>(numbers);
        return set.size() == numbers.size();
    }

    public List<Cell> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return "BoarderCellsList{" +
                "numbers=" + numbers +
                '}';
    }
}
