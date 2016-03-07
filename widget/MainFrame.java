package widget;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainFrame {

    private JFrame frame;

    public MainFrame(PanelKit panelKit) {

        this.frame = new JFrame();
        ArrayList<JPanel> panels = panelKit.getAllPanels(frame);

        initializeFrameSettings();
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

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createTitledBorder("Choose the task"));

        JButton startCrazy     = new JButton("Start the addition task!");
        startCrazy.addActionListener(e -> startCrazy());

        for(int i = 0; i < 5; i++)
            tabbedPane.addTab(tabsNames[i], panels.get(i));

        mainPanel.add(startCrazy, BorderLayout.SOUTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        frame.add(mainPanel);

    }

    private void startCrazy() {
        JPanel mainPanel       = (JPanel)frame.getContentPane().getComponents()[0];
        Component[] allComp    = mainPanel.getComponents();
        JTabbedPane tabbedPane = (JTabbedPane)allComp[1];
        Component[] panels     = tabbedPane.getComponents();

        for(Component comp : panels ){
            JPanel p = (JPanel) comp;
            changingPositionsOfElements(p);
        }
    }

    private void changingPositionsOfElements(JPanel panel) {
        LinkedList<Component> components = toLinkedList(panel.getComponents());

        frame.repaint();
    }

    private <T> LinkedList<T> toLinkedList(T[] objects) {
        LinkedList<T> listOfObjects = new LinkedList<>();
        for (T obj: objects) listOfObjects.add(obj);
        return listOfObjects;
    }
}
