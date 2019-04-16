package utils;

import com.google.gson.Gson;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.thoughtworks.xstream.XStream;
import entity.City;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

public class DataToFile {

    private static Logger log = Logger.getLogger( DataToFile.class );

    private static final String FILE_NAME = "cities";
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "Name,Country,Area,Elevation,Population";

    public void toPDF( String filePath, List<City> cities ) {
        Document document = null;
        try {
            document = new Document();
            PdfWriter.getInstance( document, new FileOutputStream( filePath + File.separator + FILE_NAME + ".pdf"));
            document.open();
            for(City c : cities) {
                document.add( new Chunk( c.toString() ));
                document.add( new Paragraph(  ));
            }
            log.info( "File cities.pdf in folder " + filePath + " was created" );
        } catch ( DocumentException | FileNotFoundException e ) {
            log.error( e );
        } finally {
            document.close();
        }
    }

    public void toXML( String filePath, List<City> cities ) {
        writeToFile(  filePath + File.separator + FILE_NAME + ".xml",
                      new XStream().toXML( cities ) );
        log.info( "File " + FILE_NAME + ".xml in folder " + filePath + " was created" );
    }

    public void toJson( String filePath, List<City> cities )  {
        writeToFile(  filePath + File.separator + "cities.json",
                      new Gson().toJson( cities ) );
        log.info( "File " + FILE_NAME + ".json in folder " + filePath + " was created" );
    }

    public void toCSV( String filePath, List<City> cities ) {
        writeToFile( filePath + File.separator + "cities.csv",
                     prepareDataForToCSV( cities ) );
        log.info( "File " + FILE_NAME + ".csv in folder " + filePath + " was created" );

    }

    public void toExcel( String filePath, List<City> cities )  {
        WritableWorkbook  writableWorkbook = null;
        try {
            writableWorkbook = Workbook.createWorkbook( new File( filePath + File.separator + FILE_NAME + ".xls" ) );
            writableWorkbook = prepareDataForToExcel( writableWorkbook, cities );
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

    private void writeToFile( String filePath, String content) {
        FileWriter writer = null;
        try {
            writer = new FileWriter( filePath );
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

    private String prepareDataForToCSV(List<City> cities){
        StringBuilder builder = new StringBuilder( FILE_HEADER );
        for ( City city : cities ) {
            builder.append( NEW_LINE_SEPARATOR );
            builder.append( city.getName() );
            builder.append( COMMA_DELIMITER );
            builder.append( city.getCountry() );
            builder.append( COMMA_DELIMITER );
            builder.append( city.getArea().toString() );
            builder.append( COMMA_DELIMITER );
            builder.append( city.getElevation().toString() );
            builder.append( COMMA_DELIMITER );
            builder.append( city.getPopulation().toString() );
        }
        return builder.toString();
    }

    private WritableWorkbook prepareDataForToExcel(WritableWorkbook writableWorkbook, List<City> cities)
            throws WriteException {
        WritableSheet writableSheet = writableWorkbook.createSheet( "City", 0 );
        writableSheet.addCell( new Label( 0, 0, "Name" ));
        writableSheet.addCell( new Label( 1, 0, "Country" ));
        writableSheet.addCell( new Label( 2, 0, "Area" ));
        writableSheet.addCell( new Label( 3, 0, "Elevation" ));
        writableSheet.addCell( new Label( 4, 0, "Population" ));
        int i = 1;
        for(City city : cities){
            writableSheet.addCell( new Label( 0, i, city.getName()) );
            writableSheet.addCell( new Label( 1, i, city.getCountry()) );
            writableSheet.addCell( new Number( 2, i, city.getArea()) );
            writableSheet.addCell( new Number( 3, i, city.getElevation()) );
            writableSheet.addCell( new Number( 4, i, city.getPopulation()) );
            i++;
        }
        return writableWorkbook;
    }

}
