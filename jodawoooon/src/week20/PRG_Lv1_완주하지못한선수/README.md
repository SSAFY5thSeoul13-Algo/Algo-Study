## Programmers Lv1 완주하지 못한 선수
- 해시
- level1



<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42576

수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.

마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.
<br>

#### ✔ 제한사항
- 마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.  
- completion의 길이는 participant의 길이보다 1 작습니다.  
- 참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.  
- 참가자 중에는 동명이인이 있을 수 있습니다.  
<br>
 
#### ✔ 입출력 예
| participant | completion | return | 
|--|--|--|
| ["leo", "kiki", "eden"] | ["eden", "kiki"] | "leo" |
| ["marina", "josipa", "nikola", "vinko", "filipa"] | ["josipa", "filipa", "marina", "nikola"] | "vinko" |
| ["mislav", "stanko", "mislav", "ana"] | ["stanko", "ana", "mislav"] | "mislav" |
예제 #1
"leo"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.

예제 #2
"vinko"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.

예제 #3
"mislav"는 참여자 명단에는 두 명이 있지만, 완주자 명단에는 한 명밖에 없기 때문에 한명은 완주하지 못했습니다.

<br><br>

###  💡 풀이
- `String[] participant` : 참가자 이름  
- `String[] completion` : 완주자 이름  

단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주했다. 즉, 딱 한 명만 찾으면 된다.  

`HashMap`을 사용하여 key에 선수 이름, value에 달리는 사람의 수를 저장했다.  
그리고 완주 한 사람은 value를 하나 감소시켰다.  

그 결과 value가 0이 아닌 key값을 찾으면  
그 key가 바로 완주하지 못한 선수의 이름이다.  


<br>



<br><br>

###  💡 소스코드
```java
package level1;

import java.util.*;

public class Solution {
	public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> map = new HashMap<>();
        for (String p : participant) {
			if(map.get(p)==null) {
				map.put(p, 1);
			}else { //이름중복일경우
				map.put(p, map.get(p)+1);
			}
		}
        
        for (String p : completion) {
        	if(map.get(p)!=0) {
        		map.put(p, map.get(p)-1);
        	}
		}
        
        for (String key : map.keySet()) {
			if(map.get(key)!=0) {
				return key;
			}
		}
        
        return answer;
    }
}

```

<br>

### 결과

정확성  테스트
테스트 1 〉	통과 (0.05ms, 52.2MB)
테스트 2 〉	통과 (0.09ms, 52.8MB)
테스트 3 〉	통과 (0.68ms, 52.9MB)
테스트 4 〉	통과 (1.06ms, 54.2MB)
테스트 5 〉	통과 (0.96ms, 53.7MB)

효율성  테스트
테스트 1 〉	통과 (42.47ms, 81.6MB)
테스트 2 〉	통과 (90.10ms, 89.3MB)
테스트 3 〉	통과 (108.61ms, 94.8MB)
테스트 4 〉	통과 (94.56ms, 95.2MB)
테스트 5 〉	통과 (71.79ms, 96.6MB)

채점 결과
정확성: 50.0
효율성: 50.0
합계: 100.0 / 100.0