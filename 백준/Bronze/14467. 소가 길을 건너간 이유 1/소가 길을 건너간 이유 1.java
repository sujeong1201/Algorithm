import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] cow = new int[11];
        Arrays.fill(cow, -1);

        int answer = 0;
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cowNo = Integer.parseInt(st.nextToken());
            int position = Integer.parseInt(st.nextToken());

            if(cow[cowNo] != -1 && cow[cowNo] != position) answer++;
            cow[cowNo] = position;
        }

        System.out.println(answer);
    }
}
