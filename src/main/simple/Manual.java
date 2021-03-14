package main.simple;

import java.util.List;

import common.Constants;
import common.Utils;

public class Manual {
	public static void main(String[] args) {

		// Sau khi chạy Solution => "The secuet message is Whtn using a stream cipher
		// never use the key more than onct"
		// Ta chỉnh sửa 1 số ký tự để thu được từ có nghĩa
		String plaintext11_ver1 = "The secret message is  When using a stream cipher  never use the key more than once";
		// Tìm khóa
		List<Integer> key = Utils.findKey(plaintext11_ver1, Constants.CYPHER_TEXTS[11]);
		// Kiểm tra các bản rõ
		for (int index = 1; index <= 11; ++index) {
			// Chuyển cyphertext (dạng chuỗi hexa) sang List<Integer>
			List<Integer> listIntegers = Utils.hexStringToListIntegers(Constants.CYPHER_TEXTS[index]);
			// XOR key và bản mã
			List<Integer> listResult = Utils.listXOR(key, listIntegers);
			// In ra bản rõ
			System.out.println(index + ". " + Utils.listIntegerstoText(listResult));
		}

		
		
		
		System.out.println("===================================================================================");
		
		String plaintext4_ver1 = "The ciphertext producd by a weak encryption algo~ithm looks as good as ciphertext ";
		// Chỉnh sửa lại 1 số ký tự
		String plaintext4_final = "The ciphertext produced by a weak encryption algorithm looks as good as ciphertext ";
		// Tìm khóa
		key = Utils.findKey(plaintext4_final, Constants.CYPHER_TEXTS[4]);
		// Kiểm tra các bản rõ
		for (int index = 1; index <= 11; ++index) {
			// Chuyển cyphertext (dạng chuỗi hexa) sang List<Integer>
			List<Integer> listIntegers = Utils.hexStringToListIntegers(Constants.CYPHER_TEXTS[index]);
			// XOR key và bản mã
			List<Integer> listResult = Utils.listXOR(key, listIntegers);
			// In ra bản rõ
			System.out.println(index + ". " + Utils.listIntegerstoText(listResult));
		}

	}
}
