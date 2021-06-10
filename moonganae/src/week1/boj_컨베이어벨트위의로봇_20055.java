package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_컨베이어벨트위의로봇_20055 {

	static int N, K;			// 벨트의 한쪽면길이, 내구도가 0인 칸의 제한
	static int[] belt;			// 컨베이어벨트의 내구도 저장배열
	static boolean[] robot;		// 컨베이어벨트 위에 로봇의 존재유무
	
	static int zeroCnt=0;		// 현재 내구도가 0인 칸의 수
	static int stage=0;			// 현재 진행 스테이지 번호
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		belt = new int[2*N];		// 벨트의 윗면과 아랫면을 편하게 이동시키기 위해 1차원 2N의 크기로 설정
		robot = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<2*N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		while(true) {
			stage++;					// 스테이지번호 증가
			rotate();					// 벨트회전
			robotMove();				// 로봇이동
			robotUp();					// 로봇올리기
			if(zeroCnt >= K) break;		// 내구도체크
		}
		
		System.out.println(stage);
	}
	/* 컨베이어 벨트회전 */
	static void rotate() {
		
		// 벨트회전
		int tmp = belt[2*N-1];
		for(int i=2*N-1; i>0; i--) {
			belt[i] = belt[i-1];
		}
		belt[0] = tmp;
		
		/*
		 * 막힌부분1 : 벨트가 돌때 로봇도 같이 돈다는것을 인지하지 못했습니다.
		 * 막힌부분2 : 내려가는 위치에 도달하면 즉시 로봇이 사라진다는 것을 인지못했습니다
		 */
		
		// 벨트위 로봇 회전
		for(int i=N-1; i>0; i--) {
			robot[i] = robot[i-1];
		}
		robot[0] = false;		// 벨트시작부분은 무조건 로봇이 없음
		robot[N-1] = false;		// 만약 내려가는 부분에 도달했다면 로봇떨어짐
	}
	/* 로봇움직이기 */
	static void robotMove() {
		for(int i=N-1; i>0; i--) {
			// 움직일 위치에 로봇이 없고, 벨트수명이 1이상이라면
			if(robot[i]==false && robot[i-1]==true && belt[i]>=1) {
				// 로봇 움직이기
				robot[i] = true;
				robot[i-1] = false;
				// 벨트수명저하
				belt[i]--;
				if(belt[i] == 0) zeroCnt++;
			}
		}
		robot[N-1] = false;
	}
	
	/* 땅에서 로봇 올리기 */
	static void robotUp() {
		// 올라가는 위치에 로봇이 존재하지않고 벨트수명이 1이상이라면
		if(!robot[0] && belt[0]>=1) {
			// 로봇올리기
			robot[0] = true;
			belt[0]--;
			if(belt[0] == 0) zeroCnt++;
		}
	}

}
