package com.servicenow.sample.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ashutosh Srivastava on 24-Mar-2021 1:59 PM.
 * Project : Ex-ServiceNow
 * Copyright (c) 2021  All rights reserved.
 */
public enum DateWrapper {

    EEE_MMM_dd_HH_mm_ss_zzz_yyyy("EEE MMM dd HH:mm:ss zzz yyyy"),
    yyyy_MM_dd_T_HH_mm_ss_SSS_Z("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
    yyyy_MM_dd_T_HH_mm_ss("yyyy-MM-dd'T'HH:mm:ss"),
    yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
    yyyy_MM_dd("yyyy-MM-dd"),
    dd_MMM_yyyy("dd MMM yyyy"),
    hh_mm_a("hh:mm a"),
    MMM_d_yyyy_hh_mm_a("MMM d, yyyy hh:mm a"),//~~~ in DateTimePickerController
    MMM_d_yyyy_HH_mm_a("MMM d, yyyy HH:mm"),//~~~ in DateTimePickerController
    HHmm("HHmm"),//~~~ in AutoCheckout
    HH_mm("HH:mm"),//~~~ in AutoCheckout
    HH_mm_ss("HH:mm:ss"),//~~~ in AutoCheckout
    MMM_dd_HH_mm_ss("MMM dd, HH:mm:ss");

    private static final String TAG = "DateWrapper";
    private final String format;

    DateWrapper(String format) {
        this.format = format;
    }

    public static Date getDateFromInput(DateWrapper format, String input) {
        Date date = null;
        try {
            date = new SimpleDateFormat(format.getValue()).parse(input);
        } catch (Exception pe) {
            pe.printStackTrace();
        }
        return date;
    }

    public static String getStringFromFormat(DateWrapper format, Date date) {
        if (date == null) return null;
        return getStringFromFormat(format.getValue(), date);
    }

    public static String getStringFromFormat(String format, Date date) {
        if (date == null) return null;
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public String getValue() {
        return format;
    }
}
