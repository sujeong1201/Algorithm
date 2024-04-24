import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();
    int strLength = str.length();
    int aCnt = 0;
    for (int i = 0; i < strLength; i++) {
      if (str.charAt(i) == 'a') aCnt++;
    }

    int change = 0;
    for (int i = 0; i < aCnt; i++) {
      if (str.charAt(i) == 'b') change++;
    }
    int minChange = change;

    str += str;
    for (int i = 1; i < strLength; i++) {
      if (str.charAt(i - 1) == 'b') change--;
      if (str.charAt(aCnt + i - 1) == 'b') change++;

      if (change < minChange) minChange = change;
    }

    System.out.println(minChange);
  }
}
