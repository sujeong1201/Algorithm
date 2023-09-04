import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) { 
        Map<String, Integer> playersMap = new HashMap<>();
        
        for(int i = 0; i < players.length; i++) {
            playersMap.put(players[i], i);
        }
        
        for(String calling : callings) {
            int index = playersMap.get(calling);
            String previousPlayer = players[index - 1];
            
            playersMap.put(calling, index - 1);
            playersMap.put(previousPlayer, index);
            
            String temp = players[index];
            players[index] = players[index - 1];
            players[index - 1] = temp;
        }
        
        return players;
    }
}