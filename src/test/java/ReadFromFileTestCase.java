import entity.City;
import listeners.LogListener;
import org.apache.log4j.Logger;
import org.testng.annotations.*;
import utils.DataFromFile;
import utils.DataToFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ReadFromFileTestCase {

    private static final String FILE_NAME = "cities";
    private static Logger log = Logger.getLogger( ReadFromFileTestCase.class );

    @Parameters("filepath")
    @BeforeTest( alwaysRun = true )
    public void prepareData(String filepath){
        DataToFile dataToFile = new DataToFile();
        List<City> cities = prepareListForWrite();
        dataToFile.toJson( filepath, cities );
        dataToFile.toXML( filepath, cities );
        dataToFile.toExcel( filepath, cities );
    }

    @Parameters("filepath")
    @Test(groups = "positive" )
    public void checkReadDataFromJson(String filepath){
        assertEquals( prepareListForWrite(), new DataFromFile().getCityList( filepath + File.separator + FILE_NAME + ".json" ));
    }

    @Parameters("filepath")
    @Test(groups = "positive" )
    public void checkReadDataFromXML(String filepath){
        assertEquals( prepareListForWrite(), new DataFromFile().getCityList( filepath + File.separator + FILE_NAME + ".xml" ) );
    }

    @Parameters("filepath")
    @Test(groups = {"positive", "special"})
    public void checkReadDataFromExcel(String filepath){
        assertEquals( prepareListForWrite(), new DataFromFile().getCityList( filepath + File.separator + FILE_NAME + ".xls" ));
    }


    @Parameters("filepath")
    @Test(groups = "negative" )
    public void checkReadDataFromFileWithoutExtension(String filepath){
        try {
            new DataFromFile().getCityList( filepath + File.separator + FILE_NAME );
        } catch ( Exception e ){
            assertEquals( e.getMessage(), "Wrong extension or file doesn't exist" );
        }
    }

    @Parameters("filepath")
    @Test(groups = "negative" )
    public void checkReadDataFromFileWithDot(String filepath){
        try {
            new DataFromFile().getCityList( filepath + File.separator + FILE_NAME + "." );
        } catch ( Exception e ) {
            assertEquals( e.getMessage(), "Wrong extension or file doesn't exist" );
        }
    }

    @Test(groups = {"negative", "special"} )
    public void checkReadDataFromEmptyFilePath(){
        try {
            new DataFromFile().getCityList( " " );
        } catch ( Exception e ) {
            assertEquals( e.getMessage(), "Wrong extension or file doesn't exist" );
        }
    }

    @Test(groups = "negative" )
    public void checkReadDataFromNullFilePath(){
        try {
         new DataFromFile().getCityList( null );
        } catch ( Exception e ) {
            assertEquals( e.getMessage(), null );
        }
    }

    @Parameters("filepath")
    @Test(groups = "negative" )
    public void checkReadDataFromFilePathWithWrongExtension(String filepath){
        try{
            new DataFromFile().getCityList( filepath + File.separator + FILE_NAME + ".doc" );
        } catch ( Exception e ) {
            assertEquals( e.getMessage(), "Wrong extension or file doesn't exist" );
        }
    }

    @Parameters("filepath")
    @AfterTest( alwaysRun = true )
    public void deleteData(String filepath){
        String[] files = { "cities.json", "cities.xml", "cities.xls"  };
        File file;
        for(int i = 0; i < 3; i++){
            file = new File(filepath + File.separator + files[i] );
            file.delete();
            log.info( "File " + files[i] + " in folder " + filepath + " was deleted" );
        }
    }

    private List<City> prepareListForWrite(){
        List<City> cities = new ArrayList<>(  );
        cities.add(new City( "Kyiv", "Ukraine", 847.66, 179,2950533L));
        cities.add(new City( "Київ", "Україна", 847.66, 179,2950533L));
        cities.add(new City( "中國", "中國", 2188.67, 40,13839910L));
        cities.add(new City( "New York", "USA", 1213.37, 10,8175133L));
        cities.add(new City( "Токио", "Япония", 2188.67, 40,13839910L));
        return cities;
    }

}
