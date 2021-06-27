package week17.PROGRAMMERS_LV2_hIndex;

import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        // 정렬
        Arrays.sort(citations);
        int size = citations.length;
        // 맨앞부터 조건탐색
        for(int i=0; i<size; i++){
            int cur = citations[i];
            // h번 이상 인용된 논문이 h편 이상이면 정답찾음!
            if(cur >= size-i){
                return size-i;
            }
        }
        
        return answer;
    }
}