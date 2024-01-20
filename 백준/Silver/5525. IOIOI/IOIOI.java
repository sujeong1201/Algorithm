import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int S = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		String p = "";
		for(int i=0; i<N; i++) {
			if(i == N - 1) p += "IOI";
			else p += "IO";
		}
	
		int cnt = 0;
		for(int i=0; i<S-2*N; i++) {
			String subStr = str.substring(i, i + 2 * N + 1);
			if(subStr.equals(p)) ++cnt;
		}
		System.out.println(cnt);
	}
}
