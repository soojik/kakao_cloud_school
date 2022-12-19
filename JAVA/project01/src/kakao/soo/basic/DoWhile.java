package kakao.soo.basic;

import java.util.Arrays;

public class DoWhile {

	public static void main(String[] args) {
	
		int [] ar = {1,5,3,2,4};
		
		//선택정렬
		for(int i=0;i<ar.length-1; i++) {
			//기준의 뒤부터 마지막 자리까
			for(int j=i+1; j< ar.length;j++) {
				//자리의 데이터를 비교해서 기준 자리의 데이터가 크면 
//				2개의 데이터를 swap
				if(ar[i]>ar[j]) {
					int temp = ar[i];
					ar[i]=ar[j];
					ar[j]=temp;
					
				}
			}
			
			//하나의 pass가 완료되면 출력 
			System.out.println(Arrays.toString(ar));
		}
		
		
}
}
