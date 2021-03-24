package com.servicenow.sample.repository.database.converters

import androidx.room.TypeConverter
import com.servicenow.sample.utils.DateWrapper
import java.util.*

/**
 * Created by Ashutosh Srivastava on 24-Mar-2021 1:57 PM.
 * Project : Ex-ServiceNow
 * Copyright (c) 2021  All rights reserved.
 */
object DateConverter {


    @JvmStatic
    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return if (value == null) null else DateWrapper.getDateFromInput(DateWrapper.yyyy_MM_dd_T_HH_mm_ss_SSS_Z, value)
    }

    @JvmStatic
    @TypeConverter
    fun dateToTimestamp(value: Date?): String? {
        return if (value == null) null else DateWrapper.getStringFromFormat(DateWrapper.yyyy_MM_dd_T_HH_mm_ss_SSS_Z, value)
    }

}