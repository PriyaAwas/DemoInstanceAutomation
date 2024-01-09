package sew.ai.api.resPojos;

public class WeatherDataResponse {
    private String weatherDate;
    private String high_fahrenheit;
    private String low_fahrenheit;
    private String avg_fahrenheit;
    private String maxhumidity;
    private String minhumidity;
    private String icon;
    private String icon_url;
    private String cityName;
    private String avehumidity;

    private WeatherDataResponse[] weatherDataResponses;

    public WeatherDataResponse() {
    }

    public String getWeatherDate() {
        return weatherDate;
    }

    public void setWeatherDate(String weatherDate) {
        this.weatherDate = weatherDate;
    }

    public String getHigh_fahrenheit() {
        return high_fahrenheit;
    }

    public void setHigh_fahrenheit(String high_fahrenheit) {
        this.high_fahrenheit = high_fahrenheit;
    }

    public String getLow_fahrenheit() {
        return low_fahrenheit;
    }

    public void setLow_fahrenheit(String low_fahrenheit) {
        this.low_fahrenheit = low_fahrenheit;
    }

    public String getAvg_fahrenheit() {
        return avg_fahrenheit;
    }

    public void setAvg_fahrenheit(String avg_fahrenheit) {
        this.avg_fahrenheit = avg_fahrenheit;
    }

    public String getMaxhumidity() {
        return maxhumidity;
    }

    public void setMaxhumidity(String maxhumidity) {
        this.maxhumidity = maxhumidity;
    }

    public String getMinhumidity() {
        return minhumidity;
    }

    public void setMinhumidity(String minhumidity) {
        this.minhumidity = minhumidity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAvehumidity() {
        return avehumidity;
    }

    public void setAvehumidity(String avehumidity) {
        this.avehumidity = avehumidity;
    }

    public WeatherDataResponse[] getWeatherDataResponses() {
        return weatherDataResponses;
    }

    public void setWeatherDataResponses(WeatherDataResponse[] weatherDataResponses) {
        this.weatherDataResponses = weatherDataResponses;
    }
}
