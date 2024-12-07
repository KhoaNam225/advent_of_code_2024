package day_01;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution02 {
  public static void main(String[] args) {
    String filePath = "day_01/input_01.txt";
    try {
      String content = new String(Files.readAllBytes(Paths.get(filePath)));

      String[] lines = content.split("\n");

      int[] locationsOne = new int[lines.length];
      int[] locationsTwo = new int[lines.length];

      File inputFile = new File(filePath);
      Scanner scanner = new Scanner(inputFile);

      for (int i = 0; i < lines.length; i++) {
        locationsOne[i] = scanner.nextInt();
        locationsTwo[i] = scanner.nextInt();
      }

      Map<Integer, Integer> locationsTwoOccurrences = countOccurrences(locationsTwo);

      int distance = 0;
      for (int i = 0; i < locationsOne.length; i++) {
        int locationTwoOccurrenceCount = locationsTwoOccurrences.getOrDefault(locationsOne[i], 0);
        distance += locationsOne[i] * locationTwoOccurrenceCount;
      }

      System.out.println("Result " + distance);
      scanner.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  private static Map<Integer, Integer> countOccurrences(int[] nums) {
    Map<Integer, Integer> occurrences = new HashMap<>();

    for (Integer num : nums) {
      int count = occurrences.getOrDefault(num, 0);
      occurrences.put(num, count + 1);
    }

    return occurrences;
  }
}