package week18.PROGRAMMERS_LV2_타겟넘버;

class Solution {
    int cnt = 0;    // target 이 되는 방법의 수
    int targetNum;  // target number
    public int solution(int[] numbers, int target) {

        targetNum = target;
        dfs(numbers,0,0);
        
        return cnt;
    }
    
    public void dfs(int[] numbers, int num, int val){
        // 끝까지 확인했을 경우 target인지 확인
        if(num == numbers.length){
            if(val == targetNum) cnt++; 
            return;
        }
        // +
        dfs(numbers, num+1, val+numbers[num]);
        // -
        dfs(numbers, num+1, val-numbers[num]);
    }
}