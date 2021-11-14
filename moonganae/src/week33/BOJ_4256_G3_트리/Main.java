package week33.BOJ_4256_G3_트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] preOrder, inOrder;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T=Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){

            N = Integer.parseInt(br.readLine());

            preOrder = new int[N];
            inOrder = new int[N];

            StringTokenizer stPre = new StringTokenizer(br.readLine());
            StringTokenizer stIn = new StringTokenizer(br.readLine());

            for(int i=0; i<N; i++){
                preOrder[i] = Integer.parseInt(stPre.nextToken());
                inOrder[i] = Integer.parseInt(stIn.nextToken());
            }

            postOrder(0,N,0);
            System.out.println();
        }
    }

    /**
     *
     * @param s : inorder 시작인덱스
     * @param e : inorder 끝인덱스
     * @param rootIdx : preorder 루트 인덱스
     */
    public static void postOrder(int s, int e, int rootIdx){

        for(int i=s; i<e; i++){
            // 루트위치 찾기
            if(preOrder[rootIdx] == inOrder[i]){
                // Left Sub Tree 탐색
                postOrder(s,i, rootIdx+1);
                // Right Sub Tree 탐색
                postOrder(i+1, e, rootIdx+i-s+1);
                // Root 출력
                System.out.print(preOrder[rootIdx] + " ");
            }
        }
    }
}
