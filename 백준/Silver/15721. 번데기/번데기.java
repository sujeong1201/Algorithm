import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A = Integer.parseInt(br.readLine());
        int T = Integer.parseInt(br.readLine());
        int what = Integer.parseInt(br.readLine());

        int answer = 0;
        int cnt = 0;  // 뻔or데기를 세기 위한 변수
        int round = 1;  // 몇회차 문장인지

        outer:
        while(true) {
            for(int i = 0; i < (round + 1) * 2 + 4; i++) {
                if(i == 0 || i == 2 || (i >= 4 && i <= round + 4)) {  // 뻔 차례
                    if(what == 0) cnt++;
                }
                else if(i == 1 || i == 3 || (i > round + 4)) {  // 데기 차례
                    if(what == 1) cnt++;
                }

                if(cnt == T) {
                    break outer;
                }
                answer++;
            }

            round++;
        }

        System.out.println(answer % A);
    }
}