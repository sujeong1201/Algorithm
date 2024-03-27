import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int H = sc.nextInt();
    int W = sc.nextInt();
    int N = sc.nextInt();
    int M = sc.nextInt();

    int row;
    if (H % (N + 1) == 0) row = H / (N + 1);
    else row = H / (N + 1) + 1;

    int col;
    if (W % (M + 1) == 0) col = W / (M + 1);
    else col = W / (M + 1) + 1;

    System.out.println(row * col);
  }
}
