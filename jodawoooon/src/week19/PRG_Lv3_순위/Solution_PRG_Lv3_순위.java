package week19.PRG_Lv3_순위;

import java.util.*;

public class Solution_PRG_Lv3_순위 {
	
	static ArrayList<Integer>[] winList;
	static ArrayList<Integer>[] loseList;
	public int solution(int n, int[][] results) {
		int answer = 0;  //정확하게 순위를 매길 수 있는 선수
		
        winList = new ArrayList[n+1]; //1~n번  선수가 이긴 선수 리스트
        loseList = new ArrayList[n+1]; //1~n번 선수가 진 선수 리스트
        
        for (int i = 0; i <= n; i++) {
			winList[i] = new ArrayList<>();
			loseList[i] = new ArrayList<>();
		}
        
        //경기 결과 승패 저장
        for (int i = 0; i < results.length; i++) {
			int A = results[i][0];
			int B = results[i][1];
			
			winList[A].add(B); //A가 B선수를 이겼다.
			loseList[B].add(A); //B가 A선수에게 졌다.
		}
        
        
        for (int idx = 1; idx <= n; idx++) {
        	int winCnt = getWinCnt(idx, n);
        	int loseCnt = getLoseCnt(idx, n);

        	if(winCnt + loseCnt == n-1) answer++; 
            //이긴선수 + 진 선수 = n-1이면 모든 경기의 결과를 알수 있으므로 등수를 구할 수 있다.
		}

        return answer;
    }
	
    //idx 선수가 이긴 선수의 수를 구하는 bfs 메소드
	private static int getWinCnt(int idx,int n) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[n+1];
		int winCnt = 0;
		
		queue.add(idx);
		visited[idx]=true;
		
		while(!queue.isEmpty()) {
			int playerNum = queue.poll();
			
			for (int i = 0; i < winList[playerNum].size(); i++) {
				int tgtNum = winList[playerNum].get(i);
				if(visited[tgtNum]) continue;
				visited[tgtNum] = true;
				queue.add(tgtNum);
				winCnt++;
			}
		}
		return winCnt;
	}
	
    //idx 선수가 진 선수의 수를 구하는 bfs 메소드
	private static int getLoseCnt(int idx,int n) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[n+1];
		int loseCnt = 0;
		
		queue.add(idx);
		visited[idx]=true;
		
		while(!queue.isEmpty()) {
			int playerNum = queue.poll();
			
			for (int i = 0; i < loseList[playerNum].size(); i++) {
				int tgtNum = loseList[playerNum].get(i);
				if(visited[tgtNum]) continue;
				visited[tgtNum] = true;
				queue.add(tgtNum);
				loseCnt++;
			}
		}
		return loseCnt;
	}
}
