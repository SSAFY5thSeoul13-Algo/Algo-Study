package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2116_G5_주사위쌓기 {
	//주사위 갯수
	static int N;
	//출력할 최대 값
	static int max = 0;
	//주사위 정보 저장할 리스트
	static List<Dice> dice = new ArrayList<Dice>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			//주사위 정보 입력
			dice.add(new Dice(Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken())
							));
		}
		
		//주사위 탑 쌓기
		process(0, 0);
		
		System.out.println(max);
	}
	
	//idx : 현재 쌓아진 주사위, prevTop : 이전 쌓은 주사위의 윗부분에 적힌 숫자
	static void process(int idx, int prevTop) {
		//모든 주사위로 탑을 쌓은 경우
		if(idx == N) {
			calcMax();
			return;
		}
		
		//다음 주사위 정보 얻기
		Dice d = dice.get(idx);
		
		//주사위 쌓기
		for (int i = 0; i < 6; i++) {
			//첫 주사위이면 바닥에 오는 숫자를 모든 경우 다 실행
			if(idx == 0) {
				d.setBottom(i);
				process(idx+1, d.num[d.top]);
			}
			//두 번째 주사위 부터는 이전 주사위의 윗부분이 현재의 아래부분 숫자와 같게 쌓음
			else if(prevTop == d.num[i]) {
				d.setBottom(i);
				process(idx+1, d.num[d.top]);
			}
		}

		
	}
	
	//쌓여진 탑의 옆면 합중 최대 계산
	static void calcMax() {
		int num;
		int sum =0;
		
		//각각의 주사위에서
		for (Dice d : dice) {
			
			num = 0;

			//각 주사위의 옆면중 최대 숫자를 저장
			for (int i = 0; i < 6; i++) {
				if(i != d.top && i != d.bottom) {
					num = Math.max(num, d.num[i]);
				}
				
			}

			//지금까지 나온 최대 숫자를 합침 
			sum += num;
		}

		//다른 탑쌓기 경우와 최대를 비교해서 저장
		max = Math.max(max, sum);
	}
	
	//주사위 클래스
	static class Dice{
		//0-5 , 1-3, 2-4
		int[] num = new int[6];	//각 주사위 면에 쓰여진 숫자
		int bottom;	//주사위 윗부분 인덱스
		int top;	//주사위 아랫부분 인덱스
		
		//생성자
		Dice(int num0, int num1, int num2, int num3, int num4, int num5) {
			num[0] = num0;
			num[1] = num1;
			num[2] = num2;
			num[3] = num3;
			num[4] = num4;
			num[5] = num5;
		}
		
		//주사위의 아랫부분 설정하는 메소드
		void setBottom(int num){
			bottom = num;
			
			//아랫부분 설정에 따른 윗부분 설정
			if(num==0) {
				top=5;
			}
			else if(num==1) {
				top=3;
			}
			else if(num==2) {
				top=4;
			}
			else if(num==3) {
				top=1;
			}
			else if(num==4) {
				top=2;
			}
			else if(num==5) {
				top=0;
			}
		}
	}
}