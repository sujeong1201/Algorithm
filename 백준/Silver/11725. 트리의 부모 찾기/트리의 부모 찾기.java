import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, parents[];
	static boolean visited[];
	static ArrayList[] adjList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parents = new int[N + 1];
		visited = new boolean[N + 1];
		adjList = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) adjList[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		dfs(1);
		for(int i = 2; i <= N; i++) System.out.println(parents[i]);
	}

	private static void dfs(int cur) {
		visited[cur] = true;
		
		for(int i = 0; i < adjList[cur].size(); i++) {
			int next = (int)adjList[cur].get(i);
			if(visited[next]) continue;
			
			parents[next] = cur;
			dfs(next);
		}
	}
}
