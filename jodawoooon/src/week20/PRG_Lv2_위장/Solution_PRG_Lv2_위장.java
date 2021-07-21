package week20.PRG_Lv2_위장;

import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for(String[] cloth : clothes){
            String key = cloth[1];
            map.put(key, map.getOrDefault(key, 0)+1);
        }
        
        for(String key : map.keySet()){
            answer *= map.get(key) + 1;
            //해당 의상 종류를 안 입을 수도 있으므로 value+1
        }
        
        return answer-1;
        //하루에 최소 1개의 의상은 입어야한다. (answer-1)
    }
}