package week25.BOJ_1647_G4_도시분할계획;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 1647 G4 도시분할계획
 * @Author : Daun JO
 * @Date : 2021. 9. 3. 
 *
 */
public class Main_BOJ_1647_G4_도시분할계획 {
	
	static int N, M;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			//A번 집과 B번 집을 연결하는 길의 유지비가 C
		}
	}
}
