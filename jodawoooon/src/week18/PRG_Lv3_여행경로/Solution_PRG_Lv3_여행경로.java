package week18.PRG_Lv3_여행경로;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Solution_PRG_Lv3_여행경로 {

	
	static int LEN;
	static boolean[] visited;
	static ArrayList<String[]> routes = new ArrayList<>();
	public static void main(String[] args) {
		String[][] tickets = new String[][]{{"ICN", "JFK"}, {"HND", "IAD"},{"JFK", "HND"}};
		
		System.out.println(Arrays.toString(solution(tickets)));
	}
    public static String[] solution(String[][] tickets) {
    	LEN = tickets.length;
    	visited = new boolean[LEN+1]; //방문체크
        
        String[] route = new String[LEN+1]; //경로 담을 배열
 	    route[0] = "ICN"; //항상 "ICN" 공항에서 출발
    	dfs(0, "ICN", tickets, route);
    	// idx  start  ticket리스트, 경로
    	
    	if(routes.size()>1) {
    		//경로가 2개 이상이다? 알파벳 순서대로 정렬
    		
    		Collections.sort(routes, new Comparator<String[]>(){

				@Override
				public int compare(String[] o1, String[] o2) {
					
					String s1 = "";
					String s2 = "";
					for(String s : o1) {
						s1 += s;
					}
					for(String s : o2) {
						s2 += s;
					}
					
					return s1.compareTo(s2);
					
				}
    			
    		});
    	}

        return routes.get(0); //알파벳 순서가 앞서는 경로를 return
    }

	private static void dfs(int idx, String from, String[][] tickets, String[] route) {
		if(idx==LEN) { //모든 티켓 사용 하여 경로를 다 찾았다면
			
			String[] tmp = new String[LEN+1];
			for(int i = 0; i<= LEN ; i++) {

				tmp[i] = route[i];
			}
			
			routes.add(tmp); //최종 ArrayList에 카피하여 경로 삽입 
			return;
		}
		
		for(int i=0 ; i <tickets.length ; i++) {
			String dept = tickets[i][0];
			String dest = tickets[i][1];
			
			if(visited[i]) continue; //이미 사용한 티켓이라면 continue
			if(from.equals(dept)) {
				
				//사용 가능한 티켓이다
				visited[i] = true;
				route[idx+1] = dest;
				dfs(idx+1, dest, tickets, route);
				visited[i] = false;
			}
		}
		 
		
	}
    
   
}
