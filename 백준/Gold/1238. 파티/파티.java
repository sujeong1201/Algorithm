import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]
                    = Integer.parseInt(st.nextToken());
        }

        int maxTime = 0;
        for(int i = 1; i <= N; i++) {
            int time = dijkstra(i, X) + dijkstra(X, i);
            if(time > maxTime) maxTime = time;
        }

        System.out.println(maxTime);
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> arr1[1] - arr2[1]);
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[start] = 0;
        pq.offer(new int[] {start, distance[start]});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(cur[0] == end) break;
            if(cur[1] > distance[cur[0]]) continue;

            for(int i = 1; i <= N; i++) {
                if(map[cur[0]][i] > 0 && cur[1] + map[cur[0]][i] < distance[i]) {
                    distance[i] = cur[1] + map[cur[0]][i];
                    pq.offer(new int[] {i, distance[i]});
                }
            }
        }

        return distance[end];
    }
}
