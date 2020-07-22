package dev.gabrielsancho.notas.dtos;

import java.util.List;

public class NoteListDTO {
    private Long current_page;
    private Long last_page;
    private List<NoteDTO> data;

    public NoteListDTO() {
    }

    public NoteListDTO(Long current_page, Long last_page, List<NoteDTO> data) {
        this.current_page = current_page;
        this.last_page = last_page;
        this.data = data;
    }

    public Long getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Long current_page) {
        this.current_page = current_page;
    }

    public Long getLast_page() {
        return last_page;
    }

    public void setLast_page(Long last_page) {
        this.last_page = last_page;
    }

    public List<NoteDTO> getData() {
        return data;
    }

    public void setData(List<NoteDTO> data) {
        this.data = data;
    }
}