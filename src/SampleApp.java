import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SampleApp extends JFrame {
    JMenuBar menuBar;
    JPanel dataPanel;
    JPanel buttonPanel;
    JPanel secondPanel;
    // Reference variable for different fields, buttons, table and table model
    DefaultTableModel model;
    JTable table;
    JTextField txtName, txtAddress;
    JRadioButton rdMale, rdFemale;
    ButtonGroup bgGroup;
    JCheckBox chkPositive;
    JButton btnSave, btnUpdate, btnDelete, btnClear;

    /**
     * Constructor of the class
     */
    public SampleApp() {
        setTitle("Sample App");
        setVisible(true);
//        setMinimumSize(new Dimension(300, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(getMenu());
        setLayout(new GridLayout(2, 1));
        JPanel subPanel = new JPanel(new GridLayout(1, 2));
        subPanel.add(dataUI());
        subPanel.add(buttonUI());
        add(subPanel);
        add(secondUI());
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * This method is used for creating menus in the GUI.
     * You must call this method in constructor inside setJMenuBar() to display in menu in the frame.
     *
     * @return list of menus
     */
    private JMenuBar getMenu() {
        menuBar = new JMenuBar();

        // define top level menu
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");

        // creating sub menu
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem exitItem = new JMenuItem("Exit");

        // sub menu added inside file menu
        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // top level menu added inside menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        return menuBar;
    }

    /**
     * This method defines interface for data entry section
     *
     * @return panel for data entry section
     */
    private JPanel dataUI() {
        dataPanel = new JPanel();
        dataPanel.setLayout(new GridBagLayout());
        dataPanel.setBorder(BorderFactory.createTitledBorder("Data Entry"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        dataPanel.add(new JLabel("Name"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        dataPanel.add(new JLabel("Address"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        dataPanel.add(new JLabel("Gender"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        dataPanel.add(new JLabel("Positive"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        txtName = new JTextField(20);
        dataPanel.add(txtName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        txtAddress = new JTextField(20);
        dataPanel.add(txtAddress, gbc);

        // Created new panel to add both radio button at same place
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        rdMale = new JRadioButton("Male");
        rdFemale = new JRadioButton("Female");
        // added radio button inside button group so that one radio button is selected at a time
        bgGroup = new ButtonGroup();
        bgGroup.add(rdMale);
        bgGroup.add(rdFemale);
        radioPanel.add(rdMale);
        radioPanel.add(rdFemale);
        gbc.gridx = 1;
        gbc.gridy = 2;
        dataPanel.add(radioPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        chkPositive = new JCheckBox();
        dataPanel.add(chkPositive, gbc);

        return dataPanel;
    }

    /**
     * This method defines interface for button functionality section
     *
     * @return panel for button functionality section
     */
    private JPanel buttonUI() {
        buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Buttons Functionality"));
        btnSave = new JButton("Save");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        buttonPanel.add(btnSave);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String row = JOptionPane.showInputDialog(buttonPanel, "Please enter row number to delete?", "Queries", JOptionPane.QUESTION_MESSAGE);
                int confirm = JOptionPane.showConfirmDialog(buttonPanel, "Are you sure want to delete row?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        int rowDelete = Integer.parseInt(row);
                        model.removeRow(rowDelete - 1);
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(buttonPanel, "You must enter valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        JOptionPane.showMessageDialog(buttonPanel, "Provided row doesn't exist. Please enter valid row number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtName.setText("");
                txtAddress.setText("");
                rdMale.setSelected(false);
                rdFemale.setSelected(false);
                bgGroup.clearSelection();
                table.clearSelection();
                chkPositive.setSelected(false);
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText().trim();
                String address = txtAddress.getText().trim();
                String gender = rdMale.isSelected() ? "Male" : "Female";
                String positive = chkPositive.isSelected() ? "Positive" : "Negative";
                if (name.isEmpty() || address.isEmpty()) {
                    JOptionPane.showMessageDialog(buttonPanel, "Some of the fields are empty", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    model.addRow(new String[]{name, address, gender, positive});
                    btnClear.doClick();
                    JOptionPane.showMessageDialog(buttonPanel, "New record is added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                String name = txtName.getText().trim();
                String address = txtAddress.getText().trim();
                String gender = rdMale.isSelected() ? "Male" : "Female";
                String positive = chkPositive.isSelected() ? "Positive" : "Negative";
                if (name.isEmpty() || address.isEmpty()) {
                    JOptionPane.showMessageDialog(buttonPanel, "Some of the fields are empty", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    model.setValueAt(name, selectedRow, 0);
                    model.setValueAt(address, selectedRow, 1);
                    model.setValueAt(gender, selectedRow, 2);
                    model.setValueAt(positive, selectedRow, 3);
                    btnClear.doClick();
                    JOptionPane.showMessageDialog(buttonPanel, "Record at row " + selectedRow + " is updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        return buttonPanel;
    }

    /**
     * This method defines interface for list of data section
     * and event listener for row selection
     *
     * @return panel for list of data
     */
    private JPanel secondUI() {
        secondPanel = new JPanel(new BorderLayout());
        secondPanel.setBorder(BorderFactory.createTitledBorder("List of data"));
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Name", "Address", "Gender", "COVID-19"});
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        secondPanel.add(scrollPane);

        // added event listener for row selection inside JTable
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // returns row number of selected row in JTable
                int selectedRow = table.getSelectedRow();
                // model.getValueAt(selectedRow, 0) returns a value for the cell at the given row and column
                // .toString() returns string representation of the object
                txtName.setText(model.getValueAt(selectedRow, 0).toString());
                txtAddress.setText(model.getValueAt(selectedRow, 1).toString());
                rdMale.setSelected(model.getValueAt(selectedRow, 2).toString().equals("Male"));
                rdFemale.setSelected(model.getValueAt(selectedRow, 2).toString().equals("Female"));
                chkPositive.setSelected(model.getValueAt(selectedRow, 3).toString().equals("Positive"));
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
        return secondPanel;
    }

    /**
     * Entry point of the application
     *
     * @param args
     */
    public static void main(String[] args) {
        new SampleApp();
    }
}