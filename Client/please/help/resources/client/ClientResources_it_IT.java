package please.help.resources.client;

import java.util.ListResourceBundle;

public class ClientResources_it_IT extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"invalidServerData", "Dati errati per la connessione al server"},
                {"serverDead", "Si è verificato un errore durante la comunicazione con il server."},
                {"initError", "Errore durante l'inizializzazione del client"},
                {"connectionError", "Impossibile connettersi al server"}
        };
    }
}
