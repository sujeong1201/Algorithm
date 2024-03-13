import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Word implements Comparable<Word> {
        String word;
        int cnt;

        public Word(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }

        public void countUp() {
            this.cnt++;
        }

        @Override
        public int compareTo(Word that) {
            if(this.cnt < that.cnt) {
                return 1;
            } else if(this.cnt > that.cnt) {
                return -1;
            } else {
                if(this.word.length() < that.word.length()) {
                    return 1;
                } else if(this.word.length() > that.word.length()) {
                    return -1;
                } else {
                    return this.word.compareTo(that.word);
                }
            }
        }

        @Override
        public String toString() {
            return word + " " + cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();
        List<Word> wordList = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            String word = br.readLine();
            if(word.length() >= M) {
                if(map.containsKey(word)) {
                    wordList.get(map.get(word)).countUp();
                } else {
                    map.put(word, wordList.size());
                    wordList.add(new Word(word, 1));
                }
            }
        }

        Collections.sort(wordList);

        StringBuilder sb = new StringBuilder();
        for(Word word : wordList) {
            sb.append(word.word + "\n");
        }
        System.out.println(sb);
    }
}
