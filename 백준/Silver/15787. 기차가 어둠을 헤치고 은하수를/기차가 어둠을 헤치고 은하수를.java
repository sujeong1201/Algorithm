import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		StringBuilder[] train = new StringBuilder[N];
		for(int i = 0; i < N; i++) train[i] = new StringBuilder("00000000000000000000");
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String comm = st.nextToken();
			int tNum = Integer.parseInt(st.nextToken()) - 1;
			switch(comm) {
			case "1":
				int sNum = Integer.parseInt(st.nextToken()) - 1;
				train[tNum].setCharAt(sNum, '1');
				break;
			case "2":
				sNum = Integer.parseInt(st.nextToken()) - 1;
				train[tNum].setCharAt(sNum, '0');
				break;
			case "3":
				train[tNum].deleteCharAt(19);
				train[tNum].insert(0, '0');
				break;
			case "4":
				train[tNum].deleteCharAt(0);
				train[tNum].append('0');
				break;
			}
		}
		
		Set<String> result = new HashSet<>();
		for(int i = 0; i < N; i++) result.add(train[i].toString());
		System.out.println(result.size());
	}
}
