package please.help.resources.mainFrame;

import java.util.ListResourceBundle;

public class MainFrameResources_it_IT extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"tableTab", "Tabella"},
                {"visualizationTab", "Visualizzazione"},
                {"updateTip", "aggiorna valore dell'elemento di raccolta"},
                {"removeTip", "rimuovi elemento dalla raccolta"},
                {"addMenu", "Aggiunta"},
                {"deleteMenu", "Elimina"},
                {"addTip", " aggiungi nuovo elemento alla raccolta"},
                {"addIfMaxTip", "aggiungi un nuovo elemento alla raccolta se il suo valore è" +
                        "supera il valore dell'elemento più grande" +
                        "questa raccolta (Ordina per campo fatturato annuo)"},
                {"removeGreaterTip", " rimuovi tutti gli elementi dalla raccolta," +
                        "superiore a quello specificato (Ordina per campo fatturato annuo)"},
                {"clearTip", " elimina tutti gli oggetti utente"},
                {"funFactsMenu", " Statistiche"},
                {"otherThingsMenu", "Altro"},
                {"historyTip", " visualizza gli ultimi 5 comandi"},
                {"averageOfAnnualTurnoverTip", "visualizza il valore medio del campo del fatturato annuo" +
                        "per tutti gli elementi della raccolta"},
                {"printFieldAscendingTypeTip", " visualizza i valori del campo type di tutti gli elementi in ordine crescente"},
                {"infoTip", " visualizza informazioni sulla raccolta"},
                {"countByAnnualTurnoverTip", "dedurre il numero di elementi," +
                        "il valore del campo fatturato annuo è uguale a quello specificato"},
                {"executeScriptTip", " leggere ed eseguire lo script dal file specificato"},
                {"scriptChooserTitle", "Seleziona il file di script"},
                {"languagesMenu", "Lingua"},
                {"connectionErrorOptions1", new String [] {"Ripeti pop-up", "Stop"}},
                {"connectionErrorOptions2", new String [] {"Ripeti poptyk", "Chiudi programma"}},
                {"connectionErrorTitle", "Errore di connessione"},
                {"connectionErrorMessage", "Impossibile connettersi al server"},
                {"scriptErrorTitle", "Errore di script"},
                {"fatalScriptErrorMessage1", "Errore fatale durante l'analisi dello script:\n"},
                {"fatalScriptErrorMessage2", "\nLo script non verrà eseguito"},
                {"fatalScriptErrorTitle", "Errore di script"},
                {"clearMessage", "Verranno eliminati tutti gli oggetti. Siete d'accordo?"},
                {"clearTitle", "Conferma eliminazione"},
                {"removeMessage", "Verrà rimosso l'oggetto con id "},
                {"removeTitle", "Conferma rimozione"},
                {"countMessage1", "Immettere un valore per il campo fatturato annuo"},
                {"countMessage2", "Valore fatturato annuo errato" +
                        "(deve essere immesso un valore double).\nRiprova"},
                {"countTitle", " conteggio del fatturato annuo"},
                {"filterLabel", "Filtro"},
                {"filterCheckBox", "on."},
                {"scriptResultsTitle", "Risultati dell'esecuzione dello script"},
                {"helpTip", "visualizzare la guida per i comandi disponibili"},
                {"helpTitle", "Comandi disponibili"}
        };
    }
}
