package utils;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entity.CustomCountry;
import exceptions.CountryNotFoundException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.Collectors;

public class CountryUtil {

    private static final String FILE_NAME = "CountryWithTheLarggestAreainOneRegion";
    private static Logger log = Logger.getLogger( CountryUtil.class );

    public List<CustomCountry> findNCoutriesWithMaxPopulation( List<CustomCountry> countryList, int n ) {
        return countryList.stream()
                          .sorted( Comparator.comparing( CustomCountry::getPopulation ).reversed() )
                          .limit( n )
                          .collect( Collectors.toList() );
    }

    public List<List<CustomCountry>> findCountriesInOneRegion( List<CustomCountry> countryList ) {
        Set<String> regionSet = new HashSet<>();
        for ( CustomCountry country : countryList ) {
            regionSet.add( country.getRegion() );
        }
        List regionList = new ArrayList( regionSet );
        List<List<CustomCountry>> coutryListsByRegion = new ArrayList<>();
        for ( int i = 0; i < regionList.size(); i++ ) {
            List<CustomCountry> currentRegionList = new ArrayList<>();
            for ( CustomCountry country : countryList ) {
                if ( country.getRegion().equals( regionList.get( i ) ) ) {
                    currentRegionList.add( country );
                }
            }
            coutryListsByRegion.add( i, currentRegionList );
        }
        return coutryListsByRegion;
    }

    public List<CustomCountry> findTheBiggestCountryInOneRegion( List<List<CustomCountry>> countryLists ) {
        List<CustomCountry> countryListmaxArea = new ArrayList<>(  );
        for ( int i = 0; i < countryLists.size(); i++ ) {
            countryListmaxArea.add( countryLists.get( i ).stream().max( Comparator.comparing( CustomCountry::getArea ) ).get());
        }
        return countryListmaxArea;
    }

    public String toPDF( String filePath, List<CustomCountry> ountryList ) {
        Document document = null;
        try {
            document = new Document();
            PdfWriter.getInstance( document, new FileOutputStream( filePath + File.separator + FILE_NAME + ".pdf"));
            document.open();
            for(CustomCountry country : ountryList) {
                document.add( new Paragraph(  ));
                document.add( new Chunk( country.toString() ));
                document.add( new Paragraph(  ));
            }
            log.info( "File cities.pdf in folder " + filePath + " was created" );
        } catch ( DocumentException | FileNotFoundException e ) {
            log.error( e );
        } finally {
            document.close();
        }
        return filePath + File.separator + FILE_NAME + ".pdf";
    }

    public CustomCountry findFirstWithNameWhichIsStartedOnLetter(String letter, List<CustomCountry> countryList){
        return countryList.stream().filter( customCountry -> customCountry.getName().substring( 0,1 ).equals( letter ) ).findFirst().orElseThrow( () -> new CountryNotFoundException(  ) );
    }

    public Set<String> getUniqueRegion(List<CustomCountry> countryList){
        Set<String> regionSet = new HashSet<>(  );
        countryList.stream().forEach( country -> regionSet.add( country.getRegion() ) );
        return regionSet;
    }

    public Map<String, List<CustomCountry>> getMapFromCountryList(List<CustomCountry> countryList){
        Map<String, List<CustomCountry>> listMap = new HashMap<>(  );
        List<String> countryKeyList = new ArrayList<>( getUniqueRegion( countryList ) );
        for ( int i = 0; i < countryKeyList.size(); i++ ) {
            List<CustomCountry> currentListForMap = new ArrayList<>();
            for ( CustomCountry country : countryList ) {
                if ( country.getRegion().equals( countryKeyList.get( i ) ) ) {
                    currentListForMap.add( country );
                }
            }
            listMap.put( countryKeyList.get( i ), currentListForMap );
        }
        return listMap;
    }

}
