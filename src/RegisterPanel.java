import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel implements AppLayout {
    private GridBagConstraints gbc;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField username;
    private JPasswordField password;
    private JTextField age;
    private JComboBox<String> gender;
    private JButton btnClear, btnRegister, btnUpdate;

    public RegisterPanel() {
        setBorder(BorderFactory.createTitledBorder("Registration"));
        setLayout(new GridBagLayout());
        setBackground(new Color(0xFFE5F1D4, true));
        gbc = new GridBagConstraints();
        firstName = new JTextField(20);
        firstName.setToolTipText("Please enter your first name");
        lastName = new JTextField(20);
        username = new JTextField(20);
        password = new JPasswordField(20);
        age = new JTextField(20);
        gender = new JComboBox<>(new String[]{"Male", "Female"});
        btnClear = new JButton("Clear All");
        btnRegister = new JButton("Register");
        btnUpdate = new JButton("Update");
    }

    @Override
    public JPanel panelUI() {

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(new JLabel("First Name"), gbc);
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(firstName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Last Name"), gbc);

        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(lastName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(new JLabel("Username"), gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(username, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(new JLabel("Password"), gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 3;
        gbc.gridy = 2;
        add(password, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(new JLabel("Age"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        add(age, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        add(new JLabel("Gender"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(gender, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(btnClear, gbc);

        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        add(btnUpdate, gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(btnRegister, gbc);
        return this;
    }

    public JTextField getFirstName() {
        return firstName;
    }

    public JTextField getLastName() {
        return lastName;
    }

    public JTextField getUsername() {
        return username;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public JTextField getAge() {
        return age;
    }

    public JComboBox getGender() {
        return gender;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public JButton getBtnRegister() {
        return btnRegister;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

}
