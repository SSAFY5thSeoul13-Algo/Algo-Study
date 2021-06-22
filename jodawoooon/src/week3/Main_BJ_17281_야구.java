package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_17281_야구 {
	/*
	 * 야구
	 * N이닝
	 * 1이닝 3아웃 종료 => 공수교대
	 * 9번까지 치고 3아웃아니면 다시 1번ㄴ부터
	 * 타순은 이닝변경되도 순서 유지
	 * 
	 *	안타: 타자와 모든 주자가 한 루씩 진루한다.    1
		2루타: 타자와 모든 주자가 두 루씩 진루한다.   2
		3루타: 타자와 모든 주자가 세 루씩 진루한다.   3
		홈런: 타자와 모든 주자가 홈까지 진루한다.     4
		아웃: 모든 주자는 진루하지 못하고, 공격 팀에 아웃이 하나 증가한다.     0
		
		1번은 4번타자
		가장 많은 득점을 하는 타순 찾고, 그 때의 득점 구하기
		각 이닝에는 아웃을 기록하는 타자가 적어도 한 명 존재한다.
		
		ex)
		2
		4 0 0 0 1 1 1 0 0
		0 0 0 0 0 0 0 0 0
		
		5,6,7이 1,2,3번 타자 나가고 1번이 4번타자 하면 4점
		
		1) 모든 경우의 수 타순 정하기 : 순열
			=> 경기진행
		2) 점수 구하기
		
	 */
	
/*	순열을 이용해서 타자들의 순서 경우의 수를 구했고, 이 때 1번 선수는 4번타자로 결정되었으므로
	처음에 먼저 고정을 시켰습니다.
	이 부분을 놓쳐서 답이 이상하게 나왔었네요..ㅠ

	그리고 그 다음에는 baseball()함수를 통해 점수를 계산했습니다.
	while문 안에서 3아웃되기 전까지 안타, 2루타, 3루타, 홈런 순서대로
	조건에 따라 선수들을 이동시키며 score에 점수를 계산하고
	3진이 되면 ans에 max score을 저장했습니다.
	처음에는 배열의 0인덱스부터 사용을했는데 헷갈려서 0인덱스를 버리고 1인덱스부터 사용했습니다.


	메모리 : 60584kb	시간 : 540ms*/
	
	static int N, arr[][], tgt[], ans, score;
	static boolean isSelected[], base[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N= Integer.parseInt(br.readLine());
		
		arr = new int[N+1][10]; //1~N이닝, 1~9
		isSelected = new boolean[10]; //1~9
		tgt = new int[10]; //타순
		for (int n = 1; n <= N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int m = 1; m <= 9; m++) {
				arr[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		 //1번은 고정
		tgt[4]=1;
		isSelected[1] = true;
		perm(1); //1~9번
		
		System.out.println(ans);
				
	}
	private static void perm(int idx) {
		if(idx>9) {
			//타순 다 정해지면
			baseball();
			//System.out.println(Arrays.toString(tgt));
			return;
		}
		
		
		if(idx==4) idx++;
		
		for (int i = 2; i <=9 ; i++) { //1번빼고

			if(!isSelected[i]) {
				isSelected[i]=true;
				tgt[idx] = i;
				perm(idx+1);
				isSelected[i]=false;
			}
		}
	}
	private static void baseball() {
		score = 0;
		int num = 1;
		int out = 0;
		
		for (int n = 1; n <= N; n++) {
			out = 0;
			base = new boolean[4];
			
			while(true) {
				if(out==3) break;
				//3아웃전까지 이닝은 끝나지 않음
				
				int playerNum = tgt[num];
				if(arr[n][playerNum]==1) {//안타
					//안타 -> 한루씩 

					//3루부터 처리해야함 
					if(base[3]) { //3루 true면 score++
						score++;
						base[3]=false;
					}
					
					for (int i = 2; i >=1 ; i--) {
						if(base[i]) { //나머지 1,2루는 한칸씩 당기고 1루 true
							base[i+1]=true;
							base[i]=false;
						}
					}
					base[1] = true;
					
				}else if(arr[n][playerNum]==2) {//2루타

					for (int i = 3; i >= 2; i--) {
						if(base[i]) {
							score++;
							base[i]=false;
						}
					}
					
					if(base[1]) {
						base[3]=true;
						base[1]=false;
					}
					base[2] = true;
				
				}else if(arr[n][playerNum]==3) {//3루타
					
					for (int i = 3; i >= 1; i--) {
						if(base[i]) {
							score++;
							base[i]=false;
						}
					}
					base[3]=true;
					
				} else if(arr[n][playerNum]==4) {//3루타
					score++;
					for (int i = 3; i >= 1; i--) {
						if(base[i]) {
							score++;
							base[i]=false;
						}
					}
					
				} else if(arr[n][playerNum]==0) {//아웃
					out++;
				}
				
				num = (num+1)%10; 
				if(num==0) num++; // num은 1~9
			
				
			}
		}
		
		ans = Math.max(ans,score);
	}
}
