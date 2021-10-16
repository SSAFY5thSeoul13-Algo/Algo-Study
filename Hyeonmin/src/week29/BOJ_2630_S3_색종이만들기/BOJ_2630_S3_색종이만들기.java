package week29.BOJ_2630_S3_색종이만들기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2630_S3_색종이만들기 {
	static int N, countSkyBlue, countWhite;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		checkMap(0, 0, N);

		System.out.println(countWhite);
		System.out.println(countSkyBlue);

	}

	static void checkMap(int startX, int startY, int length) {
		int startColor = map[startY][startX];
		
		//길이가 1인 경우 바로 체크
		if(length == 1) {
			if(startColor == 1) {
				countSkyBlue++;
			}
			else {
				countWhite++;
			}
			
			return;
		}
		
		boolean isDifferent = false;
		int endY = startY + length -1;
		int endX = startX + length -1;
		
		//해당 영역의 색종이 체크
		Loop:for (int i = startY; i <= endY; i++) {
			for (int j = startX; j <= endX; j++) {
				//다른 색의 종이가 있는 경우
				if(map[i][j] != startColor) {
					isDifferent = true;
					break Loop;
				}
			}
		}
		
		//다른 색의 종이가 있는 경우 자름
		if(isDifferent) {
			checkMap(startX, startY, length/2);
			checkMap(startX+length/2, startY, length/2);
			checkMap(startX, startY+length/2, length/2);
			checkMap(startX+length/2, startY+length/2, length/2);
		}
		else {
			if(startColor == 1) {
				countSkyBlue++;
			}
			else {
				countWhite++;
			}
		}
	}

}
