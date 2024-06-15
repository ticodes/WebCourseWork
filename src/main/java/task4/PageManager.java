package task4;


import task4.pages.*;

public class PageManager {

    private static PageManager INSTANCE = null;
    private StartPage startPage;
    private SearchPage searchPage;
    private ChannelPage channelPage;

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

    public SearchPage getSearchPage() {
        if (searchPage == null) {
            searchPage = new SearchPage();
        }
        return searchPage;
    }

    public ChannelPage getChannelPage() {
        if (channelPage == null) {
            channelPage = new ChannelPage();
        }
        return channelPage;
    }

}

