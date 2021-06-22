package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1242_G2_소풍 {
	static int N, K, M;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		//동호의 번호. 학생의 번호를 0부터 시작할 것이라서 -1을 해준다
		M = Integer.parseInt(st.nextToken()) - 1;
		
		//퇴장을 실행한 횟수
		int count = 0;
		//0번이 숫자 1을 부르는 것으로 처리하려고 tgt초기 값을 -1로 설정
		int tgt = -1;
		
		while(true) {
			//횟수 1 증가
			count++;
			
			//남은 학생이 1명이면 종료
			if(N==1)
				break;
			
			//다음에 퇴장할 학생의 번호를 지정
			tgt += K;
			
			//지정한 학생번호가 학생 수보다 크면 조정
			if(tgt >= N) {
				tgt %= N;
			}
			
			//지정된 학생이 동호이면 종료
			if(tgt == M) {
				break;
			}
			//퇴장하는 학생 번호가 동호의 번호보다 작으면 동호의 번호를 앞으로 땡기고 총 인원수 1 감소 
			else if(tgt < M) {
				N--;
				M--;
			}
			//퇴장 학생 번호가 동호보다 크면 총 인원수만 감소
			else {
				N--;
			}
			
			//해당 번호가 삭제된 경우 뒤 학생들의 번호를 1씩 땡기는 것으로 취급하기 위해서 tgt--를 해줌
			tgt--;
		}
		
		System.out.println(count);
	}
}
