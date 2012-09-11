package com.snda.sysdev.cfg.task;

import org.apache.log4j.Logger;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: liudong.leo
 * Date: 12-9-7
 * Time: 上午9:28
 * To change this template use File | Settings | File Templates.
 */
public class UpdTask {
    private static final Logger log = Logger.getLogger(UpdTask.class);

    private Calendar sleepBegin;//如10:20
    private Calendar sleepEnd;//如 11:00

    private String sleepTimeStr;

    public String getSleepTimeStr() {
        return sleepTimeStr;
    }

    public void setSleepTimeStr(String sleepTimeStr) {
        this.sleepTimeStr = sleepTimeStr;
    }

    public UpdTask() {
        init();
    }

    private void init() {
        String sleepBeginTimeStr = "0:0",
                sleepEndTimeStr = "0:0";
        if (sleepTimeStr != null && sleepTimeStr.indexOf("-") > 0) {
            sleepBeginTimeStr = sleepTimeStr.split("-")[0];
            sleepEndTimeStr = sleepTimeStr.split("-")[1];
        }
        this.sleepBegin = parseCalendar(sleepBeginTimeStr);
        this.sleepEnd = parseCalendar(sleepEndTimeStr);

        log.info("Upd Sync Task init success, sleep time: " + sleepTimeStr);
    }

    public void updSync() {
        Calendar now = Calendar.getInstance();
        if (sleepBegin != null && sleepEnd != null && now.after(sleepBegin) && now.before(sleepEnd)) {//休眠期间 不执行任务
            log.info("sleeping, task pause...");
            return;
        }

        //todo do something
        log.info("hello, world!");
    }

    private Calendar parseCalendar(String time) {
        Calendar calendar = Calendar.getInstance();
        String[] timeArr = time.split(":");
        int hour = Integer.parseInt(timeArr[0]);
        int minute = Integer.parseInt(timeArr[1]);
        if (hour < 0 || hour >= 24 || minute < 0 || minute >= 60) {//休眠时间段格式错误,默认不会进入休眠期
            log.info("sleep time format error!");
            return null;
        } else {
            calendar.set(Calendar.HOUR, hour);
            calendar.set(Calendar.MINUTE, minute);
            return calendar;
        }
    }
}
