package day_04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution01 {
  public static void main(String[] args) {
    String filePath = "day_04/input_01.txt";
    try {
      String content = new String(Files.readAllBytes(Paths.get(filePath)));

      char[][] charMatrix = convertToCharMatrix(content);

      ArrayList<String> rows = new ArrayList<>(Arrays.asList(content.split("\n")));
      ArrayList<String> cols = collectStringAtColumns(charMatrix);
      ArrayList<String> diagLeftToRight = collectStringAtLeftToRightDiag(charMatrix);
      ArrayList<String> diagRightToLeft = collectStringAtRightToLeftDiag(charMatrix);

      ArrayList<String> allSubstrings = new ArrayList<>();
      allSubstrings.addAll(rows);
      allSubstrings.addAll(cols);
      allSubstrings.addAll(diagLeftToRight);
      allSubstrings.addAll(diagRightToLeft);

      int result = 0;
      for (String substr : allSubstrings) {
        result += countXMASOccurrences(substr);
      }

      System.out.printf("Result %d\n", result);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  private static int countXMASOccurrences(String str) {
    Pattern forwardPattern = Pattern.compile("XMAS");
    Matcher forwardMatcher = forwardPattern.matcher(str);

    Pattern backwardPattern = Pattern.compile("SAMX");
    Matcher backwardMatcher = backwardPattern.matcher(str);
    return (int) (forwardMatcher.results().count() + backwardMatcher.results().count());
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

  private static ArrayList<String> collectStringAtColumns(char[][] inputMatrix) {
    ArrayList<String> result = new ArrayList<>();

    int rowsNum = inputMatrix.length;
    int colsNum = inputMatrix[0].length;

    for (int col = 0; col < colsNum; col++) {
      StringBuilder stringBuilder = new StringBuilder(rowsNum);
      for (int row = 0; row < rowsNum; row++) {
        stringBuilder.append(inputMatrix[row][col]);
      }

      result.add(stringBuilder.toString());
    }

    return result;
  }

  private static ArrayList<String> collectStringAtLeftToRightDiag(char[][] inputMatrix) {
    ArrayList<String> result = new ArrayList<>();

    int rowsNum = inputMatrix.length;
    int colsNum = inputMatrix[0].length;

    for (int col = 0; col < colsNum; col++) {
      String diagString = getLeftToRightDiagStringAt(inputMatrix, 0, col);
      result.add(diagString);
    }

    for (int row = 1; row < rowsNum; row++) {
      String diagString = getLeftToRightDiagStringAt(inputMatrix, row, 0);
      result.add(diagString);
    }

    return result;
  }

  private static ArrayList<String> collectStringAtRightToLeftDiag(char[][] inputMatrix) {
    ArrayList<String> result = new ArrayList<>();

    int rowsNum = inputMatrix.length;
    int colsNum = inputMatrix[0].length;

    for (int col = 0; col < colsNum; col++) {
      String diagString = getRightToLeftDiagStringAt(inputMatrix, 0, col);
      result.add(diagString);
    }

    for (int row = 1; row < rowsNum; row++) {
      String diagString = getRightToLeftDiagStringAt(inputMatrix, row, colsNum - 1);
      result.add(diagString);
    }

    return result;
  }

  private static String getLeftToRightDiagStringAt(char[][] inputMatrix, int row, int col) {
    StringBuilder stringBuilder = new StringBuilder();

    while (isValidPos(inputMatrix, row, col)) {
      stringBuilder.append(inputMatrix[row][col]);
      row++;
      col++;
    }

    return stringBuilder.toString();
  }

  private static String getRightToLeftDiagStringAt(char[][] inputMatrix, int row, int col) {
    StringBuilder stringBuilder = new StringBuilder();

    while (isValidPos(inputMatrix, row, col)) {
      stringBuilder.append(inputMatrix[row][col]);
      row++;
      col--;
    }

    return stringBuilder.toString();
  }

  private static boolean isValidPos(char[][] inputMatrix, int row, int col) {
    if (row < 0 || row >= inputMatrix.length)
      return false;
    if (col < 0 || col >= inputMatrix[0].length)
      return false;

    return true;
  }
}