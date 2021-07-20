package week20.PROGRAMMERS_LV3_정수삼각형;

import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        for(int h=1; h<triangle.length; h++){
            // 0번째 인덱스 처리
            triangle[h][0] += triangle[h-1][0];
            
            // 중간 인덱스 처리(2개중 최댓값 선정)
            for(int i=1; i<h; i++){
                triangle[h][i] += Math.max(triangle[h-1][i-1], triangle[h-1][i]);    
            }
            // 마지막 인덱스 처리
            triangle[h][h] += triangle[h-1][h-1];
            
        }
        
        // 최댓값 찾기
        for(int el : triangle[triangle.length-1]){
            answer = Math.max(answer, el);
        }
        
        return answer;
    }
}

