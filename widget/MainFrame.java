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

        for(int indexOfTabsName = 0; indexOfTabsName < 5; indexOfTabsName++)
            tabbedPane.addTab(tabsNames[indexOfTabsName], panels.get(indexOfTabsName));

        mainPanel.add(startCrazy, BorderLayout.SOUTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        frame.add(mainPanel);
    }

    private void startCrazy() {
        JPanel mainPanel       = (JPanel)frame.getContentPane().getComponents()[0];
        Component[] allComp    = mainPanel.getComponents();
        JTabbedPane tabbedPane = (JTabbedPane)allComp[1];
        Component[] panels     = tabbedPane.getComponents();


        Thread t = new Thread(() -> {
            for(int indexOfTab = 0; indexOfTab < panels.length; indexOfTab++) {
                JPanel p = (JPanel) panels[indexOfTab];
                tabbedPane.setSelectedIndex(indexOfTab);
                changingPositionsOfElements(p);
            }
        });

        t.start();
    }

    private void changingPositionsOfElements(JPanel panel) {
        LinkedList<Component> components = toLinkedList(panel.getComponents());
        wait1second();
        for(int i = 0; i < components.size(); i++) {
            components.addLast(components.removeFirst());
            rebuild(components, panel);
            wait1second();
        }
        wait1second();
    }

    private <T> LinkedList<T> toLinkedList(T[] objects) {
        LinkedList<T> listOfObjects = new LinkedList<>();
        for(T obj: objects) listOfObjects.add(obj);
        return listOfObjects;
    }

    private void rebuild(LinkedList<Component> components, JPanel panel) {
        panel.removeAll();
        for(Component component : components) panel.add(component);
        panel.revalidate();
        frame.repaint();
    }

    public static void wait1second() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
