package main.test;

import java.util.List;

import common.Constants;
import common.Utils;

/*
 * Kiểm nghiệm bản rõ thu được
 * Tìm key => tìm bản rõ của các bản mã còn lại => kiểm tra chính tả, ngữ nghĩa của các bản rõ
 */
public class Test {
	public static void main(String[] args) {
		
		String plaintext = "The secret message is: When using a stream cipher, never use the key more than once";
		List<Integer> key = Utils.findKey(plaintext, Constants.CYPHER_TEXTS[11]);
		// Kích thước key tương ứng
		System.out.println("Length of key: " + key.size() + " bytes");
		
		// In ra key.size() bytes trong bản rõ của từng cyphertext
		System.out.println("Plain text (" + key.size() + " bytes): ");
		for(int index = 1; index <= 11; ++ index) {
			// Chuyển cyphertext (dạng chuỗi hexa) sang List<Integer>
			List<Integer> listIntegers = Utils.hexStringToListIntegers(Constants.CYPHER_TEXTS[index]);
			// XOR key và bản mã
			List<Integer> listResult = Utils.listXOR(key, listIntegers);
			// In ra bản rõ
			System.out.println(index + ". " +  Utils.listIntegerstoText(listResult));
		}
		
	}
}
