package me.bobulo.bo3.util;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
public class Pageable<T> {

    private int pageSize;

    @NotNull
    private List<T> list;

    public Pageable(@NotNull List<T> list, int pageSize) {
        this.list = list;
        this.pageSize = pageSize;
    }

    @NotNull
    public List<T> getPage(int page) {
        return list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));
    }

    public int getPageCount() {
        return (int) Math.ceil((double) list.size() / pageSize);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

}
