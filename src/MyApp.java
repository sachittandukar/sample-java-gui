import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyApp extends JFrame {
    JMenuBar menuBar;
    LoginPanel loginPanel;
    RegisterPanel registerPanel;
    DataPanel dataPanel;
    MyApp self = this;
    UserRegistration reg;

    public MyApp() {
        setVisible(true);
        setResizable(false);
        setTitle("My Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBar = new JMenuBar();
        loginPanel = new LoginPanel();
        registerPanel = new RegisterPanel();
        dataPanel = new DataPanel();
        reg = new UserRegistration();
        setJMenuBar(getMenu());
        add(appLayout());
        refreshTable();
        pack();
        setLocationRelativeTo(null);
        loginEventHandlers();
        addTableData();
        clearField();
        updateField();
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
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenu subNew = new JMenu("New");
        subNew.setMnemonic(KeyEvent.VK_N);

        JMenuItem subPrint = new JMenuItem("Print");
        JMenuItem subClose = new JMenuItem("Close");
        subClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        subClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem deleteMenu = new JMenuItem("Delete");

        deleteMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
        deleteMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String row = JOptionPane.showInputDialog(dataPanel, "Please enter id number to delete?", "Queries", JOptionPane.QUESTION_MESSAGE);
                int confirm = JOptionPane.showConfirmDialog(dataPanel, "Are you sure want to delete row?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        int rowDelete = Integer.parseInt(row);
//                        dataPanel.getModel().removeRow(rowDelete - 1);
                        reg.delete(rowDelete);
                        refreshTable();
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(dataPanel, "You must enter valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        JOptionPane.showMessageDialog(dataPanel, "Provided row doesn't exist. Please enter valid row number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        fileMenu.add(subNew);
        fileMenu.add(subPrint);
        fileMenu.add(deleteMenu);
        fileMenu.addSeparator();
        fileMenu.add(subClose);

        subNew.add(new JMenuItem("New Project"));
        subNew.add(new JMenuItem("New GUI Project"));

        return menuBar;

    }

    private void loginEventHandlers() {
        JButton btnLogin = loginPanel.getBtnLogin();
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(self, "You are now logged in!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });


    }

    private void addTableData() {
        JButton btnRegister = registerPanel.getBtnRegister();
        DefaultTableModel model = dataPanel.getModel();
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = registerPanel.getUsername().getText().trim();
                    String firstName = registerPanel.getFirstName().getText().trim();
                    String lastName = registerPanel.getLastName().getText().trim();
                    int age = Integer.parseInt(registerPanel.getAge().getText().trim());
                    char[] password = registerPanel.getPassword().getPassword();
                    String gender = registerPanel.getGender().getSelectedItem().toString();
                    if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
                        JOptionPane.showMessageDialog(self, "Some of the fields are empty", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (password.length < 8) {
                        JOptionPane.showMessageDialog(self, "Your password length must be greater than 8", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (age <= 18) {
                        JOptionPane.showMessageDialog(self, "You must be aged more than 18 years old.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {

                        reg.insert(firstName, lastName, username, new String(password), age, gender);
                        refreshTable();
                        JOptionPane.showMessageDialog(self, "You have been registered", "Success", JOptionPane.INFORMATION_MESSAGE);
                        registerPanel.getBtnClear().doClick();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(self, "You must enter valid age in number.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        btnRegister.setMnemonic(KeyEvent.VK_S);
    }

    private void clearField() {
        JButton btnClear = registerPanel.getBtnClear();
        JButton btnUpdate = registerPanel.getBtnUpdate();
        JButton btnRegister = registerPanel.getBtnRegister();
        JTable dataTable = dataPanel.getTable();
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerPanel.getUsername().setText("");
                registerPanel.getFirstName().setText("");
                registerPanel.getLastName().setText("");
                registerPanel.getAge().setText("");
                registerPanel.getPassword().setText("");
                registerPanel.getGender().setSelectedIndex(0);
                btnUpdate.setVisible(false);
                btnRegister.setVisible(true);
                dataTable.clearSelection();
            }
        });
    }

    private void updateField() {
        JTable dataTable = dataPanel.getTable();
        DefaultTableModel model = dataPanel.getModel();
        JButton btnUpdate = registerPanel.getBtnUpdate();
        JButton btnRegister = registerPanel.getBtnRegister();
        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnUpdate.setVisible(true);
                btnRegister.setVisible(false);
                int selectedRow = dataTable.getSelectedRow();
                registerPanel.getFirstName().setText(model.getValueAt(selectedRow, 1).toString());
                registerPanel.getLastName().setText(model.getValueAt(selectedRow, 2).toString());
                registerPanel.getUsername().setText(model.getValueAt(selectedRow, 3).toString());
                registerPanel.getPassword().setText(model.getValueAt(selectedRow, 4).toString());
                registerPanel.getAge().setText(model.getValueAt(selectedRow, 5).toString());
                registerPanel.getGender().setSelectedItem(model.getValueAt(selectedRow, 6).toString());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(self, "You must select row in table to update.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    String username = registerPanel.getUsername().getText().trim();
                    String firstName = registerPanel.getFirstName().getText().trim();
                    String lastName = registerPanel.getLastName().getText().trim();
                    int age = Integer.parseInt(registerPanel.getAge().getText().trim());
                    char[] password = registerPanel.getPassword().getPassword();
                    String gender = registerPanel.getGender().getSelectedItem().toString();
                    if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
                        JOptionPane.showMessageDialog(self, "Some of the fields are empty", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (password.length < 8) {
                        JOptionPane.showMessageDialog(self, "Your password length must be greater than 8", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (age <= 18) {
                        JOptionPane.showMessageDialog(self, "You must be aged more than 18 years old.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
                        reg.update(id, firstName, lastName, username, new String(password), age, gender);
                        refreshTable();
                        JOptionPane.showMessageDialog(self, "Data of " + username + " at row " + (selectedRow + 1) + " updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        registerPanel.getBtnClear().doClick();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(self, "You must enter valid age in number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void refreshTable() {
        // removes all data from JTable
        dataPanel.getModel().setRowCount(0);
        try {
            ResultSet resultSet = reg.get();
            while (resultSet.next()) {
                dataPanel.getModel().addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getInt("age"),
                        resultSet.getString("gender"),
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new MyApp();
    }
}
