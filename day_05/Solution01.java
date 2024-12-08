package day_05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Solution01 {
  public static void main(String[] args) {
    String orderFilePath = "day_05/order_01.txt";
    String updatesFilePath = "day_05/updates_01.txt";

    try {
      String orderContent = new String(Files.readAllBytes(Paths.get(orderFilePath)));
      String updatesContent = new String(Files.readAllBytes(Paths.get(updatesFilePath)));

      HashSet<String> orderSet = createOrderSet(orderContent);
      ArrayList<ArrayList<String>> allUpdates = parseAllUpdates(updatesContent);

      int result = 0;
      for (ArrayList<String> update : allUpdates) {
        if (isValidUpdate(orderSet, update)) {
          result += getMiddlePage(update);
        }
      }
      System.out.printf("Result %d\n", result);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  private static HashSet<String> createOrderSet(String orderContent) {
    HashSet<String> orderSet = new HashSet<>(Arrays.asList(orderContent.split("\n")));

    return orderSet;
  }

  private static ArrayList<ArrayList<String>> parseAllUpdates(String updatesContent) {
    ArrayList<ArrayList<String>> allUpdates = new ArrayList<>();

    String[] rows = updatesContent.split("\n");
    for (String row : rows) {
      allUpdates.add(new ArrayList<String>(Arrays.asList(row.split(","))));
    }

    return allUpdates;
  }

  private static boolean isValidUpdate(HashSet<String> orderSet, ArrayList<String> update) {
    for (int i = 0; i < update.size() - 1; i++) {
      for (int j = i + 1; j < update.size(); j++) {
        String firstPage = update.get(i);
        String secondPage = update.get(j);

        if (orderSet.contains(String.format("%s|%s", secondPage, firstPage)))
          return false;
      }
    }

    return true;
  }

  private static int getMiddlePage(ArrayList<String> update) {
    return Integer.parseInt(update.get(update.size() / 2));
  }
}
