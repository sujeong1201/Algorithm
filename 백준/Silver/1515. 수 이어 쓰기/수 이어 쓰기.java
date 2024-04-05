import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String numStr = sc.nextLine();

    int index = 0;
    int num = 1;
    while (index < numStr.length()) {
      String numToString = Integer.toString(num);
      for (int l = numToString.length(); l > 0; l--) {
        int lastIndex = index + l;
        if (lastIndex <= numStr.length()) {
          String subStr = numStr.substring(index, lastIndex);
          if (isContained(Integer.toString(num), subStr)) {
            index = lastIndex;
            break;
          }
        }
      }

      num++;
    }

    System.out.println(num - 1);
  }

  private static boolean isContained(String string, String subStr) {
    int stringIndex = 0;

    outer:
    for (int i = 0; i < subStr.length(); i++) {
      while (stringIndex < string.length()) {
        if (subStr.charAt(i) == string.charAt(stringIndex++)) {
          continue outer;
        }
      }
      return false;
    }

    return true;
  }
}
