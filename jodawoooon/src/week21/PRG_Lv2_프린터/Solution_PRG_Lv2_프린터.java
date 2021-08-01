package week21.PRG_Lv2_프린터;

import java.util.*;

public class Solution_PRG_Lv2_프린터 {
	public static int solution(int[] priorities, int location) {
		int answer = 0;
        
        HashMap<Integer, Integer> docsMap = new HashMap<>(); //남은 문서의 우선순위별 개수
        Queue<Integer> printer = new LinkedList<>(); //프린터를 표현할 Queue
        
        for(int p : priorities) {
        	docsMap.put(p, docsMap.getOrDefault(p, 0)+1); //map에 우선순위별 갯수 저장
        	printer.add(p); //프린터에 문서의 우선순위 순서대로 넣기
        }
        
        
        
        while(!printer.isEmpty()) {

        	int cur = printer.poll();
        	boolean flag = false; //인쇄 가능한지 아닌지 체크
        	for(int next : docsMap.keySet()) {
        		if(next>cur) {
        			//나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면
        			flag= true;
        			break;
        		}
        	}
        	
        	if(flag) {
        		//대기목록의 가장 마지막에 넣습니다.
        		
        		printer.add(cur);
        		if(location==0) location = printer.size()-1; //지금 얘가 내가 인쇄를 요청한 문서인데 바로 앞에서도 인쇄 못했으니까 위치를 맨 뒤로 바꿈
        		else location--;

        	} else {
        		//그렇지 않으면 인쇄한다.
            	answer++;
            	
            	//내가 찾는 문서가 인쇄됨? 종료
            	if(location==0) break;
            	//아니면? location 위치 조정해아하면 앞으로 당긴다
            	else location--;
            	
            	
            	//map에서 우선순위별 갯수 하나 빼기
            	int cnt = docsMap.get(cur);
            	if(cnt>1) docsMap.put(cur, cnt-1);
            	else docsMap.remove(cur);
        	}

        }
        return answer;
    }
}
