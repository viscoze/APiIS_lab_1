package widget;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Enumeration;

public class PanelKitHelper {

    private JFrame frame;

    public PanelKitHelper(JFrame frame) {
        this.frame = frame;
    }

    public void changeColumn(JTable table, DefaultTableModel model, int toColumnNumber) {
        int rowNumber    = table.getSelectedRow();
        int columnNumber = table.getSelectedColumn();

        String text = (String) model.getValueAt(rowNumber, columnNumber);

        model.setValueAt("", rowNumber, columnNumber);
        model.setValueAt(text, rowNumber, toColumnNumber);
    }

    public void addToRow(int columnCount, JTextField textField, DefaultTableModel model) {
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

    public void addItem(DefaultComboBoxModel model, JTextField textField) {
        String text = textField.getText();
        if (text.equals("")) {
            popUpMessage("Enter something!");
            return;
        }

        if (model.getIndexOf(text) == -1) {
            model.addElement(text);
            textField.setText("");
        } else {
            popUpMessage("There is already one!");
        }
    }

    public void findItemAndSelectItCheckBox(ArrayList<JCheckBox> checkBoxes, JTextField textField){
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

    public void findItemAndSelectItRadioButton(ButtonGroup group, JTextField textField) {
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

    public void popUpMessage(String message) {
        JOptionPane.showMessageDialog(this.frame, message);
    }
}
