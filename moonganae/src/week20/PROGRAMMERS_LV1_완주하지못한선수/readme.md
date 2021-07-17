## Progrmmers LV1 완주하지 못한 선수
- 해쉬
- Level 1
- https://programmers.co.kr/learn/courses/30/lessons/42576
<br>

### 문제설명

> 수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.
마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.

### 제한사항
- 마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
- completion의 길이는 participant의 길이보다 1 작습니다.
- 참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
- 참가자 중에는 동명이인이 있을 수 있습니다.

### 입출력 예
|participant|	completion|	return|
|----|----|-----|
|["leo", "kiki", "eden"]	|["eden", "kiki"]|	"leo"|
|["marina", "josipa", "nikola", "vinko", "filipa"]|	["josipa", "filipa", "marina", "nikola"]	|"vinko"|
|["mislav", "stanko", "mislav", "ana"]|	["stanko", "ana", "mislav"]|	"mislav"|



#### 입출력 예 설명
예제 #1
"leo"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.

예제 #2
"vinko"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.

예제 #3
"mislav"는 참여자 명단에는 두 명이 있지만, 완주자 명단에는 한 명밖에 없기 때문에 한명은 완주하지 못했습니다.

### 풀이 및 과정
HashMap 자료구조를 사용하여 문제를 해결해 주었습니다.

Key는 이름을 위한 String, value는 동명이인 인원수 확인을 위한 Integer타입으로 선언하였고,

인원수가 null 즉, 아무도 없을때는 새롭게 한명을 넣어주고, 동명이인인 경우 +1해서 넣어주는 방식으로 구현하였습니다.

### 소스코드
```java
import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Map<String, Integer> map = new HashMap<>();
        
        // 참여한 선수 목록 넣기
        for(String part : participant){
            Integer cnt = map.get(part);
            
            if(cnt == null){
                map.put(part, 1);
            }
            // 동명이인 관리
            else{
                map.put(part, cnt+1);
            }
        }
        // 완주한 선수 목록에서 뺏기
        for(String com : completion){
            int cnt = map.get(com);
            
            if(cnt == 1){
                map.remove(com);
            }else{
                map.put(com, cnt-1);
            }
        }
        // 남은 목록에서 완주못한 선수 리턴
        for(String key : map.keySet()){
            answer = key;
        }
        return answer;
    }
}
```

### 결과
```
<정확성 테스트>
테스트 1 〉	통과 (0.06ms, 52.4MB)
테스트 2 〉	통과 (0.08ms, 52.5MB)
테스트 3 〉	통과 (0.66ms, 53.4MB)
테스트 4 〉	통과 (1.32ms, 53.8MB)
테스트 5 〉	통과 (1.33ms, 54.7MB)

<효율성 테스트>
테스트 1 〉	통과 (49.56ms, 81.7MB)
테스트 2 〉	통과 (61.52ms, 88.5MB)
테스트 3 〉	통과 (115.96ms, 96.7MB)
테스트 4 〉	통과 (88.85ms, 96.3MB)
테스트 5 〉	통과 (75.81ms, 96.7MB)
```

