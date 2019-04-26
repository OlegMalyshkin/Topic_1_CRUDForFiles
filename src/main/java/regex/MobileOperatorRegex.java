package regex;

import org.apache.log4j.Logger;
import utils.CountryUtil;

import javax.xml.transform.sax.SAXSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileOperatorRegex {

    private static Logger log = Logger.getLogger( "phoneRegex" );

    public static void getMobileOperatorList(String phoneListText) {
        HashMap<List<String>, String> mobileOperatorSet = new HashMap();
        List<String> codeListKyivstar = Arrays.asList( "039", "067", "068", "096", "097", "098" );
        List<String> codeListLifecell = Arrays.asList( "063", "093" );
        List<String> codeListMTC = Arrays.asList( "050", "066", "095", "099" );
        mobileOperatorSet.put( codeListKyivstar, "Kyivstar" );
        mobileOperatorSet.put( codeListLifecell, "Lifecell" );
        mobileOperatorSet.put( codeListMTC, "MTC" );
        String regex = "(?:\\ |W{0,0})(?:(?:((?:\\+?3?8\\s))?\\(?(0\\d\\d))?[^\\w(\\+]{0,2})?(\\d\\d\\W{0,1}\\d\\W{0,1}\\d\\W{0,1}\\d\\W{0,1}\\d\\d)";
        List<String> phoneList = Arrays.asList( phoneListText.split( "\n" ) );
        Pattern pattern = Pattern.compile( regex );
        Matcher matcher = pattern.matcher( phoneList.toString() );
        for ( String line : phoneList ) {
            if ( matcher.find() ) {
                mobileOperatorSet.forEach( ( keyList, value ) -> {
                    for ( String operatorCode : keyList ) {
                        if ( operatorCode.equals( matcher.group( 2 ) ) ) {
                            log.info( "Operator: '" + value + "' code: (" + operatorCode + ") phone number: " + matcher.group( 3 ) );
                        }
                    }
                } );
            }
        }
    }



}
