import java.io.*;
import java.util.*;

public class Main {
  // 음의 가중치 포함 -> 벨만포드 알고리즘 사용
  static int N, M, buses[][];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    buses = new int[M][3];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int time = Integer.parseInt(st.nextToken());
      buses[i] = new int[] {start, end, time};
    }
    br.close();

    long[] distance = bellmanFord();
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    if (distance == null) {
      bw.write("-1\n");
    } else {
      for (int i = 2; i <= N; i++) {
        bw.write((distance[i] == Long.MAX_VALUE ? -1 : distance[i]) + "\n");
      }
    }
    bw.flush();
    bw.close();
  }

  private static long[] bellmanFord() {
    long[] distance = new long[N + 1];
    Arrays.fill(distance, Long.MAX_VALUE);
    distance[1] = 0;

    for (int i = 0; i < N - 1; i++) {
      for (int j = 0; j < M; j++) {
        int[] bus = buses[j];
        if (distance[bus[0]] != Long.MAX_VALUE && distance[bus[1]] > distance[bus[0]] + bus[2]) {
          distance[bus[1]] = distance[bus[0]] + bus[2];
        }
      }
    }

    for (int j = 0; j < M; j++) {
      int[] bus = buses[j];
      if (distance[bus[0]] != Long.MAX_VALUE && distance[bus[1]] > distance[bus[0]] + bus[2]) {
        return null;
      }
    }

    return distance;
  }
}
