package please.help.resources.scriptExecutorService;

import java.util.ListResourceBundle;

public class ScriptExecutorServiceResources_it_IT extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"recursionMessage1", "Stringa "},
                {"recursionMessage2", ":\nSi tenta di eseguire la ricorsione. Continuare l'esecuzione dello script?"},
                {"recursionOptions", new String [] {"Continua", "Non entrare nella ricorsione"}},
                {"handlingErrorMessage1", "Errore durante l'analisi dello script "},
                {"handlingErrorMessage2",":\nStringa "},
                {"handlingErrorMessage3", "\nPuoi: \n" +
                        "- correggere la stringa non corretta nel file e continuare l'esecuzione" +
                        "(tutte le righe precedenti devono rimanere invariate)\n" +
                        "- Salta la riga non corretta e continua l'esecuzione\n" +
                        "- Interrompi l'esecuzione dello script (i comandi analizzati verranno eseguiti) / Interrompi l'aggiunta di\n" +
                        "nuovo oggetto se in modalità di creazione (l'analisi dello script continuerà)"},
                {"handlingErrorOptions", new String [] {"Aggiorna", "Salta", "Interrompi"}},
                {"endOfFileError", "Non ci sono più dati nel file,ma un altro oggetto non è stato creato completamente."}
        };
    }
}
