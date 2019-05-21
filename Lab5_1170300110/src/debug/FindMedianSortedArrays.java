package debug;

import static org.junit.Assert.assertEquals;

/**
 * Given two ordered integer arrays nums1 and nums2, with size m and n
 * Find out the median (double) of the two arrays.
 * You may suppose nums1 and nums2 cannot be null at the same time.
 *
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The output would be 2.0
  
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The output would be 2.5

 * Example 3:
 * nums1 = [1, 1, 1]
 * nums2 = [5, 6, 7]
 * The output would be 3.0

 * Example 4:
 * nums1 = [1, 1]
 * nums2 = [1, 2, 3]
 * The output would be 1.0
 */

public class FindMedianSortedArrays {

	public double findMedianSortedArrays(int[] A, int[] B) {
		if(A == null) {
			int len = B.length;
			if(len % 2 == 1) {
				return B[len/2];
			}else {
				return (B[len/2 - 1] + B[len/2]) / 2;
			}
		}
		
		if(B == null) {
			int len = A.length;
			if(len % 2 == 1) {
				return A[len/2];
			}else {
				return (A[len/2 - 1] + A[len/2]) / 2;
			}
		}
		
		
		int m = A.length;
		int n = B.length;
		//��B��A��
		if (m > n) {
			int[] temp = A;
			A = B;
			B = temp;
			int tmp = m;
			m = n;
			n = tmp;
		}
		int iMin = 0, iMax = m, halfLen = (m + n) / 2;
		while (iMin <= iMax) {
			int i = (iMin + iMax + 1) / 2; //A���м�ֵ 
			int j = halfLen - i;//B���м�λ�� 
			if (i < iMax && B[j - 1] > A[i]) { //B�м���߱ߵ��Ǹ���A�м�ֵ��
				iMin = i + 1; //����A����
			} else if (i > iMin && A[i - 1] > B[j]) {//A�м�����Ǹ��ı�B�м�ֵ��
				iMax = i - 1; //����A����
			} else {
				int maxLeft = 0;
				if (i == 0) {
					maxLeft = B[j - 1];
				} else if (j == 0) {
					maxLeft = A[i - 1];
				} else {
					maxLeft = Math.max(A[i - 1], B[j - 1]);
				}
				/*
				 * 
				 */
				
				int minRight = 0;
				if (i == m) {
					minRight = B[j];
				} else if (j == n) {
					minRight = A[i];
				} else {
					minRight = Math.min(B[j], A[i]);
				}
				
				//�ƶ���λ��
				//1�ĳ���0
				if ((m + n + 1) % 2 == 0) {
					return minRight;
				}
				
				return (maxLeft + minRight) / 2.0;
			}
		}
		return 0.0;
	}
	
	public static void main(String[] args) {
		FindMedianSortedArrays findMSA = new FindMedianSortedArrays();
		
		int[] A = {1, 2, 4, 5};
		int[] B = {6, 7, 8, 9, 10, 11, 12};
		// 11223445567
		// 7
		System.out.println(findMSA.findMedianSortedArrays(A, B));
		
		int[] C = {1, 2, 4};
		int[] D = {1, 2, 3, 4, 5, 6, 7};
		// 11 22 344567
		// 3.5
		System.out.println(findMSA.findMedianSortedArrays(C, D));
		
	}

}
