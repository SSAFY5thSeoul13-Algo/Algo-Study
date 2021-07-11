package week19.PRG_Lv3_이중우선순위큐;

import java.util.*;

public class Solution_PRG_Lv3_이중우선순위큐TreeMap {
	public static void main(String[] args) {
		System.out.println(solution(new String[] {"I 16", "D 1"}));
	}
	public static int[] solution(String[] operations) {
        int[] answer = new int[2];

        TreeMap<Integer, Integer> map = new TreeMap<>(); 
        
        for (String oper : operations) {
        	String command = oper.split(" ")[0];
        	int num = Integer.parseInt(oper.split(" ")[1]);
			
        	if(command.equals("I")) {
        		map.put(num, map.getOrDefault(num, 0)+1); //숫자와 해당 숫자의 개수 저장
				
			}else if(command.equals("D")){
				
				if(map.size()==0) continue;			
				//빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.
				
				int tgt = ( num==1 ? map.lastKey() : map.firstKey() );
				
				int cnt = map.put(tgt, map.get(tgt)-1);
				if(cnt==1) map.remove(tgt);
				
			}
		}
        
        //모든 연산을 처리한 후 큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return 
        if(map.size()>0) {
        	answer[0] = map.lastKey();
        	answer[1] = map.firstKey();
        }
        return answer;
    }
}
