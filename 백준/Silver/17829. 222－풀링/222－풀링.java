import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, map[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) map[r][c] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(divide(0, N, 0, N));
	}

	private static int divide(int rStart, int rEnd, int cStart, int cEnd) {
		if(rStart + 2 == rEnd) {
			return findSecond(map[rStart][cStart], map[rStart][cStart + 1], 
								map[rStart + 1][cStart], map[rStart + 1][cStart + 1]);
		}
		
		int n = rEnd - rStart;
		int one = divide(rStart, rStart + n / 2, cStart, cStart + n / 2);
		int two = divide(rStart, rStart + n / 2, cStart + n / 2, cEnd);
		int three = divide(rStart + n / 2, rEnd, cStart, cStart + n / 2);
		int four = divide(rStart + n / 2, rEnd, cStart + n / 2, cEnd);
		return findSecond(one, two, three, four);
	}

	private static int findSecond(int one, int two, int three, int four) {
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
		pq.offer(one);
		pq.offer(two);
		pq.offer(three);
		pq.offer(four);
		
		pq.poll();
		return pq.poll();
	}
}
