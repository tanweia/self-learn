package com.serius.learn.algorithm;

// https://www.jianshu.com/p/84130db3250e
public class Floyd {
	 
	//矩阵阶数
    static int matrixOrder = 6;
 
    //无穷距离
    static int MD = 999;
 
    //邻接矩阵
    static int[][] arcs = {
            {0,       50,     10,     MD,    45,     MD},
            {MD,      0,      15,     MD,     5,     MD},
            {20,      MD,      0,     15,    MD,     MD},
            {MD,      20,     MD,      0,    35,     MD},
            {MD,      MD,     MD,     30,     0,     MD},
            {MD,      MD,     MD,      3,    MD,     0 }} ;
 
    //路径记录
    static int[][] path = new int[matrixOrder][matrixOrder];
 
    static void floyd(){
        //初始化path
        for(int i = 0; i < matrixOrder; i++){
            for(int j = 0; j < matrixOrder; j++){
                if(arcs[i][j] != MD)
                    path[i][j] = j;
                else
                    path[i][j] = -1;
            }
        }
        //算法开始喽！基本就是暴力解决
        for(int k = 0; k < matrixOrder; k++){
            for(int i = 0; i< matrixOrder; i++){
                for (int j = 0; j < matrixOrder; j++){
                    if(arcs[i][k] >= MD || arcs[k][j] >= MD)
                        continue;
                    if(arcs[i][k] + arcs[k][j] < arcs[i][j]){
                        arcs[i][j] = arcs[i][k] + arcs[k][j];
                        path[i][j] = path[i][k];
                    }
                }
            }
        }
    }
 
    public static void main(String[] args) {
        floyd();
        System.out.println("Arcs:");
        for(int i = 0; i < matrixOrder; i++){
            for(int j = 0; j < matrixOrder; j++){
                System.out.print(arcs[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("Path:");
        for(int i = 0; i < matrixOrder; i++){
            for(int j = 0; j < matrixOrder; j++){
                System.out.print(path[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
