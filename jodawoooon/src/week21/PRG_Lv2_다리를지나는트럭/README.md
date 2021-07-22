## Programmers Lv2 다리를 지나는 트럭
- 스택/큐
- level2



<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42583

트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다. 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다. 다리에는 트럭이 최대 bridge_length대 올라갈 수 있으며, 다리는 weight 이하까지의 무게를 견딜 수 있습니다. 단, 다리에 완전히 오르지 않은 트럭의 무게는 무시합니다.

예를 들어, 트럭 2대가 올라갈 수 있고 무게를 10kg까지 견디는 다리가 있습니다. 무게가 [7, 4, 5, 6]kg인 트럭이 순서대로 최단 시간 안에 다리를 건너려면 다음과 같이 건너야 합니다.
![](https://images.velog.io/images/jodawooooon/post/a982ed43-06bf-42f9-bdb9-8073c9d544a1/image.png)

따라서, 모든 트럭이 다리를 지나려면 최소 8초가 걸립니다.

solution 함수의 매개변수로 다리에 올라갈 수 있는 트럭 수 bridge_length, 다리가 견딜 수 있는 무게 weight, 트럭 별 무게 truck_weights가 주어집니다. 이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.
<br>

#### ✔ 제한사항
- bridge_length는 1 이상 10,000 이하입니다.
- weight는 1 이상 10,000 이하입니다.
- truck_weights의 길이는 1 이상 10,000 이하입니다.
- 모든 트럭의 무게는 1 이상 weight 이하입니다.
<br>
 
#### ✔ 입출력 예

![](https://images.velog.io/images/jodawooooon/post/834db5c2-182a-4ab2-86be-d2c0196fe3ea/image.png)
<br>

###  💡 풀이
- `int time` : 시간
- `int curWeight` : 다리 위 무게
- `Queue<Integer> waiting` : 대기트럭
- `Queue<Integer> bridge` : 다리 위


<br>

먼저 다리와 대기트럭 상태를 초기화한다.  

```java
	for(int w : truck_weights){
            waiting.add(w);
        }
        //대기트럭 구현
        
        for(int i = 0 ; i < bridge_length ; i++) {
        	bridge.add(-1);
        }
        //다리 초기 셋팅 
```

그리고 첫번째 트럭이 다리를 다 지나려면 다리의 길이만큼 시간이 소요되므로 `time += bridge_length` 해준다.  


그리고 가장 먼저 다리에서 내리려 하는 트럭을 확인한다.  
빈 공간이 아니고 진짜 트럭이 내리면 현재 다리 위 무게 `curWeight`값을 갱신한다.  

```java
            int outTruck = bridge.poll();
            //다리에서 내리는 트럭
            if(outTruck!=-1) {
            //트럭에서 내릴거 있으면 (빈공간 아니면)
	            curWeight -= outTruck;
	            //현재 다리 위 무게 갱신
            }
```

그리고 `waiting` queue에서 지금 다리를 건너려 하는 트럭 무게가 다리위에 올라갈 수 있는지 확인한다.  
- 올라갈 수 없으면 빈 공간을 `bridge`에 add하고,   
- 올라갈 수 있으면 `wating` 큐에서 tgt을 poll하고 `bridge`에 올린 후  `curWeight`를 갱신한다.  

```java
            if(tgtW+curWeight > weight ) {
            	bridge.add(-1);
            	//다리 못올라가면 빈 공간 삽입
            }else {
            	
                waiting.poll();
                bridge.add(tgtW);
                curWeight += tgtW;
                //다리위에 올라감
            }
```

<br><br>

###  💡 소스코드



```java
package week21.PRG_Lv2_다리를지나는트럭;

import java.util.*;

public class Solution_PRG_Lv2_다리를지나는트럭 {
	
	public int solution(int bridge_length, int weight, int[] truck_weights)     {
        int time = 0; //시간
        int curWeight = 0; //다리 위 무게
        
        Queue<Integer> waiting = new LinkedList<>(); //대기트럭
        Queue<Integer> bridge = new LinkedList<>(); //다리 위
        
        for(int w : truck_weights){
            waiting.add(w);
        }
        //대기트럭 구현
        
        for(int i = 0 ; i < bridge_length ; i++) {
        	bridge.add(-1);
        }
        //다리 초기 셋팅 
        
        time += bridge_length;
        //첫번째 트럭이 다리를 지나는 시간 time+bridge_length
        
        
        while(!waiting.isEmpty()){
            
            int tgtW = waiting.peek();
            //지금 다리 건너려 하는 트럭 무게
           
            int outTruck = bridge.poll();
            //다리에서 내리는 트럭
            if(outTruck!=-1) {
            //트럭에서 내릴거 있으면 (빈공간 아니면)
	            curWeight -= outTruck;
	            //현재 다리 위 무게 갱신
            }
            
            if(tgtW+curWeight > weight ) {
            	bridge.add(-1);
            	//다리 못올라가면 빈 공간 삽입
            }else {
            	
                waiting.poll();
                bridge.add(tgtW);
                curWeight += tgtW;
                //다리위에 올라감
            }

            time++;
        }
        
        return time;
    }
}

```

<br>

### 🚩 결과
테스트 1 〉	통과 (1.54ms, 53MB)
테스트 2 〉	통과 (13.92ms, 53.4MB)
테스트 3 〉	통과 (0.17ms, 52MB)
테스트 4 〉	통과 (13.53ms, 53.5MB)
테스트 5 〉	통과 (51.18ms, 60.4MB)
테스트 6 〉	통과 (23.36ms, 54.6MB)
테스트 7 〉	통과 (2.57ms, 52.7MB)
테스트 8 〉	통과 (0.55ms, 52.1MB)
테스트 9 〉	통과 (4.94ms, 52MB)
테스트 10 〉	통과 (0.69ms, 52.8MB)
테스트 11 〉	통과 (0.15ms, 53.1MB)
테스트 12 〉	통과 (1.14ms, 52.3MB)
테스트 13 〉	통과 (4.42ms, 51.9MB)
테스트 14 〉	통과 (0.23ms, 52.6MB)