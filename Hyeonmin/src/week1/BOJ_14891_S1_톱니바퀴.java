package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14891_S1_톱니바퀴 {
	static int K, result;
	//톱니바퀴 배열
	static Gear[] gears= new Gear[4];
	static boolean[] isVisited = new boolean[4];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//톱니 정보 입력
		for (int i = 0; i < 4; i++) {
			gears[i] =  new Gear(br.readLine());
		}
		
		K = Integer.parseInt(br.readLine());
		
		int dir;
		int idx;
		StringTokenizer st;
		
		//회전 정보 입력
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			
			idx = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			//톱니 회전
			process(dir, idx-1);
		}
		
		//각 톱니의 12시 방향에 따른 값 저장
		for (int i = 0; i < 4; i++) {
			int top = gears[i].top;
			int score = gears[i].pole[top];
			
			result += score*Math.pow(2, i);
		}
		
		//출력
		System.out.println(result);
		
	}
	
	static void process(int dir, int idx) {
		//현재 톱니의 좌 우 위치 인덱스
		int right = gears[idx].right;
		int left = gears[idx].left;
		
		//현재 톱니가 회전했다는 표시
		isVisited[idx] = true;
		
		//우측 톱니가 회전하게 되는 경우
		if(idx+1 < 4 && !isVisited[idx+1] && (gears[idx].pole[right] != gears[idx+1].pole[gears[idx+1].left])) {
			process(dir*(-1), idx+1);
		}
		
		//좌측 톱니가 회전하게 되는 경우
		if(idx-1 >= 0 && !isVisited[idx-1] && (gears[idx].pole[left] != gears[idx-1].pole[gears[idx-1].right])) {
			process(dir*(-1), idx-1);	
		}
		
		//톱니 회전 마침
		isVisited[idx] = false;
		
		//현재 톱니의 회전을 진행
		switch(dir){
		//시계 방향
		case 1:
			gears[idx].rotateRight();
			break;
		//반시계방향
		case -1: 
			gears[idx].rotateLeft();
			break;
		}
		
	}
	
	static class Gear{
		int[] pole = new int[8];	//톱니 N,S 극 정보
		int top;	//12시 방향
		int right;	//오른쪽
		int left;	//왼쪽
		
		Gear(String str) {
			right = 2;
			left = 6;
			top = 0;
			
			for (int i = 0; i < pole.length; i++) {
				pole[i] = Character.getNumericValue(str.charAt(i));
			}
		}
		
		//시계방향 회전
		public void rotateRight() {
			top = top == 0 ? 7 : top -1;
			right = right == 0 ? 7 : right -1;
			left = left == 0 ? 7 : left -1;
		}
		
		//반시계방향 회전
		public void rotateLeft() {
			top = top == 7 ? 0 : top+1;
			right = right == 7 ? 0 : right+1;
			left = left == 7 ? 0: left+1;
		}
	}
}