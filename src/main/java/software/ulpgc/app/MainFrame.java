package software.ulpgc.app;

import software.ulpgc.arquitecture.control.Command;
import software.ulpgc.arquitecture.view.SelectDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final Map<String, Command> commands;
    private final JFreeBarcahrtDisplay barchartdisplay;
    private SelectDialog dialog;
    private final CardLayout cardLayout;
    private final JPanel contentPanel;
    private static final String barchartLayoutId = "Barchart";
    private static final String databaseLayoutId = "DataBase";

    public MainFrame(){
        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Statistics");
        add(BorderLayout.NORTH, toggle());
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.add(barchartdisplay = barchartPanel(), barchartLayoutId);
        add(contentPanel, BorderLayout.CENTER);
        commands = new HashMap<>();

    }

    private JButton toggle() {
        JButton button = new JButton("toggle");
        button.addActionListener(e -> commands.get("toggle").execute());
        return button;
    }



    public JFreeBarcahrtDisplay barchartDisplay(){return barchartdisplay;}

    private JFreeBarcahrtDisplay barchartPanel() {
        return new JFreeBarcahrtDisplay(selector());
    }
    public SelectDialog dialog(){return dialog;}

    public void put(String name, Command command) {
        commands.put(name, command);
    }

    private Component selector() {
        JComboBox<String> combo = new JComboBox<>();
        combo.addItem("quality");
        combo.addItem("exporter");
        combo.addItemListener(e -> {
            if (e.getStateChange() != ItemEvent.SELECTED) return;
            commands.get("select").execute();
            System.out.println("select");
        });
        dialog = combo::getSelectedIndex;
        return combo;
    }

    private Component generateButton() {
        JButton jButton = new JButton("Generate");
        jButton.addActionListener(e -> commands.get("generate").execute());
        return jButton;
    }

    public JPanel contentPanel(){
        return contentPanel;
    }

    public CardLayout cardLayout() {
        return cardLayout;
    }
    public String barchartLayoutId(){
        return barchartLayoutId;
    }
}
