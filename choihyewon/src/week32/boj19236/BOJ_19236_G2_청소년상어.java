package week32.boj19236;

import java.io.*;
import java.util.*;


public class BOJ_19236_G2_청소년상어 {
	static class Fish{
		int r;
		int c;
		int dir;
		boolean isAlive;
		public Fish(int r,int c,int dir,boolean isAlive) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.isAlive = isAlive;
		}
	}
	static int[] dr = {0,-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,0,-1,-1,-1,0,1,1,1};
	static int[][] arr = new int[4][4];
	static Fish[] fishes = new Fish[17];
	static Fish shark;
	static int answer = 0;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		for(int i=0; i<4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				int no = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				fishes[no] = new Fish(i,j,dir,true);
				arr[i][j] = no;
			}
		}
		// 상어가 먼저 0,0에 있는 물고기를 잡아먹는다.
		int deadFish = arr[0][0];
		shark = new Fish(0,0,fishes[deadFish].dir,true);
		arr[0][0] = -1;
		fishes[deadFish].isAlive = false;
		// 상어는 (0,0)에 있는 물고기를 먼저 먹는다.	
		move(arr,shark,fishes,deadFish);
		
//		for(int i=0; i<4; i++) {
//			for(int j=0; j<4; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		System.out.println(answer);
		

	}
	private static void move(int[][] arr,Fish shark,Fish[] fishes,int sum) {
		answer = Math.max(answer, sum);
		
		int[][] copyarr = new int[4][4];
		for(int i=0; i<4; i++) {
			System.arraycopy(arr[i], 0, copyarr[i], 0, 4);
		}
				
		
		Fish copyShark = new Fish(shark.r,shark.c,shark.dir,shark.isAlive);
		
		Fish[] copyFishes = new Fish[17];
		for(int i=1; i<=16; i++) {
			if(fishes[i]==null)	continue;
			Fish f = fishes[i];
			copyFishes[i] = new Fish(f.r,f.c,f.dir,f.isAlive);
		}
		
		int num = 1;
		
		// 물고기 이동 
		for(int i=1; i<=16; i++) {
			Fish fish = copyFishes[i];
			if(!fish.isAlive)	continue;
			
			int d = fish.dir;
			while(true) {
				int nr = fish.r + dr[d];
				int nc = fish.c + dc[d];
				// 배열의 범위를 벗어나지 않거나 상어가 위치하지 않으면 이동가능 
				if(nr>=0 && nc>=0 && nr<4 && nc<4 && copyarr[nr][nc]!=-1) {
					if(copyarr[nr][nc]==0) {
						copyarr[fish.r][fish.c] = 0;
						copyFishes[i] = new Fish(nr,nc,d,true);
						copyarr[nr][nc] = i;
					}else {
						int move = copyarr[nr][nc];
						copyFishes[i] = new Fish(nr,nc,d,true);
						copyFishes[move] = new Fish(fish.r,fish.c,copyFishes[move].dir,true);
						copyarr[nr][nc] = i;
						copyarr[fish.r][fish.c] = move;
					}
					break;
				}else {
					// 이동할 수 없는 경우 45도로 방향 전환 
					if(d==8) {
						d=1;
					}else if(d<8){
						d++;
					}
				}
				// 다시 원래의 방향으로 돌아오면 break
				if(d==fish.dir) {
					break;
				}
				
			}
		}
		
		// 상어 이동 
		for(int i=1; i<=3; i++) {
			int nr = shark.r + dr[shark.dir]*i;
			int nc = shark.c + dc[shark.dir]*i;
			if(nr<0 || nc<0 || nr>=4 || nc>=4 || copyarr[nr][nc]==0) {
				continue;
			}


			int eatFish = copyarr[nr][nc];
			Fish eatFishInfo = copyFishes[eatFish];
			
			copyFishes[eatFish].isAlive = false;
			// 상어 위치 갱신 
			copyarr[nr][nc] = -1;
			copyarr[copyShark.r][copyShark.c] = 0;
			copyShark = new Fish(nr,nc,eatFishInfo.dir,true);
			
			
			move(copyarr,copyShark,copyFishes,sum+eatFish);
			
			// backtracking
			copyarr[nr][nc] = eatFish;
			copyarr[shark.r][shark.c] = -1;
			copyFishes[eatFish].isAlive = true;
			copyShark = new Fish(shark.r,shark.c,shark.dir,true);
			
			
			
			
		}
		
	}
	

}
