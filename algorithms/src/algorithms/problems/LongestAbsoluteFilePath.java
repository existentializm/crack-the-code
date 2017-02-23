/**
 * 
 */
package algorithms.problems;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Sudharsanan Muralidharan
 */
public class LongestAbsoluteFilePath {

  private static int findLongestAbsoluteFilePath(String input) {
    Stack<String> stack = new Stack<String>();
    String[] lines = input.split("\n");
    int longest = 0;
    StringBuilder builder = new StringBuilder();
    stack.push("/");
    builder.append("/");

    for (int i = 0; i < lines.length; i++) {
      String fileName = lines[i];
      while (level(fileName) <= level(stack.peek())) {
        String top = stack.pop().trim();
        builder.setLength(builder.length() - top.length());
      }

      stack.push(fileName);
      builder.append(fileName.trim());
      if (isImgFile(fileName.trim()) && builder.length() > longest) {
        longest = builder.length();
      }
    }

    return longest;
  }

  private static boolean isImgFile(String fileName) {
    return fileName.indexOf('.') != -1 && fileName.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)");
  }

  private static int level(String fileName) {
    if(fileName.equals("/")) {
      return -1;
    }

    return fileName.lastIndexOf(' ') + 1;
  }

  public static void main(String[] args) throws FileNotFoundException {
    Scanner in = new Scanner(new FileReader("input_files/test_longest_abs_file_path"));
    StringBuilder inputBuilder = new StringBuilder();
    while (in.hasNextLine()) {
      inputBuilder.append(in.nextLine()).append("\n");
    }

    String[] inputArr = inputBuilder.toString().split("---");

    for (String input : inputArr) {
      System.out.println(findLongestAbsoluteFilePath(input));
    }

    in.close();
  }
}
