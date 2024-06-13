package task3;


import task3.pages.TabletPage;
import task3.pages.StartPage;

public class PageManager {

    private static PageManager INSTANCE = null;
    private StartPage startPage;
    private TabletPage laptopPage;

    private PageManager() {
    }

    public static PageManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PageManager();
        }
        return INSTANCE;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public TabletPage getTabletPage() {
        if (laptopPage == null) {
            laptopPage = new TabletPage();
        }
        return laptopPage;
    }

}

