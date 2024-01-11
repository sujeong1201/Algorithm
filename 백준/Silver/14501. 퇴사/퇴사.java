import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, maxPrice;
    static List<int[][]> consulting;  // [필요한 날의 배열], 금액

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        consulting = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            if(i + time - 1 > N) continue;  // 상담 일수가 퇴사 일을 넘어가면 저장 안함

            int[][] newConsulting = new int[2][];
            int[] timeArray = new int[time];
            for(int j = 0; j < time; j++) timeArray[j] = i + j;
            newConsulting[0] = timeArray;
            newConsulting[1] = new int[] {price};
            consulting.add(newConsulting);
        }

        boolean[] isFilled = new boolean[N + 1];
        recur(0, isFilled, 0);

        System.out.println(maxPrice);
    }

    private static void recur(int cnt, boolean[] isFilled, int priceSum) {
        if(cnt == consulting.size()) {
            if(priceSum > maxPrice) maxPrice = priceSum;

            return;
        }

        // 해당 상담 선택O
        int[][] curConsulting = consulting.get(cnt);
        if(!isFilled[curConsulting[0][0]]) {
            for(int i = 0; i < curConsulting[0].length; i++) {
                isFilled[curConsulting[0][i]] = true;
            }
            recur(cnt + 1, isFilled, priceSum + curConsulting[1][0]);
            for(int i = 0; i < curConsulting[0].length; i++) {
                isFilled[curConsulting[0][i]] = false;
            }
        }

        // 해당 상담 선택X
        recur(cnt + 1, isFilled, priceSum);
    }
}
