package week21.PROGRAMMERS_LV2_다리를지나는트럭;

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