package week29.BOJ_2630_S3_색종이만들기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2630_S3_색종이만들기 {
	static int N, map[][], white, blue;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		map = new int[N][N];
		
		for(int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0 ; j< N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		search(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}
	
	private static void search(int r, int c, int size) {
		
		if(size==1) { 
			//기저조건 1. 하나의 정사각형 칸이 되어 더 이상 자를 수 없다
			if(map[r][c]==0) white++;
			else blue++;
			return;
		}
		
		if(isSame(r,c, map[r][c],size)){ 
			//기저조건 2. 잘라진 종이가 모두 하얀색 또는 모두 파란색으로 칠해져있다.
			if(map[r][c]==0) white++;
			else blue++;
            return;
		}
        
        //같은 색이 아니라면 더 자른다
		search(r,c,size/2);
		search(r+size/2,c,size/2);
		search(r,c+size/2,size/2);
		search(r+size/2,c+size/2,size/2);

		
		
	}

	private static boolean isSame(int r, int c, int color, int size) {
        //색종이가 모두 같은 색으로 칠해져 있는지 체크
		for(int i = r ; i < r + size ; i++) {
			for(int j = c ; j < c + size ; j++) {
				if(color!=map[i][j]) return false;
			}
		}
		return true;
	}
}
