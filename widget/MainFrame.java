package widget;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    public MainFrame(PanelKit panelKit) {

        ArrayList<JPanel> panels = panelKit.getAllPanels(this);

        useUIManager();
        initUI(panels);
    }

    private void initUI(ArrayList<JPanel> panels) {
        JPanel mainPanel       = new JPanel(new CardLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        String tabsNames[]     = { "ComboBox","TextField","Radio","Check","Table" };

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createTitledBorder("Choose the task"));

        for(int i = 0; i < 5; i++)
            tabbedPane.addTab(tabsNames[i], panels.get(i));

        mainPanel.add(tabbedPane);
        this.add(mainPanel);

        this.setTitle("Widget by Vlad");
        this.setBounds(500,500,500,300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    private void useUIManager() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            UIManager.put("swing.boldMetal", Boolean.FALSE);
        } catch (Exception ex) {
            ex.printStackTrace();
            dispose();
        }
    }
}
