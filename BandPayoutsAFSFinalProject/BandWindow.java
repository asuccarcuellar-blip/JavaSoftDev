//Andres Succar
/**
 * BandWindow.java
 * GUI for the band payouts program. It has three tabs: 
 * Members, Gigs, Payouts. It only reads data and shows results;
 * the calculations are done by classes Band, Member and Gig.
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class BandWindow extends JFrame
{

    // Creates and shows the window.
     public static void main(String[] args)
    {
        new BandWindow().setVisible(true);
    }

    //
    private Band band = new Band();

    // Members tab. These are all the pieces
    //Option buttons. 
    private JRadioButton coreRadio = new JRadioButton("Core", true);
    private JRadioButton leadRadio = new JRadioButton("Lead");
    private JRadioButton sessionRadio = new JRadioButton("Session");
    private JTextField nameField = new JTextField(10);
    private JTextField percentField = new JTextField(5);
    private JLabel extraLabel = new JLabel("Extra:");
    private JTextField extraField = new JTextField(5);
    private JButton addMemberButton = new JButton("Add member");
    private JButton deleteMemberButton = new JButton("Delete selected");
    private DefaultTableModel membersModel =
            makeModel(new String[] {"Name", "Type", "Percent", "Fixed pay"});
    private JTable membersTable = new JTable(membersModel);

    // Gigs tab.
    private JTextField descField = new JTextField(14);
    private JTextField incomeField = new JTextField(7);
    private JTextField expensesField = new JTextField(7);
    private JButton addGigButton = new JButton("Add gig");
    private JButton deleteGigButton = new JButton("Delete selected");
    private DefaultTableModel gigsModel =
            makeModel(new String[] {"Description", "Income", "Expenses", "Net"});
    private JTable gigsTable = new JTable(gigsModel);

    // Payouts tab
    private JCheckBox roundBox = new JCheckBox("Round payouts to whole dollars");
    private JButton calcButton = new JButton("Calculate all gigs");
    private DefaultTableModel payoutsModel = makeModel(new String[] {"Gig"});

    // Bottom buttons
    private JButton saveButton = new JButton("Save to file");
    private JButton loadButton = new JButton("Load from file");
    private JButton exitButton = new JButton("Exit");

//Consitructor. Thes builds the window. 

    public BandWindow()
    {
        super("Band Payouts"); //tittle of the window
        setDefaultCloseOperation(EXIT_ON_CLOSE); //closes the window
        setSize(980, 460);
        setLocationRelativeTo(null);

        // Radio buttons in a group: only one can be selected
        ButtonGroup group = new ButtonGroup();
        group.add(coreRadio);
        group.add(leadRadio);
        group.add(sessionRadio);

        // One listener (inner class) for every component that reacts to clicks
        ButtonListener listener = new ButtonListener();
        coreRadio.addActionListener(listener);
        leadRadio.addActionListener(listener);
        sessionRadio.addActionListener(listener);
        addMemberButton.addActionListener(listener);
        deleteMemberButton.addActionListener(listener);
        addGigButton.addActionListener(listener);
        deleteGigButton.addActionListener(listener);
        calcButton.addActionListener(listener);
        saveButton.addActionListener(listener);
        loadButton.addActionListener(listener);
        exitButton.addActionListener(listener);


        //three tabs. Members, Gigs and Payouts.

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Members", buildMembersTab());
        tabs.addTab("Gigs", buildGigsTab());
        tabs.addTab("Payouts", buildPayoutsTab());

        JPanel bottom = new JPanel();
        bottom.add(saveButton);
        bottom.add(loadButton);
        bottom.add(exitButton);

        add(tabs, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        updateTypeFields();
    }

    // Table model whose cells cannot be edited by the user
    private static DefaultTableModel makeModel(String[] columns)
    {
        return new DefaultTableModel(columns, 0)
        {
            @Override
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }
        };
    }

    //this builds the members tab
    private JPanel buildMembersTab()
    {
        JPanel form = new JPanel();
        form.add(coreRadio);
        form.add(leadRadio);
        form.add(sessionRadio);
        form.add(new JLabel("Name:"));
        form.add(nameField);
        form.add(new JLabel("Percent:"));
        form.add(percentField);
        form.add(extraLabel);
        form.add(extraField);
        form.add(addMemberButton);
        form.add(deleteMemberButton);

        JPanel tab = new JPanel(new BorderLayout());
        tab.add(form, BorderLayout.NORTH);
        tab.add(new JScrollPane(membersTable), BorderLayout.CENTER);
        return tab;
    }

        //builds gigs tab.
    private JPanel buildGigsTab()
    {
        JPanel form = new JPanel();
        form.add(new JLabel("Gig Description:"));
        form.add(descField);
        form.add(new JLabel("Income:"));
        form.add(incomeField);
        form.add(new JLabel("Expenses:"));
        form.add(expensesField);
        form.add(addGigButton);
        form.add(deleteGigButton);

        JPanel tab = new JPanel(new BorderLayout());
        tab.add(form, BorderLayout.NORTH);
        tab.add(new JScrollPane(gigsTable), BorderLayout.CENTER);
        return tab;
    }

        //payouts tab builder
    private JPanel buildPayoutsTab()
    {
        JPanel top = new JPanel();
        top.add(calcButton);
        top.add(roundBox);

        JPanel tab = new JPanel(new BorderLayout());
        tab.add(top, BorderLayout.NORTH);
        tab.add(new JScrollPane(new JTable(payoutsModel)), BorderLayout.CENTER);
        return tab;
    }

    // Enables or disables fields depending on the selected member of the band 
    private void updateTypeFields()
    {
        percentField.setEnabled(!sessionRadio.isSelected());
        if (sessionRadio.isSelected())
        {
            percentField.setText("");
        }

        extraField.setEnabled(!coreRadio.isSelected());
        if (coreRadio.isSelected())
        {
            extraLabel.setText("Extra:");
            extraField.setText("");
        }
        else if (leadRadio.isSelected())
        {
            extraLabel.setText("Bonus per gig:");
        }
        else
        {
            extraLabel.setText("Fee per gig:");
        }
    }

// converts text into a number. If the user writes something different that a number, 
//it throws a BandException

    private double parseNumber(String text) throws BandException
    {
        try
        {
            return Double.parseDouble(text.trim());
        }
        catch (NumberFormatException e)
        {
            throw new BandException("That is not a valid number");
        }
    }

            //reads the form, creates the band member and adds it to the band
    private void addMember() throws BandException
    {
        String name = nameField.getText();
        Member m;
        if (sessionRadio.isSelected())
        {
            m = new SessionMember(name, parseNumber(extraField.getText()));
        }
        else if (leadRadio.isSelected())
        {
            m = new LeadMember(name, parseNumber(percentField.getText()),
                    parseNumber(extraField.getText()));
        }
        else
        {
            m = new Member(name, parseNumber(percentField.getText()));
        }
        band.addMember(m);

        nameField.setText("");
        percentField.setText("");
        extraField.setText("");
        refreshMembers();
    }

    private void addGig() throws BandException
    {
        band.addGig(new Gig(descField.getText(),
                parseNumber(incomeField.getText()),
                parseNumber(expensesField.getText())));

        descField.setText("");
        incomeField.setText("");
        expensesField.setText("");
        refreshGigs();
    }

        // deletes a selected member

    private void deleteMember() throws BandException
    {
        band.removeMember(membersTable.getSelectedRow());
        refreshMembers();
    }

    //deletes a selected gig

    private void deleteGig() throws BandException
    {
        band.removeGig(gigsTable.getSelectedRow());
        refreshGigs();
    }

    private void refreshMembers()
    {
        membersModel.setRowCount(0);
        for (Member m : band.getMembers())
        {
            membersModel.addRow(new Object[] {m.getName(), m.getType(),
                    m.getPercent(), m.getFixedPay()});
        }
    }

    private void refreshGigs()
    {
        gigsModel.setRowCount(0);
        for (Gig g : band.getGigs())
        {
            gigsModel.addRow(new Object[] {g.getDescription(), g.getIncome(),
                    g.getExpenses(), g.getNet()});
        }
    }

      // Fills the payouts table: one row per gig, one column per member, plus a TOTAL row
    private void calculatePayouts() throws BandException
    {
        double[][] matrix = band.buildPayoutMatrix();
        String[] names = band.getMemberNames();

        String[] headers = new String[names.length + 1];
        headers[0] = "Gig";
        for (int i = 0; i < names.length; i++)
        {
            headers[i + 1] = names[i];
        }
        payoutsModel.setColumnIdentifiers(headers);
        payoutsModel.setRowCount(0);

        double[] totals = new double[names.length];
        for (int row = 0; row < matrix.length; row++)
        {
            Object[] line = new Object[names.length + 1];
            line[0] = band.getGigs().get(row).getDescription();
            for (int col = 0; col < matrix[row].length; col++)
            {
                line[col + 1] = money(matrix[row][col]);
                totals[col] += matrix[row][col];
            }
            payoutsModel.addRow(line);
        }

// sums all the concerts for each member

        Object[] totalLine = new Object[names.length + 1];
        totalLine[0] = "TOTAL";
        for (int col = 0; col < totals.length; col++)
        {
            totalLine[col + 1] = money(totals[col]);
        }
        payoutsModel.addRow(totalLine);
    }

    // Formats money. The checkbox decides between whole dollars and 2 decimals.
    private String money(double value)
    {
        if (roundBox.isSelected())
        {
            return "$" + Math.round(value);
        }
        return String.format("$%.2f", value);
    }

    // Inner Class: it can use the private fields and methods of BandWindow.
    //It is the button listener that reacts to clicks. It uses e.getSource() to 
    //find out which button was clicked and acts accordingly
    private class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Object source = e.getSource();
            try
            {
                if (source == coreRadio || source == leadRadio || source == sessionRadio)
                {
                    updateTypeFields();
                }
                else if (source == addMemberButton)
                {
                    addMember();
                }
                else if (source == deleteMemberButton)
                {
                    deleteMember();
                }
                else if (source == addGigButton)
                {
                    addGig();
                }
                else if (source == deleteGigButton)
                {
                    deleteGig();
                }
                else if (source == calcButton)
                {
                    calculatePayouts();
                }
                else if (source == saveButton)
                {
                    Main.save(band);
                    JOptionPane.showMessageDialog(BandWindow.this, "Data saved to band.csv");
                }
                else if (source == loadButton)
                {
                    Band loaded = Main.load(band);
                    if (loaded != band)
                    {
                        band = loaded;
                        refreshMembers();
                        refreshGigs();
                        JOptionPane.showMessageDialog(BandWindow.this, "Data loaded from band.csv");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(BandWindow.this,
                                "Could not load band.csv", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if (source == exitButton)
                {
                    System.exit(0);
                }
            }
            catch (BandException ex)
            {
                JOptionPane.showMessageDialog(BandWindow.this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
}