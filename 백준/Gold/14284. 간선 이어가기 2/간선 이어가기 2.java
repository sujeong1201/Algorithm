import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, start, target;
    static int edges[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edges = new int[n + 1][n + 1];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[a][b] = edges[b][a] = weight;
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        // ===== 입력 끝 =====

        int answer = dijkstra();
        System.out.println(answer);
    }

    public static int dijkstra() {
        int[] distance = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        int min, current;
        for(int i = 1; i <= n; i++) {
            // 경유지 찾기
            current = -1;
            min = Integer.MAX_VALUE;

            for(int j = 1; j <= n; j++) {
                if(!visited[j] && distance[j] < min) {
                    current = j;
                    min = distance[j];
                }
            }

            if(current == -1) break;
            visited[current] = true;
            if(current == target) break;

            // 거리 최소값 갱신
            for(int j = 1; j <= n; j++) {
                if(!visited[j] && edges[current][j] != 0 && min + edges[current][j] < distance[j]) {
                    distance[j] = min + edges[current][j];
                }
            }
        }

        return distance[target];
    }
}
