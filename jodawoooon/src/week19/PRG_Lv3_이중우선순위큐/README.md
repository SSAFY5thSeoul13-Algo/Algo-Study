## Programmers Lv3 이중우선순위큐
- 힙
- level3



<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42628

이중 우선순위 큐는 다음 연산을 할 수 있는 자료구조를 말합니다.  

| 명령어 | 수신 탑(높이) |
| -- | -- |
| I 숫자 | 큐에 주어진 숫자를 삽입합니다. |
| D 1 | 큐에서 최댓값을 삭제합니다. |
| D -1 | 큐에서 최솟값을 삭제합니다. |

이중 우선순위 큐가 할 연산 operations가 매개변수로 주어질 때, 모든 연산을 처리한 후 큐가 비어있으면 [0,0]  비어있지 않으면 [최댓값, 최솟값]을 return 하도록 solution 함수를 구현해주세요.  

#### 제한사항
- operations는 길이가 1 이상 1,000,000 이하인 문자열 배열입니다.  
- operations의 원소는 큐가 수행할 연산을 나타냅니다.  
	- 원소는 “명령어 데이터” 형식으로 주어집니다.  
	- 최댓값/최솟값을 삭제하는 연산에서 최댓값/최솟값이 둘 이상인 경우, 하나만 삭제합니다.  
-  빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.  
 
#### 입출력 예
| operations | return | 
|--|--|
| ["I 16","D 1"] | [0,0] |
| ["I 7","I 5","I -5","D -1"] | [7,5] |

16을 삽입 후 최댓값을 삭제합니다. 비어있으므로 [0,0]을 반환합니다.  
7,5,-5를 삽입 후 최솟값을 삭제합니다. 최대값 7, 최소값 5를 반환합니다.  
<br><br>

###  💡 풀이
- `int[] answer` : [최댓값, 최솟값]
- `PriorityQueue<Integer> maxPQ` : 최대힙
- `PriorityQueue<Integer> minPQ` : 최소힙

최대힙, 최소힙 2개의 PQ를 사용했다.  


#1. 주어진 연산에서 명령어의 첫 글자가 I인 경우, 
minPQ, maxPQ 각각 주어진 숫자를 삽입한다.  
  ```
  if(command.equals("I")) {
      minPQ.add(num);
      maxPQ.add(num);
  ```
  
#2. 첫 글자가 D인 경우
먼저 PQ가 empty인지 확인하고 empty면 해당 연산을 무시한다.  
`if(maxPQ.isEmpty()) continue;	`

그리고 num에 따라 최대힙, 최소힙에서 각각 `poll`하고  
`poll`한 뒤 나머지 힙에서도 똑같은 숫자를 `remove`해준다.  
```
if(num==1) {
	int tgt = maxPQ.poll();
	minPQ.remove(tgt);
	//큐에서 최댓값을 삭제합니다.
}
else if(num==-1) {
	int tgt = minPQ.poll();
	maxPQ.remove(tgt);
	//큐에서 최솟값을 삭제합니다.
}
```

#3. 모든 연산을 마치고
큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return  

```
if(!maxPQ.isEmpty()) {
	answer[0] = maxPQ.poll();
	answer[1] = minPQ.poll();
}
```

<br>

최대힙, 최소힙 2개의 PQ를 사용하여 문제를 풀었더니 시간초과가 발생했다.

도대체 어느 부분에서 시간초과가 발생하는지 모르겠어서 검색을 해봤는데
각각의 PQ에서 poll한뒤 다른 PQ에서 값을 찾아서 삭제할 때,
`remove()` 메소드를 사용하여 큐를 탐색하여 제거하는 데 , 이 부분에서 시간초과가 발생한 듯 했다.

<br><br>



###  💡 풀이 1. 소스코드 (시간초과)

시간초과 코드

```
package week19.PRG_Lv3_이중우선순위큐;

import java.util.*;

public class Solution_PRG_Lv3_이중우선순위큐 {
	public static void main(String[] args) {
		System.out.println(solution(new String[] {"I 16", "D 1"}));
	}
	public static int[] solution(String[] operations) {
        int[] answer = new int[2];

        //최대힙,최소힙
        PriorityQueue<Integer> maxPQ = new PriorityQueue<Integer>(Collections.reverseOrder());
        PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>();
        	
        for (String oper : operations) {
        	String command = oper.split(" ")[0];
        	int num = Integer.parseInt(oper.split(" ")[1]);
			
        	if(command.equals("I")) {
        		minPQ.add(num);
        		maxPQ.add(num);
				//큐에 주어진 숫자를 삽입합니다.
				
			}else if(command.equals("D")){
				
				if(maxPQ.isEmpty()) continue;				
				//빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.
				
				if(num==1) {
					int tgt = maxPQ.poll();
					minPQ.remove(tgt);
					//큐에서 최댓값을 삭제합니다.
				}

				else if(num==-1) {
					int tgt = minPQ.poll();
					maxPQ.remove(tgt);
					//큐에서 최솟값을 삭제합니다.
				}
				
			}
		}
        
        //모든 연산을 처리한 후 큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return 
        if(!maxPQ.isEmpty()) {
        	answer[0] = maxPQ.poll();
        	answer[1] = minPQ.poll();
        }
        return answer;
    }
}

```

<br><br>

###  💡 풀이 2. TreeMap

TreeMap은 우선순위 큐와 탐색시간의 문제점을 동시에 해결할 수 있다.

TreeMap은 Key값에 의해 자동으로 오름차순으로 정렬되어 있고, 트리구조로 탐색속도가 O(logN)이다.

`firstKey()`와 `lastKey()`를 이용하면 최솟값, 최댓값 모두를 찾을 수 있다.


#1. 주어진 연산에서 명령어의 첫 글자가 I인 경우, map에 숫자를 삽입한다.
  ```
  if(command.equals("I")) {
      map.put(num, map.getOrDefault(num, 0)+1); //숫자와 해당 숫자의 개수 저장
  ```
  
#2. 첫 글자가 D인 경우, 뒤 숫자가 1인 경우 최댓값을 -1인 경우에는 최솟값을 삭제한다.

삭제 작업을 해야 하므로 먼저 해당 값이 존재하는지 map의 사이즈를 확인한다.
`if(map.size()==0) continue;		`

그리고 map에서 최댓값, 또는 최솟값을 제거한다.

이 때 **최댓값(최솟값)을 삭제하는 연산에서 최댓값(최솟값)이 둘 이상인 경우, 하나만 삭제** 되어야 한다. 나는 이 부분을 유념하지 않아서 애를 먹었다.
```
int tgt = ( num==1 ? map.lastKey() : map.firstKey() );
					
int cnt = map.put(tgt, map.get(tgt)-1);
if(cnt==1) map.remove(tgt);
```

<br><br>


###  💡 풀이 2. 소스코드
```
package boj.우선순위큐;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_BJ_7662_이중우선순위큐 {
	static TreeMap<Integer, Integer> map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			map = new TreeMap<>(); 
	        
			for (int k = 0; k < K; k++) {

		        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		        
		        String command = st.nextToken();
		        int num = Integer.parseInt(st.nextToken());
					
		        if(command.equals("I")) {
		        	
		        	map.put(num, map.getOrDefault(num, 0)+1); //숫자와 해당 숫자의 개수 저장
											
				}else if(command.equals("D")){
						
					if(map.size()==0) continue;			
					//빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.
					
					int tgt = ( num==1 ? map.lastKey() : map.firstKey() );
					
					int cnt = map.put(tgt, map.get(tgt)-1);
					if(cnt==1) map.remove(tgt);
				}
				
			}
			
			if(map.size()==0) {
				System.out.println("EMPTY");
			}else {
				System.out.println(map.lastKey()+" "+map.firstKey());
			}
			
			
		}
		
	}

}

```

<br><br>