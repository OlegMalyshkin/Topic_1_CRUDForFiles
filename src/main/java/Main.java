import entity.City;
import entity.Country;
import utils.DataFromFile;
import utils.DataToFile;
import utils.PrepareDataForCountry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main( String[] args ) {

        PrepareDataForCountry prepareDataForCountry = new PrepareDataForCountry();
        prepareDataForCountry.createCountryFileFromCallingCodeFile("D:\\Documents\\IdeaProjects_Data\\CallingCodeList.xls", "D:\\Documents\\IdeaProjects_Data" );

//        List<City> cities = new ArrayList<City>(  );
//        cities.add(new City( "Kyiv", "Ukraine", 	847.66, 179,2950533L));
//        cities.add(new City( "Київ", "Україна", 	847.66, 179,2950533L));
//        cities.add(new City( "中國", "中國", 	2188.67, 40,13839910L));
//        cities.add(new City( "New York", "USA", 	1213.37, 10,8175133L));
//        cities.add(new City( "Токио", "Япония", 	2188.67, 40,13839910L));
//
//        DataToFile dataToFile = new DataToFile();
//        dataToFile.toJson( "D:\\Documents\\IdeaProjects_Data", cities );
//        dataToFile.toXML( "D:\\Documents\\IdeaProjects_Data", cities );
//        dataToFile.toCSV( "D:\\Documents\\IdeaProjects_Data", cities );
//        dataToFile.toExcel( "D:\\Documents\\IdeaProjects_Data", cities );
//        dataToFile.toPDF( "D:\\Documents\\IdeaProjects_Data", cities );
//
//        DataFromFile dataFromFile = new DataFromFile();
//        System.out.println("cities.json => " + dataFromFile.getCityList( "D:\\Documents\\IdeaProjects_Data\\cities.json" ));
//        System.out.println("cities.xml => " + dataFromFile.getCityList( "D:\\Documents\\IdeaProjects_Data\\cities.xml"  ));
//        System.out.println("cities.xls => " + dataFromFile.getCityList( "D:\\Documents\\IdeaProjects_Data\\cities.xls"  ));
    }

}
