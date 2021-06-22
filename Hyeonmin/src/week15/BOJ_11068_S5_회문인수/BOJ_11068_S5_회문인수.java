package week15.BOJ_11068_S5_회문인수;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_11068_S5_회문인수 {
	
	static int T, tgt;
	static boolean correct;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//테스트 케이스
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			//이번에 확인할 수
			tgt = Integer.parseInt(br.readLine());
			//회문 여부
			correct = false;
			
			//2진법 부터 64진법
			for (int b = 2; b <= 64; b++) {
				//b진법으로 변환
				change(b);
				
				//회문인경우 더이상 확인 불필요
				if(correct) {
					break;
				}
			}
			
			//회문이면 1 아니면 0 출력
			System.out.println(correct ? 1 : 0);
		}
	}
	
	static void change(int b) {
		//해당 진법의 자릿수
		int len = 1;
		//이번에 확인할 10진법 수
		int num = tgt;
		
		//이번 진법에 해당하는 수로 더  나눌 수 없을 때 까지 반복
		while(true) {
			if(num/b < 1)
				break;
			
			//나누어지면 자릿수가 1 증가
			len++;
			
			num = num / b;
		}
		
		//이번 진법의 각 자리의 숫자를 하나의 10진법 수로 저장하기 위한 배열
		int[] arr = new int[len];
		
		num = tgt;
		
		//마지막 자릿수는 제외
		for (int i = 0; i < len-1; i++) {
			//각 배열에 b로 나눈 나머지를 저장
			arr[i] = num % b;
			
			//b로 나누었을 때의 몫을 num에 저장하고 반복
			num = num / b;
		}
		
		//마지막 숫자는 더이상 나누어 떨어지지 않는 경우이므로 그 숫자를 저장
		arr[len-1] = num;
		
		//맨 뒤와 맨 앞부터 시작해서 같은지 비교
		for (int i = 0; i < len/2; i++) {
			//숫자가 다르면 회문이 아니니 체크하고 메소드 종료
			if(arr[i] != arr[len-1-i]) {
				correct = false;
				return;
			}
		}
		
		//회문인 경우 체크
		correct = true;
	}
}
