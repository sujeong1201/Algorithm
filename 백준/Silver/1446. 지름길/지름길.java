import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, D, road[][];
    static int map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        road = new int[N][3];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) road[i][j] = Integer.parseInt(st.nextToken());
        }

        map = new int[D + 1][D + 1];
        for(int i = 0; i <= D; i++) Arrays.fill(map[i], -1);
        for(int i = 0; i < N; i++) {
            int start = road[i][0];
            int end = road[i][1];
            if(end > D || (map[start][end] != -1 && map[start][end] <= road[i][2])) continue;

            map[start][end] = road[i][2];
        }
        for(int i = 0; i < D; i++) {
            if(map[i][i + 1] == -1 || map[i][i + 1] > 1) map[i][i + 1] = 1;
        }

        System.out.println(dijkstra());
    }

    private static int dijkstra() {
        int[] distance = new int[D + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> Integer.compare(arr1[1], arr2[1]));

        distance[0] = 0;
        pq.offer(new int[] {0, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(cur[1] > distance[cur[0]]) continue;

            for(int i = cur[0] + 1; i <= D; i++) {
                if(map[cur[0]][i] >= 0 && cur[1] + map[cur[0]][i] < distance[i]) {
                    distance[i] = cur[1] + map[cur[0]][i];
                    pq.offer(new int[] {i, distance[i]});
                }
            }
        }

        return distance[D];
    }
}
