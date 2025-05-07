package com.demo.helpers;

import java.util.Random;

public class RandomStringHelper {
	 public static void main(String[] args) {
	        String randomString = generateRandomString(6);
	        
	        // In ra chuỗi ngẫu nhiên
	        System.out.println(randomString);
	    }

	    public static String generateRandomString(int length) {
	        // Chứa các ký tự có thể xuất hiện trong chuỗi
	        String characters = "0123456789";
	        StringBuilder randomString = new StringBuilder();

	        Random random = new Random();

	        for (int i = 0; i < length; i++) {
	            // Lấy một ký tự ngẫu nhiên từ chuỗi characters
	            char randomChar = characters.charAt(random.nextInt(characters.length()));

	            // Thêm ký tự ngẫu nhiên vào chuỗi
	            randomString.append(randomChar);
	        }

	        return randomString.toString();
	    }
}
