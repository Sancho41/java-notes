package dev.gabrielsancho.notas.logs;

import java.time.LocalDate;

public class LoggerBuilder {

    private String text;
    private Exception exceptionHandle;
    private LogType logType;

    public LoggerBuilder(String text) {
        this.text = text;

    }

    public void setExceptionHandle(Exception exceptionHandle) {
        this.exceptionHandle = exceptionHandle;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public static LoggerBuilder INFO(String text){
        LoggerBuilder builder =  new LoggerBuilder("[INFO]  - "+text);
        builder.setLogType(LogType.INFO);
        return builder;
    }

    public static LoggerBuilder ERROR(String text, Exception e) {
        LoggerBuilder builder =  new LoggerBuilder("[ERROR] - "+text+ e.getMessage());
        builder.setLogType(LogType.ERROR);
        builder.setExceptionHandle(e);
        return builder;
    }

    public void log(){
        System.out.println(this.text);
        if(this.logType.equals(LogType.ERROR)){
            this.exceptionHandle.printStackTrace();
        }
    }

}
