## Programmers - 이중 우선순위 큐 
- heap, PriorityQueue
- Level3

## 풀이
> 우선순위 큐를 2개 만들어 하나는 오름차순, 하나는 reverseOrder을 이용한 내림자순인 큐를 만들었습니다. 그리고 명령어 데이터의 첫부분이 I 이면 큐에 삽입, D인 경우 큐를 통해 최솟값(혹은 최댓값)을 확인한뒤 remove해주었습니다. 

## 막힌점 
> 마지막 answer에 최댓값,최솟값을 저장하는 부분에서 우선순위큐가 비어있을 때 answer에 값을 최댓값,최소값을 저장하면 NullpointerError가 나서 우선순위큐가 비어있을때 조건을 추가해주었습니다.

## 소스코드
~~~java
import java.util.Collections;
import java.util.PriorityQueue;
class Solution {
	    public int[] solution(String[] operations) {
	    	// 최댓값/최솟값 출력 
	        int[] answer = new int[2];
	        
	        PriorityQueue<Integer> pq = new PriorityQueue<>();
	        PriorityQueue<Integer> reversepq = new PriorityQueue<>(Collections.reverseOrder());
	        
	        for(int i=0; i<operations.length; i++) {
	        	String str = operations[i];
	        	String[] op = str.split(" ");
	        	
	        	if(op[0].equals("I")) {
	        		int num = Integer.parseInt(op[1]);
	        		pq.add(num);
	        		reversepq.add(num);
	        	}else if(op[0].equals("D")) {
	        		int num = Integer.parseInt(op[1]);
	        		if(!pq.isEmpty()) {
	        			int remove_num = 0;
		        		if(num==-1) {
		        			remove_num = pq.peek();
		        		}else if(num==1) {
		        			remove_num = reversepq.peek();
		        		}
		        		
		        		pq.remove(remove_num);
		        		reversepq.remove(remove_num);
	        		}
	        	}
	        }
	        
	        if(pq.isEmpty()) {
	        	answer[0] = 0;
		        answer[1] = 0;
	        }else {
	        	answer[0] = reversepq.poll();
		        answer[1] = pq.poll();
	        }
	        
	        
	        return answer;
	    }
	}

~~~

## 결과 
-------

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (1.14ms, 52.5MB)|
|테스트 2 |	통과 (0.68ms, 53.4MB)|
|테스트 3 |	통과 (0.72ms, 51.8MB)|
|테스트 4 |	통과 (0.61ms, 52MB)|
|테스트 5 |	통과 (0.70ms, 53MB)|
|테스트 6 |	통과 (0.65ms, 52.8MB)|

