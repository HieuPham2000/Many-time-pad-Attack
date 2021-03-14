package main.solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.Constants;
import common.Utils;


public class Solution {
	
	
	public static void main(String[] args) {		
		
		// Chuyển từng bản mã về dạng List<Integer>
		List<Integer> list[] = new ArrayList[12];
		for(int i = 1; i <= 11; i++) {
			list[i] = Utils.hexStringToListIntegers(Constants.CYPHER_TEXTS[i]);
		}
		
		// Tìm vị trí space trong từng bản
		List<Integer> listSpaceIndex[] = new ArrayList[12];
		for(int i = 1; i <= 11; i++) {
			listSpaceIndex[i] = Utils.findListSpaceIndex(list, i);
		}
		
		
		// List ký tự tương ứng với bản rõ của bản mã 11
		char res[] = new char[list[11].size()];
		// Khởi tạo
		for(int i = 0; i < res.length; ++i) {
			res[i] = '*';
		}
		
		// XOR với các ký tự space trong bản 1 => 10
		for(int i = 1; i <= 10; ++i) {
			for(int j: listSpaceIndex[i]) {
				int c = list[i].get(j) ^ list[11].get(j) ^ 32;
				res[j] = (char)c;
			}
			System.out.println(res);
		}
		
		for(int j: listSpaceIndex[11]) {
			res[j] = ' ';
		}
	
		
		System.out.println(res);
	}
}
