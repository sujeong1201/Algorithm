import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] lsi = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		lsi[0] = 1;
		int ans = lsi[0];
		for(int i = 0; i < N; i++) {
			int max = 0;
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i] && max < lsi[j]) max = lsi[j];
			}
			
			lsi[i] = max + 1;
			if(ans < lsi[i]) ans = lsi[i];
		}
		
		System.out.println(ans);
	}
}
