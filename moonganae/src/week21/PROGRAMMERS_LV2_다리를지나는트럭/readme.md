## Progrmmers LV2 다리를 지나는 트럭
- 스택/큐
- Level 2
- https://programmers.co.kr/learn/courses/30/lessons/42583
<br>

### 문제설명

> 트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다. 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다. 다리에는 트럭이 최대 bridge_length대 올라갈 수 있으며, 다리는 weight 이하까지의 무게를 견딜 수 있습니다. 단, 다리에 완전히 오르지 않은 트럭의 무게는 무시합니다.

|경과 시간|	다리를 지난 트럭|	다리를 건너는 트럭|	대기 트럭|
|----|----|----|----|
|0|	[]|	[]|	[7,4,5,6]|
|1~2|	[]|	[7]|	[4,5,6]|
|3	|[7]	[4]|	[5,6]|
|4	|[7]	| [4,5]|	[6]|
|5	|[7,4]|	[5]|	[6]|
|6~7	|[7,4,5]	|[6]	|[]|
|8	|[7,4,5,6]|	[]|	[]|

> 따라서, 모든 트럭이 다리를 지나려면 최소 8초가 걸립니다.
solution 함수의 매개변수로 다리에 올라갈 수 있는 트럭 수 bridge_length, 다리가 견딜 수 있는 무게 weight, 트럭 별 무게 truck_weights가 주어집니다. 이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.


### 제한사항
- bridge_length는 1 이상 10,000 이하입니다.
- weight는 1 이상 10,000 이하입니다.
- truck_weights의 길이는 1 이상 10,000 이하입니다.
- 모든 트럭의 무게는 1 이상 weight 이하입니다.

### 입출력 예
|bridge_length|	weight|	truck_weights|	return|
|----|----|----|----|
|2|	10|	[7,4,5,6]|	8|
|100	|100|	[10]|	101|
|100	|100|	[10,10,10,10,10,10,10,10,10,10]|	110|


### 풀이 및 과정
Queue 자료구조를 사용하여 해결하였습니다.

대기 트럭 배열을 순회하면서 현재 트럭이 탑승할 수 있는지 없는지를 확인하면서 로직을 수행하였습니다.
- 다리에 트럭이 올라갈 수 있다면 올리고 1초 증가
- 트럭이 올라갈 수 없다면 트럭한대 하차시키고 시간을 갱신

### 소스코드
```java
import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        // 현재 다리위의 총무게
        int W=0;
        // 다리 위의 트럭 정보들
        Queue<Truck> q = new LinkedList<>();
        
        // 현재 시간 (1초 부터 올라갈 수 있으므로)
        int time = 1;
        for(int i=0; i<truck_weights.length; i++){
        
            // 다리에 올라갈 수 있다면
            if(q.size() <= bridge_length && W + truck_weights[i] <= weight){
                // 다리 위에 트럭 올리기
                q.offer(new Truck(truck_weights[i], time + bridge_length));
                // 다리 위의 총무게 증가
                W += truck_weights[i];
                // 시간 1초 증가
                time++;
            }
            // 다리에 올라갈 수 없다면
            else{
                // 젤 처음 다리에 올라간 트럭 하차
                Truck cur = q.poll();
                // 다리 위의 총무게 감소
                W -= cur.w;
                // 시간 갱신 (현재 시간과 방금 하차한 트럭의 시간중 가장 높은것)
                time = Math.max(time, cur.endTime);
                // i번째 트럭은 탑승하지 못했음으로 i--
                i--;
            }
            
        }
        // 다리에 모든 트럭을 내리면서 하차시간 확인
        while(!q.isEmpty()){
            time = q.poll().endTime;
        }
        return time;
    }
    class Truck {
        int w, endTime;
        public Truck(int w, int endTime){
            this.w = w;
            this.endTime = endTime;
        }
    }
}
```

### 결과
```
테스트 1 〉	통과 (0.40ms, 54.4MB)
테스트 2 〉	통과 (0.41ms, 53.2MB)
테스트 3 〉	통과 (0.38ms, 52.4MB)
테스트 4 〉	통과 (0.73ms, 53MB)
테스트 5 〉	통과 (1.15ms, 53.3MB)
테스트 6 〉	통과 (0.99ms, 52.9MB)
테스트 7 〉	통과 (0.43ms, 52MB)
테스트 8 〉	통과 (0.37ms, 53.8MB)
테스트 9 〉	통과 (0.94ms, 52.3MB)
테스트 10 〉	통과 (0.41ms, 52.5MB)
테스트 11 〉	통과 (0.39ms, 52.4MB)
테스트 12 〉	통과 (0.47ms, 52.2MB)
테스트 13 〉	통과 (0.47ms, 52.9MB)
테스트 14 〉	통과 (0.33ms, 52.1MB)
```

