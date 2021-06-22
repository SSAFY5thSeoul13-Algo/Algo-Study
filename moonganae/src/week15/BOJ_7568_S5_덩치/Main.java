package week15.BOJ_7568_S5_덩치;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/* 
 * 풀이시간 : 26분
 * */
public class Main {

	public static void main(String[] args) throws Exception{
		
		/*
		 	몸무게 :x, 키 :y -> (x,y)
		 	
		 	덩치가크다 (x,y) vs (p,q) == x>p && y > q
		 */
		
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 사람수 N 입력
		int N = Integer.parseInt(br.readLine());
		
		int[] weight = new int[N];
		int[] tall = new int[N];
		// N명의 몸무게 키 입력
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			weight[i] = Integer.parseInt(st.nextToken());
			tall[i] = Integer.parseInt(st.nextToken());
		}
		// 순서를 저장할 grade 배열 설정
		int[] grade = new int[N];
		// 한사람씩 총 N명 순회 현재 i
		for(int i=0; i<N-1; i++) {
			// i보다 뒤에 있는 사람 순회 j
			for(int j=i+1; j<N; j++) {
				
				int result = isBig(weight[j], tall[j], weight[i], tall[i]);
				
				// 둘중 작은사람 grade[min(i,j)]++;
				
				// j가 i보다 덩치가 크다.
				if(result == 2) {
					grade[i]++;
				}
				// i가 j보다 덩치가 크다.
				else if(result == -2) {
					grade[j]++;
				}
			}
		}
			
		//결과출력
		for(int i=0; i<N; i++) {
			System.out.print(grade[i]+1 + " ");
		}
		
	}
	/*
	 * @Return
	 * 		2 : you가 덩치가 큼
	 * 		0 : 덩치가 같거나 알수없음
	 * 		-2 : you가 덩치가 작음 
	 */
	static int isBig(int youX,int youY,int meX,int meY) {
		
		int bigger = youX - meX;
		int taller = youY - meY;
		
		if(bigger != 0) {
			if(bigger > 1) bigger = 1;
			else if(bigger < -1) bigger = -1;
		}
		if(taller != 0) {
			if(taller> 1) taller = 1;
			else if(taller < -1) taller = -1;
		}
		
		return bigger + taller;
	}

}
