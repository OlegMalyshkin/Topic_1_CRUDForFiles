import entity.City;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.DataFromFile;
import utils.DataToFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class ReadFromFileTestCase {

    private static final String FILE_PATH = "D:\\Documents\\IdeaProjects_Data";
    private static final String FILE_NAME = "cities";

    @BeforeTest
    public void prepareData(){
        DataToFile dataToFile = new DataToFile();
        List<City> cities = prepareListForWrite();
        dataToFile.toJson( FILE_PATH, cities );
        dataToFile.toXML( FILE_PATH, cities );
        dataToFile.toExcel( FILE_PATH, cities );
    }

    @Test
    public void checkReadDataFromJson(){
        assertEquals( prepareListForWrite(), new DataFromFile().getCityList( FILE_PATH + File.separator + FILE_NAME + ".json" ));
    }

    @Test
    public void checkReadDataFromXML(){
        assertEquals( prepareListForWrite(), new DataFromFile().getCityList( FILE_PATH + File.separator + FILE_NAME + ".xml" ));
    }

    @Test
    public void checkReadDataFromExcel(){
        assertEquals( prepareListForWrite(), new DataFromFile().getCityList( FILE_PATH + File.separator + FILE_NAME + ".xls" ));
    }


    @Test
    public void checkReadDataFromFileWithoutExtension(){
        assertNull( new DataFromFile().getCityList( FILE_PATH + File.separator + FILE_NAME ));
    }

    @Test
    public void checkReadDataFromFileWithDot(){
        assertNull( new DataFromFile().getCityList( FILE_PATH + File.separator + FILE_NAME + "." ));
    }

    @Test
    public void checkReadDataFromEmptyFilePath(){
        assertNull( new DataFromFile().getCityList( " " ));
    }

    @Test
    public void checkReadDataFromNullFilePath(){
        assertNull( new DataFromFile().getCityList( null ));
    }

    @Test
    public void checkReadDataFromFilePathWithWrongExtension(){
        assertNull( new DataFromFile().getCityList( FILE_PATH + File.separator + FILE_NAME + ".doc" ));
    }

    @AfterTest
    public void deleteData(){
        String[] files = { "cities.json", "cities.xml", "cities.xls"  };
        File file;
        for(int i = 0; i < 3; i++){
            file = new File(FILE_PATH + File.separator + files[i] );
            file.delete();
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
