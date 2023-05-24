package CRC;

import java.util.ArrayList;
import java.util.Scanner;

public class CRCChecker {

    //  CRC Method
    public static ArrayList<Integer> CRC(ArrayList<Integer> dividend, ArrayList<Integer> divisor,
                                         int left, int right) {
        //  Dividend left current position to Dividend length
        while (left < dividend.toArray().length) {
            //  If first bit is 0 go to next bit
            if (dividend.get(left) == 0) {
                left++;
                right++;
            }
            //  If dividend right position is equal to dividend length then return result
            if (right == dividend.toArray().length) {
                return dividend;
            }
            //  If not then
            else {
                //  Check current bit is 0 or not
                if (dividend.get(left) != 0) {
                    //  If not then perform XOR operation between dividend and divisor
                    for (int i = 0, j = left; i < divisor.toArray().length; i++, j++) {
                        dividend.set(j, (dividend.get(j)) ^ (divisor.get(i)));
                    }
                }
            }
        }

        return dividend;
    }

    //  Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Dividend : ");
        String dividend = sc.next();

        System.out.print("Enter Divisor : ");
        String divisor = sc.next();

        ArrayList<Integer> dividendList = new ArrayList<>();
        ArrayList<Integer> mainDividendList = new ArrayList<>();

        //  Store Dividend as List from String to Integer
        for (int i = 0; i < dividend.length(); i++) {
            int e = Integer.parseInt(String.valueOf(dividend.charAt(i)));
            if (e == 1 || e == 0) {
                dividendList.add(e);
                mainDividendList.add(e);
            } else {
                System.out.println("Please Enter Binary Data !!");
                System.exit(0);
            }
        }

        //  Add divisor length -1 bit into dividend end position
        for (int i = 1; i < divisor.length(); i++) {
            dividendList.add(0);
        }

        ArrayList<Integer> divisorList = new ArrayList<>();

        //  Store Divisor as List from String to Integer
        for (int i = 0; i < divisor.length(); i++) {
            int e = Integer.parseInt(String.valueOf(divisor.charAt(i)));
            if (e == 1 || e == 0) {
                divisorList.add(e);
            } else {
                System.out.println("Please Enter Binary Data !!");
                System.exit(0);
            }
        }

        //  Call the CRC method and Store the Result
        ArrayList<Integer> resultList = (CRC(dividendList, divisorList, 0, divisor.length() - 1));

        ArrayList<Integer> crcCode = new ArrayList<>();

        //  Split the CRC Code and add to the Main Dividend end position
        for (int i = (resultList.toArray().length - (divisor.length() - 1)); i < resultList.toArray().length; i++) {
            crcCode.add(resultList.get(i));
            mainDividendList.add(resultList.get(i));
        }

        System.out.print("CRC Code : ");
        for (Integer el : crcCode) {
            System.out.print(el);
        }

        System.out.print("\nCode Word : ");
        for (Integer el : mainDividendList) {
            System.out.print(el);
        }
    }
}