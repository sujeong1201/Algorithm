import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) {
            return 5 * cities.length;
        }
        
        Map<String, Integer> cache = new HashMap<>();
        int time = 0;
        
        for(int i = 0; i < cities.length; i++) {
            String cityName = cities[i].toLowerCase();
            
            if(cache.containsKey(cityName)) {  // cache hit
                time += 1;
            } else {  // cache miss
                if(cache.size() >= cacheSize) {
                    int lruNum = Integer.MAX_VALUE;
                    String lruName = null;
                    for(String cacheCity : cache.keySet()) {
                        if(cache.get(cacheCity) < lruNum) {
                            lruNum = cache.get(cacheCity);
                            lruName = cacheCity;
                        }
                    }

                    cache.remove(lruName);
                }
                
                time += 5;
            } 
            
            cache.put(cityName, i);
        }
        
        return time;
    }
}