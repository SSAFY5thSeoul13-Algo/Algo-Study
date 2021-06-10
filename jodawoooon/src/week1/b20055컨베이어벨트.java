package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b20055컨베이어벨트 {
/*	
 * 길이가 N인 컨베이어 벨트를 길이가 2N인 벨트가 감싸고 돌고있다.  1부터 2N번의 칸
 * 한 칸씩 이동. 1번을 올라가는 위치, N번을  내려가는 위치
 * 로봇을하나씩 올리자
 * 올라가는 위치에서만 올라가고, 내려가는 위치에서만 내려간다
 * 로봇이 어떤 칸에 올라가거나 이동하면 그 칸의 내구도가 감소. 내구도가 0이면 올라갈 수 없다
 * 로봇은 스스로 이동할 수 있다.
 * 
 * 로봇을 옮기는 과정
 * 1. 벨트가 한칸 회전한다
 * 2. 가장 먼저 벨트에 올라간 로봇부터 벨트가 회전하는 방향으로 한칸 이동할 수 있다면 이동. 
 * 이동할 수 없다면 가만히 있는다. 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며 칸의 내구도가 1이상 남아있어야한다
 * 3. 올라가는 위치(1번)에 로봇이 없다면 로봇을 하나 올린다
 * 4. 내구도가 0인 칸의 개수가 k개 이상이면 종료. 아니면 다시 1번으로 돌아감
 * 
 * 종료되었을때 몇번째 단계였는지 구하자.
*/
	static int N, K, ans, zeroCnt;
	static int[] arr;
	static boolean[] robot;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[2*N+1];
		robot = new boolean[N+1];
		StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");

		for (int i = 1; i <= 2*N; i++) {
			arr[i] = Integer.parseInt(st2.nextToken());
		}


		while(true) {
	
			//1. 벨트가 한 칸 회전한다.
			int tmp = arr[2*N]; //arr[1]을 처리하기 위한 tmp값. 
			for (int i = 2*N; i > 1; i--) { //뒤에서부터 처리. i=1부터 시작하면 값이 덮어써짐
				arr[i] = arr[i-1]; //한칸씩 옮긴다.
				if(i<=N) {
					robot[i] = robot[i-1]; //로봇도 한칸 옮긴다.
				}
			}		
			//한칸씩 옮긴 후 1번 자리의 내구도와 로봇 유무를 처리한다.
			arr[1] = tmp; //2N번째 칸을 1번으로 넣는다.
			robot[1] = false; //옮겨졌으므로 false
			
			if(robot[N]) robot[N] = false; //N번 위치에 로봇이 있다면 내려와야 한다

				
			
			//2. 가장 먼저 올라간 로봇부터 이동
			for (int i = N; i > 1; i--) {
				if(!robot[i] && arr[i]>=1 && robot[i-1]) { //이동하려는 칸에 로봇이 없으며 칸의 내구도가 1이상 남아있으면
					robot[i] = true;
					robot[i-1] = false; //로봇 한칸 이동
					arr[i]--; //내구도 감소
					if(arr[i]==0) zeroCnt++; //내구도가 0인지 체크
				}
			}
			
			if(robot[N]) robot[N] = false; //N번 위치에 로봇이 있다면 내려와야 한다
			
			
			
			
			//3.올라가는 위치에 로봇이 없다면 올린다
			if(!robot[1] && arr[1]>=1) {
				robot[1] = true; //로봇 올리기
				arr[1] --; //내구도 감소
				if(arr[1]==0) zeroCnt++;
			}
			
			ans++;

			
//			for (int i = 1; i < 2*N; i++) {
//				System.out.print(arr[i]+" ");
//			}System.out.println();
//			
			//4. 내구도가 0인칸의 개수가 K개이상이라면 종료
			if(zeroCnt>=K) break; 

		}
		System.out.println(ans);
		
		
	}
}
