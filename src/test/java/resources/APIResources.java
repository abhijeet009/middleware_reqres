package resources;

public enum APIResources {

    //API end points list which will be called on execution
    GetUsers("users"), //get
    GetUsersID("users/{id}"), //put
    Login("login"), //post
    Logout("logout");

    private String resource;
    //Constructor to get end point run time
    APIResources (String resource){this.resource= resource;}

    public String getResource(){
        return resource;
    }
}
