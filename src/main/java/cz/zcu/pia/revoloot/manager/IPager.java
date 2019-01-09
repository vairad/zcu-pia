package cz.zcu.pia.revoloot.manager;

public interface IPager {

    int  getPage();
    int  getPageSize();
    int  getPagesCount();
    int  getOffset();

}
