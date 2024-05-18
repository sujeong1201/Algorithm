import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int X = sc.nextInt();
    int Y = sc.nextInt();
    int W = sc.nextInt();
    int S = sc.nextInt();

    long minTime;
    if (S > 2 * W) {
      minTime = (long) Math.min(X, Y) * 2 * W;
    } else {
      minTime = (long) Math.min(X, Y) * S;
    }

    int remain = Math.abs(X - Y);
    if (S < W) {
      minTime += (long) (remain / 2) * 2 * S + (remain % 2) * W;
    } else {
      minTime += (long) remain * W;
    }

    System.out.println(minTime);
  }
}
