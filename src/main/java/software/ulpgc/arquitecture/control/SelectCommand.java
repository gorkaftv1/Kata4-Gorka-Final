package software.ulpgc.arquitecture.control;

import software.ulpgc.app.JFreeBarcahrtDisplay;
import software.ulpgc.arquitecture.io.BarchartLoader;
import software.ulpgc.arquitecture.view.SelectDialog;

public class SelectCommand implements Command{
    private final SelectDialog dialog;
    private final BarchartLoader loader;
    private final JFreeBarcahrtDisplay display;


    public SelectCommand(SelectDialog dialog, BarchartLoader loader, JFreeBarcahrtDisplay display) {
        this.dialog = dialog;
        this.loader = loader;
        this.display = display;
    }

    @Override
    public void execute() {
        display.show(loader.load(dialog.getSelection()));
    }
}
