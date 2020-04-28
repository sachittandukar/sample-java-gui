import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel implements AppLayout {
    private GridBagConstraints gbc;
    private JButton btnLogin;
    private JTextField username;
    private JPasswordField password;

    public LoginPanel() {
        setBorder(BorderFactory.createTitledBorder("Login"));
        setLayout(new GridBagLayout());
        setBackground(new Color(0xFFC2EBEC, true));

        gbc = new GridBagConstraints();
        btnLogin = new JButton("Login");
        username = new JTextField(20);
        password = new JPasswordField(20);
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    @Override
    public JPanel panelUI() {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(new JLabel("Username"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Password"), gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(username, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(password, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        add(btnLogin, gbc);
        return this;
    }
}
