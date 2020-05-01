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
    DefaultTableModel model;
    JTable table;
    JTextField txtName, txtAddress;
    JRadioButton rdMale, rdFemale;
    ButtonGroup bgGroup;
    JCheckBox chkPositive;
    JButton btnSave, btnUpdate, btnDelete, btnClear;

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

    private JMenuBar getMenu() {
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");

        JMenuItem newItem = new JMenuItem("New");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        return menuBar;
    }

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


        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        rdMale = new JRadioButton("Male");
        rdFemale = new JRadioButton("Female");
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
                String row = JOptionPane.showInputDialog(buttonPanel, "Please enter row number to delete?", "Queries", JOptionPane.INFORMATION_MESSAGE);
                int confirm = JOptionPane.showConfirmDialog(buttonPanel, "Are you sure want to delete row?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    int rowDelete = Integer.parseInt(row);
                    model.removeRow(rowDelete - 1);
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

                model.addRow(new String[]{name, address, gender, positive});
                btnClear.doClick();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                String name = txtName.getText();
                String address = txtAddress.getText();
                String gender = rdMale.isSelected() ? "Male" : "Female";
                String positive = chkPositive.isSelected() ? "Positive" : "Negative";

                model.setValueAt(name, selectedRow, 0);
                model.setValueAt(address, selectedRow, 1);
                model.setValueAt(gender, selectedRow, 2);
                model.setValueAt(positive, selectedRow, 3);
                btnClear.doClick();
            }
        });

        return buttonPanel;
    }

    private JPanel secondUI() {
        secondPanel = new JPanel(new BorderLayout());
        secondPanel.setBorder(BorderFactory.createTitledBorder("Second Panel"));
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Name", "Address", "Gender", "COVID-19"});
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        secondPanel.add(scrollPane);

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
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

    public static void main(String[] args) {
        new SampleApp();
    }
}