import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();
    int length = str.length();

    int oneCnt = 0;
    int zeroCnt = 0;
    for (int i = 0; i < length; i++) {
      if (str.charAt(i) == '0') zeroCnt++;
      else oneCnt++;
    }

    oneCnt /= 2;
    zeroCnt /= 2;
    boolean[] check = new boolean[length];
    for (int i = 0; i < length; i++) {
      if (str.charAt(i) == '0') {
        check[i] = true;
        zeroCnt--;
        if (zeroCnt == 0) break;
      }
    }
    for (int i = length - 1; i >= 0; i--) {
      if (str.charAt(i) == '1') {
        check[i] = true;
        oneCnt--;
        if (oneCnt == 0) break;
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      if (check[i]) sb.append(str.charAt(i));
    }
    System.out.println(sb);
  }
}
