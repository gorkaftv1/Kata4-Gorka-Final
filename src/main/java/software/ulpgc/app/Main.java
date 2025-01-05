package software.ulpgc.app;

import software.ulpgc.arquitecture.control.ReadCommand;
import software.ulpgc.arquitecture.control.SelectCommand;
import software.ulpgc.arquitecture.control.ToggleCommand;
import software.ulpgc.arquitecture.io.*;
import software.ulpgc.arquitecture.model.Wood;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static File database = new File("C:/Users/gorka/Downloads/woods.db");
    public static void main(String[] args) throws Exception {
        List<Wood> woodList = fileLoader().load();
        MainFrame mainFrame = createMainFrame(woodList);
        initializeDataBase(woodList);
        mainFrame.put("generate", getReaderOfDatabase(mainFrame));
        mainFrame.setVisible(true);
    }

    private static ReadCommand getReaderOfDatabase(MainFrame mainFrame) throws SQLException {
        return new ReadCommand(mainFrame.databaseDisplay(), WoodDatabaseReader.open(database));
    }

    private static void initializeDataBase(List<Wood> woodList) throws Exception {
        WoodDatabaseWriter writer = WoodDatabaseWriter.open(database);
        for (Wood wood : woodList) writer.write(wood);
        writer.close();
    }

    private static MainFrame createMainFrame(List<Wood> woodList) {
        BarchartLoader loader = new BarchartLoader(woodList);
        MainFrame frame = new MainFrame();
        frame.put("toggle", new ToggleCommand(frame.cardLayout(), frame.contentPanel(), frame.barchartLayoutId(), frame.dataBaseLayoutid()));
        frame.put("select", new SelectCommand(frame.dialog(), loader, frame.barchartDisplay()));
        frame.barchartDisplay().show(loader.load(0));
        return frame;
    }

    private static FileWoodLoader fileLoader() throws IOException {
        File file = new File("C:/Users/gorka/Downloads/WoodsData.tsv");
        return new FileWoodLoader(file, new TsvWoodDeserializer());
    }
}
