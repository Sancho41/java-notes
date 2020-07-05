package dev.gabrielsancho.notas.logs;

import dev.gabrielsancho.notas.utils.ConsoleColorsUtils;

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
        String msg = ConsoleColorsUtils.consoleColor(
                ConsoleColorsUtils.BLUE_BACKGROUND_BRIGHT,
                String.format("[INFO] - %s", text)
        );

        LoggerBuilder builder =  new LoggerBuilder(msg);
        builder.setLogType(LogType.INFO);
        return builder;
    }

    public static LoggerBuilder ERROR(String text, Exception e) {
        String msg = ConsoleColorsUtils.consoleColor(
                ConsoleColorsUtils.RED_BACKGROUND_BRIGHT,
                String.format("[ERROR] - %s: %s", text, e.getMessage())
        );

        LoggerBuilder builder =  new LoggerBuilder(msg);
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
