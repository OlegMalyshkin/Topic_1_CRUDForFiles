package entity;

import java.util.List;

public class CustomCountry {

    private Integer numericCode;
    private String name;
    private String capital;
    private Double area;
    private Integer population;
    private String region;

    public CustomCountry(
            Integer numericCode,
            String name,
            String capital,
            Double area,
            Integer population,
            String region
    ) {
        this.numericCode = numericCode;
        this.name = name;
        this.capital = capital;
        this.area = area;
        this.population = population;
        this.region = region;
    }

    public Integer getNumericCode() {
        return numericCode;
    }

    public void setNumericCode( Integer numericCode ) {
        this.numericCode = numericCode;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital( String capital ) {
        this.capital = capital;
    }

    public Double getArea() {
        return area;
    }

    public void setArea( Double area ) {
        this.area = area;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation( Integer population ) {
        this.population = population;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion( String region ) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "CustomCountry{" +
                "numericCode=" + numericCode +
                ", name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", area=" + area +
                ", population=" + population +
                ", region='" + region + '\'' +
                '}';
    }
}
