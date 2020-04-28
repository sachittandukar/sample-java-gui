import javax.swing.*;
import java.awt.*;

public class SampleApp extends JFrame {
    JMenuBar menuBar;
    JPanel dataPanel;
    JPanel buttonPanel;
    JPanel secondPanel;
    public SampleApp()  {
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

//        add(new JButton("North"), BorderLayout.NORTH);
//        add(new JButton("EAST"),BorderLayout.EAST);
//        add(new JButton("West"), BorderLayout.WEST);
//        add(new JButton("South"),BorderLayout.SOUTH);
//        add(new JButton("New South"),BorderLayout.SOUTH);
//        add(new JButton("Center"));
//        add(new JButton("New Center"));

        pack();
        setLocationRelativeTo(null);
    }

    private JMenuBar getMenu(){
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

    private JPanel dataUI(){
        dataPanel = new JPanel();
        dataPanel.setLayout(new GridBagLayout());
        dataPanel.setBorder(BorderFactory.createTitledBorder("Data Entry"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        dataPanel.add(new JLabel("Name"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        dataPanel.add(new JLabel("Address"),gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        dataPanel.add(new JLabel("Gender"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        dataPanel.add(new JLabel("Positive"),gbc);
//        JTextField txtName = new JTextField( 20);
//        txtName.setSize(200, 20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        dataPanel.add(new JTextField( 20), gbc);  // "m" yesko font size ko width * 20


        gbc.gridx = 1;
        gbc.gridy = 1;
        dataPanel.add(new JTextField(20), gbc);



        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
//        radioPanel.setBackground(Color.CYAN);
        JRadioButton rdMale = new JRadioButton("Male");
        JRadioButton rdFemale = new JRadioButton("Female");
        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(rdMale);
        bgroup.add(rdFemale);
        radioPanel.add(rdMale);
        radioPanel.add(rdFemale);
        gbc.gridx = 1;
        gbc.gridy = 2;
        dataPanel.add(radioPanel, gbc);


        gbc.gridx = 1;
        gbc.gridy = 3;
        dataPanel.add(new JCheckBox(),gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        dataPanel.add(new JButton("Submit"),gbc);

        return dataPanel;
    }

    private JPanel buttonUI(){
        buttonPanel = new JPanel(new GridLayout(2,2));
//        buttonPanel.setLayout(new GridLayout(2,2));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Buttons Functionality"));
        buttonPanel.add(new JButton("Save"));
        buttonPanel.add(new JButton("Update"));
        buttonPanel.add(new JButton("Delete"));
        buttonPanel.add(new JButton("Clear"));
        return buttonPanel;
    }

    private JPanel secondUI(){
        secondPanel = new JPanel();
        secondPanel.setBorder(BorderFactory.createTitledBorder("Second Panel"));
        return secondPanel;
    }

    public static void main(String[] args) {
        new SampleApp();
    }
}