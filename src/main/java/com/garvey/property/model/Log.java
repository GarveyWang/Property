package com.garvey.property.model;

/**
 * @author GarveyWong
 * @date 2019/5/4
 */
public class Log {
    private long timestamp;
    private String operatorAddress;
    private String operatorName;
    private String operation;

    public Log() {
    }

    public Log(String log) {
        String[] arr = log.split(":");
        timestamp = Long.valueOf(arr[0]);
        operatorAddress = arr[1];
        operatorName = arr[2];
        operation = arr[3];
    }

    public Log(long timestamp, String operatorAddress, String operatorName, String operation) {
        this.timestamp = timestamp;
        this.operatorAddress = operatorAddress;
        this.operatorName = operatorName;
        this.operation = operation;
    }

    public String getOperatorAddress() {
        return operatorAddress;
    }

    public void setOperatorAddress(String operatorAddress) {
        this.operatorAddress = operatorAddress;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return timestamp + ":" + operatorAddress + ":" + operatorName + ":" + operation;
    }
}
