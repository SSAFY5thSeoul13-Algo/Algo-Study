## BOJ 19236 청소년상어 
- BackTracking , Simulation 
- 🥇 Gold2
- 🔗[청소년 상어 문제 바로가기](https://www.acmicpc.net/problem/19236)



## 풀이

이 문제는 상어가 먹을 수 있는 물고기의 합의 최댓값을 구하기위해 백트래킹을 사용하여 문제를 풀었습니다.
먼저 상어는 0,0에 위치한 물고기를 잡아먹고 그 물고기의 방향을 가지기 때문에 상어의 정보를 0,0에 위치한 물고기의 정보로 바꾸어주고 배열(0,0)에는 -1로 표시합니다.(상어가 위치한다는 것을 표시)

~~~java
		int deadFish = arr[0][0];
		shark = new Fish(0,0,fishes[deadFish].dir,true);
		arr[0][0] = -1;
		fishes[deadFish].isAlive = false;
		// 상어는 (0,0)에 있는 물고기를 먼저 먹는다.	
		move(arr,shark,fishes,deadFish);
~~~

이제 물고기의 이동을 시작합니다. 숫자가 작은 순서부터 이동해야되기 때문에 
죽지 않은 물고기라면 현재 가지고 있는 방향으로 이동을 시켜주었고, 범위를 벗어나거나 상어가 위치한 곳이라면 방향을 반시계 45도로 돌려 바꾸어주었습니다. 
이 때 이동한 위치에 물고기가 없으면 위치상태만 변경해주고, 물고기가 있다면 서로의 위치를 바꾸어 줍니다.

~~~java
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
~~~

그 다음 상어가 이동을 시작합니다. 총 4*4 배열이므로 상어는 최대 3칸까지 이동이 가능합니다.
범위밖으로 나가거나 0인경우에는 continue, 아니라면 이동한 위치의 물고기 정보를 상어의 정보로 저장한뒤 재귀를 통해 현재 물고기의 번호를 더한값을 넘겨줍니다.

또한 최대값을 구할 수 있는 모든 경를 구하기 위해 백트래킹을 사용하였습니다.

헷갈렸던 점은 재귀함수를 호출할 때 물고기와 상어가 이동하여 원본 배열 데이터를 보존하기 위해 배열을 복사하여 넘겨주어야 했던 것 입니다.

~~~java
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
~~~


## 소스코드
~~~java
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

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 11668kb| 84ms|