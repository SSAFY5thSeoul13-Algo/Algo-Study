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
				if(num==1) {
					
					//빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.
					
					int tgt = maxPQ.poll();
					minPQ.remove(tgt);
					//큐에서 최댓값을 삭제합니다.
				}

				else if(num==-1) {
					//빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.
					
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
