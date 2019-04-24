package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberRegex {
    public static void main(String args[]) {
        //regex: (?:(?:(?:(?:\+?3)?8\W{0,5})?\(?0\W{0,5})?[0-9]\s?\d[^\w,;(\+]{0,5})?\d\W{0,5}\d\W{0,5}\d\W{0,5}\d\W{0,5}\d\W{0,5}\d\W{0,5}\d(?!(\W?\d))
        String regex = "(?:(?:(?:(?:\\+?3)?8\\W{0,5})?\\(?0\\W{0,5})?[0-9]\\s?\\d[^\\w,;(\\+]{0,5})?\\d\\W{0,5}\\d\\W{0,5}\\d\\W{0,5}\\d\\W{0,5}\\d\\W{0,5}\\d\\W{0,5}\\d(?!(\\W?\\d))";
        String string = "0545454545 09-890-234-456 093 9808654 -380915678901 +4 8095 345 6789 093-888 90 80  0918909090 +38 093 2345678 (093)2345678 +38 (098) 345 45 45 +38 (071) 323 45 67";
        String subst = "";

        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(string);
        String result = matcher.replaceAll(subst);

        System.out.println("Substitution result: " + result);

    }
}
