import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String str1, String str2) {
    	Map<String, Integer> map1 = new HashMap<>();
    	Map<String, Integer> map2 = new HashMap<>();
        int union = 0, intersect = 0;
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        makeMap(str1, map1);
        makeMap(str2, map2);
        
        for(String key : map1.keySet()) {
        	if(map2.containsKey(key)) {
        		union += Math.max(map1.get(key), map2.get(key));
        		intersect += Math.min(map1.get(key), map2.get(key));
        	} else {
        		union += map1.get(key);
        	}
        }
        for(String key : map2.keySet()) {
        	if(!map1.containsKey(key)) union += map2.get(key);
        }
        
        double jaccard = (union == 0) ? 1.0 : (double)intersect/(double)union; 
        
        return (int)(jaccard * 65536);
    }
    
    public void makeMap(String str, Map<String, Integer> map) {
        for(int i=0; i<str.length()-1; i++) {
        	if(str.charAt(i) < 97 || str.charAt(i) > 122 || 
        			str.charAt(i + 1) < 97 || str.charAt(i + 1) > 122) continue;
        	
        	String key = str.substring(i, i + 2);
        	if(map.containsKey(key)) map.put(key, map.get(key) + 1);
        	else map.put(key, 1);
        }
    }
}