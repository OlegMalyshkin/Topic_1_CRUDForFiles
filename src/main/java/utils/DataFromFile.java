package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import entity.City;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFromFile {

    private static Logger log = Logger.getLogger( DataFromFile.class );

    public List<City> getCityList( String filePath ) {
        String fileExtension = FilenameUtils.getExtension( filePath );
        switch ( fileExtension ) {
            case "json":
                return fromJson( filePath );
            case "xml":
                return fromXML( filePath );
            case "xls":
                return fromExcel( filePath );
            default:
                throw new RuntimeException("Wrong extension or file doesn't exist");
        }
    }

    private List<City> fromJson( String pathFile ) {
        Gson gson = new Gson();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader( new FileReader( pathFile ) );
            City[] citiesArray = gson.fromJson( reader, City[].class );
            return new ArrayList<>( Arrays.asList( citiesArray ) );
        } catch ( FileNotFoundException e ) {
            log.error( e );
        } finally {
            try {
                reader.close();
            } catch ( IOException e ) {
                log.error( e );
            }
        }
        return null;
    }

    private List<City> fromExcel( String pathFile ) {
        try {
            Workbook workbook = Workbook.getWorkbook( new File( pathFile ) );
            List<City> cities = getDataFromExcel( workbook );
            workbook.close();
            return cities;
        } catch ( IOException | BiffException e ) {
            log.error( e );
            throw new RuntimeException(  );
        }
    }

    private List<City> fromXML(String pathFile) {
        XmlMapper objectMapper = new XmlMapper(  );
        try {
            String xml = inputStreamToString( new FileInputStream( pathFile ) );
            List<City> cities = objectMapper.readValue( xml, new TypeReference<List<City>>() {} );
            return cities;
        } catch ( IOException e ) {
            log.error( e );
            throw new RuntimeException(  );
        }
    }

    private String inputStreamToString( InputStream is ) throws IOException {
        StringBuilder builder = new StringBuilder();
        String line;
        BufferedReader reader = new BufferedReader( new InputStreamReader( is ) );
        while ( ( line = reader.readLine() ) != null ) {
            builder.append( line );
        }
        reader.close();
        return builder.toString();
    }

    private List<City> getDataFromExcel( Workbook workbook ) {
        List<City> cities = new ArrayList<>();
        Sheet sheet = workbook.getSheet( 0 );
        int rowsCount = sheet.getRows();
        for ( int i = 1; i < rowsCount; i++ ) {
            cities.add( new City(
                    sheet.getCell( 0, i ).getContents(),
                    sheet.getCell( 1, i ).getContents(),
                    Double.parseDouble( sheet.getCell( 2, i ).getContents() ),
                    Integer.parseInt( sheet.getCell( 3, i ).getContents() ),
                    Long.parseLong( sheet.getCell( 4, i ).getContents() )
            ) );
        }
        return cities;
    }

}
