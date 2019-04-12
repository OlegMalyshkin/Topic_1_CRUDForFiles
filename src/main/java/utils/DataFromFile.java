package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import entity.City;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.read.biff.SheetImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFromFile {

    public List<City> getCityList(String filePath) {
        if( filePath != null || !filePath.equals( "" ) ) {
            String fileExtension = getFileExtension( filePath );
            if ( fileExtension != null ) {
                switch ( fileExtension ) {
                    case "json":
                        return fromJson( filePath );
                    case "xml":
                        return fromXML( filePath );
                    case "xls":
                        return fromExcel( filePath );
                    default:
                        return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private List<City> fromJson(String pathFile) {
        Gson gson = new Gson();
        BufferedReader br = null;
        try {
            br = new BufferedReader( new FileReader( pathFile));
            City[] citiesArray = gson.fromJson( br, City[].class );
            return new ArrayList<>( Arrays.asList( citiesArray ) );
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private List<City> fromExcel(String pathFile){
        List<City> cities = null;
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook( new File( pathFile ) );
            cities = getDataFromExcel( workbook );
            workbook.close();
            return cities;
        } catch ( IOException e ) {
            e.printStackTrace();
        } catch ( BiffException e ) {
            e.printStackTrace();
        }
        return  null;
    }

    private List<City> fromXML(String pathFile) {
        XmlMapper objectMapper = new XmlMapper(  );
        try {
            String xml = inputStreamToString( new FileInputStream( pathFile ) );
            List<City> cities = objectMapper.readValue( xml, new TypeReference<List<City>>() {} );
            return cities;
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return null;
    }

    private String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    private List<City> getDataFromExcel(Workbook workbook){
        List<City> cities = new ArrayList<>(  );
        Sheet sheet = workbook.getSheet( 0 );
        int rowsCount = sheet.getRows();
        for(int i = 1; i < rowsCount; i++ ) {
            cities.add(new City(
                    sheet.getCell( 0, i ).getContents(),
                    sheet.getCell( 1, i ).getContents(),
                    Double.parseDouble( sheet.getCell( 2, i ).getContents() ),
                    Integer.parseInt( sheet.getCell( 3, i ).getContents() ),
                    Long.parseLong( sheet.getCell( 4, i ).getContents() )
            ));
        }
        return cities;
    }

    private String getFileExtension(String filePath) {
        if(filePath.lastIndexOf(".") != -1 && filePath.lastIndexOf(".") != 0) {
            return filePath.substring( filePath.lastIndexOf( "." ) + 1 );
        } else {
            return null;
        }
    }

}
