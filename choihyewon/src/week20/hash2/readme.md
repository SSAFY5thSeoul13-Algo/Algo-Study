## Programmers - 전화번호 목록 
- Array, sort
- Level2

🔗[전화번호 목록 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/42577)

## 풀이
저는 배열을 이용하여 문제를 풀었습니다. 먼저 배열을 정렬해주고, index 0번째 부터 접두어로 지정해주어 정렬된 순서로 접두어를 포함하는지 아닌지 판단을 하였습니다. 
어떤 번호를 접두어로 포함하는 문자는 그 번호의 바로 뒤의 index 위치에 정렬된다는 것을 이용하여 문제를 풀었습니다.


## 막힌점
~~~java
public class Programmers_전화번호_목록 { 
	public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Arrays.sort(phone_book);
        
        for(int i=0; i<phone_book.length; i++) {
        	String prefix = phone_book[i];
        	
        	for(int j=i+1; j<phone_book.length; j++) {
        		if(phone_book[j].startsWith(prefix)) {
        			return false;
        		}
        	}
        }
        return true;
    }

}
~~~

처음에 이렇게 코드를 작성했는데 효율성 3,4문제에서 시간 초과가 나왔었습니다.
어떤 번호를 접두어로 포함하는 문자는 그 번호의 바로 뒤의 index 위치에 정렬되기 때문에 이중for문을 돌려줄 필요가 없었습니다.


## 소스코드
~~~java

import java.util.Arrays;

public class Programmers_전화번호_목록 { 
	public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Arrays.sort(phone_book);
        
        for(int i=0; i<phone_book.length-1; i++) {
    		if(phone_book[i+1].startsWith(phone_book[i])) {
    			return false;
    		}
    	}
        return true;
    }

}

~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (0.24ms, 52.1MB)|
|테스트 2 |	통과 (0.24ms, 53MB)|
|테스트 3 |	통과 (1.65ms, 52.9MB)|
|테스트 4 |	통과 (0.21ms, 52.3MB)|
|테스트 5 |	통과 (0.24ms, 53.4MB)|
|테스트 6 |	통과 (0.27ms, 52MB)|
|테스트 7 |	통과 (0.27ms, 52.5MB)|
|테스트 8 |	통과 (0.22ms, 52.3MB)|
|테스트 9 |	통과 (0.27ms, 52.2MB)|
|테스트 10 |	통과 (0.21ms, 52.7MB)|
|테스트 11 |	통과 (0.30ms, 52.5MB)|
|테스트 12 |	통과 (0.25ms, 52.3MB)|
|테스트 13 |	통과 (0.22ms, 53.2MB)|
|테스트 14 |	통과 (3.66ms, 52.7MB)|
|테스트 15 |	통과 (4.45ms, 53MB)|
|테스트 16 |	통과 (5.43ms, 52.8MB)|
|테스트 17 |	통과 (6.59ms, 54MB)|
|테스트 18 |	통과 (5.55ms, 54.5MB)|
|테스트 19 |	통과 (9.75ms, 53.3MB)|
|테스트 20 |	통과 (9.60ms, 54.3MB)|

--------------

|효율성 | 테스트|
|-----|-----|
|테스트 1 |	통과 (19.05ms, 58.8MB)|
|테스트 2 |	통과 (16.47ms, 56.2MB)|
|테스트 3 |	통과 (427.69ms, 98.1MB)|
|테스트 4 |	통과 (280.10ms, 96.7MB)|