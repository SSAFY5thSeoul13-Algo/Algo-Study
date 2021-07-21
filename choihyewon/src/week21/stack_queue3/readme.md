## Programmers - 다리를 지나는 트럭 
- Queue
- Level2

🔗[다리를 지나는 트럭 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/42583)

## 풀이

트럭만큼 반복문을 실행한다. 그리고 대기트럭 배열에서 하나씩 순차적으로 꺼내어 트럭이 다리를 지날때 큐에 넣어준다.
만약 다리에 아무런 트럭이 없다면 트럭의 무게를 큐에 삽입하고 무게와 시간을 증가시킨다.
만약 다리에 트럭이 있다면 큐가 다리의 길이와 같을 경우 다리에서 트럭 하나가 나와야하기 때문에 큐에서 poll을 한 값을 sum에서 빼준다. 
그리고 지금까지 다리 위의 트럭의 총 무게 sum과 현재 트럭의 무게를 더한값이 weight보다 작거나 같은 경우 트럭의 무게를 큐에 삽입하고 무게와 시간을 증가시킨다. 아닐 경우에는 0을 넣어주고 시간만 증가시킨다. 

이렇게 대기트럭이 모두 다리에 진입했을 경우 반복문이 종료가 되는데, 마지막 트럭이 다리를 다 건넌 후 time이 정답이 되므로 구한 time에 다리의 길이를 더해 답을 구한다.


## 소스코드
~~~java
import java.util.*;

public class Programmers_다리를_지나는_트럭 {
	public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> bridge = new LinkedList<>();
        
        
        // 걸린 시간 
        int time = 0;
        // 다리위에 있는 트럭의 무게 합 
        int sum = 0;
        
        for(int i=0; i<truck_weights.length; i++) {
        	int truck = truck_weights[i];
        	while(true) {
        		// 다리에 아무 트럭도 없다면 
            	if(bridge.isEmpty()) {
            		bridge.add(truck);
            		sum += truck;
            		time++;
            		break;
            	}
            	// 다리에 트럭이 있다면 
            	else {
            		// 다리위에 트럭을 하나 빼준다.
            		if(bridge.size()==bridge_length) {
            			sum -= bridge.poll();
            		}
            		// 다리위의 트럭의 총 무게가 weight보다 작은 경우 
            		if(sum + truck <= weight) {
            			bridge.add(truck);
            			sum += truck;
                		time++;
                		break;
            		}else {
            			// 다리에 트럭이 들어갈 수 없으므로 0이 들어간다.
            			bridge.add(0);
            			time++;
            		}
            	}
        	}
        }
        
        // 대기 트럭이 다 나가도 마지막 트럭은 다리를 건너야하기 때문에 다리 길이를 더해준다.
        answer = time + bridge_length;
        
        return answer;
    }

}
~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (1.16ms, 52.4MB)|
|테스트 2 |	통과 (10.47ms, 53.1MB)|
|테스트 3 |	통과 (0.12ms, 53.2MB)|
|테스트 4 |	통과 (7.97ms, 53.2MB)|
|테스트 5 |	통과 (33.55ms, 60.7MB)|
|테스트 6 |	통과 (18.13ms, 54.6MB)|
|테스트 7 |	통과 (1.85ms, 52.4MB)|
|테스트 8 |	통과 (1.75ms, 52.4MB)|
|테스트 9 |	통과 (4.36ms, 53.3MB)|
|테스트 10 |	통과 (0.59ms, 52.9MB)|
|테스트 11 |	통과 (0.10ms, 52.3MB)|
|테스트 12 |	통과 (0.89ms, 52.3MB)|
|테스트 13 |	통과 (2.65ms, 52.3MB)|
|테스트 14 |	통과 (0.11ms, 52.3MB)|