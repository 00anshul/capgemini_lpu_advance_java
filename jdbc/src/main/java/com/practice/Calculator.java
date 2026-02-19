package com.practice;

public class Calculator {
public static int add(int a,int b) {
	System.out.println(a+b);
	return a+b;
}
public static String reverseString(String s) {
	String rev="";
	for(int i=s.length()-1;i>=0;i--) {
		rev=rev+s.charAt(i);
		
	}
	return rev;
}

public static int palindrome(int i) {
	int reverse = 0;
	int temp = i;
    while (temp != 0) {
        reverse = (reverse * 10) + (temp % 10);
        temp = temp / 10;
    }
	return reverse;
	
}
public static int factorial(int i) {
	int fact=1;
	while(i>0) {
		fact=fact*i;
		i--;
	}
	return fact;
}

public static int div(int a,int b) {

	return a/b;
}
}
