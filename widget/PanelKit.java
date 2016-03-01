package widget;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Enumeration;

public class PanelKit {

    private JFrame frame;

    public ArrayList<JPanel> getAllPanels(JFrame frame) {

        this.frame = frame;

        ArrayList<JPanel> allPanels = new ArrayList<>();

        allPanels.add(createWidgetComboBox());
        allPanels.add(createWidgetLineEdit());
        allPanels.add(createWidgetRadioButton());
        allPanels.add(createWidgetCheckBox());
        allPanels.add(createWidgetTable());

        return allPanels;
    }

    public JPanel createWidgetComboBox() {
        JPanel  panel                = new JPanel();
        JButton enterButton          = new JButton("PUSH ME!");
        JTextField textField         = new JTextField(10);

        DefaultComboBoxModel model   = new DefaultComboBoxModel(new String[] {"Person1"});
        JComboBox<String> comboBox   = new JComboBox<>(model);

        enterButton.addActionListener(e -> {
            addItem(model, textField);
        });

        panel.add(textField);
        panel.add(enterButton);
        panel.add(comboBox);

        panel.setBorder(BorderFactory.createTitledBorder("ComboBox"));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));

        return panel;
    }

    public JPanel createWidgetLineEdit() {
        JPanel  panel        = new JPanel();
        JButton enterButton1 = new JButton("PUSH ME!");
        JButton enterButton2 = new JButton("PUSH ME LATER!");
        JTextField textField = new JTextField(10);

        enterButton2.setEnabled(false);

        enterButton1.addActionListener(e -> {
            String text = textField.getText();
            enterButton2.setEnabled(true);
            enterButton2.setText(text);
        });

        enterButton2.addActionListener(e -> {
            String text = enterButton2.getText();
            enterButton1.setText(text);
        });

        panel.add(textField);
        panel.add(enterButton1);
        panel.add(enterButton2);

        panel.setBorder(BorderFactory.createTitledBorder("TextField"));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));

        return panel;
    }

    public JPanel createWidgetRadioButton() {
        JPanel  panel        = new JPanel();
        JButton enterButton  = new JButton("PUSH ME!");
        JTextField textField = new JTextField(5);
        ButtonGroup group    = new ButtonGroup();

        for (int i = 0; i < 3; i++) {
            JRadioButton radio = new JRadioButton("Item" + (i+1));
            group.add(radio);
            panel.add(radio);
        }

        enterButton.addActionListener(e -> {
            findItemAndSelectItRadioButton(group, textField);
        });

        panel.setLayout(new GridLayout(0,3,10,20));

        panel.add(textField);
        panel.add(enterButton);

        panel.setBorder(BorderFactory.createTitledBorder("RadioButton"));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return panel;
    }

    public JPanel createWidgetCheckBox() {
        JPanel  panel                   = new JPanel();
        JButton enterButton             = new JButton("PUSH ME!");
        JTextField textField            = new JTextField(5);
        ArrayList<JCheckBox> checkBoxes = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            JCheckBox checkBox = new JCheckBox("Item" + (i+1));
            checkBoxes.add(checkBox);
            panel.add(checkBox);
        }

        enterButton.addActionListener(e -> {
            findItemAndSelectItCheckBox(checkBoxes, textField);
        });

        panel.setLayout(new GridLayout(0,3,10,20));

        panel.add(textField);
        panel.add(enterButton);

        panel.setBorder(BorderFactory.createTitledBorder("CheckBox"));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return panel;
    }

    public JPanel createWidgetTable() {
        JPanel panel            = new JPanel();

        JPanel gridPanel        = new JPanel();
        gridPanel.setLayout(new GridLayout(2,2,10,20));

        JButton toTable         = new JButton("Add to table");
        JButton toSecondColumn  = new JButton("To second column");
        JButton toFirstColumn   = new JButton("To first column");

        JTextField textField    = new JTextField(5);

        String columnNames[]    = { "First", "Second" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table            = new JTable(model);

        toTable.addActionListener(e -> {
            addToRow(1, textField, model);
        });

        toSecondColumn.addActionListener(e -> {
            changeColumn(table,  model, 1);
        });

        toFirstColumn.addActionListener(e -> {
            changeColumn(table,  model, 0);
        });

        gridPanel.add(textField);
        gridPanel.add(toTable);
        gridPanel.add(toSecondColumn);
        gridPanel.add(toFirstColumn);

        panel.add(gridPanel);
        panel.add(new JScrollPane(table));

        panel.setBorder(BorderFactory.createTitledBorder("Table"));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return panel;
    }



    private void changeColumn(JTable table, DefaultTableModel model, int toColumnNumber) {
        int rowNumber    = table.getSelectedRow();
        int columnNumber = table.getSelectedColumn();

        String text = (String) model.getValueAt(rowNumber, columnNumber);

        model.setValueAt("", rowNumber, columnNumber);
        model.setValueAt(text, rowNumber, toColumnNumber);
    }

    private void addToRow(int columnCount, JTextField textField, DefaultTableModel model) {
        String text  = textField.getText();
        int rowCount = model.getRowCount();
        columnCount -= 1;

        if (rowCount == 6) {
            popUpMessage("Enough!");
            return;
        }

        if (text.equals("")) {
            popUpMessage("Text field is empty!");
            return;
        }

        model.setRowCount(++rowCount);
        model.setValueAt(text, rowCount-1, columnCount);
    }

    private void addItem(DefaultComboBoxModel model, JTextField textField) {
        String text = textField.getText();
        if (model.getIndexOf(text) == -1) {
            model.addElement(text);
            textField.setText("");
        } else {
            popUpMessage("There is already one!");
        }
    }

    private void findItemAndSelectItCheckBox(ArrayList<JCheckBox> checkBoxes, JTextField textField){
        String text = textField.getText();
        for (JCheckBox cb : checkBoxes) {
            if (cb.getText().equals(text)) {
                if (cb.isSelected()) {
                    cb.setSelected(false);
                    return;
                }
                cb.setSelected(true);
                return;
            }
        }

        popUpMessage("There is no one!");
    }

    private void findItemAndSelectItRadioButton(ButtonGroup group, JTextField textField) {
        String text = textField.getText();
        Enumeration<AbstractButton> buttonsModels = group.getElements();

        while (buttonsModels.hasMoreElements()) {
            AbstractButton element = buttonsModels.nextElement();
            if (element.getText().equals(text)) {
                ButtonModel buttonModel = element.getModel();
                if (buttonModel.isSelected()) {
                    group.clearSelection();
                    return;
                }
                buttonModel.setSelected(true);
                return;
            }
        }

        popUpMessage("There is no one!");
    }

    private void popUpMessage(String message) {
        JOptionPane.showMessageDialog(this.frame, message);
    }

}
