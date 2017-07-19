package com.test;

import java.util.Date;

public class TestGC {

	public static void main(String[] args) {
	/*	Long maxMemory = Runtime.getRuntime().maxMemory();
		Long totalMemory = Runtime.getRuntime().totalMemory();
		System.out.println("maxMemory:"+maxMemory+"byte,"+maxMemory/1024/1024+"MB");
		System.out.println("total:"+totalMemory+"byte,"+totalMemory/1024/1024+"MB");*/
		/*String str ="abc";
		while(true) {
			str +=str+new Random().nextInt(666666);
		}*/
		
		while(true) {
			byte[] b = new byte[1024*1024*50];
		}
	}

}
