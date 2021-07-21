## Programmers Lv2 위장
- 해시
- level2

<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42578

스파이들은 매일 다른 옷을 조합하여 입어 자신을 위장합니다.

스파이가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때 서로 다른 옷의 조합의 수를 return 하도록 solution 함수를 작성해주세요.


#### 제한사항
clothes의 각 행은 [의상의 이름, 의상의 종류]로 이루어져 있습니다.
스파이가 가진 의상의 수는 1개 이상 30개 이하입니다.
같은 이름을 가진 의상은 존재하지 않습니다.
clothes의 모든 원소는 문자열로 이루어져 있습니다.
모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_' 로만 이루어져 있습니다.
스파이는 하루에 최소 한 개의 의상은 입습니다.
<br><br>

###  💡 풀이

변수
`Map<String, Integer> map = new HashMap<>()` : Key로 옷의 종류, value로 해당 종류의 옷의 개수를 갖는 map


<br>

`clothes` 배열에 대한 foreach문을 실행해서 이미 `map`에 존재하는 종류의 옷인경우 value를 1 증가시키고 없는 경우는 value로 1을 갖는 새로운 키로 `map` 저장한다  

```
		for (String[] item : clothes) {
			String category = item[1];
			
			if(map.containsKey(category)) {
				map.put(category, map.get(category)+1);
			}
			else {
				map.put(category, 1);
			}
		}
```

저장이 끝난 후 map의 entrySet에 대한 foreach문을 실행하여 가능한 옷의 조합 수를 계산한다.
각 종류별로 선택을 안하는 경우를 포함하여 value에 +1를 하여 각 옷의 종류별로 갯수의 곱을 구한 것이 가능한 옷 조합의 개수가 된다.
이 때 모두 선택하지 않는 경우도 포함되기 때문에 모든 계산이 끝나고 -1을 해준다

```
		int result = 1;
		
		for (Map.Entry<String, Integer> entry: map.entrySet()) {
			System.out.println(entry.getValue());
			result *= (entry.getValue()+1);
		}
		
		return result-1;
```



<br><br>

###  💡 소스코드
```
import java.util.HashMap;
import java.util.Map;

public class Programmers_LV2_위장 {
	public static void main(String[] args) {
		String[][] clothes = {
				{"yellowhat", "headgear"},
				{"bluesunglasses", "eyewear"},
				{"green_turban", "headgear"}
		};
		
		System.out.println(solution(clothes));
	}
	
	static int solution(String[][] clothes) {
		//String: 옷의 종류, Integer: 각 종류별 옷의 개수
		Map<String, Integer> map = new HashMap<>();
		
		
		for (String[] item : clothes) {
			//옷의 종류
			String category = item[1];
			
			//이미 있는 종류면 value를 1 증가시킨다
			if(map.containsKey(category)) {
				map.put(category, map.get(category)+1);
			}
			//없는 종류면 새로 map에 넣어준다
			else {
				map.put(category, 1);
			}
		}
		
		//가능한 옷의 조합 수
		int result = 1;
		
		//가능한 옷의 조합 수 구하기
		for (Map.Entry<String, Integer> entry: map.entrySet()) {
			System.out.println(entry.getValue());
			//해당 옷을 선택하지 않는 것도 하나의 경우로 생각하여 value에 +1한 경우로 조합을 구한다
			result *= (entry.getValue()+1);
		}
		
		//선택을 안하는 경우로 +1을 하여 곱했기 때문에 모두 선택하지 않는 경우가 포함되므로 그 경우를 제외하고 리턴한다 
		return result-1;
		
	}
}



```


<br>

테스트 1 〉	통과 (1.41ms, 52.9MB)
테스트 2 〉	통과 (0.23ms, 51.8MB)
테스트 3 〉	통과 (0.52ms, 52.1MB)
테스트 4 〉	통과 (0.54ms, 52.6MB)
테스트 5 〉	통과 (0.47ms, 52.4MB)
테스트 6 〉	통과 (0.41ms, 54.4MB)
테스트 7 〉	통과 (0.87ms, 51.7MB)
테스트 8 〉	통과 (0.43ms, 52.3MB)
테스트 9 〉	통과 (0.28ms, 52.1MB)
테스트 10 〉	통과 (0.22ms, 52.2MB)
테스트 11 〉	통과 (0.33ms, 52.3MB)
테스트 12 〉	통과 (0.69ms, 52.2MB)
테스트 13 〉	통과 (0.67ms, 52.6MB)
테스트 14 〉	통과 (0.31ms, 52.4MB)
테스트 15 〉	통과 (0.22ms, 53.1MB)
테스트 16 〉	통과 (1.74ms, 52.8MB)
테스트 17 〉	통과 (0.22ms, 52.2MB)
테스트 18 〉	통과 (0.41ms, 52.3MB)
테스트 19 〉	통과 (0.65ms, 52.6MB)
테스트 20 〉	통과 (0.33ms, 52.7MB)
테스트 21 〉	통과 (0.24ms, 52.1MB)
테스트 22 〉	통과 (0.33ms, 53MB)
테스트 23 〉	통과 (0.26ms, 52MB)
테스트 24 〉	통과 (0.26ms, 52.3MB)
테스트 25 〉	통과 (0.39ms, 53.1MB)
테스트 26 〉	통과 (1.05ms, 52.4MB)
테스트 27 〉	통과 (0.33ms, 51.7MB)
테스트 28 〉	통과 (0.69ms, 51.7MB)