package widget;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

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

        EventQueue.invokeLater(() -> {
            for(int i = 0; i < panels.length; i++) {
                JPanel p = (JPanel) panels[i];
                changingPositionsOfElements(p);
                //tabbedPane.setSelectedIndex(i);
            }
        });
    }

    private void changingPositionsOfElements(JPanel panel) {
        LinkedList<Component> components = toLinkedList(panel.getComponents());

        for(int i = 0; i < components.size(); i++) {
            components.addLast(components.removeFirst());
            panel.removeAll();
            addToPanel(components, panel);
            rebuild(panel);
        }
    }

    private <T> LinkedList<T> toLinkedList(T[] objects) {
        LinkedList<T> listOfObjects = new LinkedList<>();
        for(T obj: objects) listOfObjects.add(obj);
        return listOfObjects;
    }

    private void delayProcess(int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (Exception e) {
            frame.dispose();
        }
    }

    private void rebuild(JPanel panel) {
        panel.revalidate();
        frame.repaint();
    }

    private void addToPanel(LinkedList<Component> components, JPanel panel) {
        for(Component component : components) panel.add(component);
    }
}
