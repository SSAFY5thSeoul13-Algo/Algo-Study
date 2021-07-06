package week18.dfs_bfs4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 여행경로 {
	static boolean[] visited;
	static List<String> list = new ArrayList<>();
	static String route;
	public String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length+1];
        visited = new boolean[tickets.length];
        
        for(int i=0; i<tickets.length; i++) {
        	String start = tickets[i][0];
        	String end = tickets[i][1];
        	
        	if(start.equals("ICN")) {
        		visited[i] = true;
        		route = start + " ";
        		dfs(tickets,start,end,0);
        		visited[i] = false;
        	}
        }
        // 알파벳 순으로 정렬 
        Collections.sort(list);
        answer = list.get(0).split(" ");
        return answer;
    }
	
	private void dfs(String[][] tickets, String start, String end,int cnt) {
		route += end + " ";
		if(cnt == tickets.length+1) {
			list.add(route);
			return;
		}
		
		for(int i=0; i<tickets.length; i++) {
			if(!visited[i] && end.equals(tickets[i][0])) {
				visited[i] = true;
				dfs(tickets, tickets[i][0], tickets[i][1], cnt+1);
				visited[i] = false;
				// 전 단계로 초기화 
				route = route.substring(0,route.length()-4);
				
			}
		}
		
	}

}
