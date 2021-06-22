package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_2116_주사위쌓기2 {
	//주사위쌓기
	//아래있는 주사위의 윗면은 위에 있는 주사위의 아랫면에 적힌 숫자와 같아야함
	//이렇게 주사위를 쌓으면
	//사각기둥 4개의 옆면 중 어느 한면의 숫자의 합이 최대가 되도록
	//각 주사위를 위아래를 고정하고 옆으로 90, 180, 270도 돌릴수있따.
	//한옆면의 최댓값구하기
	
	static int N, ans;
	static int[][] arr;
	static int[] select = {5,3,4,1,2,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][6];
		
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int m = 0; m < 6; m++) {
				arr[n][m] = Integer.parseInt(st.nextToken());
			}
			//A F / B D / C E
			//0 5   1 3    2 4
			//top, bottom 빼고 옆면은 돌릴수있으니 제일 큰값 다더하기
		}
		
		for (int i = 0; i < 6; i++) {

			int sum = 0;
			int bottom = arr[0][i];
			int top = arr[0][select[i]];

			int max = 0;
			for (int t = 1; t <= 6; t++) {
				if(t!=bottom&&t!=top) { //bottom과 대칭되는 top빼고 temp에 담는다
					max = t;
				}
			}
			sum += max;
			
			
			for (int j = 1; j < N; j++) {
				
				bottom = top;
				int bottomIdx = 0;
				for (int idx = 0; idx < 6; idx++) {
					if(top==arr[j][idx]) {
						bottomIdx = idx;
						break;
					}
				}
				
				top = arr[j][select[bottomIdx]];

				max = 0;
				for (int t = 1; t <= 6; t++) {
					if(t!=bottom&&t!=top) { //bottom과 대칭되는 top빼고 temp에 담는다

						max = t;
					}
				}
				sum += max;

			}

			ans = Math.max(sum, ans);
			
		}
		System.out.println(ans);
		
	}
}
