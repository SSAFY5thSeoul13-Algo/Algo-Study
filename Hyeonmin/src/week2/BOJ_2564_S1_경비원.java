package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2564_S1_경비원 {
	//X : 가로, Y : 세로, N : 상점 수
	static int X, Y, N;
	//상점의 위치와 거리. 0: 위치, 1: 거리
	static int[][] shop;
	//동근이의 위치과 거리
	static int[] start = new int[2];
	//최소 거리의 합
	static int result = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//가로, 세로 입력
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		//상점 개수 입력
		N = Integer.parseInt(br.readLine());
		
		//상점 개수만큼 생성
		shop = new int[N][2];
		
		//상점 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			shop[i][0] = Integer.parseInt(st.nextToken());
			shop[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//동근이 정보 입력
		st = new StringTokenizer(br.readLine());
		start[0] = Integer.parseInt(st.nextToken());
		start[1] = Integer.parseInt(st.nextToken());
		
		//각 상점까지의 최소거리 계산
		for (int i = 0; i < N; i++) {
			calcDistance(i);
		}
		
		//출력
		System.out.println(result);
		
	}
	
	//shopIdx : 거리를 계산하려는 상점의 인덱스
	static void calcDistance(int shopIdx) {
		//상점의 위치
		int shopDir = shop[shopIdx][0];
		//상점의 기준전에서의 거리
		int shopLocation = shop[shopIdx][1];
		
		//동근이의 위치
		int startDir = start[0];
		//동근이의의 기준전에서의 거리
		int startLocation = start[1];
		
		//같은 방향에 위치한 경우
		if(startDir == shopDir) {
			result += Math.abs(shopLocation-startLocation);
		}
		//동근이가 북쪽에 위치한 경우
		else if(startDir == 1) {
			if(shopDir == 2) {
				result += Y + Math.min(startLocation+shopLocation, X-startLocation + X-shopLocation);
			}
			else if(shopDir == 3) {
				result += shopLocation + startLocation;
			}
			else if(shopDir == 4) {
				result += X - startLocation + shopLocation;
			}
		}
		//동근이가 남쪽에 위치한 경우
		else if(startDir == 2) {
			if(shopDir == 1) {
				result += Y + Math.min(startLocation+shopLocation, X-startLocation + X-shopLocation);
			}
			else if(shopDir == 3) {
				result += Y - shopLocation + startLocation;
			}
			else if(shopDir == 4) {
				result += Y - shopLocation + X - startLocation;
			}
		}
		//동근이가 서쪽에 위치한 경우
		else if(startDir == 3) {
			if(shopDir == 1) {
				result += shopLocation + startLocation;
			}
			else if(shopDir == 2) {
				result += shopLocation + Y - startLocation;
			}
			else if(shopDir == 4) {
				result += X + Math.min(startLocation+shopLocation, (Y-startLocation) + (Y-shopLocation));
			}
		}
		//동근이가 동쪽에 위치한 경우
		else if(startDir == 4) {
			if(shopDir == 1) {
				result += Y - shopLocation + startLocation;
			}
			else if(shopDir == 2) {
				result += X - shopLocation + Y - startLocation;
			}
			else if(shopDir == 3) {
				result += X + Math.min(startLocation+shopLocation, (Y-startLocation) + (Y-shopLocation));
			}
		}
	}
}