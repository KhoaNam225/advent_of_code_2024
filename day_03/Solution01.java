package day_03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution01 {
  public static void main(String[] args) {
    String filePath = "day_03/input_01.txt";
    try {
      String content = new String(Files.readAllBytes(Paths.get(filePath)));

      ArrayList<String> mulOperations = extractMulPatterns(content);

      long result = 0;
      for (String op : mulOperations) {
        ArrayList<Integer> factors = extractNumbersFromMulOperation(op);

        result += factors.get(0) * factors.get(1);
      }

      System.out.printf("Result %d\n", result);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public static ArrayList<String> extractMulPatterns(String input) {
    ArrayList<String> mulOperations = new ArrayList<>();
    Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
    Matcher matcher = pattern.matcher(input);

    while (matcher.find()) {
      mulOperations.add(matcher.group());
    }

    return mulOperations;
  }

  public static ArrayList<Integer> extractNumbersFromMulOperation(String input) {
    ArrayList<Integer> result = new ArrayList<>();
    Pattern pattern = Pattern.compile("\\d+");
    Matcher matcher = pattern.matcher(input);

    while (matcher.find()) {
      result.add(Integer.parseInt(matcher.group()));
    }

    return result;
  }
}
