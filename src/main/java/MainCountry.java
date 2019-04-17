import entity.CustomCountry;
import utils.CountryUtil;
import utils.PrepareDataForCountry;

import java.util.List;

public class MainCountry {

    public static void main( String[] args ) {

        PrepareDataForCountry prepareDataForCountry = new PrepareDataForCountry();
//        prepareDataForCountry.createCountryFileFromCallingCodeFile("D:\\Documents\\IdeaProjects_Data\\CallingCodeList.xls", "D:\\Documents\\IdeaProjects_Data" );
        List<CustomCountry> countryList = prepareDataForCountry.fromExcel( "D:\\Documents\\IdeaProjects_Data\\countries.xls" );
        CountryUtil countryUtil = new CountryUtil();
        System.out.println( countryUtil.findNCoutriesWithMaxPopulation( countryList, 5 ) );
        List<CustomCountry> countryListMaxPopulation = countryUtil.findNCoutriesWithMaxPopulation( countryList, 5 );
        List<List<CustomCountry>> countriesInOneRegionList = countryUtil.findCountriesInOneRegion( countryListMaxPopulation );
        List<CustomCountry> countryListMaxAreainOneRegion = countryUtil.findTheBiggestCountryInOneRegion( countriesInOneRegionList );
        System.out.println( countryListMaxAreainOneRegion );
        countryUtil.toPDF( "D:\\Documents\\IdeaProjects_Data", countryListMaxAreainOneRegion );
    }

}
