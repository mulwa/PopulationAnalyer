package cj_server.com.opendata.Pojo;

/**
 * Created by cj-sever on 1/30/17.
 */
public class Data {
    private String district;
    private String category;
    private int  no_of_male;
    private int  no_of_female;
    private int total_population;
    private String county;
    private String location;

    public Data(String district, String category, int no_of_male, int no_of_female, int total_population, String county, String location) {
        this.district = district;
        this.category = category;
        this.no_of_male = no_of_male;
        this.no_of_female = no_of_female;
        this.total_population = total_population;
        this.county = county;
        this.location = location;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setNo_of_male(int no_of_male) {
        this.no_of_male = no_of_male;
    }

    public void setNo_of_female(int no_of_female) {
        this.no_of_female = no_of_female;
    }

    public void setTotal_population(int total_population) {
        this.total_population = total_population;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDistrict() {
        return district;
    }

    public String getCategory() {
        return category;
    }

    public int getNo_of_male() {
        return no_of_male;
    }

    public int getNo_of_female() {
        return no_of_female;
    }

    public int getTotal_population() {
        return total_population;
    }

    public String getCounty() {
        return county;
    }

    public String getLocation() {
        return location;
    }
}
