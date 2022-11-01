package com.gymdroid.service;

import com.gymdroid.domain.beans.core.BasicBean;

import java.util.Date;

public class DataOperationService {

    public static boolean isReadyForInsert(Date lastUpdateDate, BasicBean basicBean) {
        return lastUpdateDate.compareTo(basicBean.getCreatedAt()) < 0 && !compareEqualDatesOnStringLevel(lastUpdateDate, basicBean.getCreatedAt());
    }

    private static boolean compareEqualDatesOnStringLevel(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            return date1.toString().equals(date2.toString());
        }
        return false;
    }

    public static boolean isReadyForUpdate(Date lastUpdateDate, BasicBean basicBean) {
        return lastUpdateDate.before(basicBean.getUpdatedAt()) && basicBean.getCreatedAt().getTime() != basicBean.getUpdatedAt().getTime();
    }

}
