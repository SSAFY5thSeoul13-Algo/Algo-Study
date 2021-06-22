package week16.BOJ_12018_YonseiToTo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_12018_S3_YonseiToTo {
	
	static int N, M, cnt;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] minArr = new int[N];
		for (int i = 0; i < N; i++) {
			// 각 과목에 신청한 사람 수 P, 과목의 수강인원 L
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			//각 사람이 마일리지를 얼마나 넣었는지 주어진다. 
			int[] tmp = new int[P];
			for (int j = 0; j < P; j++) {
				tmp[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(tmp);

			if(P>=L) minArr[i] = tmp[P-L];
			if(P<L) minArr[i] = 1;
			
		}
		
		Arrays.sort(minArr);
		
		//단 마일리지가 같다면 성준이에게 우선순위가 주어진다고 하자.
		//최소 마일리지를 넣은 사람과 똑같이 넣는다.
		for (int i = 0; i < N; i++) {
			if(M>=minArr[i]) {
				cnt++;
				M -= minArr[i];
			}else break;
		}
		
		System.out.println(cnt);
	}
}
