package software.ulpgc.arquitecture.control;

import software.ulpgc.arquitecture.io.WoodDatabaseReader;
import software.ulpgc.arquitecture.model.Wood;
import software.ulpgc.arquitecture.view.DatabaseDisplay;

import java.util.Optional;

public class ReadCommand implements Command{
    private final DatabaseDisplay display;
    private final WoodDatabaseReader reader;

    public ReadCommand(DatabaseDisplay display, WoodDatabaseReader reader) {
        this.display = display;
        this.reader = reader;
    }

    @Override
    public void execute() {
        display.showText(getFormatedString(reader.read()));
    }

    private String getFormatedString(Optional<Wood> wood) {
        return wood.map(woodin -> "<html> ID: " + woodin.id() + "<br>" +
                        "Name: " + woodin.name() + "<br>" +
                        "Continent: " + woodin.continent() + "<br>" +
                        "Quality: " + woodin.quality() + "<br>" +
                        "Tone: " + woodin.tone() + "<br>" +
                        "Country: " + woodin.country() + "<br>" +
                        "Price: " + woodin.pricePerSquareMeters() + " $" + "<br>" +
                        "Exporters: " + woodin.exportCountry().toString() + "</html>")
                .orElse("<html>No data available</html>");

    }
}
