package com.core.reflection;


/**
 * Created by Administrator on 2017/2/22.
 */
public class Factory {
    private static Factory ourInstance = new Factory();

    public static Factory getInstance() {
        return ourInstance;
    }

    private Factory() {
    }

    public Person createPerson(String className){
        try {
            Person person = (Person) Class.forName(className).newInstance();
            return person;
        }catch (Exception e){
            return null;
        }
    }
}
