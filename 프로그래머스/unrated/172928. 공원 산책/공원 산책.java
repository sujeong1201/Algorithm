import java.util.*;

class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] current = new int[2];
        char[][] map = new char[park.length][];
        for(int i = 0; i < park.length; i++) {
            map[i] = park[i].toCharArray();
            for(int j = 0; j < map[i].length; j++) 
                if(map[i][j] == 'S') current = new int[] {i, j};
        }
        
        outer:
        for(String route : routes) {
            String[] splitStr = route.split(" ");
            String dir = splitStr[0];
            int count = Integer.parseInt(splitStr[1]);
            
            int newR, newC;
            switch(dir) {
                case "N":
                    if(current[0] - count < 0) continue outer;
                    for(int c = 1; c <= count; c++) {
                        if(map[current[0] - c][current[1]] == 'X') continue outer;
                    }
                    
                    current[0] = current[0] - count;
                    break;
                case "S":
                    if(current[0] + count >= map.length) continue;
                    for(int c = 1; c <= count; c++) {
                        if(map[current[0] + c][current[1]] == 'X') continue outer;
                    }
                                               
                    current[0] = current[0] + count;
                    break;
                case "W":
                    if(current[1] - count < 0) continue;
                    for(int c = 1; c <= count; c++) {
                        if(map[current[0]][current[1] - c] == 'X') continue outer;
                    }               
                                               
                    current[1] = current[1] - count;
                    break;
                case "E":
                    if(current[1] + count >= map[0].length) continue;
                    for(int c = 1; c <= count; c++) {
                        if(map[current[0]][current[1] + c] == 'X') continue outer;
                    }   
                    
                    current[1] = current[1] + count;
                    break;
            }
        }
        
        return current;
    }
}