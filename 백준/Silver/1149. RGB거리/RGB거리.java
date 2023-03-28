import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] cost = new int[N][3];
		
		String[] split = br.readLine().split(" ");
		for(int j=0; j<3; j++) cost[0][j] = Integer.parseInt(split[j]);
		
		for(int i=1; i<N; i++) {
			split = br.readLine().split(" ");
			
			for(int j=0; j<3; j++) {
				int min = 0;
				if(j == 0) min = Math.min(cost[i - 1][1], cost[i - 1][2]);
				else if(j == 1) min = Math.min(cost[i - 1][0], cost[i - 1][2]);
				else min = Math.min(cost[i - 1][0], cost[i - 1][1]);
				
				cost[i][j] = Integer.parseInt(split[j]) + min;
			}
		}
		
		System.out.println(min(cost[N - 1]));
	}

	private static int min(int[] arr) {
		int min = Integer.MAX_VALUE;
		
		for(int i=0; i<arr.length; i++) {
			if(min > arr[i]) min = arr[i];
		}
		
		return min;
	}
}
