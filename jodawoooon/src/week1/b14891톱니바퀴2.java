package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class b14891톱니바퀴2 {
/*	8개의 톱니를 가진 톱니바퀴 4개
	톱니는 N극 또는 S극 중 하나를 가짐
	톱니바퀴는 가장 왼쪽부터 1,2,3,4번
	
	이 때 톱니바퀴를 총 K번 회전(한칸) 시킨다
	회전은 시계방향 or 반시계방향.
	
	톱니바퀴를 회전시키려면 방향을 결정해야한다
	서로 맞닿은 극에 따라 결정
	
	A를 회전할 때, B와 서로 맞닿은 톱니의 극이 다르면
	B는 A가 회전한 방향과 반대방향으로 회전하게 된다
	
	*최종 톱니바퀴 상태를 구하자
	*
	*/
	static int K, ans;
	//static boolean flagL, flagR;
	static char[][] arr = new char[5][8];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 1; i <= 4; i++) {
			arr[i] = br.readLine().toCharArray(); //12시방향부터 시계방향순서
		}
		
		K= Integer.parseInt(br.readLine()); //회전 횟수
		
		for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int num = Integer.parseInt(st.nextToken());//a = 회전시킨 톱니바퀴 번호,
			int d = Integer.parseInt(st.nextToken());// b=방향 : 1이면 시계, -1이면 반대
			
			
			solve(true, true, num, d); //첫 톱니바퀴는 오른쪽 왼쪽 다 회전한다. 

		}

		//점수 계산
		for (int i = 1; i <= 4; i++) {
			//각 톱니바퀴의 12시 방향이 S극이면 순서대로 1,2,4,8점
			//System.out.println(Arrays.toString(arr[i]));
            if (arr[i][0]-'0'==1) {
            	
                ans += Math.pow(2, i-1); //2의 i-1제곱수들을 더함
            }
        }
		
		System.out.println(ans);
		
	}
	
	private static void solve(boolean rotateLeft,boolean rotateRight, int num, int d ) {
		if(num<1||num>4) return;
		//a-1번 톱니바퀴 회전 (왼쪽)
		if(num!=1&&arr[num][6]!=arr[num-1][2]) {
			//a가 1번이 아니고
			//왼쪽 톱니바퀴와 맞닿은 부분이 서로 다른 극이라면
			//왼쪽 톱니바퀴는  반대방향(-b) 방향으로 회전
			
			//회전안했으면 옆에 톱니바퀴도 회전하면 안된다.
			//flag를 넘겨줘서 flag가 true여야 회전한다.
			
			if(rotateLeft) { //왼쪽으로 회전했으므로
				solve(true, false, num-1, -d); //왼쪽만 true
			}
			
		}
		
		//a+1번 톱니바퀴 회전 (오른쪽)
		if(num!=4&&arr[num][2]!=arr[num+1][6]) {

			//a가 4번이 아니고, 오른쪽 톱니바퀴와 맞닿은 부분이 서로 다른 극이라면
			//오른쪽 톱니바퀴는 -b 방향으로 회전
			
			if(rotateRight) {
				solve(false, true, num+1, -d); //오른쪽만 true
			}

		}
		//System.out.println(Arrays.toString(arr[num]));
		
		
		//a번 톱니바퀴 회전
		if(d==1) { //시계방향이면
			char tmp = arr[num][7]; //마지막 값 기억하기
			for (int i = 7 ; i > 0; i--) {
				arr[num][i] = arr[num][i-1];
			}
			arr[num][0] = tmp;
			
			
		}else if(d==-1) { //반시계방향
			char tmp = arr[num][0];
			for (int i = 0; i < 7; i++) {
				arr[num][i] = arr[num][i+1];
			}
			arr[num][7] = tmp;
			
		}
		//System.out.println(Arrays.toString(arr[num]));
	}
}
	

