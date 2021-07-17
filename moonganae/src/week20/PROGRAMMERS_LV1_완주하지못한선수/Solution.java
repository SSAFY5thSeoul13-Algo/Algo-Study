package week20.PROGRAMMERS_LV1_완주하지못한선수;

import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Map<String, Integer> map = new HashMap<>();
        
        // 참여한 선수 목록 넣기
        for(String part : participant){
            Integer cnt = map.get(part);
            
            if(cnt == null){
                map.put(part, 1);
            }
            // 동명이인 관리
            else{
                map.put(part, cnt+1);
            }
        }
        // 완주한 선수 목록에서 뺏기
        for(String com : completion){
            int cnt = map.get(com);
            
            if(cnt == 1){
                map.remove(com);
            }else{
                map.put(com, cnt-1);
            }
        }
        // 남은 목록에서 완주못한 선수 리턴
        for(String key : map.keySet()){
            answer = key;
        }
        return answer;
    }
}

