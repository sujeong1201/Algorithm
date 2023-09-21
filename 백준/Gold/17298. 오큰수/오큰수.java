import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];

        String[] split = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(split[i]);
        }

        int[] nge_index = new int[N];
        nge_index[N - 1] = -1;
        for(int i = N - 2; i >= 0; i --) {
            int greater_i = i + 1;

            while(greater_i < N) {
                if (array[greater_i] > array[i]) {
                    nge_index[i] = greater_i;
                    break;
                } else {
                    if(nge_index[greater_i] == -1) {
                        nge_index[i] = -1;
                        break;
                    }
                    greater_i = nge_index[greater_i];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append((nge_index[i] == -1 ? "-1" : array[nge_index[i]]) + " ");
        }
        System.out.println(sb.toString());
    }
}
