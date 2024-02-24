import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        
        int[] result = bfs(N, K);
        System.out.println(result[0] + "\n" + result[1]);
    }

    private static int[] bfs(int N, int K) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[200_001];

        queue.offer(N);
        visited[N] = true;

        boolean flag = false;
        int time = 0;
        int cnt = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> visitedPoint = new ArrayList<>();

            for(int s = 0; s < size; s++) {
                int cur = queue.poll();
                if(cur == K) {
                    flag = true;
                    cnt++;
                }

                for(int d = 0; d < 3; d++) {
                    int next = getNext(cur, d);
                    if(next >= 0 && next <= 200_000 && !visited[next]) {
                        queue.offer(next);
                        visitedPoint.add(next);
                    }
                }
            }

            for(Integer point : visitedPoint) {
                visited[point] = true;
            }
            if(flag) break;
            time++;
        }

        return new int[] {time, cnt};
    }

    private static int getNext(int cur, int d) {
        if(d == 0) return cur - 1;
        if(d == 1) return cur + 1;
        if(d == 2) return cur * 2;
        return -1;
    }
}
