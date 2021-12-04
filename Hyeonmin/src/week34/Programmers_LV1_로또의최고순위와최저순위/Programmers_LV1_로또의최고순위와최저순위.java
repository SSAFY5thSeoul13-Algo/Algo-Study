package week34.Programmers_LV1_로또의최고순위와최저순위;

import java.util.*;

class Solution {
    static int[] rank ={6,6,5,4,3,2,1};
    
    public int[] solution(int[] lottos, int[] win_nums) {         
        Arrays.sort(lottos);
        Arrays.sort(win_nums);
        
        int lottosIdx = 0;
        int winNumsIdx = 0;
        int size = lottos.length;
        int sameCount = 0;
        int zeroCount = 0;
        
        while(lottosIdx < size && winNumsIdx < size){
            if(lottos[lottosIdx] == 0){
                lottosIdx++;
                zeroCount++;
            }
            else if(lottos[lottosIdx] == win_nums[winNumsIdx]){
                sameCount++;
                lottosIdx++;
                winNumsIdx++;
            }
            else if(lottos[lottosIdx] < win_nums[winNumsIdx]){
                lottosIdx++;
            }
            else if(lottos[lottosIdx] > win_nums[winNumsIdx]){
                winNumsIdx++;
            }
        }
        
        int[] answer = {rank[sameCount+zeroCount], rank[sameCount]};
        
        return answer;
        
    }
}