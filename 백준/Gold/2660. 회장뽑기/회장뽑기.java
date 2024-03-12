import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static boolean relation[][];
    static int minScore;
    static List<Integer> candidates;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        relation = new boolean[N + 1][N + 1];
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a == -1) break;
            relation[a][b] = relation[b][a] = true;
        }

        minScore = Integer.MAX_VALUE;
        candidates = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            int score = bfs(i);
            if(score == minScore) {
                candidates.add(i);
            } else if(score < minScore) {
                minScore = score;
                candidates.clear();
                candidates.add(i);
            }
        }

        System.out.println(minScore + " " + candidates.size());
        for(Integer candidate : candidates) System.out.print(candidate + " ");
    }

    public static int bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        queue.offer(start);
        visited[start] = true;

        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int s = 0; s < size; s++) {
                int cur = queue.poll();

                for(int i = 1; i <= N; i++) {
                    if(visited[i]) continue;
                    if(relation[cur][i]) {
                        queue.offer(i);
                        visited[i] = true;
                    }
                }
            }

            level++;
        }

        return level - 1;
    }
}
