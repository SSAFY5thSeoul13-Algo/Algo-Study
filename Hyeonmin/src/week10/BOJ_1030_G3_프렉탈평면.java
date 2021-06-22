package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1030_G3_프렉탈평면 {
	static int S, N, K, R1, R2, C1, C2;
	//결과를 저장할 배열
	static int[][] result;
	static Queue<Node> q = new LinkedList<Node>();
	
	static class Node{
		int time;
		int y;
		int x;
		int num;
		
		public Node(int time, int y, int x, int num) {
			super();
			this.time = time;
			this.y = y;
			this.x = x;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		S = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R1 = Integer.parseInt(st.nextToken());
		R2 = Integer.parseInt(st.nextToken());
		C1 = Integer.parseInt(st.nextToken());
		C2 = Integer.parseInt(st.nextToken());
		
		//출력할 범위에 해당하는 크기의 배열 생성
		result = new int[R2-R1+1][C2-C1+1];
		
		bfs();
		
		for (int i = 0; i <= R2-R1; i++) {
			for (int j = 0; j <= C2-C1; j++) {
				System.out.print(result[i][j]);
			}
			System.out.println();
		}
	}
	
	static void bfs() {
		//처음 시작하는 사각형을 큐에 저장
		q.offer(new Node(0,0,0,0));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			process(node.time, node.y, node.x, node.num);
		}
	}
	
	//사각형 분열
	static void process(int time, int y, int x, int num) {
		//원하는 시칸만큼 분열이 끝난 경우
		if(time == S) {
			//출력을 원하는 범위에 해당하면
			if(isRange(y, x))
				//배열에 정보를 저장
				result[y-R1][x-C1] = num;
			
			return;
		}
		
		//남은 시간 후에 현재 사각형의 좌표 값을 구하기 위한 변수
		int pow = (int)Math.pow(N, S - time);
		
		//현재 사각형이 남은 시간 후에 분열되어진 영역이 출력을 원하는 영역과 겸치는 부분이 없으면 더이상 분열을 계산하지 않고 종료
		if(y*pow > R2 ||  x*pow > C2 || y*pow + pow -1 < R1 || x*pow + pow -1 < C1) {
			return;
		}
		
		//현재 사각형이 NxN 개의 사각형으로 분열
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int nNum = 1;
				//분열된 사각형의 좌표
				int ny = y*N + i;
				int nx = x*N + j;
				
				//현재 위치의 색이 흰색인 경우
				if(num == 0) {
					//검은색에 해당하는 좌표인 경우
					if( isBlack(i, j) ) {
						nNum = 1;
					}
					else {
						nNum = 0;
					}
				}
				
				if(ny <= R2 && nx <=C2)
					q.offer(new Node(time + 1, ny, nx, nNum));
			}
		}
	}
	
	static boolean isRange(int y, int x) {
		if(y >= R1 && y <= R2 && x >=C1 && x <= C2)
			return true;
		
		return false;
	}
	
	//분열을 할 때 해당하는 좌표가 검은색 영역이면 true를 반환
	static boolean isBlack(int y, int x) {
		if(y < (N - K)/2 || x < (N - K)/2 || y > N - (N - K)/2 -1 || x > N - (N - K)/2 -1)
			return false;
		
		return true;
	}
}

