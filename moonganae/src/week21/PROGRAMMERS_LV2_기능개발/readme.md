## Progrmmers LV2 기능개발
- 스택/큐
- Level 2
- https://programmers.co.kr/learn/courses/30/lessons/42586
<br>

### 문제설명

> 프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.<br>
또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.<br>
먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.


### 제한사항
- 작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
- 작업 진도는 100 미만의 자연수입니다.
- 작업 속도는 100 이하의 자연수입니다.
- 배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.

### 입출력 예
|progresses|	speeds|	return|
|----|----|----|
|[93, 30, 55]|	[1, 30, 5]|	[2, 1]|
|[95, 90, 99, 99, 80, 99]|	[1, 1, 1, 1, 1, 1]|	[1, 3, 2]|


#### 입출력 예 설명
입출력 예 #1
- 첫 번째 기능은 93% 완료되어 있고 하루에 1%씩 작업이 가능하므로 7일간 작업 후 배포가 가능합니다.
- 두 번째 기능은 30%가 완료되어 있고 하루에 30%씩 작업이 가능하므로 3일간 작업 후 배포가 가능합니다. 하지만 이전 첫 번째 기능이 아직 완성된 상태가 아니기 때문에 첫 번째 기능이 배포되는 7일째 배포됩니다.
- 세 번째 기능은 55%가 완료되어 있고 하루에 5%씩 작업이 가능하므로 9일간 작업 후 배포가 가능합니다.

따라서 7일째에 2개의 기능, 9일째에 1개의 기능이 배포됩니다.

입출력 예 #2
- 모든 기능이 하루에 1%씩 작업이 가능하므로, 작업이 끝나기까지 남은 일수는 각각 5일, 10일, 1일, 1일, 20일, 1일입니다. 
- 어떤 기능이 먼저 완성되었더라도 앞에 있는 모든 기능이 완성되지 않으면 배포가 불가능합니다.

따라서 5일째에 1개의 기능, 10일째에 3개의 기능, 20일째에 2개의 기능이 배포됩니다.

### 풀이 및 과정
ArrayList를 사용하여 문제를 해결하였습니다.


먼저 progresses 배열과 speeds 배열을 이용하여 개발을 진행합니다.

```java
// 작업 증가!
for(int i=sIdx; i<limit; i++){
    progresses[i] += speeds[i];
}
```
 
 그 후,작업을 돌면서 끝난 개발을 마무리하고 배포하면서 배포수를 기록합니다.
 
 ```java
 // 끝난 작업 배포
int cnt = 0;
for(int i=sIdx; i<limit; i++){
    if(progresses[i] < 100) break;
    cnt++;
    sIdx++;
}

if(cnt != 0){
    answer.add(cnt);
}
 ```

#### 막힌점
- 계속해서 테스트 8번이 틀렸다고 나왔었는데, 맨처음 HashSet에 연속된 N을 넣어줄때 계산을 잘못해주었습니다 ㅠㅠ
- 원래는 HashSet이 아니라 list로 처리했는데, 중복된 결과들이 너무 많이 나와서 i가 증가할수록 너무나 많은 값들이 발생하여 Set으로 수정하였습니다.

### 소스코드
```java
import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        
        int limit = progresses.length;
        int sIdx = 0;
        
        while(sIdx < limit){
            
            // 작업 증가!
            for(int i=sIdx; i<limit; i++){
                progresses[i] += speeds[i];
            }
            
            // 끝난 작업 배포
            int cnt = 0;
            for(int i=sIdx; i<limit; i++){
                if(progresses[i] < 100) break;
                cnt++;
                sIdx++;
            }
            
            if(cnt != 0){
                answer.add(cnt);
            }
        }
        
        int[] ans = new int[answer.size()];
        
        for(int i=0; i<answer.size(); i++){
            ans[i] = answer.get(i);
        }
        
        
        return ans;
    }
}
```

### 결과
```
테스트 1 〉	통과 (0.03ms, 51.7MB)
테스트 2 〉	통과 (0.14ms, 52.4MB)
테스트 3 〉	통과 (0.21ms, 51.9MB)
테스트 4 〉	통과 (0.09ms, 52.7MB)
테스트 5 〉	통과 (0.03ms, 52.2MB)
테스트 6 〉	통과 (0.06ms, 52MB)
테스트 7 〉	통과 (0.18ms, 53MB)
테스트 8 〉	통과 (0.05ms, 53.6MB)
테스트 9 〉	통과 (0.14ms, 53MB)
테스트 10 〉	통과 (0.14ms, 52MB)
테스트 11 〉	통과 (0.03ms, 52.5MB)

```

