package main.simple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.Constants;
import common.Utils;


public class SimpleSolution {
	
	public static void main(String[] args) {
		
		// Xem độ dài các bản mã
		for(String i: Constants.CYPHER_TEXTS) {
			System.out.print(i.length() + ", ");
		}
		System.out.println();
		System.out.println("=============================================================================================");
		
		
		
		// Chuyển từng bản mã về dạng List<Integer>
		List<Integer> list[] = new ArrayList[12];
		for(int i = 1; i <= 11; i++) {
			list[i] = Utils.hexStringToListIntegers(Constants.CYPHER_TEXTS[i]);
		}
		
		
		
		// XOR lần lượt bản mã 1 -> 10 với bản mã 11
		// Chuyển kết quả sang List ký tự
		// Phần tử nào không chuyển được về [a-zA-Z] thì sẽ in ra dấu *
		List<List<Character>> tmp = new ArrayList<List<Character>>();
		for(int i = 1; i <= 10; i++) {
			// XOR
			List<Integer> listXOR = Utils.listXOR(list[i], list[11]);
			// Chuyển phần tử sang dạng ký tự
			List<Character> tmp1 = Utils.listIntegersToListCharacters(listXOR);
			
			System.out.println(tmp1.toString());
			
			tmp.add(Utils.listIntegersToListCharacters(Utils.listXOR(list[i], list[11])));
		}
		
		
		// List ký tự tương ứng với bản rõ của bản mã 11
		List<Character> res = new ArrayList<>();
		
		/*
		 * Ý tưởng:
		 * Tại cùng 1 vị trí XOR của bản 11:
		 * - Nếu thu được nhiều ký tự [a-zA-Z] khác nhau, thì khả năng cao, tại vị trí đó, là ký tự space của bản 11
		 * - Nếu số lượng ký tự [a-zA-Z] khác nhau > 0 và <= 2, thì khả năng cao, đó là ký tự tương ứng của bản 11
		 */
		for(int i = 0; i < list[11].size(); i++) {
			// Dùng set để lưu các ký tự [a-zA-Z]
			Set<Character> set = new HashSet<Character>();
			char c = '*';
			for(List<Character> l: tmp) {
				if(l.get(i) != '*') {
					if(c=='*') {
						c = l.get(i);
					}
					set.add(l.get(i));
				}
			}
			// Nếu số lượng ký tự [a-zA-Z] khác nhau > 0 và <= 2, thì khả năng cao, đó là ký tự tương ứng của bản rõ
			if(set.size() <= 2 && set.size() > 0) {
				char element = (char)((int)c ^ 32); // XOR với space để trả lại ký tự gốc của bản rõ
				res.add(element);
			} else {
				res.add(' ');
			}
		}
		
		System.out.println("=============================================================================================");
		System.out.println(res);
		System.out.println(Utils.listCharacterstoText(res));
	}
}
