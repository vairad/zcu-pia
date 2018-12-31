package cz.zcu.pia.revoloot.web;

public class ServletNaming {

    //region customer
    public static final String CUSTOMER = "/customer";
    public static final String CUSTOMER_DASHBOARD = CUSTOMER+"/home";
    public static final String CUSTOMER_ACCOUNT = CUSTOMER+"/account";
    //endregion customer

    //region admin
    public static final String ADMIN = "/admin";
    public static final String ADMIN_DASHBOARD = ADMIN+"/home";
    public static final String ADMIN_REGISTER = ADMIN+"/register";
    //endregion admin

    //region PUBLIC
    public static final String LOGIN = "/login";
    public static final String LOGIN_PROCESS = "/process-login";
    public static final String LOGOUT_PROCESS = "/process-logout";
    public static final String CONTACTS = "/contacts";
    public static final String PRODUCTS = "/products";
    public static final String WELCOME = "/home";
    public static final String ERRORS = "/errors";
    //endregion PUBLIC
}

