package software.ulpgc.app;

import software.ulpgc.arquitecture.control.Command;
import software.ulpgc.arquitecture.view.DatabaseDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class JFreeDatabaseDisplay extends JPanel implements DatabaseDisplay {
    private JLabel label;
    private Map<String, Command> command;
    private Component generateButton;

    public JFreeDatabaseDisplay(Component component) {
        setLayout(new BorderLayout());
        this.generateButton = component;
        this.add(groupPanel());
    }

    private Component groupPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(BorderLayout.SOUTH, this.generateButton);
        panel.add(BorderLayout.CENTER, label = new JLabel());
        label.setText("Here will appear the results of queries");
        label.setHorizontalAlignment(JLabel.CENTER);
        return panel;
    }



    @Override
    public void showText(String text) {
        label.setText(text);
    }
}
