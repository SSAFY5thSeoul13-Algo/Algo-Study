## Progrmmers LV3 입국심사
- 이분탐색
- Level 3

<br>

### 문제설명

> n명이 입국심사를 위해 줄을 서서 기다리고 있습니다. 각 입국심사대에 있는 심사관마다 심사하는데 걸리는 시간은 다릅니다.<br>
처음에 모든 심사대는 비어있습니다. 한 심사대에서는 동시에 한 명만 심사를 할 수 있습니다. 가장 앞에 서 있는 사람은 비어 있는 심사대로 가서 심사를 받을 수 있습니다. 하지만 더 빨리 끝나는 심사대가 있으면 기다렸다가 그곳으로 가서 심사를 받을 수도 있습니다.<br>
모든 사람이 심사를 받는데 걸리는 시간을 최소로 하고 싶습니다.<br>
입국심사를 기다리는 사람 수 n, 각 심사관이 한 명을 심사하는데 걸리는 시간이 담긴 배열 times가 매개변수로 주어질 때, 모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 return 하도록 solution 함수를 작성해주세요.


### 제한사항
- 입국심사를 기다리는 사람은 1명 이상 1,000,000,000명 이하입니다.
- 각 심사관이 한 명을 심사하는데 걸리는 시간은 1분 이상 1,000,000,000분 이하입니다.
- 심사관은 1명 이상 100,000명 이하입니다.

### 풀이 및 과정
처음에 왜 이분탐색으로 분류되었는지도 감이오지않아, 결국 블로그를 참고해서 문제를 해결하였습니다.

1. 먼저 최소시간 1분, 최대시간 n*[심시관중 가장 오래걸리는시간]을 설정한다.
2. 평균시간을 설정하고, 평균시간동안 처리할 수 있는 입국자수를 구한다.
3. 입국자수가 부족하다면 최소시간을 증가시킨다.
4. 입국자수가 충분하다면 최솟값을 저장하고, 최대시간을 줄인다.


### 소스코드
```java
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
```

### 결과
```
테스트 1 〉	통과 (0.03ms, 52.7MB)
테스트 2 〉	통과 (0.12ms, 52.3MB)
테스트 3 〉	통과 (1.36ms, 52.2MB)
테스트 4 〉	통과 (38.67ms, 59.6MB)
테스트 5 〉	통과 (46.25ms, 56.8MB)
테스트 6 〉	통과 (47.20ms, 58.6MB)
테스트 7 〉	통과 (60.94ms, 60MB)
테스트 8 〉	통과 (50.37ms, 57.2MB)
테스트 9 〉	통과 (0.05ms, 53.1MB)
```

