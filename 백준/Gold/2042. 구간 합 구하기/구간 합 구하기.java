import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static long data[], tree[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		data = new long[N + 1];
		tree = new long[N + 1];
		
		for (int i = 1; i <= N; i++) {
			data[i] = Long.parseLong(br.readLine());
			update(i, data[i]);
		}

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int choose = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			
			if(choose == 1) {
				long b = Long.parseLong(st.nextToken());
				update(a, b - data[a]);
				data[a] = b;
			} else if(choose == 2) {
				int b = Integer.parseInt(st.nextToken());
				sb.append(partialSum(a, b) + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	public static void update(int i, long data) {
		int idx = i;
		while(idx <= N) {
			tree[idx] += data;
			idx += (idx & -idx);
		}
	}
	
	public static long partialSum(int start, int end) {
		return getSum(end) - getSum(start - 1);
	}

	private static long getSum(int i) {
		int idx = i;
		long sum = 0;
		while(idx > 0) {
			sum += tree[idx];
			idx -= (idx & -idx);
		}
		
		return sum;
	}
}
