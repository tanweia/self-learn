package com.serius.learn.other;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class CompletionServiceTests {
	BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>(19);
	ExecutorService exec = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, workQueue);
	final BlockingDeque<Future<String>> queue = new LinkedBlockingDeque<Future<String>>(20);
	final CompletionService<String> completionService = new ExecutorCompletionService<String>(exec, queue);
	
	@Test
	public void test() throws InterruptedException, ExecutionException {
		for (int i = 0; i < 20; i++) {
			System.out.println("******");
			completionService.submit(new Callable<String>() {
				public String call() throws InterruptedException {
					int sleepTime = new Random().nextInt(1000);
					Thread.sleep(sleepTime);
					String name = Thread.currentThread().getName();
					System.out.println(name);
					return name + ":睡眠" + sleepTime + "毫秒";
				}
			});
		}
		System.out.println("----------");
		
		int i = 0;
		while (i < 20) {
			Future<String> future = completionService.take();
			if(future == null){
//				i --;
				continue;
			}
			String result = future.get();
			System.out.println(result);
			i ++;
		}
		
	}
	
}
