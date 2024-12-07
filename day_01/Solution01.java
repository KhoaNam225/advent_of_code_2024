package day_01;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Scanner;

public class Solution01 {
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

      // for (int i = 0; i < lines.length; i++) {
      // System.out.printf("%d %d\n", locationsOne[i], locationsTwo[i]);
      // }

      Arrays.sort(locationsOne);
      Arrays.sort(locationsTwo);

      int distance = 0;
      for (int i = 0; i < locationsOne.length; i++) {
        distance += Math.abs(locationsOne[i] - locationsTwo[i]);
      }

      System.out.println("Result " + distance);
      scanner.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}