package day_04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution02 {
  public static void main(String[] args) {
    String filePath = "day_04/input_01.txt";
    try {
      String content = new String(Files.readAllBytes(Paths.get(filePath)));

      char[][] charMatrix = convertToCharMatrix(content);
      int result = 0;
      for (int row = 0; row < charMatrix.length - 2; row++) {
        for (int col = 0; col < charMatrix[0].length - 2; col++) {
          if (isXMAS(charMatrix, row, col))
            result++;
        }
      }

      System.out.printf("Result %d\n", result);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  private static char[][] convertToCharMatrix(String content) {
    String[] rows = content.split("\n");
    int rowsNum = rows.length;
    int colsNum = rows[0].length();

    char[][] matrix = new char[rowsNum][colsNum];

    for (int row = 0; row < rowsNum; row++) {
      for (int col = 0; col < colsNum; col++) {
        matrix[row][col] = rows[row].charAt(col);
      }
    }

    return matrix;
  }

  private static boolean isXMAS(char[][] inputMatrix, int row, int col) {
    if (inputMatrix[row + 1][col + 1] != 'A')
      return false;

    Set<Character> validChars = new HashSet<>(Arrays.asList('S', 'M'));
    boolean leftToRightValid = inputMatrix[row][col] != inputMatrix[row + 2][col + 2]
        && validChars.contains(inputMatrix[row][col]) && validChars.contains(inputMatrix[row + 2][col + 2]);

    boolean rightToLeftValid = inputMatrix[row][col + 2] != inputMatrix[row + 2][col]
        && validChars.contains(inputMatrix[row][col + 2]) && validChars.contains(inputMatrix[row + 2][col]);

    return leftToRightValid && rightToLeftValid;
  }
}
