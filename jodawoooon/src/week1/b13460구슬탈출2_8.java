package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class marble{
    int rr, rc, br, bc, cnt;

	public marble(int rr, int rc, int br, int bc, int cnt) {
		this.rr = rr;
		this.rc = rc;
		this.br = br;
		this.bc = bc;
		this.cnt = cnt;
	}

	public marble() {
		// TODO Auto-generated constructor stub
	}

	
    
}

public class b13460구슬탈출2_8 {
    static char[][] arr;
    static boolean[][][][] select;
    static int redR, redC, blueR, blueC, holeR, holeC;
    static int[] dr = {-1,0, 1, 0}; //위 오른쪽 아래 왼쪽
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   	 

    	StringTokenizer st = new StringTokenizer(br.readLine());
	    int N = Integer.parseInt(st.nextToken());
	    int M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        select = new boolean[N][M][N][M];

        
        for (int i = 0; i < N; i++) {
        	String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j);
                if (arr[i][j] == 'R') {
                	redR = i;
                	redC = j;

                }
                if (arr[i][j] == 'B') {
                	blueR = i;
                	blueC = j;

                }if (arr[i][j]=='O') {
                	holeR = i;
                	holeC = j;
                }
            }
        }
        
       
        go(new marble(redR, redC, blueR, blueC, 0));
    }
    

    private static void go(marble m) {
        Queue<marble> q = new LinkedList<>();
        
        q.offer(m);

       
        while (!q.isEmpty()) {
            marble tmp = q.poll();

            int r_red = tmp.rr;
        	int c_red = tmp.rc;
        	int r_blue = tmp.br;
        	int c_blue = tmp.bc;
        	int cnt = tmp.cnt;
        	
        	select[r_red][c_red][r_blue][c_blue] = true;
        	
        	if(cnt>=10) {
            	System.out.println(-1);
            	return;
            }
        	
            
            
            
            for (int i = 0; i < 4; i++) {
            	
            	int rr = r_red+dr[i];
            	int rc = c_red+dc[i];
            	int br = r_blue+dr[i];
            	int bc = c_blue+dc[i];

            
                
              //파란구슬
                while(true) {
                	if(arr[br][bc]=='#') {
            			br-=dr[i];
            			bc-=dc[i];
            			break;
            		}
                	if(br==holeR&&bc==holeC) {
            			break;
            		}

            		br+=dr[i];
            		bc+=dc[i];
                }

                
                
                
                if(br==holeR&&bc==holeC) {
                	//파란구슬이 구멍에빠지면 안된다
                	continue;
                }
                
            	//빨간구슬
                while(true) {
                	
                	if(arr[rr][rc]=='#') {
            			rr-=dr[i];
            			rc-=dc[i];
            			break;
            		}
                	if(rr==holeR&&rc==holeC) {
            			break;
            		}

            		rr+=dr[i];
            		rc+=dc[i];
                }

                if(rr==holeR&&rc==holeC) {
                	//빨간구슬만빠졌으면
                	System.out.println(cnt+1);
                	return;
                }
                
                if(br==rr&&bc==rc) { //두개가 같은 위치에 있다면

                	if(i==0) { //위쪽
                		if(r_blue>r_red) { //움직이기 전에 파란구슬이 더 뒤에있었다면? 
                			br+=1;//파란구슬은 br까지 올 수 없었다.(한칸에 하나만 올수있음) 그러므로 1을 더해줘서 한칸 뒤로 이동시킨다
                		}else { //빨간구슬이 뒤에있었다면
                			rr+=1;
                		}
                	}else if(i==1) { //오른쪽으로 이동할때
                		if(c_blue>c_red) { //파란구슬의 c좌표가 더 컸다면 파란구슬이 앞에있다 
                			rc-=1; //빨간구슬을 한칸뒤로 이동한다
                		}else {
                			bc-=1;
                		}
                		
                	}else if(i==2) {//아래쪽으로 이동할때
                		if(r_blue>r_red) { //파란구슬의 r좌표가 더 컸다면, 파란구슬이 더 앞에있다
                			rr-=1;
                		}else {
                			br-=1;
                		}
                	}else if(i==3) {//왼쪽으로 이동할때
                		if(c_blue>c_red) { //파란구슬의 r좌표가 더 컸다면, 빨간구슬이 더앞에있다
                			bc+=1;
                		}else {
                			rc+=1;
                		}
                	}
                }
                
                if(!select[rr][rc][br][bc]) { //아직 체크해보지 않은경우의수이다.
                	q.offer(new marble(rr,rc,br,bc,cnt+1));//큐에 넣는다
                }
            }
        }System.out.println(-1);
        
    }


}