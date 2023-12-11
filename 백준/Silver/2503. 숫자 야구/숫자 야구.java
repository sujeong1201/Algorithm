import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] questions = new int[N][3];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) questions[i][j] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        // 111 ~ 999 다 검사하기
        for(int i = 1; i <= 9; i++) {
            for(int j = 1; j <= 9; j++) {
                if(i == j) continue;
                number:
                for(int k = 1; k <= 9; k++) {
                    if(i == k || j == k) continue;

                    for(int n = 0; n < N; n++) {
                        int strike = 0;
                        int ball = 0;

                        // 첫번째 자리 수
                        int target = questions[n][0] / 100;
                        if(target == i) strike++;
                        else if(target == j || target == k) ball++;

                        // 두번째 자리 수
                        target = (questions[n][0] % 100) / 10;
                        if(target == j) strike++;
                        else if(target == i || target == k) ball++;
                        
                        // 세번째 자리 수
                        target = questions[n][0] % 10;
                        if(target == k) strike++;
                        else if(target == i || target == j) ball++;

                        if(strike != questions[n][1] || ball != questions[n][2]) continue number;
                    }

                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
