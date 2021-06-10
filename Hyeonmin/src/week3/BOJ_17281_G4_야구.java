package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17281_G4_야구 {
	//이닝 수
	static int N;
	//각 타자의 결과를 저장할 배열
	static int[][] arr;
	//타자의 순서를 저장할 배열
	static int[] order = new int[10];
	//수열을 만들기 위한 배열
	static boolean[] isSelected = new boolean[10];
	//현재 타자
	static int now;
	//최대 스코어
	static int max = 0;
	//현재 스코어
	static int score;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[10][N+1];
		
		//각 타자의 이닝별 결과를 저장. 첫 인덱스로 타자를 지정하고 다음 인덱스로 이닝별 결과를 지정
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 10; j++) {
				arr[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		//4번째 타자는 1번 타자로 고정
		order[4]=1;
		isSelected[1] = true;
		
		//순열
		perm(1);
		
		//결과 출력
		System.out.println(max);
	}
	
	static void perm(int idx) {
		//타자 순서를 모두 고른 경우
		if(idx == 10) {
			//시작 스코어와 타석 순서를 초기화
			score = 0;
			now =1;
			
			//경기 시작
			for (int i = 1; i <= N; i++) {
				play(i);
			}
			
			//결과를 비교
			max = Math.max(max, score);
			
			return;
		}
		
		//4번째 타자는 고정이므로 다음타자 지정으로 이동
		if(idx == 4) {
			perm(idx+1);
			return;
		}
		
		//순열로 타자의 순서를 저장
		for (int i = 1; i < 10; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				order[idx]=i;
				perm(idx+1);
				isSelected[i] = false;
			}
		}
	}
	
	//경기 내용을 구현한 메소드
	static void play(int inning) {
		//현재 이닝 아웃 카운트
		int out =0;
		//각 루의 주자 상황을 저장할 배열
		boolean[] bases = new boolean[4];
		
		//아웃 카운트가 3개개 될 때까지 반복
		while(out <3) {
			//현재 타자의 이번 이닝 결과
			int num = arr[order[now]][inning];
			
			//다음 타석 지정. 9번쨰 타자 다음은 1번째 타자로
			now = now == 9 ? 1 : now+1;
			
			//타자 결과에 따른 처리
			switch(num) {
				//아웃인경우 아웃 카운트 증가
			case 0:
				out++;
				break;
			case 1:
				//1루타인 경우
				move(1, bases);
				break;
			case 2:
				//2루타인 경우
				move(2, bases);
				break;
			case 3:
				//3루타인 경우
				move(3, bases);
				break;
			case 4:
				//홈런인 경우
				move(4, bases);
				break;
			}
		}
	}
	
	//아웃이 아닌 상황
	static void move(int step, boolean[] bases) {
		//타자가 진루하는 걸로 취급하기 위해서 true로 해놓음
		bases[0] = true;
		//홈으로 들어오는 상황들을 처리
		//n루타인 경우 마지막 n개의 루에 있는 주자들이 모두 홈으로 들어옴
		for (int i = 4-step; i < 4 ; i++) {
			//해당 베이스에 주자가 있을 경우 홈으로 들어오고 점수가 1점 오름
			if(bases[i]) {
				bases[i] = false;
				score++;
			}
		}
		//홈으로 들어오지 않은 진루상황을 처리
		//n루타에서 마지막 n개의 루 이후에 있는 주자들의 진루 상황을 반영. 여기서 타자의 진루도 반영 됨
		for (int i = 3-step; i >= 0; i--) {
			//해당 베이스에 주자가 있을 경우
			//n 만큼 진루를 한다
			if(bases[i]) {
				bases[i] = false;
				bases[i+step] = true;
			}
		}
	}
}