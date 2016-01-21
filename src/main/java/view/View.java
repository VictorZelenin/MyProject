package view;

/**
 * Created by User on 13.10.2015.
 *  @Version 1.0
 */
public class View {
    // створюємо всі форми
    private EnterFrame enterFrame;
    private ClientFrame clientFrame;
    private ExitFrame exitFrame;
    private LoginFrame loginFrame;
    private AdminFrame adminFrame;
    private ListFrame listFrame;
    private ReportFrame reportFrame;

    public View() {
        // ініціалізуємо всі форми
        enterFrame = new EnterFrame();
        clientFrame = new ClientFrame();
        exitFrame = new ExitFrame();
        loginFrame = new LoginFrame();
        adminFrame = new AdminFrame();
        listFrame = new ListFrame();
        reportFrame = new ReportFrame();
    }


    /* геттери для контролера*/

    public EnterFrame getEnterFrame() {
        return enterFrame;
    }

    public ClientFrame getClientFrame() {
        return clientFrame;
    }

    public ExitFrame getExitFrame() {
        return exitFrame;
    }

    public LoginFrame getLoginFrame() {
        return loginFrame;
    }

    public AdminFrame getAdminFrame() { return adminFrame; }

    public ListFrame getListFrame() {
        return listFrame;
    }

    public ReportFrame getReportFrame() {
        return reportFrame;
    }


}
