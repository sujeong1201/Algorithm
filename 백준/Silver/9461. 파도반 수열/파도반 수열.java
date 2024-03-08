import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			long[] arr = new long[N + 1];
			
			for(int i=1; i<=N; i++) {
				if(i == 1) {
					arr[i] = 1;
					continue;
				} else if(i == 2) {
					arr[i] = 1;
					continue;
				} else if(i == 3) {
					arr[i] = 1;
					continue;
				} else if(i == 4) {
					arr[i] = 2;
					continue;
				} else if(i == 5) {
					arr[i] = 2;
					continue;
				}
				
				arr[i] = arr[i - 1] + arr[i - 5];
			}
			
			System.out.println(arr[N]);
		}
	}
}