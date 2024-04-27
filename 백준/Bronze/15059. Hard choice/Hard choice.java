import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int[] available = new int[3];
    for (int i = 0; i < 3; i++) available[i] = sc.nextInt();

    int answer = 0;
    for (int i = 0; i < 3; i++) {
      int request = sc.nextInt();
      if (request > available[i]) answer += (request - available[i]);
    }

    System.out.println(answer);
  }
}
