package week27.BOJ_17779_G4_개리맨더링2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17779_G4_개리맨더링2 {
	static int N, result = Integer.MAX_VALUE;
	static int[][] map;
	static int[] population;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//시작 좌표와 길이에 따른 반복문 실행
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int d1 = 1; d1 <= (N-1)/2; d1++) {
					for (int d2 = 1; d2 <= (N-1)/2; d2++) {
						//범위를 벗어나는 경우들
						if(i+d2 >= N || i-d1 < 0)	continue;
						if(j+d1+d2 >= N)	continue;
						
						countPopulation(i,j,d1,d2);
					}
				}
				
			}
		}
		
		System.out.println(result);
	}
	
	static void countPopulation(int y, int x, int d1, int d2) {
		population = new int[5];
		//각 위치의 구역
		int[][] area = new int[N][N];
		
		//5번 구역 경계선
		for (int i = 0; i <= d1; i++) {
			area[y-i][x+i] = 5;
		}
		
		for (int i = 0; i <= d2; i++) {
			area[y+i][x+i] = 5;
		}
		
		for (int i = 0; i <= d2; i++) {
			area[y-d1+i][x+d1+i] = 5;
		}
		
		for (int i = 0; i <= d1; i++) {
			area[y+d2-i][x+d2+i] = 5;
		}
		
		boolean check = false;
		//5번구역 내부
		for (int i =y-d1+1 ; i <= y+d2-1; i++) {
			for (int j = x; j <= x+d1+d2; j++) {
				if(check) {
					if(area[i][j] == 5) {
						check = false;
						break;
					}
					
					area[i][j] = 5;
				}
				
				if(area[i][j] == 5)
					check = true;
			}
		}
		
		//1번 구역
		for (int i = 0; i < y; i++) {
			for (int j = 0; j <= x+d1; j++) {
				if(area[i][j] == 5)	break;
				
				area[i][j] = 1;
			}
		}

		//2번 구역
		for (int i = 0; i <= y-d1+d2; i++) {
			for (int j = N-1; j > x+d1; j--) {
				if(area[i][j] == 5)	break;
				
				area[i][j] = 2;
			}
		}
		
		//3번 구역
		for (int i = y; i < N; i++) {
			for (int j = 0; j < x+d2; j++) {
				if(area[i][j] == 5)	break;
				
				area[i][j] = 3;
			}
		}
		
		//4번 구역
		for (int i = y-d1+d2+1; i < N; i++) {
			for (int j = N-1; j >= x+d2; j--) {
				if(area[i][j] == 5)	break;
				
				area[i][j] = 4;
			}
		}
		
		int min = Integer.MAX_VALUE;
		int max = 0;
		
		population = new int[5];
		
		//각 구역별 인구 더하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				population[area[i][j]-1]+=map[i][j];
			}
		}
		
		//최소, 최대 인구
		for (int i = 0; i < 5; i++) {
			min = Math.min(population[i], min);
			max = Math.max(population[i], max);
		}
		
		result = Math.min(result, max-min);
	}

}
