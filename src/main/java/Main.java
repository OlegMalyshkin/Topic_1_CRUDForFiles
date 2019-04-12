import entity.City;
import utils.DataFromFile;
import utils.DataToFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main( String[] args ) {

        List<City> cities = new ArrayList<City>(  );
        cities.add(new City( "Kyiv", "Ukraine", 	847.66, 179,2950533L));
        cities.add(new City( "New York", "USA", 	1213.37, 10,8175133L));

        DataToFile dataToFile = new DataToFile();
        dataToFile.toJson( "D:\\Documents\\IdeaProjects_Data", cities );
        dataToFile.toXML( "D:\\Documents\\IdeaProjects_Data", cities );
        dataToFile.toCSV( "D:\\Documents\\IdeaProjects_Data", cities );
        dataToFile.toExcel( "D:\\Documents\\IdeaProjects_Data", cities );
        dataToFile.toPDF( "D:\\Documents\\IdeaProjects_Data", cities );

        DataFromFile dataFromFile = new DataFromFile();
        System.out.println(dataFromFile.getCityList( "D:\\Documents\\IdeaProjects_Data\\cities.json" ));
        System.out.println(dataFromFile.getCityList( "D:\\Documents\\IdeaProjects_Data\\cities.xml"  ));
        System.out.println(dataFromFile.getCityList( "D:\\Documents\\IdeaProjects_Data\\cities.xls"  ));
    }

}
