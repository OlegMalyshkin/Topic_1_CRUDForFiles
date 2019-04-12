package utils;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import entity.City;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataToFile {

    private final String COMMA_DELIMITER = ",";
    private final String NEW_LINE_SEPARATOR = "\n";
    private final String FILE_HEADER = "name,country,area,elevation,population";

    public void toXML( String filePath, List<City> cities ) {
        writeToFile(  filePath + File.separator + "cities.xml",
                      new XStream().toXML( cities ) );
    }

    public void toJson( String filePath, List<City> cities )  {
        writeToFile(  filePath + File.separator + "cities.json",
                      new Gson().toJson( cities ) );
    }

    public void toCSV( String filePath, List<City> cities ) {
        writeToFile( filePath + File.separator + "cities.csv",
                     prepareDataForToCSV( cities ) );

    }

    public void toExcel( String filePath, List<City> cities )  {
        try {
            WritableWorkbook  writableWorkbook = Workbook.createWorkbook( new File( filePath + File.separator + "cities.xls" ) );
            prepareDataForToExcel( writableWorkbook, cities );
            writableWorkbook.write();
            writableWorkbook.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        } catch ( WriteException e ) {
            e.printStackTrace();
        }
    }

    private void writeToFile( String filePath, String content) {
        try {
            FileWriter writer = new FileWriter( filePath );
            writer.write( content );
            writer.flush();
            writer.close();
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }
    }

    private String prepareDataForToCSV(List<City> cities){
        StringBuilder sb = new StringBuilder( FILE_HEADER );
        for ( City c : cities ) {
            sb.append( NEW_LINE_SEPARATOR );
            sb.append( c.getName() );
            sb.append( COMMA_DELIMITER );
            sb.append( c.getCountry() );
            sb.append( COMMA_DELIMITER );
            sb.append( c.getArea().toString() );
            sb.append( COMMA_DELIMITER );
            sb.append( c.getElevation().toString() );
            sb.append( COMMA_DELIMITER );
            sb.append( c.getPopulation().toString() );
        }
        return sb.toString();
    }

    private boolean prepareDataForToExcel(WritableWorkbook writableWorkbook, List<City> cities)
            throws WriteException, IOException {
        int i = 1;
        WritableSheet writableSheet = writableWorkbook.createSheet( "City", 0 );
        writableSheet.addCell( new Label( 0, 0, "Name" ));
        writableSheet.addCell( new Label( 1, 0, "Country" ));
        writableSheet.addCell( new Label( 2, 0, "Area" ));
        writableSheet.addCell( new Label( 3, 0, "Elevation" ));
        writableSheet.addCell( new Label( 4, 0, "Population" ));
        for(City c : cities){
            writableSheet.addCell( new Label( 0, i, c.getName()) );
            writableSheet.addCell( new Label( 1, i, c.getCountry()) );
            writableSheet.addCell( new Number( 2, i, c.getArea()) );
            writableSheet.addCell( new Number( 3, i, c.getElevation()) );
            writableSheet.addCell( new Number( 4, i, c.getPopulation()) );
            i++;
        }
        return true;
    }

}
