import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[] accSum = new long[N + 1];
		
		st = new StringTokenizer(br.readLine());
		long sum = 0;
		for(int n=1; n<=N; n++) {
			sum += Integer.parseInt(st.nextToken());
			accSum[n] = sum;
		}
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			sb.append(accSum[j] - accSum[i - 1] + "\n");
		}
		System.out.println(sb);
	}
}
