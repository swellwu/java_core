package com.utils.clone;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
public class CloneTestClass implements Serializable,Cloneable {

    private static final long serialVersionUID = 2631590509763408280L;

    private List<Integer> list;

    public CloneTestClass(){
        list = Arrays.asList(1,2,3,4);
    }

    @Override
    public CloneTestClass clone() throws CloneNotSupportedException {
        return (CloneTestClass)super.clone();
    }

    public void changeList(Integer... args){
        for(int i=0;i<list.size()&&i<args.length;++i){
            list.set(i,args[i]);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CloneTestClass that = (CloneTestClass) o;

        return list.equals(that.list);

    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }
}