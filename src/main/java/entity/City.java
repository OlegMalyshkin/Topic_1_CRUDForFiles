package entity;

public class City {

    private String name;
    private String country;
    private Double area;
    private Integer elevation;
    private Long population;

    public City(){}

    public City(
            String name,
            String country,
            Double area,
            Integer elevation,
            Long population
    ) {
        this.name = name;
        this.country = country;
        this.area = area;
        this.elevation = elevation;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry( String country ) {
        this.country = country;
    }

    public Double getArea() {
        return area;
    }

    public void setArea( Double area ) {
        this.area = area;
    }

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation( Integer elevation ) {
        this.elevation = elevation;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation( Long population ) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", area=" + area +
                ", elevation=" + elevation +
                ", population=" + population +
                '}';
    }
}
