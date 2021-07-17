package week20.PROGRAMMERS_LV2_위장;

import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();
        // 각 의상의 종류별로 갯수를 저장
        for(int i=0; i<clothes.length; i++){
            Integer cnt = map.get(clothes[i][1]);
            
            if(cnt == null){
                map.put(clothes[i][1], 1);
            }else{
                map.put(clothes[i][1], cnt+1);
            }
        }
        
        for(int cnt : map.values()){
            // 각 의상의 종류를 입는경우의수 + 1(압입는 경우의수)
            answer *= cnt+1;
        }
        
        // 모든 의상의 종류를 안입는 경우의 수는 없기 때문에 -1
        return answer-1;
    }
}

