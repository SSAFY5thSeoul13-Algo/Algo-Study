package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_직사각형_2527 {
	static Rectangle a, b;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		a = new Rectangle();
		b = new Rectangle();

		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			a.x = Integer.parseInt(st.nextToken());
			a.y = Integer.parseInt(st.nextToken());
			a.p = Integer.parseInt(st.nextToken());
			a.q = Integer.parseInt(st.nextToken());
			
			b.x = Integer.parseInt(st.nextToken());
			b.y = Integer.parseInt(st.nextToken());
			b.p = Integer.parseInt(st.nextToken());
			b.q = Integer.parseInt(st.nextToken());
			solve();
			
		}
	}
	static void solve() {
		
		if(isLine()) {
			System.out.println('b');
		} else if(isDot()) {
			System.out.println('c');
		} else if(isIsolation()) {
			System.out.println('d');
		} else {
			System.out.println('a');
		}
	}
	static boolean isLine() {
		// 오른쩍 
		if(a.p == b.x && a.q > b.y && b.q > a.y) {
			return true;
		}
		// 왼쪽
		if(b.p == a.x && b.q > a.y && a.q > b.y) {
			return true;
		}
		// 위쪽 
		if(a.q == b.y && a.p > b.x && b.p > a.x) {
			return true;
		}
		// 아래쪽
		if(b.q == a.y && b.p > a.x && a.p > b.x) {
			return true;
		}
		
		return false;
	}
	static boolean isDot() {
		// 우상
		if(a.p==b.x && a.q==b.y) {
			return true;
		}
		// 좌하
		if(a.x==b.p && a.y==b.q) {
			return true;
		}
		// 우하
		if(a.p==b.x && a.y==b.q) {
			return true;
		}
		// 좌상
		if(a.x==b.p && a.p==b.y) {
			return true;
		}
		return false;
	}
	static boolean isIsolation() {
		// 우측
		if(a.p < b.x) {
			return true;
		}
		// 좍측
		if(b.p < a.x) {
			return true;
		}
		// 상
		if(a.q < b.y) {
			return true;
		}
		// 하 
		if(b.q < a.y) {
			return true;
		}
			
		return false;
	}
	static class Rectangle{
		int x,y,p,q;
	}

}
