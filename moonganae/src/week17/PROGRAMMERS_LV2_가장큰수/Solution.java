package week17.PROGRAMMERS_LV2_가장큰수;

import java.util.*;
class Solution {
    
    public String solution(int[] numbers) {
        List<String> list = new ArrayList<>();
        
        /* 하나의 숫자를 5번 반복해서 하기 (4자리이기 때문)*/
        for(int i=0; i<numbers.length; i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<5; j++){
                sb.append(numbers[i]);
            }
            list.add(sb.toString());
        }
        // 맨앞의 숫자가 큰것으로 정렬
        Collections.sort(list, Collections.reverseOrder());
        
        // 만약 모든 수가 0으로 시작하면 0으로 리턴
        if(list.get(0).equals("00000")){
            return "0";
        }
        
        // 아니라면 숫자 붙여서 리턴
        StringBuilder answer = new StringBuilder();
        for(String a : list){
            answer.append(a.substring(0, a.length()/5));
        }
        
        return answer.toString();
    }
}