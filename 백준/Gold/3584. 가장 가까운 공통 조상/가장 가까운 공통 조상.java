import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int parent[];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    while (T-- > 0) {
      N = Integer.parseInt(br.readLine());
      parent = new int[N + 1];
      for (int i = 0; i < N - 1; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        parent[b] = a;
      }

      StringTokenizer st = new StringTokenizer(br.readLine());
      int node1 = Integer.parseInt(st.nextToken());
      int node2 = Integer.parseInt(st.nextToken());
      bw.write(getCommonParent(node1, node2) + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }

  private static int getCommonParent(int node1, int node2) {
    boolean[] check = new boolean[N + 1];

    int i = node1;
    while (true) {
      if (i == 0) break;
      check[i] = true;
      i = parent[i];
    }

    i = node2;
    while (true) {
      if (check[i]) return i;
      i = parent[i];
    }
  }
}
