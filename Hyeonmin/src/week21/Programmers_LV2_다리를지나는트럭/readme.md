## Programmers LV2 다리를 지나는 트럭
- 스택/큐
- level2

<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42583

트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다. 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다. 다리에는 트럭이 최대 bridge_length대 올라갈 수 있으며, 다리는 weight 이하까지의 무게를 견딜 수 있습니다. 단, 다리에 완전히 오르지 않은 트럭의 무게는 무시합니다.

solution 함수의 매개변수로 다리에 올라갈 수 있는 트럭 수 bridge_length, 다리가 견딜 수 있는 무게 weight, 트럭 별 무게 truck_weights가 주어집니다. 이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.


#### 제한사항
bridge_length는 1 이상 10,000 이하입니다.
weight는 1 이상 10,000 이하입니다.
truck_weights의 길이는 1 이상 10,000 이하입니다.
모든 트럭의 무게는 1 이상 weight 이하입니다.
<br><br>

###  💡 풀이

변수
`Queue<int[]> q` : 다리위에 올라가있는 트럭의 올라간 시간과 무게를 저장하는 큐
`int weightOnBridge` : 다리위에 올라가있는 트럭들의 무게 합
`int time` : 현재 시간


<br>

모든 트럭이 다리위에 올라갈 때 까지 while문을 실행한다

```java
        //올라 탄 시간, 무게
        Queue<int[]> q = new LinkedList<>();
        
        int weightOnBridge = 0;
        int time = 1;
        int idx = 0;
        int size = truck_weights.length;
        
        while(idx<size){
        	...
        }
```

현재 시간에 다리 입구에 있는 트럭이 다리에 올라가지 못하는 경우(해당 트럭이 올라가면 `weightOnBridge`의 값이 다리의 최대 무게보다 커지는 경우, `q.size()`가 다리의 길이 `weight`보다 커지는 경우) 다리 위에서 가장 앞에있는 트럭이 다리에서 내리는 시간까지 이동한다  

```java
            int truckWeight = truck_weights[idx];
            
            //다리 위에 못 올라가는 경우
            if((weightOnBridge + truckWeight > weight) || q.size() >= bridge_length){
                //가장 앞에있는 트럭 제거
                int[] truck = q.poll();
                
                //가장 앞에있는 트럭이 다리를 건너고 난 시간
                time += bridge_length - (time - truck[0]);
                //다리위에 트럭 무게 감소
                weightOnBridge -= truck[1];
                
                continue;
            }
```

트럭이 다리위에 올라가는 경우 `q`에 해당 트럭의 정보를 저장하고 `idx`와 `time`, `weightOnBridge`를 증가시킨다

```java
            //다리 위에 올라가는 경우
            q.offer(new int[]{time, truckWeight});
            //다음 트럭
            idx++;
            //다리 위에 있는 트럭 무게 합 증가
            weightOnBridge += truckWeight;
            //시간 증가
            time++;
```

`time`이 1 증가했을 때 `q`의 가장 앞에있는 트럭이 다리에서 내려가는지 확인하고 내려가는 경우 다리 위 무게`weightOnBridge`의 값을 감소시킨다

```java
            //가장 앞에 있는 트럭이 다리를 건너는 경우
            if(time - truck[0] > bridge_length){
            	//해당 트럭 제거
                q.poll();
                //다리 위 무게 감소
                weightOnBridge -= truck[1];
            }
```

모든 트럭이 다리 위에 올라가면 `q`에 있는 트럭이 모두 내려갈 때 까지 시간을 증가시킨다

```java
        //모든 트럭이 다리에 올라간 경우 그 트럭들이 모두 건너 갈 때 까지
        while(!q.isEmpty()){
        	//트럭제거
            int[] truck = q.poll();
            //트럭이 다리를 내려갔을 때의 시간
            time += bridge_length - (time - truck[0]);
        }
```


<br><br>

###  💡 소스코드
```java
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_LV2_다리를지나는트럭 {
	
	
	public static void main(String[] args) {
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights= {7,4,5,6};
		
		System.out.println(solution(bridge_length, weight, truck_weights));
		
	}
	
	static int solution(int bridge_length, int weight, int[] truck_weights) {
        //다리위에 있는 트럭을 저장할 배열. 올라 탄 시간, 트럭 무게
        Queue<int[]> q = new LinkedList<>();
        
        //다리 위에 올라가있는 트럭들의 무게 합
        int weightOnBridge = 0;
        //지난 시간
        int time = 1;
        int idx = 0;
        int size = truck_weights.length;
        
        while(idx<size){
            int truckWeight = truck_weights[idx];
            
            //다리 위에 못 올라가는 경우
            if((weightOnBridge + truckWeight > weight) || q.size() >= bridge_length){
                //가장 앞에있는 트럭 제거
                int[] truck = q.poll();
                
                //가장 앞에있는 트럭이 다리를 건너고 난 시간
                time += bridge_length - (time - truck[0]);
                //다리위에 트럭 무게 감소
                weightOnBridge -= truck[1];
                
                continue;
            }
            
            //다리 위에 올라가는 경우
            q.offer(new int[]{time, truckWeight});
            //다음 트럭
            idx++;
            //다리 위에 있는 트럭 무게 합 증가
            weightOnBridge += truckWeight;
            //시간 증가
            time++;
            
            //가장 앞에 있는 트럭
            int[] truck = q.peek();
            
            //가장 앞에 있는 트럭이 다리를 건너는 경우
            if(time - truck[0] > bridge_length){
            	//해당 트럭 제거
                q.poll();
                //다리 위 무게 감소
                weightOnBridge -= truck[1];
            }
        }
        
        //모든 트럭이 다리에 올라간 경우 그 트럭들이 모두 건너 갈 때 까지
        while(!q.isEmpty()){
        	//트럭제거
            int[] truck = q.poll();
            //트럭이 다리를 내려갔을 때의 시간
            time += bridge_length - (time - truck[0]);
        }
        
        return time;
	}

}



```


<br>


테스트 1 〉	통과 (0.14ms, 55.4MB)
테스트 2 〉	통과 (0.13ms, 53MB)
테스트 3 〉	통과 (0.13ms, 52.3MB)
테스트 4 〉	통과 (0.47ms, 52.7MB)
테스트 5 〉	통과 (1.11ms, 53.4MB)
테스트 6 〉	통과 (0.71ms, 53.1MB)
테스트 7 〉	통과 (0.22ms, 51.9MB)
테스트 8 〉	통과 (0.14ms, 52.6MB)
테스트 9 〉	통과 (0.60ms, 52.5MB)
테스트 10 〉	통과 (0.18ms, 52.7MB)
테스트 11 〉	통과 (0.14ms, 52.1MB)
테스트 12 〉	통과 (0.21ms, 52.7MB)
테스트 13 〉	통과 (0.21ms, 52.9MB)
테스트 14 〉	통과 (0.13ms, 52.3MB)