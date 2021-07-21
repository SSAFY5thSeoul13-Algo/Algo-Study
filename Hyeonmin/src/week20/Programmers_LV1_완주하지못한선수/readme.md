## Programmers Lv1 완주하지 못한 선수
- 해시
- level1

<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42576

수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.

마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.


#### 제한사항
마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
completion의 길이는 participant의 길이보다 1 작습니다.
참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
참가자 중에는 동명이인이 있을 수 있습니다.
<br><br>

###  💡 풀이

변수
`Map<String, Integer> map` : 참여한 선수의 이름을 key, 동명이인의 수를 Value로 하는 HashMap


<br>

for문을 통해서 마라톤에 참여하는 사람들을 `map`에 저장한다. 이 때 `map`에 이미 존재하는 경우는 해당 Key 값이 갖는 Value를 1증가시킨다.

```java
		//참여한 선수들 등록
		for (int i = 0; i < participant.length; i++) {
			//동명이인의 선수인 경우
			if(map.containsKey(participant[i])) {
				//해당 이름 선수의 수를 1 증가
				int num = map.get(participant[i]);
				
				map.put(participant[i], num+1);
			}
			//등록한 선수중 같은 이름이 없는 경우
			else {
				map.put(participant[i], 1);
			}
		}
```

참여자 등록이 끝났으면 `completion`배열에 저장된 완주자 목록에 대해서 해당 이름이 `map`에 존재하는데 value 가 1이면 삭제를 하고 2이상이면 1 감소시킨다.

```java
		//완주자 선수들 확인
		for (int i = 0; i < completion.length; i++) {
			//해당 이름의 선수 수
			int num = map.get(completion[i]);
			
			//한명만 있는 경우 제거
			if(num == 1) {
				map.remove(completion[i]);
			}
			//2명 이상인 경우 숫자 1 감소
			else {
				map.replace(completion[i], num-1);
			}
		}
```

`map`에 남아있는 1개의 키 값이 참여를 한 사람중 완주를 하지 못한 사람이다.

```java
		//남은 선수의 이름을 answer에 저장
		for (String key : map.keySet()) {
			answer = key;
		}
```

<br><br>

###  💡 소스코드
```java
import java.util.HashMap;
import java.util.Map;

public class Programmers_LV1_완주하지못한선수 {

	public static void main(String[] args) {
		String[] participant = {"mislav", "stanko", "mislav", "ana"};
		String[] completion = {"stanko", "ana", "mislav"};
		
		System.out.println(solution(participant, completion));
	}
	
	static String solution(String[] participant, String[] completion) {
		Map<String, Integer> map = new HashMap<>();
		
		//참여한 선수들 등록
		for (int i = 0; i < participant.length; i++) {
			//동명이인의 선수인 경우
			if(map.containsKey(participant[i])) {
				//해당 이름 선수의 수를 1 증가
				int num = map.get(participant[i]);
				
				map.put(participant[i], num+1);
			}
			//등록한 선수중 같은 이름이 없는 경우
			else {
				map.put(participant[i], 1);
			}
		}
		
		String answer = "";

		//완주자 선수들 확인
		for (int i = 0; i < completion.length; i++) {
			//해당 이름의 선수 수
			int num = map.get(completion[i]);
			
			//한명만 있는 경우 제거
			if(num == 1) {
				map.remove(completion[i]);
			}
			//2명 이상인 경우 숫자 1 감소
			else {
				map.replace(completion[i], num-1);
			}
		}
		
		//남은 선수의 이름을 answer에 저장
		for (String key : map.keySet()) {
			answer = key;
		}
		
		return answer;
	}
}


```


<br>


정확성  테스트
테스트 1 〉	통과 (0.05ms, 52.4MB)
테스트 2 〉	통과 (0.06ms, 52.1MB)
테스트 3 〉	통과 (0.68ms, 52.9MB)
테스트 4 〉	통과 (1.28ms, 53.6MB)
테스트 5 〉	통과 (5.27ms, 54MB)

효율성  테스트
테스트 1 〉	통과 (33.70ms, 81MB)
테스트 2 〉	통과 (58.14ms, 89.5MB)
테스트 3 〉	통과 (64.44ms, 94.5MB)
테스트 4 〉	통과 (69.11ms, 97.2MB)
테스트 5 〉	통과 (78.44ms, 95.8MB)

채점 결과
정확성: 50.0
효율성: 50.0
합계: 100.0 / 100.0