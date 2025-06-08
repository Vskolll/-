package com.example.myapplication;

public class WeatherItem {
    private String dateTime;
    private String temperature;
    private String wind;
    private String pressure;
    private String iconUrl;  // нове поле

    public WeatherItem(String dateTime, String temperature, String wind, String pressure, String iconUrl) {
        this.dateTime = dateTime;
        this.temperature = temperature;
        this.wind = wind;
        this.pressure = pressure;
        this.iconUrl = iconUrl;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWind() {
        return wind;
    }

    public String getPressure() {
        return pressure;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
