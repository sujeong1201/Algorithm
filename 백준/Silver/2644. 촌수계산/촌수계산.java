import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, targetA, targetB;
    static int[][] relations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        targetA = Integer.parseInt(st.nextToken());
        targetB = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        relations = new int[N + 1][N + 1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            relations[parent][child] = relations[child][parent] = 1;
        }

        int answer = bfs();
        System.out.println(answer);
    }

    public static int bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        queue.offer(targetA);
        visited[targetA] = true;

        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int current = queue.poll();
                if(current == targetB) return level;

                for(int j = 1; j <= N; j++) {
                    if(visited[j]) continue;
                    if(relations[current][j] == 1) {
                        queue.offer(j);
                        visited[j] = true;
                    }
                }
            }

            level++;
        }

        return -1;
    }
}