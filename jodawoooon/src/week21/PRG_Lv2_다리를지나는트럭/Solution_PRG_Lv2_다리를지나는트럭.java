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
