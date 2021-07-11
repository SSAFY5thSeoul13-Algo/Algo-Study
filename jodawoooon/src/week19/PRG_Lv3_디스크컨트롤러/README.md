## Programmers Lv3 디스크 컨트롤러
- 힙
- level2



<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42627

각 작업에 대해 [작업이 요청되는 시점, 작업의 소요시간]을 담은 2차원 배열 jobs가 매개변수로 주어질 때, 작업의 요청부터 종료까지 걸린 시간의 평균을 가장 줄이는 방법으로 처리하면 평균이 얼마가 되는지 return 하도록 solution 함수를 작성해주세요. (단, 소수점 이하의 수는 버립니다)

#### 제한사항
- jobs의 길이는 1 이상 500 이하입니다.
- jobs의 각 행은 하나의 작업에 대한 [작업이 요청되는 시점, 작업의 소요시간] 입니다.
- 각 작업에 대해 작업이 요청되는 시간은 0 이상 1,000 이하입니다.
- 각 작업에 대해 작업의 소요시간은 1 이상 1,000 이하입니다.
- 하드디스크가 작업을 수행하고 있지 않을 때에는 먼저 요청이 들어온 작업부터 처리합니다.
 
#### 입출력 예
| jobs | return | 
|--|--|
| [[0, 3], [1, 9], [2, 6]] | 9 |

0ms 시점에 3ms 걸리는 작업 요청이 들어옵니다.
1ms 시점에 9ms 걸리는 작업 요청이 들어옵니다.
2ms 시점에 6ms 걸리는 작업 요청이 들어옵니다.
<br><br>

###  💡 풀이
- `PriorityQueue<int[]> jobPQ`
- `int answer` : 작업의 요청부터 종료까지 걸린 시간의 합
- `currTime`
- `idx` : jobs 번호
<br>

SJF를 구현한다.  
현재 시점에 도착한 job중에서 가장 짧은 처리시간을 가진 job부터 처리하면 된다.
<br>

이를 구현하기 위해서  
1. 먼저 `jobs` arr를 `요청시간(job[0])` 기준으로 오름차순 정리한다.

```
Arrays.sort(jobs, new Comparator<int[]>() {
	@Override
	public int compare(int[] o1, int[] o2) {
		if(o1[0]==o2[0]) return o1[1] - o2[1];
		return o1[0] - o2[0];
	}
        	
});
```
<br>

2.그리고 `jobPQ`를 `처리시간(job[1])` 기준으로 오름차순 정렬한다.

```
PriorityQueue<int[]> jobPQ = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
			
        });
```
<br>

3.가장 빠른 요청시간을 가진 jobs[0]을 jobPQ에 add하고  
currTime을 jobs[0][0]으로 둔다.

```
jobPQ.add(jobs[0]);
currTime = jobs[0][0];
int idx = 1;
```
<br>

4.jobPQ가 빌 때 까지 아래 과정을 반복한다.
- `jopPQ`에서 처리할 일(job)을 `poll`한다. 
- 현재시간`currTime` (`job[1]`)을 갱신하고, `answer`에 작업의 요청부터 종료까지 걸린시간 `currTime - job[0]`을 더한다.
  ```
  int[] job = jobPQ.poll();
  currTime += job[1]; 
  answer += currTime - job[0]; 
  ```

- 그리고 `currTime` 기준으로 요청된 job을 `jobPQ`에 add한다.
  ```
  while(idx < len && jobs[idx][0] <= currTime) {
      jobPQ.add(jobs[idx++]);
  }
  ```
  
- 아직 처리할 작업이 남았는데 jobPQ 비었다면 	`currTime`을 변화시키고 `PQ`에 제일 빠른 요청시간을 가진 job 넣기
  ```
  if(idx < len && jobPQ.isEmpty()) {
  	currTime = jobs[idx][0];
    jobPQ.add(jobs[idx++]);
  }
  ```

<br><br>

###  💡 소스코드
```
package week19.PRG_Lv3_디스크컨트롤러;

import java.util.*;

public class Solution_PRG_Lv3_디스크컨트롤러 {
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{0,3},{1,9},{2,6}}));
	}
	
	public static int solution(int[][] jobs) {
		
		//SJF
        //도착한 애 중에서 가장 짧은 소요시간
        
        int answer = 0; //작업의 요청부터 종료까지 걸린 시간의 합
        int currTime = 0;
        int len = jobs.length;
        
        Arrays.sort(jobs, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]==o2[0]) return o1[1] - o2[1];
				return o1[0] - o2[0];
			}
        	
        });
		//jobs : 요청 시간 기준 오름차순 정렬
        
        PriorityQueue<int[]> jobPQ = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
			
        });
        //job PQ : 처리시간 기준 오름차순 정렬

        jobPQ.add(jobs[0]);
        currTime = jobs[0][0];
        int idx = 1;
        
        while(!jobPQ.isEmpty()) {
        	
        	int[] job = jobPQ.poll(); //작업할 일 poll
        	currTime += job[1]; //현재 시간 갱신
        	answer += currTime - job[0]; //작업의 요청부터 종료까지 걸린 시간

        	
        	//현재까지 도착한 job pq에 넣기
        	while(idx < len && jobs[idx][0] <= currTime) {
        		jobPQ.add(jobs[idx++]);
        	}
        	
        	//아직 처리할 작업이 남았는데 jobPQ 비었다. 도착한pq없다
        	//currTime을 변화시키고 PQ에 제일 빨리 도착하는 job 넣기
        	if(idx < len && jobPQ.isEmpty()) {
        		currTime = jobs[idx][0];
        		jobPQ.add(jobs[idx++]);
        	}
        }
        
        return answer / len;
    }
}

```

<br>

### 결과

정확성  테스트  
테스트 1 〉	통과 (0.25ms, 52.7MB)  
테스트 2 〉	통과 (0.27ms, 52.2MB)  
테스트 3 〉	통과 (0.62ms, 53.4MB)  
테스트 4 〉	통과 (0.30ms, 52.1MB)  
테스트 5 〉	통과 (2.84ms, 52.6MB)  
테스트 6 〉	통과 (5.42ms, 53.7MB)  
테스트 7 〉	통과 (12.77ms, 53.7MB)  
테스트 8 〉	통과 (27.11ms, 57.2MB)  
테스트 9 〉	통과 (28.51ms, 54.7MB)  
테스트 10 〉	통과 (29.21ms, 55.3MB)  
채점 결과  
정확성: 100.0  
합계: 100.0 / 100.0  