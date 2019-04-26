package regex;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberRegex {
    public static void main(String args[]) {
        String input = "Oleg Malyshkin 096 43 18 120 \n" +
                "Oleg Malyshkin (097) 965 45 03\n" +
                "Oleg Malyshkin +38 (093) 123 02 67\n" +
                "Oleg Malyshkin 38 (098) 569 45 67\n" +
                "Oleg Malyshkin 38 095 894 96 67\n" +
                "Oleg Malyshkin +38 096 023 45 99\n" +
                "Oleg Malyshkin +38 099 12 00 567\n" +
                "Oleg Malyshkin +38 (096) 12 34 567";

        MobileOperatorRegex.getMobileOperatorList( input );

    }
}
