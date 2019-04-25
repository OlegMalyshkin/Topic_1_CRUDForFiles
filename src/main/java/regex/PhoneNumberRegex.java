package regex;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberRegex {
    public static void main(String args[]) {
        String input = "Oleg Malyshkin 096 43 18 120 \n" +
                "Oleg Malyshkin (097) 123 45 67\n" +
                "Oleg Malyshkin +38 (093) 123 45 67\n" +
                "Oleg Malyshkin 38 (098) 123 45 67\n" +
                "Oleg Malyshkin 38 095 123 45 67\n" +
                "Oleg Malyshkin +38 096 123 45 67\n" +
                "Oleg Malyshkin +38 099 12 34 567\n" +
                "Oleg Malyshkin +38 (096) 12 34 567";

        MobileOperatorRegex.getMobileOperatorList( input );

    }
}
