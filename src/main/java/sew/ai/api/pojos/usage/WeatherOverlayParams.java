package sew.ai.api.pojos.usage;

public class WeatherOverlayParams {
    private String WeatherType;
    private String WeatherFromDate;
    private String WeatherToDate;
    private String CityName;
    private String StateName;
    private String CountryName;

    public WeatherOverlayParams() {}

    public String getWeatherType() {
        return WeatherType;
    }

    public void setWeatherType(String weatherType) {
        WeatherType = weatherType;
    }

    public String getWeatherFromDate() {
        return WeatherFromDate;
    }

    public void setWeatherFromDate(String weatherFromDate) {
        WeatherFromDate = weatherFromDate;
    }

    public String getWeatherToDate() {
        return WeatherToDate;
    }

    public void setWeatherToDate(String weatherToDate) {
        WeatherToDate = weatherToDate;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }
}
