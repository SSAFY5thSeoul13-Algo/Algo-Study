## Programmers Lv2 프린터
- 스택/큐
- level2



<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42587

일반적인 프린터는 인쇄 요청이 들어온 순서대로 인쇄합니다. 그렇기 때문에 중요한 문서가 나중에 인쇄될 수 있습니다. 이런 문제를 보완하기 위해 중요도가 높은 문서를 먼저 인쇄하는 프린터를 개발했습니다. 이 새롭게 개발한 프린터는 아래와 같은 방식으로 인쇄 작업을 수행합니다.

```
1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
3. 그렇지 않으면 J를 인쇄합니다.
예를 들어, 4개의 문서(A, B, C, D)가 순서대로 인쇄 대기목록에 있고 중요도가 2 1 3 2 라면 C D A B 순으로 인쇄하게 됩니다.
```

내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 알고 싶습니다. 위의 예에서 C는 1번째로, A는 3번째로 인쇄됩니다.

현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와 내가 인쇄를 요청한 문서가 현재 대기목록의 어떤 위치에 있는지를 알려주는 location이 매개변수로 주어질 때, 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return 하도록 solution 함수를 작성해주세요.

<br>

#### ✔ 제한사항
- 현재 대기목록에는 1개 이상 100개 이하의 문서가 있습니다.
- 인쇄 작업의 중요도는 1~9로 표현하며 숫자가 클수록 중요하다는 뜻입니다.
- location은 0 이상 (현재 대기목록에 있는 작업 수 - 1) 이하의 값을 가지며 대기목록의 가장 앞에 있으면 0, 두 번째에 있으면 1로 표현합니다.
<br>
 
#### ✔ 입출력 예

| priorities | location | return |  
|--|--|  --|  
|[2, 1, 3, 2]	 | 2 |  	1 |  
|[1, 1, 9, 1, 1, 1]	 | 0 |  5 |  

입출력 예 설명
예제 #1

문제에 나온 예와 같습니다.

예제 #2

6개의 문서(A, B, C, D, E, F)가 인쇄 대기목록에 있고 중요도가 1 1 9 1 1 1 이므로 C D E F A B 순으로 인쇄합니다.

###  💡 풀이

`HashMap`과 `Queue`를 이용해서 풀었다.
- `HashMap<Integer, Integer> docsMap` : 남은 문서의 우선순위별 개수
- `Queue<Integer> printer` : 문제에서 제시한 프린터를 표현할 Queue


풀이 과정은 다음과 같다.
1. `priorities`의 문서를 `printer`와 `map`에 저장한다.  
```java
        for(int p : priorities) {
        	docsMap.put(p, docsMap.getOrDefault(p, 0)+1); //map에 우선순위별 갯수 저장
        	printer.add(p); //프린터에 문서의 우선순위 순서대로 넣기
        }
```

그리고 내가 요청한 문서가 인쇄 될 때 까지 다음과정을 반복한다.    
<br>
2. `printer`에서 가장 앞에 있는 문서를 `poll`하고 `map`의 `keySet()`을 이용해 나머지 인쇄 대기 목록에서 중요도가 더 높은 문서가 한개라도 있는지 체크한다.  
```java
for(int next : docsMap.keySet()) {
	if(next>cur) {
		flag= true;
        break;
	}
}
```
존재 한다면 대기목록의 가장 마지막에 넣고 `location` 위치를 재조정한다.  
```java
printer.add(cur);
if(location==0) location = printer.size()-1; //지금 얘가 내가 인쇄를 요청한 문서인데 바로 앞에서도 인쇄 못했으니까 위치를 맨 뒤로 바꿈
else location--;
```
존재하지 않는다면 인쇄한다.  
<br>
3. 현재 인쇄된 문서가 내가 찾는 문서인지 확인한다.  
queue 구조이므로 `location==0`이면 내가 찾는 문서가 인쇄된 것이다.  


```java
//내가 찾는 문서가 인쇄됨? 종료
if(location==0) break;
//아니면? location 위치 조정해아하면 앞으로 당긴다
else location--;
```
내가 찾는 문서라면 break한다.  
내가 찾는 문서가 아니라면 `location` 위치를 재조정한다.  
<br>
4. 인쇄했다면 `map`에서 인쇄한 우선순위 개수를 하나 제거한다.  
```java 
int cnt = docsMap.get(cur);
if(cnt>1) docsMap.put(cur, cnt-1);
else docsMap.remove(cur);
```
<br><br>

###  💡 소스코드



```java
import java.util.*;

class Solution {
    public static int solution(int[] priorities, int location) {
		int answer = 0;
        
        HashMap<Integer, Integer> docsMap = new HashMap<>(); //남은 문서의 우선순위별 개수 (TreeMap으로 정렬)
        Queue<Integer> printer = new LinkedList<>(); //프린터를 표현할 Queue
        
        for(int p : priorities) {
        	docsMap.put(p, docsMap.getOrDefault(p, 0)+1); //map에 우선순위별 갯수 저장
        	printer.add(p); //프린터에 문서의 우선순위 순서대로 넣기
        }
        
        
        
        while(!printer.isEmpty()) {

        	int cur = printer.poll();
        	boolean flag = false;
        	for(int next : docsMap.keySet()) {
        		if(next>cur) {
        			//나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면
        			flag= true;
        			break;
        		}
        	}
        	
        	if(flag) {
        		//대기목록의 가장 마지막에 넣습니다.
        		
        		printer.add(cur);
        		if(location==0) location = printer.size()-1; //지금 얘가 내가 인쇄를 요청한 문서인데 바로 앞에서도 인쇄 못했으니까 위치를 맨 뒤로 바꿈
        		else location--;

        	} else {
        		//그렇지 않으면 인쇄한다.
            	answer++;
            	
            	//내가 찾는 문서가 인쇄됨? 종료
            	if(location==0) break;
            	//아니면? location 위치 조정해아하면 앞으로 당긴다
            	else location--;
            	
            	
            	//map에서 우선순위별 갯수 하나 빼기
            	int cnt = docsMap.get(cur);
            	if(cnt>1) docsMap.put(cur, cnt-1);
            	else docsMap.remove(cur);
        	}

        }
        return answer;
    }
}
```

<br>

### 결과
테스트 1 〉	통과 (0.40ms, 52.9MB)
테스트 2 〉	통과 (1.16ms, 54.1MB)
테스트 3 〉	통과 (0.23ms, 53.1MB)
테스트 4 〉	통과 (0.25ms, 53.2MB)
테스트 5 〉	통과 (0.15ms, 52.5MB)
테스트 6 〉	통과 (0.43ms, 52MB)
테스트 7 〉	통과 (0.36ms, 52.2MB)
테스트 8 〉	통과 (0.84ms, 53MB)
테스트 9 〉	통과 (0.22ms, 53.3MB)
테스트 10 〉	통과 (0.45ms, 53.2MB)
테스트 11 〉	통과 (0.89ms, 53.3MB)
테스트 12 〉	통과 (0.26ms, 53.4MB)
테스트 13 〉	통과 (0.75ms, 52.2MB)
테스트 14 〉	통과 (0.21ms, 52MB)
테스트 15 〉	통과 (0.23ms, 52.5MB)
테스트 16 〉	통과 (0.36ms, 52.5MB)
테스트 17 〉	통과 (0.99ms, 51.8MB)
테스트 18 〉	통과 (0.28ms, 52.8MB)
테스트 19 〉	통과 (0.82ms, 52.8MB)
테스트 20 〉	통과 (0.38ms, 52.9MB)