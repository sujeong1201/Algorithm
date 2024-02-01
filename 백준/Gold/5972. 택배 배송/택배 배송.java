import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<int[]> adjList[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new int[] {b, c});
            adjList[b].add(new int[] {a, c});
        }

        System.out.println(dijkstra());
    }

    private static int dijkstra() {
        int[] distance = new int[N + 1];
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((arr1, arr2) -> arr1[1] - arr2[1]);

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        priorityQueue.offer(new int[] {1, 0});

        while(!priorityQueue.isEmpty()) {
            int cur[] = priorityQueue.poll();

            if(cur[0] == N) break;

            for(int i = 0; i < adjList[cur[0]].size(); i++) {
                int[] next = adjList[cur[0]].get(i);
                if(cur[1] + next[1] < distance[next[0]]) {
                    distance[next[0]] = cur[1] + next[1];
                    priorityQueue.offer(new int[] {next[0], distance[next[0]]});
                }
            }
        }

        return distance[N];
    }
}
