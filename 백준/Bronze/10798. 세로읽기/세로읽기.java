import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] words = new char[5][];
        int maxLength = 0;
        for(int i = 0; i < 5; i++) {
            words[i] = br.readLine().toCharArray();
            if(words[i].length > maxLength) maxLength = words[i].length;
        }
        br.close();
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int c = 0; c < maxLength; c++) {
            for(int r = 0; r < 5; r++) {
                if(words[r].length > c) bw.write(words[r][c]);
            }
        }
        
        bw.flush();
        bw.close();
    }
}