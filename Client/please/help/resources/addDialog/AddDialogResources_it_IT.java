package please.help.resources.addDialog;

import java.util.ListResourceBundle;

public class AddDialogResources_it_IT extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"creationDateLabel", "Data creazione:"},
                {"ownerLabel", "Proprietario:"},
                {"nameLabel", "Titolo (String, Non null)"},
                {"coordinatesLabel", "Coordinate"},
                {"annualTurnoverLabel", "Fatturato annuo (double, maggiore di 0)"},
                {"typeLabel", "Tipo di organizzazione:"},
                {"addressLabel", "Indirizzo"},
                {"streetLabel", "Strada (String, può essere null):"},
                {"zipCodeLabel", "Indice (String, la lunghezza della stringa non è inferiore a 4, può essere null):"},
                {"okButton", "Fatto"},
                {"cancelButton", "Annulla"},
                {"FIELDS_FOR_ERRORS", new String [] {"Titolo:", "Coordinata X:", "Coordinata Y: "
                        , "Fatturato annuo:", "tipo di organizzazione:", "Strada:", "Indice:"}},
                {"show", "Informazioni sull'oggetto"},
                {"add", "Creazione di un oggetto"},
                {"update", "Aggiornamento oggetto"}
        };
    }
}
