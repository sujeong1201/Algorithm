import java.io.*;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int[][] a = new int[N][M];
    int[][] b = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) a[i][j] = Integer.parseInt(st.nextToken());
    }
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) b[i][j] = Integer.parseInt(st.nextToken());
    }
    br.close();

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) bw.write(a[i][j] + b[i][j] + " ");
      bw.write("\n");
    }

    bw.flush();
    bw.close();
  }
}
