package utils;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import entity.City;
import entity.Country;
import entity.CustomCountry;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class PrepareDataForCountry {

    private static Logger log = Logger.getLogger( PrepareDataForCountry.class );
    private static final String URL = "https://restcountries.eu/rest/v2/callingcode/";
    private static final String FILE_NAME = "countries";
    private static final Integer NUMBER_OF_COUNTRY = 50;

    public void createCountryFileFromCallingCodeFile(String filePathInput, String filePathOutput){
        List<Integer> randomCallingCodeList = getRandomCallingCodeListfromExcel( filePathInput );
        List<Country> countryList = getCountryDataFromRandomList(randomCallingCodeList);
        createCountryFile( filePathOutput, countryList );
    }

    private List<Integer> getRandomCallingCodeListfromExcel( String pathFile ) {
        List<Integer> indexList = null;
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook( new File( pathFile ) );
            indexList = getDataFromExcel( workbook );
            workbook.close();
            return indexList;
        } catch ( IOException | BiffException e ) {
            log.error( e );
            throw new RuntimeException(  );
        }
    }

    private List<Integer> getDataFromExcel(Workbook workbook ) {
        List<Integer> indexList = new ArrayList<>();
        Sheet sheet = workbook.getSheet( 0 );
        int rowsCount = sheet.getRows();
        for ( int i = 1; i < rowsCount; i++ ) {
            indexList.add( Integer.parseInt( sheet.getCell( 2, i ).getContents()));
        }
        return indexList;
    }

    private List<Country> getCountryDataFromRandomList(List<Integer> indexList){
        List<Country> countryList = new ArrayList<>(  );
        Gson gson = new Gson();
        Scanner in = null;
        URL url = null;
        Collections.shuffle(indexList);
        for ( int i = 0; i < NUMBER_OF_COUNTRY; i++ ) {
            try {
                url = new URL( URL + indexList.get( i ) );
                in = new Scanner( (InputStream) url.getContent() );
                Country[] country = gson.fromJson( in.nextLine(), Country[].class );
                countryList.add( country[0] );
                log.info( i + " " +  url );
            } catch ( IOException e ) {
                log.error( e );
            }
        }
        return countryList;
    }

    private void createCountryFile( String filePath, List<Country> countryList )  {
        WritableWorkbook writableWorkbook = null;
        try {
            writableWorkbook = Workbook.createWorkbook( new File( filePath + File.separator + FILE_NAME + ".xls" ) );
            writableWorkbook = prepareDataForExcelFile( writableWorkbook, countryList );
            writableWorkbook.write();
            log.info( "File " + FILE_NAME + ".xls in folder " + filePath + " was created" );
        } catch ( IOException | WriteException e ) {
            log.error( e );
        } finally {
            try {
                writableWorkbook.close();
            } catch ( IOException | WriteException e ) {
                log.error( e );
            }
        }
    }

    private WritableWorkbook prepareDataForExcelFile( WritableWorkbook writableWorkbook, List<Country> countryList)
            throws WriteException {
        WritableSheet writableSheet = prepareExcelFile( writableWorkbook );
        int i = 1;
            for ( Country country : countryList ) {
                writableSheet.addCell( new Label( 0, i, country.getNumericCode() ) );
                writableSheet.addCell( new Label( 1, i, country.getName() ) );
                writableSheet.addCell( new Label( 2, i, country.getCapital() ) );
                writableSheet.addCell( new Number( 3, i, country.getArea() ) );
                writableSheet.addCell( new Number( 4, i, country.getPopulation() ) );
                writableSheet.addCell( new Label( 5, i, country.getRegion() ) );
                i++;
            }
        return writableWorkbook;
    }

    private WritableSheet prepareExcelFile(WritableWorkbook writableWorkbook)
            throws WriteException {
        WritableSheet writableSheet = writableWorkbook.createSheet( "Country", 0 );
        writableSheet.addCell( new Label( 0, 0, "Code" ) );
        writableSheet.addCell( new Label( 1, 0, "Country" ) );
        writableSheet.addCell( new Label( 2, 0, "Capital" ) );
        writableSheet.addCell( new Label( 3, 0, "Area" ) );
        writableSheet.addCell( new Label( 4, 0, "Population" ) );
        writableSheet.addCell( new Label( 5, 0, "Region" ) );
        return writableSheet;
    }

    public List<CustomCountry> fromExcel( String pathFile ) {
        List<CustomCountry> countryList = null;
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook( new File( pathFile ) );
            countryList = getCountryListFromExcel( workbook );
            workbook.close();
            return countryList;
        } catch ( IOException | BiffException e ) {
            log.error( e );
            throw new RuntimeException(  );
        }
    }


    private List<CustomCountry> getCountryListFromExcel( Workbook workbook ) {
        List<CustomCountry> countryList = new ArrayList<>();
        Sheet sheet = workbook.getSheet( 0 );
        int rowsCount = sheet.getRows();
        for ( int i = 1; i < rowsCount; i++ ) {
            countryList.add( new CustomCountry(
                    Integer.parseInt( sheet.getCell( 0, i ).getContents() ),
                    sheet.getCell( 1, i ).getContents(),
                    sheet.getCell( 2, i ).getContents(),
                    Double.parseDouble( sheet.getCell( 3, i ).getContents() ),
                    Integer.parseInt( sheet.getCell( 4, i ).getContents() ),
                    sheet.getCell( 5, i ).getContents()
            ) );
        }
        return countryList;
    }

}
