import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Integer> relation[];
    static boolean answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        relation = new ArrayList[N];
        for(int i = 0; i < N; i++) relation[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relation[a].add(b);
            relation[b].add(a);
        }

        answer = false;
        for(int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            dfs(visited, i, 0);
            if(answer) break;
        }

        System.out.println(answer ? 1 : 0);
    }

    private static void dfs(boolean[] visited, int num, int cnt) {
        visited[num] = true;
        if(cnt == 4) {
            answer = true;
            return;
        }

        for(int friend: relation[num]) {
            if(answer) return;
            if(visited[friend]) continue;
            dfs(visited, friend, cnt + 1);
        }

        visited[num] = false;
    }
}
