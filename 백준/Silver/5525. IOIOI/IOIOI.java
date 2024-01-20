import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int answer = 0;
        int index = 0;
        for(; index < M; index++) {
            if(str.charAt(index) == 'O') continue;
            
            int no = 0;
            while(true) {
                if(index + 1 < M && str.charAt(index + 1) == 'O' &&
                        index + 2 < M && str.charAt(index + 2) == 'I') {
                    no++;
                    index += 2;
                } else {
                    break;
                }
            }

            if(no >= N) answer += (no - N + 1);
        }

        System.out.println(answer);
    }
}
