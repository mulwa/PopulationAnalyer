package cj_server.com.opendata.Pojo;

/**
 * Created by cj-sever on 2/6/17.
 */
public class Animal{
    private int Camels;

    private int Cattle;

    private String County;

    private int Bee_Hives;

    private int Goats;

    private String District;

    private int Sheep;

    private int Indigenous_Chicken;

    private int Chicken_Commercial;

    private String Province;

    private int Pigs;

    private int Donkeys;

    public Animal(int camels, int cattle, String county, int bee_Hives,
                  int goats, String district, int sheep, int indigenous_Chicken,
                  int chicken_Commercial, String province, int pigs, int donkeys) {
        Camels = camels;
        Cattle = cattle;
        County = county;
        Bee_Hives = bee_Hives;
        Goats = goats;
        District = district;
        Sheep = sheep;
        Indigenous_Chicken = indigenous_Chicken;
        Chicken_Commercial = chicken_Commercial;
        Province = province;
        Pigs = pigs;
        Donkeys = donkeys;
    }

    public int getCamels ()
    {
        return Camels;
    }

    public void setCamels (int Camels)
    {
        this.Camels = Camels;
    }

    public int getCattle ()
    {
        return Cattle;
    }

    public void setCattle (int Cattle)
    {
        this.Cattle = Cattle;
    }

    public String getCounty ()
    {
        return County;
    }

    public void setCounty (String County)
    {
        this.County = County;
    }

    public int getBee_Hives ()
    {
        return Bee_Hives;
    }

    public void setBee_Hives (int Bee_Hives)
    {
        this.Bee_Hives = Bee_Hives;
    }

    public int getGoats ()
    {
        return Goats;
    }

    public void setGoats (int Goats)
    {
        this.Goats = Goats;
    }

    public String getDistrict ()
    {
        return District;
    }

    public void setDistrict (String District)
    {
        this.District = District;
    }

    public int getSheep ()
    {
        return Sheep;
    }

    public void setSheep (int Sheep)
    {
        this.Sheep = Sheep;
    }

    public int getIndigenous_Chicken ()
    {
        return Indigenous_Chicken;
    }

    public void setIndigenous_Chicken (int Indigenous_Chicken)
    {
        this.Indigenous_Chicken = Indigenous_Chicken;
    }

    public int getChicken_Commercial ()
    {
        return Chicken_Commercial;
    }

    public void setChicken_Commercial (int Chicken_Commercial)
    {
        this.Chicken_Commercial = Chicken_Commercial;
    }

    public String getProvince ()
    {
        return Province;
    }

    public void setProvince (String Province)
    {
        this.Province = Province;
    }

    public int getPigs ()
    {
        return Pigs;
    }

    public void setPigs (int Pigs)
    {
        this.Pigs = Pigs;
    }

    public int getDonkeys ()
    {
        return Donkeys;
    }

    public void setDonkeys (int Donkeys)
    {
        this.Donkeys = Donkeys;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Camels = "+Camels+", Cattle = "+Cattle+", County = "+County+", Bee_Hives = "+Bee_Hives+", Goats = "+Goats+", District = "+District+", Sheep = "+Sheep+", Indigenous_Chicken = "+Indigenous_Chicken+", Chicken_Commercial = "+Chicken_Commercial+", Province = "+Province+", Pigs = "+Pigs+", Donkeys = "+Donkeys+"]";
    }
}



