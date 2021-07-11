package week19.Programmers_LV5_방의개수;

import java.util.HashSet;
import java.util.Set;

public class Programmers_LV5_방의개수 {
	static int[] deltaX = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] deltaY = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	public static void main(String[] args) {
		int[] arrows = {6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0};
		System.out.println(solution(arrows));
	}
	
	static int solution(int[] arrows) {
		//지나온 점들
		Set<String> nodeSet = new HashSet<>();
		//지나온 선들
		Set<String> edgeSet = new HashSet<>();
		
		int y = 0;
		int x = 0;
		//방의 수
		int count = 0;
		
		//시작점 체크
		nodeSet.add("0&0");
		
		//모든 이동실행
		for (int arrow : arrows) {
			
			//각 이동을 동일하게 2번 실행
			for (int i = 0; i < 2; i++) {
				//이동한 y위치
				int ny = y + deltaY[arrow];
				//이동한 x위치
				int nx = x + deltaX[arrow];
				
				//방문 위치 체크
				String node = ny+"&"+nx;
				//지나온 선 체크
				String edge1 = y+"&"+x+"&"+ny+"&"+nx;
				//지나온 선 역방향 체크
				String edge2 = ny+"&"+nx+"&"+y+"&"+x;
				
				//이미 방문을 했던 위치에 왔을 때 방문한 방향이 처음인 경우 방이 생긴다
				if(nodeSet.contains(node) && !edgeSet.contains(edge1)) {
					count++;
				}
				
				//지나온 선 체크
				edgeSet.add(edge1);
				edgeSet.add(edge2);
				//점 방문체크
				nodeSet.add(node);
				
				x = nx;
				y = ny;
			}
		}
		
		
		return count;
	}

}
