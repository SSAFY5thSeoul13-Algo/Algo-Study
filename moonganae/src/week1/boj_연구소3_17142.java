package week1;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//public class Main {
//	
//	static int N, M;							// 연구소크기, 바이러스 수 
//	
//	static int[][] map;							// 연구소 상태저장
//	static int[][] time;						// bfs방문 및 전파시간을 저장할 배열
//	
//	static int roomCnt = 0;						// 빈방(0)의 수
//	static int virusCnt=0;						// 바이러스(2)의 수
//	static Virus[] virusArr = new Virus[10];	// 바이러스 위치를 저장할 배열
//	static boolean[] select = new boolean[10];	// 바이러스 뽑기(조합)를 확인할 배열
//	
//	/* delta Array */
//	static int[] dx = {0,1,0,-1};
//	static int[] dy = {1,0,-1,0};
//	
//	static int ans = Integer.MAX_VALUE;			// 정답 : 최솟값을 구하기 때문에 MAX_VALUE로 초기화
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//		
//		map = new int[N][N];
//		time = new int[N][N];
//		
//		for(int i=0; i<N; i++) {
//			st = new StringTokenizer(br.readLine());
//			for(int j=0; j<N; j++) {
//				int data = Integer.parseInt(st.nextToken());
//				map[i][j] = data;
//				
//				if(data == 2) virusArr[virusCnt++] = new Virus(i, j);	// 바이러스 위치 저장
//				else if(data == 0) roomCnt++;							// 빈방의 수 세기
//			}
//		}
//		
//		/* 이미 빈방이 없다면 */
//		if(roomCnt == 0) {
//			ans = 1;		// ans를 -1하기 때문에 1을 준다.
//		}else {
//			/* 조합시작 */
//			comb(0,0);
//		}
//		
//		/* 결과출력 */
//		if(ans == Integer.MAX_VALUE) System.out.println(-1);
//		else System.out.println(ans-1);		// 시간계산할때 시작이 1부터라 -1을 빼줘야 한다.
//	}
//	
//	/* 조합 
//	 * num : 현재 뽑은 수
//	 * start : 시작하는 수
//	 */
//	static void comb(int num, int start) {
//		
//		if(num == M) {			// M개의 바이러스 선택완료
//			bfs();				// bfs시작
//			return;
//		}
//		
//		for(int i=start; i<virusCnt; i++) {
//			select[i] = true;
//			comb(num+1, i+1);
//			select[i] = false;
//		}
//		
//	}
//	/* 전염되는 시간 구하기 */
//	static void bfs() {
//		
//		Queue<Virus> q = new LinkedList<>();
//		
//		/* 바이러스 활성화 */
//		for(int i=0; i<virusCnt; i++) {
//			if(select[i]) {									// 선택된 바이러스라면
//				time[virusArr[i].y][virusArr[i].x] = 1;		// 활성화 default_value 0과 구분하기 위해 1로 설정
//				q.offer(virusArr[i]);
//			}
//		}
//		
//		int removeRoom = 0;				// 전파한 빈방의수
//		while(!q.isEmpty()) {
//			Virus cur = q.poll();
//			
//			for(int z=0; z<4; z++) {
//				int zy = cur.y + dy[z];
//				int zx = cur.x + dx[z];
//				
//				/* 제외조건 */
//				if(zy<0 || zx<0 || zy>=N || zx>=N) continue;		// 범위를 벗어나면
//				if(map[zy][zx] == 1) continue;						// 벽이라면
//				if(time[zy][zx] > 0) continue;						// 이미 방문했다면
//				
//				/* 접근할 수 있는 방 */
//				q.offer(new Virus(zy,zx));
//				time[zy][zx] = time[cur.y][cur.x] + 1;
//				
//				if(map[zy][zx] == 0) {								// 빈방이었다면
//					removeRoom++;									// 전파완료
//				}
//				if(removeRoom == roomCnt) {							// 빈방을 모두 전파하였다면
//					ans = Math.min(ans, time[zy][zx]);				// 결과저장
//					q.clear();										// bfs 종료
//				}
//			}
//		}
//		
//		/* time 배열 초기화 */
//		for(int i=0; i<N; i++)
//			Arrays.fill(time[i], 0);
//	}
//	
//	static class Virus{
//		int y, x;
//
//		public Virus(int y, int x) {
//			super();
//			this.y = y;
//			this.x = x;
//		}
//	}
//}
//
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

public class boj_연구소3_17142 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(new InputStreamReader (System.in));
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] map = new int[n][n];
        LinkedList<node> virus_can = new LinkedList<node>();
        for(int i = 0 ; i < n ; i++)
        {
            for(int j = 0 ; j < n ; j++)
            {
                int get_v = sc.nextInt();
                if(get_v == 2)
                {
                    map[i][j] = 0;
                    node v = new node(i,j,0);
                    virus_can.add(v);
                }
                if(get_v == 1)
                {
                    map[i][j] = -1;

                }
                else map[i][j] = 0;
            }
        }
        int[][] spread = new int[n][n];        //-1은 벽 -2는 바이러스 놓은 위치 -3 바이러스 후보지 중 바이러스 안 놓은 곳
        int min = 999999999;
        int[] virus_position = new int[m];
        for(int c = 0; c < m; c++)
        {
            virus_position[c] = c;
        }
        boolean run_flag = true;
        while(run_flag)
        {
            int resize_count = 0;
            boolean resize_flag = true;
            int t = m-1;
                int set_n = 1;
                for(int tc = t+1; tc < m; tc++)
                {
                    virus_position[tc] = virus_position[t]+set_n++;
                }
                    LinkedList<node> virus = new LinkedList<node>();


                    int temp_c = 0;
                    for(int c = 0 ; c < virus_can.size(); c++)
                    {

                        if( temp_c < m && c == virus_position[temp_c])
                        {
                            map[virus_can.get(c).y][virus_can.get(c).x] = -2;
                            temp_c ++;
                        }
                        else
                            map[virus_can.get(c).y][virus_can.get(c).x] = -3;
                    }

                for(int c = 0 ; c < m; c++)
                    virus.add(virus_can.get(virus_position[c]));

                
                int value = 0;
                for(int a = 0 ; a < n ; a++)
                {
                    for(int b = 0; b < n ; b++)
                    {
                        spread[a][b] = map[a][b];
                    }
                }
                int temp_max = 0;
                boolean flag = true;
                while(!virus.isEmpty())
                {
                    node temp = virus.pop();
                    {
                        int y = temp.y;
                        int x = temp.x;
                        value = temp.value;
                        if(y > 0)
                        {
                            if(spread[y-1][x] == 0)
                            {                                
                                spread[y-1][x] = value+1;
                                node new_n = new node(y-1,x,value+1);
                                virus.add(new_n);
                            }
 
                            if(spread[y-1][x] == -3)
                            {                                
                                spread[y-1][x]--;
                                node new_n = new node(y-1,x,value+1);
                                virus.add(new_n);
                            }

                        }
                        if(y < n-1)
                        {
                            if(spread[y+1][x] == 0 )
                            {
                                spread[y+1][x] = value+1;
                                node new_n = new node(y+1,x,value+1);
                                virus.add(new_n);
                            }

                            if(spread[y+1][x] == -3)
                            {
                                spread[y+1][x]--;
                                node new_n = new node(y+1,x,value+1);
                                virus.add(new_n);
                            }

                        }
                        if(x > 0)
                        {
                            if(spread[y][x-1] == 0)
                            {
                                spread[y][x-1] = value+1;
                                node new_n = new node(y,x-1,value+1);
                                virus.add(new_n);
                            }

                            if(spread[y][x-1] == -3)
                            {
                                spread[y][x-1]--;
                                node new_n = new node(y,x-1,value+1);
                                virus.add(new_n);
                            }
                            
                        }
                        if(x < n-1)
                        {
                            if(spread[y][x+1] ==  0)
                            {
                                spread[y][x+1] = value + 1;
                                node new_n = new node(y,x+1,value+1);
                                virus.add(new_n);
                            }

                            if(spread[y][x+1] ==  -3)
                            {
                                spread[y][x+1]--;
                                node new_n = new node(y,x+1,value+1);
                                virus.add(new_n);
                            }

                        }
                    }
                    
                }
                temp_max = 0;
                for(int a = 0 ; a < n ; a++)
                {
                    for(int b = 0; b < n ; b++)
                    {
                        if(spread[a][b] == 0)
                        {
                            flag = false;
                            break;
                        }
                        if(temp_max < spread[a][b])
                            temp_max = spread[a][b];
                    }
                }
//                    System.out.println("temp_max : " + temp_max + flag);

                if(min > temp_max && flag)
                {
/*                        for(int a = 0 ; a < n ; a++)
                        {
                            for(int b = 0; b < n ; b++)
                                System.out.print(spread[a][b]+ "\t");
                            System.out.println();
                        }
                        System.out.println();
*/
                        min = temp_max;
                    }
                    resize_count = 0;
                    while(resize_flag)
                    {
                        resize_flag = false;
                        if(t== -1)
                        {
                            run_flag = false;
                            break;
                        }
                        if(virus_position[t]+1 >=virus_can.size()-resize_count)
                        {
                            resize_count++;
                            t--;
                            resize_flag = true;
                        }
                        else virus_position[t]++;
                    }
                    int sum_count = 1;
                    if(run_flag)
                    for(int tc = t+1; tc < m ; tc++)
                    {
                        virus_position[tc] = virus_position[t]+sum_count++;
                    }

        }
        if(min == 999999999)
            System.out.println(-1);
        else
            System.out.println(min);
    }
}

class node
{
    int x;
    int y;
    int value;
    node(int Y, int X, int V)
    {
        this.y = Y;
        this.x = X;
        this.value = V;
    }
}