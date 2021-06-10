package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14888_연산자끼워넣기 {
	//N개의 수로 이루어진 수열
	//N-1개의 연산자
	
	//수의 순서는 바꾸면 안되고, 수와 수 사이에 연산자를 넣어 수식을 만든다
	
	//식의 계산은 연산자 우선순위를 무시하고 앞에서부터 진행
	//나눗셈은 int형으로 몫만 취함
	//만들수 있는 식의 결과가 최대인것과 최소인것 구하기
	
/*	arr배열에 입력받은 숫자를 저장하고, 연산자의 개수는 operator 배열에 저장했습니다.
	연산자 갯수가 총 N-1개 이므로 각각의 갯수를 체크하여 모든 경우의 연산자를 대입했습니다.
	기저조건 idx==N-1이면 (마지막 연산자까지 대입) max,min을 구했습니다.

	메모리 : 12996kb  / 	시간 : 80ms*/

	
	static int N, arr[], operator[], max, min;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
	
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		arr = new int[N]; //수열을 담을 배열
		operator = new int[4]; //연산자의 갯수를 담을 배열
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(st.nextToken()); //수열 저장
		}
		
		//합이 N-1인 4개의 정수.
		//차례대로 +, -, x, /의 갯수
		st = new StringTokenizer(br.readLine(), " ");
		for (int n = 0; n < 4; n++) {
			operator[n] = Integer.parseInt(st.nextToken()); //연산자 수 저장
		}
		
		ans(0, arr[0]); //max와 min을 계산하는 recursive 함수
		
		
		System.out.println(max);
		System.out.println(min);
		
	}
	private static void ans(int idx, int res) {
		//기저조건
		
		if(idx==N-1) {
			max = Math.max(max, res);
			min = Math.min(min, res);
			return;
		}
		
		//+
		if(operator[0]>0) {
			operator[0]--;
			ans(idx+1, res+arr[idx+1]);
			operator[0]++;
		}
		
		//-
		if(operator[1]>0) {
			operator[1]--;
			ans(idx+1, res-arr[idx+1]);
			operator[1]++;
		}
		//*
		if(operator[2]>0) {
			operator[2]--;
			ans(idx+1, res*arr[idx+1]);
			operator[2]++;
		}
		// divide
		if(operator[3]>0) {
			operator[3]--;
			ans(idx+1, res/arr[idx+1]);
			operator[3]++;
		}
		
	}
}
