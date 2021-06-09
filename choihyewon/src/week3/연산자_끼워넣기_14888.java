package week3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연산자_끼워넣기_14888 {
	static int N;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int[] num;
	static int[] operator;
	public static void main(String[] args)throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		num = new int[N];
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		operator = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		
		calc(num[0],1);
		
		System.out.println(max);
		System.out.println(min);

	}
	static void calc(int number, int idx) {
		// 기저조건
		if(idx==N) {
			max = Math.max(max, number);
			min = Math.min(min, number);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(operator[i]==0) {
				continue;
			}
			operator[i]--;
			switch(i) {
			case 0 :
				calc(number+num[idx],idx+1);
				break;
			case 1 :
				calc(number-num[idx],idx+1);
				break;
			case 2 :
				calc(number*num[idx],idx+1);
				break;
			case 3 :
				calc(number/num[idx],idx+1);
				break;
			}
			
			operator[i]++;
		}
		
	}

}
