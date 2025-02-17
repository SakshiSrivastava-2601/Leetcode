import java.util.HashMap;

class Solution {
    public int numTilePossibilities(String tiles) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        
        for (char c : tiles.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        return backtrack(freqMap);
    }

    private static int backtrack(HashMap<Character, Integer> freqMap) {
        int count = 0;
        
        for (char c : freqMap.keySet()) {
            if (freqMap.get(c) > 0) {
                freqMap.put(c, freqMap.get(c) - 1);
                count += 1 + backtrack(freqMap);
                freqMap.put(c, freqMap.get(c) + 1);
            }
        }
        
        return count;
    }
}