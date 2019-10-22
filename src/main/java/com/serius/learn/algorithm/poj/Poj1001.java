package com.serius.learn.algorithm.poj;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 大值精度计算是一个常见的问题。例如，计算国债是许多计算机系统都要处理的一种征税问题。此类问题，要求编写一个程序来计算R的N次方的精确值，其中R是一个实数（0.0<R<99.999），N是一个整数，0<N<25。
 * 输入将由一组r和n值对组成。r值将占据第1列到第6列，n值将位于第8列和第9列。
 * 输出将由每行输入的一行组成，精确值为r^n。输出中去掉前导零。不重要的尾随零不打印。如果结果是整数，则不打印小数点。
 * 
 * Created by serius on Aug 9, 2019
 */
public class Poj1001 {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) { // 支持多次输入
			// 输入r、n
			BigDecimal r = scanner.nextBigDecimal();
			int n = scanner.nextInt();
			
			// 求幂
			BigDecimal result = new BigDecimal("1");
			while (n > 0) {
				result = result.multiply(r);
				n--;
			}
			
			// 掐头去尾
			result = result.stripTrailingZeros();
			String output = result.toPlainString();
			if (output.startsWith("0.")) {
				output = output.substring(1);
			}
			
			System.out.println(output);
		}
	}
}
