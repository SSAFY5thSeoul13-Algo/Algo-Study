## Programmers Lv2 기능개발
- 스택/큐 인데... 스택/큐 안씀  
- level2



<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42586

프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.

또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.

먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.

<br>

#### ✔ 제한사항
- 작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
- 작업 진도는 100 미만의 자연수입니다.
- 작업 속도는 100 이하의 자연수입니다.
- 배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.
<br>
 
#### ✔ 입출력 예

| progresses | speeds | return |  
|--|--|  --|  
|[93, 30, 55]	 | [1, 30, 5] |  	[2, 1] |   
|[95, 90, 99, 99, 80, 99][93, 30, 55]	 | [1, 1, 1, 1, 1, 1] |  	[1, 3, 2]  |   


###  💡 풀이

각 기능은 진도가 100% 일 때 반영할 수 있다.  
각 기능의 개발 속도는 모두 달라서, 뒤의 기능이 먼저 개발 가능 하지만 배포는 앞에 기능이 배포될 때 함께 배포된다.  

각 배포마다 몇 개의 기능이 배포되는지를 구해야 한다.  


1. 1일마다 지날 때 마다 진도율에 speed를 더한다.  
2. 100퍼센트가 되면 배포한다. (제거한다?)  


위 과정에 따라 문제를 풀었다. 사용한 변수는 다음과 같다.  
- `P_LEN` : 작업 배열의 길이  
- `idx` : 현재 작업 대상 (현재 처리되지 않은 작업 중 가장 앞에 있는 작업)  
- `cnt` : 현재 작업 배포 시 같이 배포되는 기능의 수  

<br>

`while`문은 하루를 기준으로 반복하며 매일 진도율에 speed를 더한다.  
이 때 이미 배포된 작업 진도는 체크할 필요가 없으므로, idx번째 작업부터 진도를 더하면 된다.  

```java
 while(idx<P_LEN){

	for(int i = idx ; i < P_LEN ; i++){
		progresses[i] += speeds[i];
	}
            //하루가 지난 뒤 작업량
```


그리고 배포할 수 있는지 확인한다.  
만약 가장 맨 앞 작업이 배포 가능하다면,  
즉, `progresses[idx]>=100`이라면 배포가 가능하므로 이를 배포하고   

```java
if(progresses[idx]>=100){ //현재 가장 앞에있는 작업이 배포 가능하다면
                cnt++; //배포 가능한 기능의 갯수 1개 증가
```

그 뒤에 또 배포 가능한 작업이 있는지 검사한다.  

```java
int i = idx+1; //탐색 대상 인덱스
while(i<P_LEN){
	if(progresses[i]>=100){
		cnt++; //배포 가능한 기능의 갯수++
		i++;
	}else break;
```

배포가능한 모든 작업을 배포 했으면 `answerList`에 배포한 `cnt`를 저장하고  
`idx`와 `cnt`를 갱신하고 위 작업을 모든 작업이 끝날 때 까지 반복한다.  

```java
idx = i; //while문 탈출 시 인덱스를 저장
answerList.add(cnt); //배포된 작업의 갯수 answerList에 추가
cnt = 0; //cnt 초기화
```
<br><br>

###  💡 소스코드


```java
import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answerList = new ArrayList<>(); //나중에 Array형으로 변환할 정답 리스트
        
        //앞에 기능이 배포 되어야 내가 배포될 수 있따.
        int P_LEN = progresses.length;
        
        int idx = 0; //작업 대상
        int cnt = 0; //배포되는 기능의 수
        
        while(idx<P_LEN){

            for(int i = idx ; i < P_LEN ; i++){
                progresses[i] += speeds[i];
            }
            //하루가 지난 뒤 작업량
            
            
            
            if(progresses[idx]>=100){ //현재 가장 앞에있는 작업이 배포 가능하다면
                cnt++; //배포 가능한 기능의 갯수 1개 증가

                //이제 뒤에꺼 배포 가능한거 있는지 검사해야한다
                
                
                int i = idx+1; //탐색 대상 인덱스
                while(i<P_LEN){
                    if(progresses[i]>=100){
                        cnt++; //배포 가능한 기능의 갯수++
                        i++;
                    }else break;
                }
                
                idx = i; //while문 탈출 시 인덱스를 저장
                answerList.add(cnt); //배포된 작업의 갯수 answerList에 추가
                cnt = 0; //cnt 초기화
            }

            
        }
        
        int[] answer = new int[answerList.size()];
        for(int i = 0; i<answerList.size() ; i++){
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}
```

<br>

### 결과
채점을 시작합니다.
정확성  테스트
테스트 1 〉	통과 (10.87ms, 53.1MB)
테스트 2 〉	통과 (18.58ms, 53.1MB)
테스트 3 〉	통과 (16.72ms, 52.8MB)
테스트 4 〉	통과 (17.64ms, 53.3MB)
테스트 5 〉	통과 (15.39ms, 52.3MB)
테스트 6 〉	통과 (11.06ms, 53.1MB)
테스트 7 〉	통과 (14.49ms, 53.4MB)
테스트 8 〉	통과 (16.21ms, 53.1MB)
테스트 9 〉	통과 (14.10ms, 52.8MB)
테스트 10 〉	통과 (11.97ms, 53.4MB)
테스트 11 〉	통과 (11.03ms, 52.5MB)
채점 결과
정확성: 100.0
합계: 100.0 / 100.0