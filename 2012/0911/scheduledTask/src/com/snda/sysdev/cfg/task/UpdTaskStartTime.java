package com.snda.sysdev.cfg.task;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: liudong.leo
 * Date: 12-9-6
 * Time: 下午6:00
 * To change this template use File | Settings | File Templates.
 */
public class UpdTaskStartTime extends Date {
    public UpdTaskStartTime(int startTime) {
        super();
        Calendar calendar = Calendar.getInstance(); //expect start time
        calendar.set(Calendar.MINUTE, startTime);
        if (this.after(calendar.getTime())) {
            calendar.add(Calendar.HOUR, 1);
        }
        super.setTime(calendar.getTimeInMillis());
    }
}
