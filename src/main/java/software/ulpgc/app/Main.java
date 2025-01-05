package software.ulpgc.app;

import software.ulpgc.arquitecture.control.SelectCommand;
import software.ulpgc.arquitecture.io.BarchartLoader;
import software.ulpgc.arquitecture.io.FileWoodLoader;
import software.ulpgc.arquitecture.io.TsvWoodDeserializer;
import software.ulpgc.arquitecture.model.Wood;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWoodLoader fileLoader = fileLoader();
        List<Wood> woodList = fileLoader.load();
        BarchartLoader loader = new BarchartLoader(woodList);
        MainFrame frame = new MainFrame();
        frame.put("select", new SelectCommand(frame.dialog(), loader, frame.barchartDisplay()));
        frame.barchartDisplay().show(loader.load(0));
        frame.setVisible(true);
    }


    private static FileWoodLoader fileLoader() throws IOException {
        File file = new File("C:/Users/gorka/Downloads/WoodsData.tsv");
        return new FileWoodLoader(file, new TsvWoodDeserializer());
    }
}
