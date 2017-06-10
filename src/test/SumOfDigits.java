package test;

import java.math.BigInteger;

/**
 * Created by PILOT on 12.01.2016.
 */
public class SumOfDigits {

    public static void main(String[] args) {
        System.out.println(getSum(getNumberFactBig())); // Print sum of of the digits in the number 100
    }

    // There is a method where will be calculated factorial
    static BigInteger getNumberFactBig() {
        BigInteger factorialRes = BigInteger.ONE; // Use BigInteger because such types as int, double unsuitable. They will be overflowing
        for (int i = 1; i <= 100; i++) {
            factorialRes = factorialRes.multiply(BigInteger.valueOf(i));
        }
        return factorialRes;
    }

    // Method getSum for calculating sum of digits.
    // As a parameter, method take variable where contained our factorial
    static long getSum(BigInteger factorialRes) {

        long sum = 0;  // Create a variable 'sum', where will be contained sum of digits of number 100

        while (factorialRes.compareTo(BigInteger.ZERO) > 0) {  //Here we run the cycle which will been end when variable be empty
            sum += factorialRes.mod(BigInteger.TEN).longValue(); // Use mod function to get each digit and add all digits to variable 'sum'
            factorialRes = factorialRes.divide(BigInteger.TEN); //Through division remove last added digit if number
        }
        return sum; // Return gotten sum
    }
}
