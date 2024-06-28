package pojo;

import java.util.List;

public class Fields {
    int page;
    int perPage;
    int total;
    int totalPages;

    List<Object> data;
    List<Object> support;

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }



    public void setPage(int page) {
        this.page = page;
    }
    public int getPage() {
        return page;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
    public int getPerPage() {
        return perPage;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return total;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public int getTotalPages() {
        return totalPages;
    }

    public List<Object>  getSupport() {
        return support;
    }

    public void setSupport(List<Object>  support) {
        this.support = support;
    }



}
