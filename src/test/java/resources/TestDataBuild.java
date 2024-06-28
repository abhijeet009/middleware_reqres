package resources;

import pojo.Data;
import pojo.Fields;
import pojo.LoginPojo;
import pojo.Support;

import java.io.IOException;
import java.util.List;

public class TestDataBuild extends Utils{
    public LoginPojo LoginPojo() throws IOException {
        LoginPojo L = new LoginPojo();
        L.setEmail(getGlobalValue("email"));
        L.setPassword(getGlobalValue("password"));
        loginfo("Login data added");
        return L;
    }

    public void fields(int page, int perPage, int total, int totalPages, List<Object> addData, String url, String text) {
    Fields F = new Fields();
    Data D = new Data();
        Support S = new Support();
        F.setPage(page);
        F.setPerPage(perPage);
        F.setTotal(total);
        F.setTotalPages(totalPages);
        //Object List to respective data type

        for (int i = 0; i < addData.size(); i++) {
            Object obj = addData.get(i);
            switch (i) {
                case 0 -> D.setId((Integer) obj);
                case 1 -> D.setEmail((String) obj);
                case 2 -> D.setFirstName((String) obj);
                case 3 -> D.setLastName((String) obj);
                default -> loginfo("Found wrong data type");
            }
        }
        S.setUrl(url);
        S.setText(text);
        System.out.println("Data added in pojo");
    }
}
