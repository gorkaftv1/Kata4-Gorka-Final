package software.ulpgc.arquitecture.control;

import java.awt.*;
import java.util.List;

public class ToggleCommand implements Command{
    private final Container contentPanel;
    private final CardLayout cardLayout;
    private final List<String> layoutsList;
    private int i = 0;

    public ToggleCommand(CardLayout cardLayout, Container contentPanel, String barcharLayoutId, String dataBaseLayoutId) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.layoutsList = List.of( barcharLayoutId, dataBaseLayoutId);
    }

    @Override
    public void execute() {
        cardLayout.show(contentPanel, nextLayout());
    }

    private String nextLayout() {
        return layoutsList.get(i++%2);
    }
}
