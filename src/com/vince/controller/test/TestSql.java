/**
 * @author 流浪大法师
 * @time 2016-2-16 上午12:24:15
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.controller.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.vince.model.common.DBConn;

public class TestSql {

	/**
	 * 
	 */
	public TestSql() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		int[] intArr1 = new int[]{1,2},intArr2 = new int[]{3,4};
		ArrayList<int[]> arr = new ArrayList<int[]>();
		arr.add(intArr1);arr.add(intArr2);
		intArr1[1] = 4;
		Iterator<int[]> iterator = arr.iterator();
		while(iterator.hasNext()){
			System.out.println(Arrays.toString(iterator.next()));
		}
	}

}
