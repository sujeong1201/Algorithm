import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K, X;
    static List<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // N이 최대 30,000이기 때문에 인접행렬로 하면 메모리초과! 인접리스트로 해야함!
        adjList = new List[N + 1];
        for(int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjList[start].add(end);
        }

        List<Integer> answerList = bfs();
        if(answerList.size() == 0) {
            System.out.println(-1);
        } else {
            Collections.sort(answerList);
            for(int i = 0; i < answerList.size(); i++) System.out.println(answerList.get(i));
        }
    }

    static List<Integer> bfs() {
        List<Integer> answerList = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        // 출발 지점 넣기
        queue.offer(X);
        visited[X] = true;

        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            // 최단거리가 K인 도시들
            if(level == K) {
                for(int s = 0; s < size; s++) answerList.add(queue.poll());
                break;
            }

            for(int s = 0; s < size; s++) {
                int cur = queue.poll();

                for(Integer adjNode : adjList[cur]) {
                    if(visited[adjNode]) continue;

                    queue.offer(adjNode);
                    visited[adjNode] = true;
                }
            }

            level++;
        }

        return answerList;
    }
}
