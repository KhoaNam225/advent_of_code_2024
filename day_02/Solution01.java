package day_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Solution01 {
  public static void main(String[] args) {
    String filePath = "day_02/input_01.txt";
    try {
      String content = new String(Files.readAllBytes(Paths.get(filePath)));
      String[] reports = content.split("\n");

      int safeReportsCount = 0;
      for (String report : reports) {
        if (isSafe(report))
          safeReportsCount++;
      }

      System.out.printf("Result: %d\n", safeReportsCount);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  private static boolean isSafe(String report) {
    int[] levels = Arrays.stream(report.split(" ")).mapToInt(Integer::parseInt).toArray();

    for (int excludedLevel = 0; excludedLevel < levels.length; excludedLevel++) {
      int[] newLevels = excludeReport(levels, excludedLevel);
      boolean isSafe = checkSafeWithoutRemove(newLevels);

      if (isSafe)
        return true;
    }

    return false;
  }

  private static boolean checkSafeWithoutRemove(int[] levels) {
    boolean descending = isDescending(levels);
    boolean ascending = isAscending(levels);

    if (!descending && !ascending)
      return false;

    return isGraduallyDecreaseOrIncrease(levels);
  }

  private static int[] excludeReport(int[] levels, int excludeIndex) {
    if (excludeIndex == levels.length)
      return levels;

    int[] newLevels = new int[levels.length - 1];

    for (int i = 0, j = 0; i < levels.length; i++) {
      if (i == excludeIndex)
        continue;

      newLevels[j] = levels[i];
      j++;
    }

    return newLevels;
  }

  private static boolean isAscending(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] <= nums[i - 1])
        return false;
    }

    return true;
  }

  private static boolean isDescending(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] >= nums[i - 1])
        return false;
    }

    return true;
  }

  private static boolean isGraduallyDecreaseOrIncrease(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      int diff = Math.abs(nums[i] - nums[i - 1]);
      if (diff < 1 || diff > 3)
        return false;
    }

    return true;
  }
}
