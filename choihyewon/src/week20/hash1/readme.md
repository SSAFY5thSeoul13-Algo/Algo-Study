## Programmers - 완주하지 못한 선수 
- Hash, map
- Level1

🔗[완주하지 못한 선수 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/42576)

## 풀이

하나의 hashmap을 생성하여 participant 배열에 들어있는 참가자의 이름과 참가자 이름의 수 (동명이인이 있는지 파악하기 위함)을 넣어주었습니다. 그리고 completion 배열에 존재하는 참가자의 value값에서 1을 빼주었고, hashmap에서 value값이 0이 아닌 참가자의 이름을 answer에 저장하였습니다.


## 소스코드
~~~java
import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        HashMap<String, Integer> hm = new HashMap<String,Integer>();
        for(int i=0; i<participant.length; i++) {
        	String person = participant[i];
        	
        	if(!hm.containsKey(person)) {
        		hm.put(person, 1);
        	}else {
        		hm.put(person, hm.get(person)+1);
        	}
        }
        
       for(int i=0; i<completion.length; i++) {
        	String person = completion[i];
        	hm.put(person, hm.get(person)-1);       	
        }
        
        for (String person : hm.keySet()) {
			if(hm.get(person) != 0) {
				answer = person;
			}
		}
        
        return answer;
    }
}

~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (0.07ms, 52.2MB)|
|테스트 2 |	통과 (0.07ms, 52.4MB)|
|테스트 3 |	통과 (0.63ms, 53.3MB)|
|테스트 4 |	통과 (1.13ms, 55.2MB)|
|테스트 5 |	통과 (1.18ms, 53.7MB)|

-------

|효율성 | 테스트 |
|---|---|
|테스트 1 |	통과 (40.87ms, 81.4MB)|
|테스트 2 |	통과 (67.90ms, 88.2MB)|
|테스트 3 |	통과 (72.01ms, 95.1MB)|
|테스트 4 |	통과 (105.24ms, 95.4MB)|
|테스트 5 |	통과 (90.78ms, 95.5MB)|