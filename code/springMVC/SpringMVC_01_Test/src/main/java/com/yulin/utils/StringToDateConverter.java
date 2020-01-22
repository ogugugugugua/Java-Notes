package com.yulin.utils;

import org.springframework.core.convert.converter.Converter;

import javax.crypto.spec.PSource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        if(s==null){
            throw new RuntimeException("please input data");
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            return dateFormat.parse(s);
        } catch (ParseException e) {
            throw new RuntimeException("converter wrong");
        }
    }
}
