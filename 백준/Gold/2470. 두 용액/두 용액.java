import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) data[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(data);

        int front = 0;
        int rear = N - 1;

        int ans = data[front] + data[rear];
        int ansFront = front;
        int ansRear = rear;

        while(front < rear) {
            int sum = data[front] + data[rear];

            if(Math.abs(ans) > Math.abs(sum)) {
                ans = sum;
                ansFront = front;
                ansRear = rear;
            }

            if(sum == 0) break;
            if(sum < 0) {
                front++;
            } else {
                rear--;
            }
        }

        System.out.println(data[ansFront] + " " + data[ansRear]);
    }
}
