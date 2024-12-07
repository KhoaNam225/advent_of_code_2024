package day_03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution02 {
  private static final String DO = "do()";
  private static final String DONT = "don't()";

  public static void main(String[] args) {
    String filePath = "day_03/input_01.txt";
    try {
      String content = new String(Files.readAllBytes(Paths.get(filePath)));

      ArrayList<String> enabledStrings = filterEnableMulOperations(content);

      long result = 0;
      for (String enabledOps : enabledStrings) {
        ArrayList<String> mulOperations = extractMulPatterns(enabledOps);

        for (String op : mulOperations) {
          ArrayList<Integer> factors = extractNumbersFromMulOperation(op);

          result += factors.get(0) * factors.get(1);
        }
      }

      System.out.printf("Result %d\n", result);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  private static ArrayList<String> filterEnableMulOperations(String input) {
    String controlFlowRegex = "do\\(\\)|don't\\(\\)";
    ArrayList<String> enabledOps = new ArrayList<>();

    Pattern controlFlowPattern = Pattern.compile(controlFlowRegex);
    Matcher matcher = controlFlowPattern.matcher(input);

    boolean isEnable = true;
    int startSearchIdx = 0;
    while (matcher.find()) {
      String matchedControlFlow = matcher.group();

      if (isEnable) {
        enabledOps.add(input.substring(startSearchIdx, matcher.start()));
      }
      System.out.printf("Matched %s\n", matchedControlFlow);
      isEnable = matchedControlFlow.equals(DO);
      startSearchIdx = matcher.end();
    }

    if (isEnable) {
      enabledOps.add(input.substring(startSearchIdx));
    }

    return enabledOps;
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
