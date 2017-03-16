package com.pattern.observer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.util.Observable;
import java.util.Observer;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/3/16.
 */
public class JDKObserverTest {

    @Rule
    public final SystemOutRule log = new SystemOutRule();

    private String format(String name,float temperature,float humidity,float pressure){
            final StringBuilder sb = new StringBuilder("OneObserver{");
            sb.append("name='").append(name).append('\'');
            sb.append(", temperature=").append(temperature);
            sb.append(", humidity=").append(humidity);
            sb.append(", pressure=").append(pressure);
            sb.append('}');
            return sb.toString();
    }

    @Test
    public void observerTest(){
        log.enableLog();
        String name1="No1";
        String name2="No2";
        WeatherData weatherData = new WeatherData();
        Observer observerOne = new OneObserver(name1);
        Observer observerTwo=new OneObserver(name2);
        weatherData.addObserver(observerOne);
        weatherData.addObserver(observerTwo);
        weatherData.setMeasurements(37,55,100);
        String observerOneString=format(name1,37,55,100);
        String observerTwoString=format(name1,37,55,100);
        assertTrue(log.getLogWithNormalizedLineSeparator().contains(observerOneString));
        assertTrue(log.getLogWithNormalizedLineSeparator().contains(observerTwoString));
    }
}