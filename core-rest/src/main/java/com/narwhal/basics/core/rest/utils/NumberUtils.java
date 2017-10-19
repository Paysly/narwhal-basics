package com.narwhal.basics.core.rest.utils;

/**
 * Created by tomyair on 9/28/17.
 */
public class NumberUtils {

    public static String toRoman(int number) {
        return String.valueOf(new char[number]).replace('\0', 'I')
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L")
                .replace("XXXX", "XL")
                .replace("LL", "C")
                .replace("LXL", "XC")
                .replace("CCCCC", "D")
                .replace("CCCC", "CD")
                .replace("DD", "M")
                .replace("DCD", "CM");
    }

    public static Integer toNumber(String roman) {
        return roman.replace("CM", "DCD")
                .replace("M", "DD")
                .replace("CD", "CCCC")
                .replace("D", "CCCCC")
                .replace("XC", "LXL")
                .replace("C", "LL")
                .replace("XL", "XXXX")
                .replace("L", "XXXXX")
                .replace("IX", "VIV")
                .replace("X", "VV")
                .replace("IV", "IIII")
                .replace("V", "IIIII").length();
    }
}
