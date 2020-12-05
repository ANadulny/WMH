package sudoku.reader;

import dao.Board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuReader {
    private final String SEPARATOR = File.separator;
    private final String PROJECT_PATH = System.getProperty("user.dir") + SEPARATOR + "testingData" + SEPARATOR;

    public Board readSudokuFromFile(String level, String type, String fileName) {
        String filePath = PROJECT_PATH + level + SEPARATOR + type + SEPARATOR + fileName;
        int[][] matrix = new int[9][9];
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            int iteration = 0;
            do {
                String[] elements = line.split("\s");
                List<Integer> numbers = Arrays.stream(elements)
                        .map(row -> isNumeric(row) ? Integer.parseInt(row) : 0)
                        .collect(Collectors.toList());
                for (int i = 0; i < 9; i++) {
                    matrix[iteration][i] = numbers.get(i);
                }
                line = br.readLine();
                iteration++;
            } while (line != null && iteration < 9);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error occur for file " + filePath);
            return new Board(new int[0][0]);
        }
        return new Board(matrix);
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
