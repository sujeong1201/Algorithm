import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int V, roads[][];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    V = Integer.parseInt(st.nextToken());
    int E = Integer.parseInt(st.nextToken());
    roads = new int[V + 1][V + 1];
    while (E-- > 0) {
      st = new StringTokenizer(br.readLine());
      roads[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] =
          Integer.parseInt(st.nextToken());
    }

    System.out.println(floydWarshall());
  }

  private static int floydWarshall() {
    int[][] distance = new int[V + 1][V + 1];
    for (int i = 1; i <= V; i++) Arrays.fill(distance[i], Integer.MAX_VALUE);

    // 자기 자신으로 가는 비용 0으로 초기화
    for (int i = 1; i <= V; i++) {
      distance[i][i] = 0;
    }

    // 각 간선에 대한 정보를 입력받아 그 값으로 초기화
    for (int i = 1; i <= V; i++) {
      for (int j = 1; j <= V; j++) {
        if (roads[i][j] > 0) distance[i][j] = roads[i][j];
      }
    }

    // Dab = min(Dab, Dak + Dkb)
    for (int k = 1; k <= V; k++) { // 경유지
      for (int a = 1; a <= V; a++) {
        for (int b = 1; b <= V; b++) {
          if (distance[a][k] + distance[k][b] > 0)
            distance[a][b] = Math.min(distance[a][b], distance[a][k] + distance[k][b]);
        }
      }
    }

    int min = Integer.MAX_VALUE;
    for (int i = 1; i <= V; i++) {
      for (int j = 1; j <= V; j++) {
        if (i == j) continue;
        if (roads[j][i] > 0
            && distance[i][j] != Integer.MAX_VALUE
            && min > distance[i][j] + roads[j][i]) min = distance[i][j] + roads[j][i];
      }
    }

    return min == Integer.MAX_VALUE ? -1 : min;
  }
}
