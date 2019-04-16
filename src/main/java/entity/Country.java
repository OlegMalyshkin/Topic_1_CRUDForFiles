package entity;

import java.util.List;

public class Country {

    protected String name;

    private List<String> topLevelDomain;

    protected String alpha2Code;

    private String alpha3Code;

    private List<String> callingCodes;

    protected String capital;

    private List<String> altSpellings;

    protected String region;

    protected String subregion;

    protected Integer population;

    private List<Double> latlng;

    private String demonym;

    private Double area;

    protected Double gini;

    private List<String> timezones;

    protected List<String> borders;

    protected String nativeName;

    private String numericCode;

    public String getName() {
        return name;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public List<String> getAltSpellings() {
        return altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public Integer getPopulation() {
        return population;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public Double getArea() {
        return area;
    }

    public Double getGini() {
        return gini;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public List<String> getBorders() {
        return borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", topLevelDomain=" + topLevelDomain +
                ", alpha2Code='" + alpha2Code + '\'' +
                ", alpha3Code='" + alpha3Code + '\'' +
                ", callingCodes=" + callingCodes +
                ", capital='" + capital + '\'' +
                ", altSpellings=" + altSpellings +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", population=" + population +
                ", latlng=" + latlng +
                ", demonym='" + demonym + '\'' +
                ", area=" + area +
                ", gini=" + gini +
                ", timezones=" + timezones +
                ", borders=" + borders +
                ", nativeName='" + nativeName + '\'' +
                ", numericCode='" + numericCode + '\'' +
                '}';
    }
}