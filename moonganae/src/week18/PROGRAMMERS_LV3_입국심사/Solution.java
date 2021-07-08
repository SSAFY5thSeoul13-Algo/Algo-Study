package week18.PROGRAMMERS_LV3_입국심사;

class Solution {
    public long solution(int n, int[] times) {        
        // 최소시간은 1분
        long minTime = 1;
        
        /* 최대로 걸리는 시간구하기 */
        long maxTime = -1;
        for(int i=0; i<times.length; i++){
            if(times[i] > maxTime){
                maxTime = times[i];
            }
        }
        maxTime *= n;
        
        return binarySearch(n, minTime, maxTime, times);
        
    }
    /**
     * 
     * @param n : 입국심사 대기자수
     * @param minTime : 최소시간
     * @param maxTime : 최대시간
     * @param times : 십사관들의 심시시간
     * @return : 모든 심사가 끝난 시간
     */
    public long binarySearch(int n, long minTime, long maxTime, int[] times){
        long answer = Long.MAX_VALUE;
        
        // 이분탐색 시작
        while(minTime <= maxTime){
        	// 평균시간 구하기
            long avgTime = (maxTime + minTime) / 2;
            
            // 평균시간을 기준으로 심사위원들이 심사를 완료하는 수
            long cnt = 0;
            for(int time : times){
                cnt += avgTime/time;
                if(cnt > n) break;
            }
        
            // 입국자보다 많은 인원이 수용가능하다면 
            if(n <= cnt){
                answer = Math.min(answer, avgTime);
                maxTime = avgTime-1;
            }
            // 입국자보다 적은 수라면 시간을 더 올려본다.
            else{
                minTime = avgTime+1;
            }
        }
        
        return answer;
    }
}

