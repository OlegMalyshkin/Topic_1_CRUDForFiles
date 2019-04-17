import entity.CustomCountry;
import utils.CountryUtil;
import utils.PrepareDataForCountry;

import java.util.List;

public class MainCountry {

    public static void main( String[] args ) {

        PrepareDataForCountry prepareDataForCountry = new PrepareDataForCountry();
        prepareDataForCountry.createCountryFileFromCallingCodeFile("D:\\Documents\\IdeaProjects_Data\\CallingCodeList.xls", "D:\\Documents\\IdeaProjects_Data" );
        List<CustomCountry> countryList = prepareDataForCountry.fromExcel( "D:\\Documents\\IdeaProjects_Data\\countries.xls" );

        CountryUtil countryUtil = new CountryUtil();
        List<CustomCountry> countryListMaxPopulation = countryUtil.findNCoutriesWithMaxPopulation( countryList, 5 );
        List<List<CustomCountry>> countriesInOneRegionList = countryUtil.findCountriesInOneRegion( countryListMaxPopulation );
        List<CustomCountry> countryListMaxAreainOneRegion = countryUtil.findTheBiggestCountryInOneRegion( countriesInOneRegionList );
        countryUtil.toPDF( "D:\\Documents\\IdeaProjects_Data", countryListMaxAreainOneRegion );
        System.out.println(countryUtil.findFirstWithNameWhichIsStartedOnLetter( "A" ,countryList));
        System.out.println(countryUtil.getUniqueRegion(countryList));
        countryUtil.getMapFromCountryList(countryList);
        countryUtil.getMapFromCountryList( countryList ).entrySet().stream().forEach( System.out::println );

    }

}
