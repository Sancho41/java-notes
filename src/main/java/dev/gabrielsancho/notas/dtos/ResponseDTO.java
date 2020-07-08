package dev.gabrielsancho.notas.dtos;

public class ResponseDTO {

    private Integer stauts;
    private Object data;

    public ResponseDTO() {
    }

    public ResponseDTO(Integer stauts, Object data) {
        this.stauts = stauts;
        this.data = data;
    }

    public Integer getStauts() {
        return stauts;
    }

    public void setStauts(Integer stauts) {
        this.stauts = stauts;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}