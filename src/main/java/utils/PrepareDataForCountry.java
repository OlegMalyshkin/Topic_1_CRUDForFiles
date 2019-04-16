package utils;

import com.google.gson.Gson;
import entity.City;
import entity.Country;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
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

    public List<Integer> fromExcel( String pathFile ) {
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

    public void createCountriesJsonFile(String filePath, List<Integer> indexList){
        Scanner in = null;
        URL url = null;
        Collections.shuffle(indexList);
        int stepCounter = 0;
        for(Integer index : indexList) {
            if(stepCounter > 50) {
                try {
                    url = new URL(URL + index);
                    in = new Scanner((InputStream) url.getContent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String result = "";
                while (in.hasNext()) {
                    result += in.nextLine();
                }
                toJson(filePath, result);
            } else {
                break;
            }
        }
//        System.out.println(result);
//        Gson gson = new Gson();
//        String s = gson.toJson(result);
//        Country[] country = gson.fromJson(result, Country[].class);
//        System.out.println(Arrays.toString(country));
    }

    public void toJson( String filePath, String jsonCoutry )  {
        writeToFile(  filePath + File.separator + FILE_NAME + ".json",
                new Gson().toJson( jsonCoutry ) );
        log.info( "File " + FILE_NAME + ".json in folder " + filePath + " was created" );
    }

    private void writeToFile( String filePath, String content) {
        FileWriter writer = null;
        try {
            writer = new FileWriter( filePath, true );
            writer.write( content );
        } catch ( IOException e ) {
            log.error( e );
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch ( IOException e ) {
                log.error( e );
            }
        }
    }

}
