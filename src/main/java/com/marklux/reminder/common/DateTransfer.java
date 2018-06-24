package com.marklux.reminder.common;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by lumin on 18/6/23.
 */
@Component
public class DateTransfer {
    public String transDate(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        return fmt.format(date);
    }
}
