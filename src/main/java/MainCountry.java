import entity.City;
import entity.CustomCountry;
import utils.DataFromFile;
import utils.DataToFile;
import utils.PrepareDataForCountry;

import java.util.ArrayList;
import java.util.List;

public class MainCountry {

    public static void main( String[] args ) {

        PrepareDataForCountry prepareDataForCountry = new PrepareDataForCountry();
//        prepareDataForCountry.createCountryFileFromCallingCodeFile("D:\\Documents\\IdeaProjects_Data\\CallingCodeList.xls", "D:\\Documents\\IdeaProjects_Data" );
        List<CustomCountry> countryList = prepareDataForCountry.fromExcel( "D:\\Documents\\IdeaProjects_Data\\countries.xls" );
        countryList.forEach(country -> System.out.println(country.toString()));
    }

}
