import javax.swing.*;
import java.awt.*;

public class MyApp extends JFrame {
    JMenuBar menuBar;
    LoginPanel loginPanel;
    RegisterPanel registerPanel;
    DataPanel dataPanel;
    MyApp self = this;

    public MyApp() {
        setVisible(true);
        setResizable(false);
        setTitle("My Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBar = new JMenuBar();
        loginPanel = new LoginPanel();
        registerPanel = new RegisterPanel();
        dataPanel = new DataPanel();
        setJMenuBar(getMenu());
        add(appLayout());
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel appLayout() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(loginPanel.panelUI(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(registerPanel.panelUI(), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        mainPanel.add(dataPanel.panelUI(), gbc);

        return mainPanel;
    }

    private JMenuBar getMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        JMenu subNew = new JMenu("New");
        JMenuItem subPrint = new JMenuItem("Print");
        JMenuItem subClose = new JMenuItem("Close");
        fileMenu.add(subNew);
        fileMenu.add(subPrint);
        fileMenu.addSeparator();
        fileMenu.add(subClose);

        subNew.add(new JMenuItem("New Project"));
        subNew.add(new JMenuItem("New GUI Project"));

        return menuBar;

    }

    public static void main(String[] args) {
        new MyApp();
    }
}
