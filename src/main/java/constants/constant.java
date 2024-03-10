package constants;


public class constant {
    public static class TimeoutVar {
        public static final int EXPLICIT_WAIT_10_SEC = 10;
    }

    public static class Urls {

        public static final String MAIN_PAGE = "file://" + System.getProperty("user.dir") + "/testPages/testPageMain.html";
        public static final String FIRST_PAGE = "file://" + System.getProperty("user.dir") + "/testPages/link1.html";
        public static final String SECOND_PAGE = "file://" + System.getProperty("user.dir") + "/testPages/link2.html";

    }
}
