import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] bus = new int[N + 1][N + 1];
        for(int i = 0; i <= N; i++) Arrays.fill(bus[i], -1);

        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if(bus[a][b] != -1 && bus[a][b] < cost) continue;
            bus[a][b] = cost;
        }

        st = new StringTokenizer(br.readLine());
        int start =Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(N, bus, start, end));
    }

    private static long dijkstra(int N, int[][] bus, int start, int end) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((arr1, arr2) -> Long.compare(arr1[1], arr2[1]));
        long[] distance = new long[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        // 시작 지점
        distance[start] = 0;
        pq.offer(new long[] {start, distance[start]});

        while(!pq.isEmpty()) {
            long[] current = pq.poll();
            long min = current[1];
            int curNum = (int) current[0];
            if(min > distance[curNum]) continue;

            if(curNum == end) break;

            for(int i = 1; i <= N; i++) {
                if(bus[curNum][i] >= 0 && min + bus[curNum][i] < distance[i]) {
                    distance[i] = min + bus[curNum][i];
                    pq.offer(new long[] {i, distance[i]});
                }
            }
        }

        return distance[end];
    }
}
