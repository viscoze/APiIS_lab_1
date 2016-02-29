import java.awt.*;
import widget.*;

class Main {

    public Main() {
        PanelKit panelKit = new PanelKit();
        new MainFrame(panelKit);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater( Main::new );
    }
}
