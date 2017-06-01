/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiPannels;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import utilities.DatabaseUtilities;
import wrappers.AreaWrapper;
import wrappers.BoughtProductWrapper;
import wrappers.CustomerWrapper;
import wrappers.ProductWrapper;

public class MainFrame extends javax.swing.JFrame {

    ArrayList<AreaWrapper> areas = new ArrayList<>();
    ArrayList<AreaWrapper> areasCheck = new ArrayList<>();

    ArrayList<ProductWrapper> products = new ArrayList<>();

    boolean validCustomerID = false;

    public MainFrame() {
        initComponents();

        areas = DatabaseUtilities.getAllAreas();

        areasCheck = DatabaseUtilities.getAllAreas();
        areasCheck.add(0, new AreaWrapper(0, "Total", 0));

        products = DatabaseUtilities.getAllProducts();

        bindCombobox(comboBoxAreas, areas);
        bindCombobox(comboBoxAreasCheck, areasCheck);
        bindCombobox(comboBoxProducts, products);

        buttonCheckCustomer.addActionListener(event -> {
            if (!validCustomerID) {
                JOptionPane.showMessageDialog(null, "Invalid customerID!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                JOptionPane.showMessageDialog(null,
                        (DatabaseUtilities.checkCustomer(Integer.parseInt(textFieldCustomerID.getText().trim()))
                        ? "Valid customer" : "Invalid customer"));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Incorrect format number!");
            }
        });

        buttonBuy.addActionListener(event -> {
            if (!validCustomerID) {
                JOptionPane.showMessageDialog(null, "Invalid customerID!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                int customerID = Integer.parseInt(textFieldCustomerID.getText().trim());
                int productID = ((ProductWrapper) comboBoxProducts.getSelectedItem()).getProductID();
                int quantity = (Integer) spinnerQuantity.getValue();
                int bought = DatabaseUtilities.buyProduct(customerID, productID, quantity);
                JOptionPane.showMessageDialog(null, (bought > 0 ? "Bought" : "Not bought"));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Incorrect format number!");
            }
        });

        buttonChange.addActionListener(event -> {
            if (!validCustomerID) {
                JOptionPane.showMessageDialog(null, "Invalid customerID!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                JOptionPane.showMessageDialog(null,
                        (DatabaseUtilities.changeArea(Integer.parseInt(textFieldCustomerID.getText().trim()),
                                ((AreaWrapper) comboBoxAreas.getSelectedItem()).getAreaID()))
                        ? "Area changed" : "Access denied");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Incorrect format number!");
            }
        });

        buttonAddCustomer.addActionListener(event -> {
            Integer newCustomer = DatabaseUtilities.addCustomer(textFieldCustomerName.getText().trim());
            if (newCustomer != -1) {
                textFieldNewCustomerID.setText(Integer.toString(newCustomer));
            }
        });

        comboBoxProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTextFieldPrice();
            }
        });

        setTextFieldPrice();
        setNumberOfCustomers();
    }

    private void setTextFieldPrice() {
        Object value = comboBoxProducts.getSelectedItem();
        if (value != null && value instanceof ProductWrapper) {
            spinnerQuantity.setValue(1);
            textFieldPrice.setText(Double.toString(((ProductWrapper) value).getPrice()));
        }
    }

    private void bindCombobox(JComboBox combo, ArrayList arrayToBind) {
        if (areas != null) {
            DefaultComboBoxModel areaModel = new DefaultComboBoxModel(arrayToBind.toArray());
            combo.setModel(areaModel);
            combo.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    Component currentComponent = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Object) {
                        Object area = (Object) value;
                        setText(area.toString());
                    }
                    return currentComponent;
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        panelCenter = new javax.swing.JPanel();
        comboBoxAreasCheck = new javax.swing.JComboBox<>();
        filler19 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 17), new java.awt.Dimension(0, 17), new java.awt.Dimension(32767, 17));
        textFieldCustomerID = new javax.swing.JTextField();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 17), new java.awt.Dimension(0, 17), new java.awt.Dimension(32767, 17));
        panelProduct = new javax.swing.JPanel();
        comboBoxProducts = new javax.swing.JComboBox<>();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        spinnerQuantity = new javax.swing.JSpinner();
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        textFieldPrice = new javax.swing.JTextField();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 12), new java.awt.Dimension(0, 12), new java.awt.Dimension(32767, 12));
        comboBoxAreas = new javax.swing.JComboBox<>();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 12), new java.awt.Dimension(0, 12), new java.awt.Dimension(32767, 12));
        textFieldTotal = new javax.swing.JTextField();
        filler22 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 17), new java.awt.Dimension(0, 17), new java.awt.Dimension(32767, 17));
        panelAddCustomer = new javax.swing.JPanel();
        textFieldCustomerName = new javax.swing.JTextField();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        textFieldNewCustomerID = new javax.swing.JTextField();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 14), new java.awt.Dimension(0, 14), new java.awt.Dimension(32767, 14));
        panelTitle = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        panelButtons = new javax.swing.JPanel();
        textFieldNumber = new javax.swing.JTextField();
        filler21 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 12), new java.awt.Dimension(0, 12), new java.awt.Dimension(32767, 12));
        buttonCheckCustomer = new javax.swing.JButton();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        buttonBuy = new javax.swing.JButton();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6), new java.awt.Dimension(32767, 6));
        buttonChange = new javax.swing.JButton();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 8), new java.awt.Dimension(0, 8), new java.awt.Dimension(32767, 8));
        buttonCashout = new javax.swing.JButton();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        buttonAddCustomer = new javax.swing.JButton();
        panelLabels = new javax.swing.JPanel();
        filler20 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        jLabel1 = new javax.swing.JLabel();
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 25), new java.awt.Dimension(32767, 25));
        labelCustomerID = new javax.swing.JLabel();
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 24), new java.awt.Dimension(0, 24), new java.awt.Dimension(32767, 24));
        labelProduct = new javax.swing.JLabel();
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 26), new java.awt.Dimension(0, 26), new java.awt.Dimension(32767, 26));
        labelArea = new javax.swing.JLabel();
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 26), new java.awt.Dimension(0, 26), new java.awt.Dimension(32767, 26));
        labelPaymet = new javax.swing.JLabel();
        filler18 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 24), new java.awt.Dimension(0, 24), new java.awt.Dimension(32767, 24));
        labelNewCustomer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SPA Manager");
        setMaximumSize(new java.awt.Dimension(2147483647, 380));
        setMinimumSize(new java.awt.Dimension(680, 340));
        setPreferredSize(new java.awt.Dimension(680, 340));
        setSize(new java.awt.Dimension(680, 340));

        panelMain.setLayout(new java.awt.BorderLayout());

        panelCenter.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelCenter.setMinimumSize(new java.awt.Dimension(352, 220));
        panelCenter.setPreferredSize(new java.awt.Dimension(352, 220));
        panelCenter.setLayout(new javax.swing.BoxLayout(panelCenter, javax.swing.BoxLayout.Y_AXIS));

        comboBoxAreasCheck.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxAreasCheck.setMaximumSize(new java.awt.Dimension(32767, 28));
        comboBoxAreasCheck.setMinimumSize(new java.awt.Dimension(72, 28));
        comboBoxAreasCheck.setPreferredSize(new java.awt.Dimension(72, 28));
        comboBoxAreasCheck.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxAreasCheckItemStateChanged(evt);
            }
        });
        panelCenter.add(comboBoxAreasCheck);
        panelCenter.add(filler19);

        textFieldCustomerID.setText("Customer ID");
        textFieldCustomerID.setMaximumSize(new java.awt.Dimension(2147483647, 24));
        textFieldCustomerID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textFieldCustomerIDFocusLost(evt);
            }
        });
        panelCenter.add(textFieldCustomerID);
        panelCenter.add(filler2);

        panelProduct.setMaximumSize(new java.awt.Dimension(2147483647, 26));
        panelProduct.setLayout(new javax.swing.BoxLayout(panelProduct, javax.swing.BoxLayout.LINE_AXIS));

        comboBoxProducts.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxProducts.setPreferredSize(new java.awt.Dimension(240, 26));
        panelProduct.add(comboBoxProducts);
        panelProduct.add(filler12);

        spinnerQuantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinnerQuantity.setMaximumSize(new java.awt.Dimension(200, 32767));
        spinnerQuantity.setMinimumSize(new java.awt.Dimension(50, 26));
        spinnerQuantity.setPreferredSize(new java.awt.Dimension(60, 26));
        spinnerQuantity.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerQuantityStateChanged(evt);
            }
        });
        panelProduct.add(spinnerQuantity);
        panelProduct.add(filler13);

        textFieldPrice.setEditable(false);
        textFieldPrice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textFieldPrice.setText("Price");
        textFieldPrice.setMaximumSize(new java.awt.Dimension(500, 2147483647));
        textFieldPrice.setMinimumSize(new java.awt.Dimension(40, 24));
        textFieldPrice.setName(""); // NOI18N
        textFieldPrice.setPreferredSize(new java.awt.Dimension(100, 24));
        panelProduct.add(textFieldPrice);

        panelCenter.add(panelProduct);
        panelCenter.add(filler3);

        comboBoxAreas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxAreas.setMaximumSize(new java.awt.Dimension(32767, 28));
        comboBoxAreas.setMinimumSize(new java.awt.Dimension(72, 28));
        comboBoxAreas.setPreferredSize(new java.awt.Dimension(72, 28));
        panelCenter.add(comboBoxAreas);
        panelCenter.add(filler4);

        textFieldTotal.setEditable(false);
        textFieldTotal.setText("Total");
        textFieldTotal.setMaximumSize(new java.awt.Dimension(2147483647, 26));
        textFieldTotal.setMinimumSize(new java.awt.Dimension(14, 26));
        textFieldTotal.setPreferredSize(new java.awt.Dimension(41, 26));
        textFieldTotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textFieldTotalMouseClicked(evt);
            }
        });
        textFieldTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldTotalActionPerformed(evt);
            }
        });
        panelCenter.add(textFieldTotal);
        panelCenter.add(filler22);

        panelAddCustomer.setMaximumSize(new java.awt.Dimension(2147483647, 24));
        panelAddCustomer.setLayout(new javax.swing.BoxLayout(panelAddCustomer, javax.swing.BoxLayout.LINE_AXIS));

        textFieldCustomerName.setText("Customer name");
        panelAddCustomer.add(textFieldCustomerName);
        panelAddCustomer.add(filler10);

        textFieldNewCustomerID.setEditable(false);
        textFieldNewCustomerID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textFieldNewCustomerID.setText("-");
        textFieldNewCustomerID.setPreferredSize(new java.awt.Dimension(30, 24));
        panelAddCustomer.add(textFieldNewCustomerID);

        panelCenter.add(panelAddCustomer);
        panelCenter.add(filler6);

        panelMain.add(panelCenter, java.awt.BorderLayout.CENTER);

        panelTitle.setLayout(new java.awt.BorderLayout());

        labelTitle.setFont(new java.awt.Font("YuGothic", 0, 24)); // NOI18N
        labelTitle.setForeground(new java.awt.Color(51, 51, 255));
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("SPA CONTROL PANEL");
        panelTitle.add(labelTitle, java.awt.BorderLayout.CENTER);

        panelMain.add(panelTitle, java.awt.BorderLayout.PAGE_START);

        panelButtons.setLayout(new javax.swing.BoxLayout(panelButtons, javax.swing.BoxLayout.Y_AXIS));

        textFieldNumber.setEditable(false);
        textFieldNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textFieldNumber.setText("Customers");
        textFieldNumber.setMaximumSize(new java.awt.Dimension(2147483647, 30));
        textFieldNumber.setMinimumSize(new java.awt.Dimension(0, 30));
        textFieldNumber.setName(""); // NOI18N
        textFieldNumber.setPreferredSize(new java.awt.Dimension(0, 30));
        panelButtons.add(textFieldNumber);
        panelButtons.add(filler21);

        buttonCheckCustomer.setText("Check");
        buttonCheckCustomer.setMaximumSize(new java.awt.Dimension(150, 32));
        buttonCheckCustomer.setMinimumSize(new java.awt.Dimension(150, 32));
        buttonCheckCustomer.setPreferredSize(new java.awt.Dimension(150, 32));
        panelButtons.add(buttonCheckCustomer);
        panelButtons.add(filler7);

        buttonBuy.setText("Buy");
        buttonBuy.setMaximumSize(new java.awt.Dimension(150, 32));
        buttonBuy.setMinimumSize(new java.awt.Dimension(150, 32));
        buttonBuy.setPreferredSize(new java.awt.Dimension(150, 32));
        panelButtons.add(buttonBuy);
        panelButtons.add(filler8);

        buttonChange.setText("Change");
        buttonChange.setMaximumSize(new java.awt.Dimension(150, 32));
        buttonChange.setMinimumSize(new java.awt.Dimension(150, 32));
        buttonChange.setPreferredSize(new java.awt.Dimension(150, 32));
        panelButtons.add(buttonChange);
        panelButtons.add(filler9);

        buttonCashout.setText("Cashout");
        buttonCashout.setMaximumSize(new java.awt.Dimension(150, 32));
        buttonCashout.setMinimumSize(new java.awt.Dimension(150, 32));
        buttonCashout.setPreferredSize(new java.awt.Dimension(150, 32));
        buttonCashout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCashoutActionPerformed(evt);
            }
        });
        panelButtons.add(buttonCashout);
        panelButtons.add(filler11);

        buttonAddCustomer.setText("Add Customer");
        buttonAddCustomer.setMaximumSize(new java.awt.Dimension(150, 32));
        buttonAddCustomer.setMinimumSize(new java.awt.Dimension(150, 32));
        buttonAddCustomer.setPreferredSize(new java.awt.Dimension(150, 32));
        panelButtons.add(buttonAddCustomer);

        panelMain.add(panelButtons, java.awt.BorderLayout.LINE_END);

        panelLabels.setMinimumSize(new java.awt.Dimension(135, 236));
        panelLabels.setPreferredSize(new java.awt.Dimension(125, 226));
        panelLabels.setLayout(new javax.swing.BoxLayout(panelLabels, javax.swing.BoxLayout.Y_AXIS));
        panelLabels.add(filler20);

        jLabel1.setText("Customers number:");
        panelLabels.add(jLabel1);
        panelLabels.add(filler15);

        labelCustomerID.setText("CustomerID:");
        panelLabels.add(labelCustomerID);
        panelLabels.add(filler14);

        labelProduct.setText("Product:");
        panelLabels.add(labelProduct);
        panelLabels.add(filler16);

        labelArea.setText("Area:");
        panelLabels.add(labelArea);
        panelLabels.add(filler17);

        labelPaymet.setText("Payment:");
        panelLabels.add(labelPaymet);
        panelLabels.add(filler18);

        labelNewCustomer.setText("New customer:");
        panelLabels.add(labelNewCustomer);

        panelMain.add(panelLabels, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldTotalActionPerformed

    private void buttonCashoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCashoutActionPerformed
        if (!validCustomerID) {
            JOptionPane.showMessageDialog(null, "Invalid customerID!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Integer customerID = Integer.parseInt(textFieldCustomerID.getText());
        Integer rowsAffected = DatabaseUtilities.deleteTransactions(customerID);
        textFieldTotal.setText("0.0");
    }//GEN-LAST:event_buttonCashoutActionPerformed

    private void textFieldTotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldTotalMouseClicked
        if (!validCustomerID) {
            JOptionPane.showMessageDialog(null, "Invalid customerID!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Integer customerID = Integer.parseInt(textFieldCustomerID.getText());
        ArrayList<BoughtProductWrapper> transactions = DatabaseUtilities.cashout(customerID);
        if (transactions == null) {
            JOptionPane.showMessageDialog(null, "Customer with ID " + customerID.toString() + " has no transactions", "Invoice", JOptionPane.INFORMATION_MESSAGE);
            textFieldTotal.setText("0.0");
            return;
        }

        String joinTransactions = "";
        for (BoughtProductWrapper i : transactions) {
            joinTransactions += i.toString() + "\n";
        }

        if (joinTransactions.equals("")) {
            JOptionPane.showMessageDialog(null, "Customer with ID " + customerID.toString() + " has no transactions", "Invoice", JOptionPane.INFORMATION_MESSAGE);
            textFieldTotal.setText("0.0");
            return;
        }

        String message = "Transactions carried out by customer with ID " + customerID.toString() + ":\n\n" + joinTransactions;
        double sum = 0.0;
        for (BoughtProductWrapper p : transactions) {
            sum += p.getAmount() * p.getPrice();
        }

        textFieldTotal.setText(Double.toString(sum));
        JOptionPane.showMessageDialog(null, message + "\n\n                        Total: " + sum + " RON", "Invoice", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_textFieldTotalMouseClicked

    private void comboBoxAreasCheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxAreasCheckItemStateChanged
        setNumberOfCustomers();
    }//GEN-LAST:event_comboBoxAreasCheckItemStateChanged

    private void spinnerQuantityStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerQuantityStateChanged
        Object value = comboBoxProducts.getSelectedItem();
        if (value != null && value instanceof ProductWrapper) {
            if (((ProductWrapper) value).getTypeID() == 1) {
                spinnerQuantity.setValue(1);
                return;
            }
            Integer quantity = (Integer) spinnerQuantity.getValue();

            textFieldPrice.setText(Double.toString(((ProductWrapper) value).getPrice() * quantity));

        }
    }//GEN-LAST:event_spinnerQuantityStateChanged

    private void textFieldCustomerIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textFieldCustomerIDFocusLost
        try {
            Integer.parseInt(textFieldCustomerID.getText());
            validCustomerID = true;
        } catch (NumberFormatException nrEx) {
            validCustomerID = false;
            //textFieldCustomerID.requestFocus();
        }
    }//GEN-LAST:event_textFieldCustomerIDFocusLost

    private void setNumberOfCustomers() {
        String selectedValue = comboBoxAreasCheck.getSelectedItem().toString();

        Integer index;

        if (selectedValue.equals("Total")) {
            index = 0;
        } else {
            index = DatabaseUtilities.getAreaID(selectedValue);
        }

        Integer numberOfCustomers = DatabaseUtilities.getNumberOfCustomers(index);

        textFieldNumber.setText(numberOfCustomers.toString());
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddCustomer;
    private javax.swing.JButton buttonBuy;
    private javax.swing.JButton buttonCashout;
    private javax.swing.JButton buttonChange;
    private javax.swing.JButton buttonCheckCustomer;
    private javax.swing.JComboBox<String> comboBoxAreas;
    private javax.swing.JComboBox<String> comboBoxAreasCheck;
    private javax.swing.JComboBox<String> comboBoxProducts;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler17;
    private javax.swing.Box.Filler filler18;
    private javax.swing.Box.Filler filler19;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler20;
    private javax.swing.Box.Filler filler21;
    private javax.swing.Box.Filler filler22;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelArea;
    private javax.swing.JLabel labelCustomerID;
    private javax.swing.JLabel labelNewCustomer;
    private javax.swing.JLabel labelPaymet;
    private javax.swing.JLabel labelProduct;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JPanel panelAddCustomer;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel panelLabels;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelProduct;
    private javax.swing.JPanel panelTitle;
    private javax.swing.JSpinner spinnerQuantity;
    private javax.swing.JTextField textFieldCustomerID;
    private javax.swing.JTextField textFieldCustomerName;
    private javax.swing.JTextField textFieldNewCustomerID;
    private javax.swing.JTextField textFieldNumber;
    private javax.swing.JTextField textFieldPrice;
    private javax.swing.JTextField textFieldTotal;
    // End of variables declaration//GEN-END:variables

}
