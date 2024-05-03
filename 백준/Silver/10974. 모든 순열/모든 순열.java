import java.util.Scanner;

public class Main {
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();

    int[] numbers = new int[N];
    boolean[] selected = new boolean[N + 1];
    permutation(numbers, selected, 0, N);
    System.out.println(sb);
  }

  private static void permutation(int[] numbers, boolean[] selected, int cnt, int N) {
    if (cnt == N) {
      for (int i = 0; i < N; i++) {
        sb.append(numbers[i] + " ");
      }
      sb.append("\n");

      return;
    }

    for (int i = 1; i <= N; i++) {
      if (selected[i]) continue;

      selected[i] = true;
      numbers[cnt] = i;
      permutation(numbers, selected, cnt + 1, N);
      selected[i] = false;
    }
  }
}
