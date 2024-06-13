package task2;

import task2.pages.ScheduleGroupPage;
import task2.pages.SchedulePage;
import task2.pages.StartPage;

public class PageManager {

    private static PageManager INSTANCE = null;
    private StartPage startPage;
    private SchedulePage schedulePage;
    private ScheduleGroupPage scheduleGroupPage;

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

    public SchedulePage getSchedulePage() {
        if (schedulePage == null) {
            schedulePage = new SchedulePage();
        }
        return schedulePage;
    }

    public ScheduleGroupPage getScheduleGroupPage() {
        if (scheduleGroupPage == null) {
            scheduleGroupPage = new ScheduleGroupPage();
        }
        return scheduleGroupPage;
    }

}

