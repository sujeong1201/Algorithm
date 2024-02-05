import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static boolean ableToGo[];
    static List<int[]> adjList[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ableToGo = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) ableToGo[i] = st.nextToken().equals("0") ? true : false;
        ableToGo[N - 1] = true;

        adjList = new ArrayList[N];
        for(int i = 0; i < N; i++) adjList[i] = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            adjList[a].add(new int[] {b, t});
            adjList[b].add(new int[] {a, t});
        }

        System.out.println(dijkstra());
    }

    private static long dijkstra() {
        PriorityQueue<long[]> pq = new PriorityQueue<>((arr1, arr2) -> Long.compare(arr1[1], arr2[1]));
        long[] distance = new long[N];
        Arrays.fill(distance, Long.MAX_VALUE);

        distance[0] = 0;
        pq.offer(new long[] {0, distance[0]});

        while(!pq.isEmpty()) {
            long[] cur = pq.poll();
            int curPoint = (int) cur[0];
            long curDist = cur[1];
            if(curPoint == N - 1) break;
            if(curDist > distance[curPoint]) continue;

            for(int i = 0; i < adjList[curPoint].size(); i++) {
                int[] next = adjList[curPoint].get(i);
                if(!ableToGo[next[0]]) continue;

                if(distance[next[0]] > curDist + next[1]) {
                    distance[next[0]] = curDist + next[1];
                    pq.offer(new long[] {next[0], distance[next[0]]});
                }
            }
        }

        return distance[N - 1] == Long.MAX_VALUE ? -1 : distance[N - 1];
    }
}
