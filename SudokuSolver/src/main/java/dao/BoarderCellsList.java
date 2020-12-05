package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoarderCellsList {
    List<Integer> numbers;

    public BoarderCellsList(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public boolean hasDiffNumbers() {
        Set<Integer> set = new HashSet<>(numbers);
        return set.size() == numbers.size();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return "BoarderCellsList{" +
                "numbers=" + numbers +
                '}';
    }
}
