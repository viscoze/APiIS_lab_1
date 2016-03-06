package widget;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class MainFrame {

    private JFrame frame;

    public MainFrame(PanelKit panelKit) {

        this.frame = new JFrame();
        ArrayList<JPanel> panels = panelKit.getAllPanels(frame);

        initializeFrameSettings();
        useUIManager();
        initUI(panels);
    }

    private void initializeFrameSettings() {
        frame.setTitle("Widget by Vlad");
        frame.setBounds(500,500,500,300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void initUI(ArrayList<JPanel> panels) {
        JPanel mainPanel       = new JPanel(new CardLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        String tabsNames[]     = { "ComboBox","TextField","Radio","Check","Table" };

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createTitledBorder("Choose the task"));

        JButton startCrazy     = new JButton("Start the addition task!");
        startCrazy.addActionListener(e -> crazyStart());

        for(int i = 0; i < 5; i++)
            tabbedPane.addTab(tabsNames[i], panels.get(i));

        mainPanel.add(tabbedPane);
        frame.add(mainPanel);
    }

    private void useUIManager() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            UIManager.put("swing.boldMetal", Boolean.TRUE);
        } catch (Exception ex) {
            ex.printStackTrace();
            frame.dispose();
        }
    }

    private void crazyStart() {
        JPanel mainPanel    = (JPanel)frame.getContentPane().getComponents()[0];
        Component[] allComp = mainPanel.getComponents();
        JTabbedPane panel   = (JTabbedPane)allComp[0];

        for(int i = 0; i < 5; i++)
            panel.setSelectedIndex(i);

    }
}
