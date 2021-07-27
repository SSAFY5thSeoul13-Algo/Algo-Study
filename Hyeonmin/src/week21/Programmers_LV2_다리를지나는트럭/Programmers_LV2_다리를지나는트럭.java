package week21.Programmers_LV2_다리를지나는트럭;

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
