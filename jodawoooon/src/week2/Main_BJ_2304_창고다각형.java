package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_2304_창고다각형 {
	//창고 다각형
	//
	static int N,L,H, cnt;
	static int maxX, maxY, tmpY;
	static int[][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];

		
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			L = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			arr[n][0] = L; //입력받은 두 좌표 값을 배열에 넣어둠
			arr[n][1] = H;
			
			if(H>maxY) {//가장 Y값이 긴 좌표값을 maxX, maxY에 저장해서 기억한다.
				maxX = L;
				maxY = H;
			}
			
		}
		
		//x좌표 순서대로 정렬한다
		Arrays.sort(arr, (o1,o2) -> Integer.compare(o1[0], o2[0]));

		int endX = arr[N-1][0];
		int idx = 0;
		
		//maxY를 기준으로 전, 후를 구분했다.
		
		
		//maxY 전까지
		for (int x = arr[0][0]; x < maxX; x++) { //x=0부터 maxX-1까지
			if(x==arr[idx][0]) {
				if(arr[idx][1]>tmpY) { 
					tmpY = arr[idx][1];
				}
				idx++;
			}
			cnt += tmpY;
		}

		//maxY
		cnt += maxY;
		tmpY = 0;
		
		//maxY 이후
		idx = N-1;
		for (int x = endX ; x>maxX ; x--) { //x=endX부터 maxX+1까지
			if(x==arr[idx][0]) { //반대로 뒤에서부터 확인
				if(arr[idx][1]>tmpY) {
					tmpY = arr[idx][1];
				}
				idx--;
			}
			cnt += tmpY;
			
		}	


		System.out.println(cnt);
	
	}
}
