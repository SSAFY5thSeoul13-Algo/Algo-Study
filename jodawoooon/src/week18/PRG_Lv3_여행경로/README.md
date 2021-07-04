## Programmers Lv3 여행경로

- 깊이/너비 우선 탐색(DFS/BFS)
- level3



<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/43164

주어진 항공권을 모두 이용하여 여행경로를 짜려고 합니다. 항상 "ICN" 공항에서 출발합니다.  

항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때, 방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성해주세요.  


#### 제한사항
모든 공항은 알파벳 대문자 3글자로 이루어집니다.  
주어진 공항 수는 3개 이상 10,000개 이하입니다.  
tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.  
주어진 항공권은 모두 사용해야 합니다.  
만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.  
모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.  
<br><br>

###  💡 풀이
푸는데 상당히 오래걸린 문제입니다..  
BFS로 풀어보려고 오랜시간 시도했으나, 계속 풀리지 않아  
DFS로 풀었습니다 (로직은 비슷하다)  

**이 문제의 포인트는 `경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.` 입니다.**  
<br>

이를 구현하기 위해 String[]형 ArrayList `routes`를 이용했습니다.  
(다른 방법들도 (Map, Stack 이용) 시도해 보았으나 잘 안 됐음..)  

DFS로 탐색하며 경로를 저장하고, 경로가 완성되면 `routes`에 add했습니다.

<br>

전역 변수는 다음과 같습니다.  
`int LEN` : 항공권 리스트 tickets 배열의 길이  
`boolean[] visited` : 항공권을 사용했는지 여부를 체크할 boolean형 배열  
`ArrayList<String[]> routes` : 모든 경로를 저장할 리스트  

DFS 메소드의 매개변수는 다음과 같이 구성했습니다.  
`int idx` : 경로 idx  
`String from` : 출발지  
`String[][] tickets` : 항공권 리스트  
`String[] route` : 여행 경로 루트  

<br>

DFS 메소드 안에서는 티켓을 사용할 수 있다면  
즉, 티켓의 출발지와 from값이 같다면  
티켓을 사용하고 (visited 체크)  
route에 사용한 티켓의 도착지를 저장하고 해당 도착지를 출발지로 하여  또 다른 티켓에 대해 탐색 합니다.  

위 과정을 반복하여 모든 티켓을 사용하면 (idx==LEN)  
전역 리스트 routes에 현재 방문한 경로 route 배열을 add 해줍니다.  

```
private void dfs(int idx, String from, String[][] tickets, String[] route) {
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
		 
        return;
		
	}
```

<br>


그리고 마지막에 `routes.size()>1`일 경우 Comparator를 이용해 알파벳 순서대로 정렬했습니다.
```
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
```


그리고 문제에서 요구한 대로 알파벳 순서가 앞서는 경로를 return 했습니다.



<br><br>

###  💡 소스코드
```
import java.util.*;

class Solution {
    static int LEN;
	static boolean[] visited;
	static ArrayList<String[]> routes = new ArrayList<>();
	
    public String[] solution(String[][] tickets) {
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

	private void dfs(int idx, String from, String[][] tickets, String[] route) {
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
		 
        return;
		
	}
}
```


<br>