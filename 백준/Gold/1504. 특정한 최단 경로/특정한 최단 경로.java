import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, graph[][], essential[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = graph[b][a] = c;
        }

        essential = new int[2];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2; i++) essential[i] = Integer.parseInt(st.nextToken());

        int distance1 = -1;
        int d1 = dijkstra(1, essential[0]);
        int d2 = dijkstra(essential[0], essential[1]);
        int d3 = dijkstra(essential[1], N);
        if(d1 != -1 && d2 != -1 && d3 != -1) distance1 = d1 + d2 + d3;

        int distance2 = -1;
        int d21 = dijkstra(1, essential[1]);
        int d22 = dijkstra(essential[1], essential[0]);
        int d23 = dijkstra(essential[0], N);
        if(d21 != -1 && d22 != -1 && d23 != -1) distance2 = d21 + d22 + d23;

        System.out.println(distance1 == -1 ? (distance2 == -1 ? -1 : distance2) : (distance2 == -1 ? distance1 : Math.min(distance1, distance2)));
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> arr1[1] - arr2[1]);
        int[] distance = new int[N + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        pq.offer(new int[] {start, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(cur[0] == end) break;
            if(distance[cur[0]] < cur[1]) continue;

            for(int i = 1; i <= N; i++) {
                if(graph[cur[0]][i] > 0 && cur[1] + graph[cur[0]][i] < distance[i]) {
                    distance[i] = cur[1] + graph[cur[0]][i];
                    pq.offer(new int[] {i, distance[i]});
                }
            }
        }

        return distance[end] == Integer.MAX_VALUE ? -1 : distance[end];
    }
}
