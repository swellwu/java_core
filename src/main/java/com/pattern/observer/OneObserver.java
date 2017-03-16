package com.pattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Administrator on 2017/3/16.
 */
public class OneObserver implements  Observer{

    private String name;
    private float temperature;
    private float humidity;
    private float pressure;

    public OneObserver(String name) {
        this.name=name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OneObserver{");
        sb.append("name='").append(name).append('\'');
        sb.append(", temperature=").append(temperature);
        sb.append(", humidity=").append(humidity);
        sb.append(", pressure=").append(pressure);
        sb.append('}');
        return sb.toString();
    }

    public void display() {
        System.out.println(toString());
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData)o;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            this.pressure=weatherData.getPressure();
            display();
        }
    }
}
