package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14719_빗물 {
	
	/*
	 * 
	기준점 maxW를 기준으로 해서 좌,우를 나눈다
	좌,우에서 가장 높은 tmpH를 찾고, tmpH와의 차가 빗물이 고이는 양이다.
	이때 h가 0이 넘을때부터 빗물의 양을 구한다
	
	*/
	
	static int H, W, map[], midH, midW, ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new int[W];
		
		st = new StringTokenizer(br.readLine());
		for (int w = 0; w < W; w++) {
			//블록이 쌓인 높이를 의미하는 0이상 H이하의 정수
			map[w] = Integer.parseInt(st.nextToken());
			
			if(map[w]>midH) { //제일 높은 블록의 w,h 저장 (기준점 정하기)
				midH = map[w];
				midW = w;
			}
		}
		
		//2차원 세계에서는 한 칸의 용량은 1이다. 고이는 빗물의 총량을 출력하여라.
		//빗물이 전혀 고이지 않을 경우 0을 출력하여라.
		
		boolean flag = false; //빗물이 고일 수 있는지 체크하는 flag
		int maxH = 0;
		for (int w = 0; w < midW; w++) { //기준점에서 왼쪽 탐색
			if(map[w]>0) flag = true; //빗물 고일 수 있으면 true
			
			maxH = Math.max(maxH, map[w]); //탐색 과정에서 가장 높은 H찾기
			if(flag) ans += maxH-map[w]; //빗물 고이는 양 더하기
			
		}
		
		flag = false;
		maxH = 0;
		for (int w = W-1; w > midW; w--) { //기준점에서 오른쪽 탐색
			if(map[w]>0) flag = true;//빗물 고일 수 있으면 true
			
			maxH = Math.max(maxH, map[w]); //탐색 과정에서 가장 높은 H찾기
			if(flag) ans += maxH-map[w]; //빗물 고이는 양 더하기
			
		}
		
		System.out.println(ans); //빗물의 총량
		
	}
}
