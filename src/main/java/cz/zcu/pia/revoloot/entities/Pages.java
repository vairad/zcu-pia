package cz.zcu.pia.revoloot.entities;


/**
 * Entita popisující stránkování elementů
 *
 * @author Radek VAIS
 */
public class Pages {

    /**
     * index požadované strany
     */
    private long page;
    /**
     * velikost požadované strany
     */
    private int pageSize;

    /**
     * počet stran
     */
    private long pagesCount;
    /**
     * počáteční element výběru
     */
    private int offset;

    /**
     * Vytvoří objekt stránkování
     *
     * @param page     index požadované strany
     * @param pageSize velikost strany
     */
    public Pages(int page, int pageSize) {
        this.page = page;
        if (page < 0) {
            throw new IllegalArgumentException("Page must be grather than zero.");
        }
        this.pageSize = pageSize;
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be grather than zero.");
        }
        offset = page * pageSize;
    }

    /**
     * Vrací index strany
     *
     * @return index strany
     */
    public long getPage() {
        return page;
    }

    /**
     * Vrací velikost strany
     *
     * @return velikost strany
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Vrací počet stran
     *
     * @return počet stran
     */
    public long getPagesCount() {
        return pagesCount;
    }

    /**
     * Vrací index prvního elementu
     *
     * @return index prvního elementu
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Nastaví skutečný počet stran a v ppřípadě potřeby přepočte požadované nastavení.
     *
     * @param pagesCount nastaví skuutečný počet stran
     */
    public void setPagesCount(long pagesCount) {
        if(pagesCount == 0){
            pagesCount = 1;
        }
        this.pagesCount = pagesCount;
        if (page >= pagesCount) {
            page = pagesCount - 1;
        }
        offset = (int) (page * pageSize);
    }

    @Override
    public String toString() {
        return "Pages :" + page + " (" + pageSize + ")";
    }
}
