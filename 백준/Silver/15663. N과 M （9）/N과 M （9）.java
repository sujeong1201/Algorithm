import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int data[], numbers[];
    static boolean selected[];
    static List<int[]> answerList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        data = new int[N];
        numbers = new int[M];
        selected = new boolean[N];
        answerList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) data[i] = Integer.parseInt(st.nextToken());

        permutation(0);

        Collections.sort(answerList, (o1, o2) -> {
            int[] arr1 = o1;
            int[] arr2 = o2;
            for (int i = 0; i < M; i++) {
                if(arr1[i] > arr2[i]) return 1;
                else if(arr1[i] == arr2[i]) continue;
                else return -1;
            }

            return 0;
        });

        List<int[]> notDuplicated = new ArrayList<>();
        int[] cur = null;
        for(int[] arr : answerList) {
            if(Arrays.equals(cur, arr)) continue;

            notDuplicated.add(arr);
            cur = arr;
        }

        StringBuilder sb = new StringBuilder();
        for(int[] arr : notDuplicated) {
            sb.append(printArray(arr) + "\n");
        }
        System.out.println(sb);
    }

    private static String printArray(int[] arr) {
        String str = "";
        for(int num : arr) str += num + " ";

        return str;
    }

    private static void permutation(int cnt) {
        if(cnt == M) {
            int[] curNumbers = numbers.clone();
            answerList.add(curNumbers);

            return;
        }

        for(int i = 0; i < N; i++) {
            if(selected[i]) continue;

            selected[i] = true;
            numbers[cnt] = data[i];
            permutation(cnt + 1);
            selected[i] = false;
        }
    }
}
