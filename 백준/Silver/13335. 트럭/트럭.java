import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] truck = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) truck[i] = Integer.parseInt(st.nextToken());

        System.out.println(solve(N, W, L, truck));
    }

    private static int solve(int n, int w, int l, int[] truck) {
        Queue<Integer> waitingQ = new ArrayDeque<>();
        for(int i = 0; i < n; i++) waitingQ.offer(truck[i]);

        List<int[]> bridge = new ArrayList<>();
        int time = 0;
        int weightSum = 0;

        while(!waitingQ.isEmpty()) {
            time++;
            for(int i = 0; i < bridge.size(); i++) {
                bridge.get(i)[1]++;
            }
            if(bridge.size() > 0 && bridge.get(0)[1] == w) {
                weightSum -= bridge.get(0)[0];
                bridge.remove(0);
            }
            if(bridge.size() == w) continue;

            int curTruck = waitingQ.peek();
            if(weightSum + curTruck <= l) {
                bridge.add(new int[] {curTruck, 0});
                weightSum += waitingQ.poll();
            }
        }

        int additionalTime = bridge.size() > 0 ? w - bridge.get(bridge.size() - 1)[1] : 0;
        return time + additionalTime;
    }
}
