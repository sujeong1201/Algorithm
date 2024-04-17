import java.io.*;
import java.util.*;

public class Main {
  static class Player implements Comparable {
    String nickname;
    int level;

    public Player(String nickname, int level) {
      this.nickname = nickname;
      this.level = level;
    }

    @Override
    public int compareTo(Object o) {
      return nickname.compareTo(((Player) o).nickname);
    }

    @Override
    public String toString() {
      return level + " " + nickname + "\n";
    }
  }

  static class Room {
    int level;
    int cnt;
    PriorityQueue<Player> playerList;

    public Room(int level, String nickname) {
      this.level = level;
      cnt = 1;
      playerList = new PriorityQueue<>();
      playerList.offer(new Player(nickname, level));
    }

    public void addPlayer(int level, String nickname) {
      cnt++;
      playerList.offer(new Player(nickname, level));
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int p = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    List<Room> roomList = new ArrayList<>();
    while (p-- > 0) {
      st = new StringTokenizer(br.readLine());
      int level = Integer.parseInt(st.nextToken());
      String nickname = st.nextToken();

      boolean flag = false;
      for (Room room : roomList) {
        if (room.cnt < m && (room.level >= level - 10 && room.level <= level + 10)) {
          room.addPlayer(level, nickname);
          flag = true;
          break;
        }
      }

      if (!flag) {
        roomList.add(new Room(level, nickname));
      }
    }

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    for (Room room : roomList) {
      if (room.cnt == m) bw.write("Started!\n");
      else bw.write("Waiting!\n");

      for (int i = 0; i < room.cnt; i++) {
        Player player = room.playerList.poll();
        bw.write(player.toString());
      }
    }

    bw.flush();
    bw.close();
  }
}
