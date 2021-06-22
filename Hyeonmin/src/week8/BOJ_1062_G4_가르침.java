package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1062_G4_가르침 {
	static int N, K;
	static int max = 0;
	//단어를 비트마스크로 저장할 배열
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		//단어를 읽어서 저장
		for (int i = 0; i < N; i++) {
			//단어 저장
			String str = br.readLine();
			//단어를 비트마스크로 저장할 배열
			int temp = 0;
			
			//단어의 글자 하나하나를 살펴봄
			for (int j = 0; j < str.length(); j++) {
				//j번째 단어
				char tgt = str.charAt(j);
				//아직 포함이 안된 알파벳이면 비트마스크에 저장
				if( (temp & (1 << tgt - 'a')) == 0 ) {
					temp += (1<< (tgt - 'a'));
				}
			}
			//완성된 비트마스크를 배열에 저장
			arr[i] = temp;
		}
		
		//초기 시작 비스마스크 : a, c, i, n, t는 항상 포함해야 함
		int start = 1  + (1 << 'c'-'a')  + (1 << 'i'-'a')  + (1 << 'n'-'a')  + (1 << 't'-'a');
		
		//최소 5개의 알파벳(a, c, i, n, t)을 배워야 단어를 읽을 수 있음
		if( K < 5 ) {
			System.out.println(0);
			return;
		}
		
		//비트마스킹
		process(0, start, 5);
		
		System.out.println(max);
	}
	
	static void process(int idx, int mask, int cnt) {
		//K 만큼 알파벳를 가르친 경우 읽을 수 있는 단어 수를 확인
		if(cnt == K) {
			check(mask);
		}
		
		//마지막까지 간 경우
		if(idx == 26) {
			return;
		}
		
		
		for (int i = idx; i < 26; i++) {
			//아직 배우지 않은 알파벳인 경우
			if( (mask & (1 << i)) == 0 ) {
				//비스마스킹을 해서 다음으로 넘어감
				process(i+1, mask + (1 << i), cnt +1 );
			}
			
		}
	}
	
	//읽을 수 있는 문장 수 확인하는 메소드
	static void check(int mask) {
		//문장 수
		int cnt = 0;
		
		//모든 단어들에 대해서
		for (int i : arr) {
			//비트마스크로 저장했기 때문에 and 연산을 했을 때 해당 단어와 같은 결과가 나오면 읽을 수 있는 것
			if((mask & i) == i)
				//읽은 문장 수 증가
				cnt++;
		}
		
		//더 큰 값을 저장
		if( max < cnt )
			max = cnt;
	}
}
