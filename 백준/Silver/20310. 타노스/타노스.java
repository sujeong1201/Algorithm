import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s = sc.nextLine();
    char[] chArr = s.toCharArray();
    int oneCnt = 0;
    int zeroCnt = 0;
    for (int i = 0; i < chArr.length; i++) {
      if (chArr[i] == '0') zeroCnt++;
      else oneCnt++;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < zeroCnt / 2; i++) sb.append('0');
    for (int i = 0; i < oneCnt / 2; i++) sb.append('1');
    System.out.println(sb);
  }
}
