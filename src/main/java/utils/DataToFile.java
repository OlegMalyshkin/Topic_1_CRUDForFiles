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

    private static Logger LOG = Logger.getLogger( DataToFile.class );

    private final String COMMA_DELIMITER = ",";
    private final String NEW_LINE_SEPARATOR = "\n";
    private final String FILE_HEADER = "Name,Country,Area,Elevation,Population";

    public void toPDF( String filePath, List<City> cities ) {
        Document document = null;
        try {
            document = new Document();
            PdfWriter.getInstance( document, new FileOutputStream( filePath + File.separator + "cities.pdf"));
            document.open();
            for(City c : cities) {
                document.add( new Chunk( c.toString() ));
                document.add( new Paragraph(  ));
            }
            LOG.info( "File cities.pdf in folder " + filePath + " was created" );
        } catch ( DocumentException e ) {
            LOG.error( e );
        } catch ( FileNotFoundException e ) {
            LOG.error( e );
        } finally {
            document.close();
        }
    }

    public void toXML( String filePath, List<City> cities ) {
        writeToFile(  filePath + File.separator + "cities.xml",
                      new XStream().toXML( cities ) );
        LOG.info( "File cities.xml in folder " + filePath + " was created" );
    }

    public void toJson( String filePath, List<City> cities )  {
        writeToFile(  filePath + File.separator + "cities.json",
                      new Gson().toJson( cities ) );
        LOG.info( "File cities.json in folder " + filePath + " was created" );
    }

    public void toCSV( String filePath, List<City> cities ) {
        writeToFile( filePath + File.separator + "cities.csv",
                     prepareDataForToCSV( cities ) );
        LOG.info( "File cities.csv in folder " + filePath + " was created" );

    }

    public void toExcel( String filePath, List<City> cities )  {
        WritableWorkbook  writableWorkbook = null;
        try {
            writableWorkbook = Workbook.createWorkbook( new File( filePath + File.separator + "cities.xls" ) );
            prepareDataForToExcel( writableWorkbook, cities );
            writableWorkbook.write();
            LOG.info( "File cities.xls in folder " + filePath + " was created" );
        } catch ( IOException e ) {
            LOG.error( e );
        } catch ( WriteException e ) {
            LOG.error( e );
        } finally {
            try {
                writableWorkbook.close();
            } catch ( IOException e ) {
                LOG.error( e );
            } catch ( WriteException e ) {
                LOG.error( e );
            }
        }
    }

    private void writeToFile( String filePath, String content) {
        FileWriter writer = null;
        try {
            writer = new FileWriter( filePath );
            writer.write( content );
        } catch ( IOException e ) {
            LOG.error( e );
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch ( IOException e ) {
                LOG.error( e );
            }
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
            throws WriteException {
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
