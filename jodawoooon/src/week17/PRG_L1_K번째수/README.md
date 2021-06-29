
## Prg Lv1 K번째 수
- 고득점 kit (lv1)
- 정렬

<br>

### 문제풀이
배열 array의 i번째 숫자부터 j번째 숫자까지 자르고  정렬했을 때, k번째에 있는 수를 구하려 합니다.

i번째 부터 j번째 숫자만 array list에 넣고  
`Collections.sort(list)`를 이용해 오름차순 정렬했습니다.  

그리고 `list.get(k-1)`해서 k번째 숫자를 answer에 저장합니다.

<br>
