import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());

    if (N == R) {
      System.out.println("*");
      System.exit(0);
    }

    boolean[] live = new boolean[N + 1];
    for (int i = 0; i < R; i++) {
      live[Integer.parseInt(st.nextToken())] = true;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= N; i++) if (!live[i]) sb.append(i + " ");
    System.out.println(sb);
  }
}
