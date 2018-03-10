package com.serius.learn.other;

import java.text.DecimalFormat;

import org.junit.Test;

public class MathTests {
	
	
	/**
	 * 等比数列公式：
	 * Sn=a1+a2+……+an
	 * q*Sn=a1*q+a2*q+……+an*q=a2+a3+……+a(n+1)
     * Sn-q*Sn=a1-a(n+1)=a1-a1*q^n
     * (1-q)*Sn=a1*(1-q^n)
     * Sn=a1*(1-q^n)/(1-q)
	 * 
	 * 9+99+999+...9999999999
	 * =10+100+1000+...10000000000-1*10
	 * =10*(1-10^9)/(1-10)-1*10
	 */
	@Test
	public void powTest() {
		double sum = countGeoPro(10, 10, 10);
		double res = sum - 1*10;
		// 默认是科学计数法表示，格式化设置  
		DecimalFormat decimalFormat = new DecimalFormat("#,##0"); 
        System.out.println(decimalFormat.format(res));
		
	}
	
	/**
	 * 计算等比数列
	 * @param a1
	 * @param q
	 * @param n
	 * @return
	 */
	private static double countGeoPro(int a1, int q, int n) {
		double sum = q*(1-Math.pow(q, n))/(1-q);
		return sum;
	}
}
