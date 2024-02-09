import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<int[]> adjList[];
    static int maxLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();
        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[a].add(new int[] {b, weight});
            adjList[b].add(new int[] {a, weight});
        }

        maxLength = 0;
        for(int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            visited[i] = true;
            bfs(i, visited, 0);
        }
        System.out.println(maxLength);
    }

    private static void bfs(int num, boolean[] visited, int sum) {
        int cnt = 0;

        for(int i = 0; i < adjList[num].size(); i++) {
            int[] cur = adjList[num].get(i);
            if(!visited[cur[0]]) {
                cnt++;
                visited[cur[0]] = true;
                bfs(cur[0], visited, sum + cur[1]);
                visited[cur[0]] = false;
            }
        }

        if(cnt == 0 && sum > maxLength) {
            maxLength = sum;
        }
    }
}
