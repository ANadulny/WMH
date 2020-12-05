package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Column {
    List<Integer> numbers;

    public Column(List<Integer> numbers) {
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
        return "Column{" +
                "numbers=" + numbers +
                '}';
    }
}
