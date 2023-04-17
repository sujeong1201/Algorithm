import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, S, data[];
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		data = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) data[i] = Integer.parseInt(st.nextToken());
		
		subset();
		System.out.println(answer);
	}

	private static void subset() {
		for(int i = 1; i < (1<<N); i++) {
			int sum = 0;
			for(int j = 0; j < N; j++) {
				if((i & (1<<j)) != 0) sum += data[j];
			}
			
			if(sum == S) answer++;
		}
	}
}
