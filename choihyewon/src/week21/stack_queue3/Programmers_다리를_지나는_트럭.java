package week21.stack_queue3;

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
