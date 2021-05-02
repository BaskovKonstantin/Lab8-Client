package please.help.resources;

import java.util.ListResourceBundle;

public class CommandsResources_it_IT extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"orgAdded", "Elemento aggiunto"},
                {"addError", "Elemento non Aggiunto - si è verificato un errore durante l'aggiunta di un elemento alla tabella."},
                {"smallAddError", "Elemento non aggiunto"},
                {"averageMessage", "Media del campo fatturato annuo per tutti gli elementi della raccolta: "},
                {"clearDBError", "Elementi non rimossi - errore durante l'Accesso alla tabella."},
                {"clearMessage", "Tutti gli elementi di proprietà dell'utente sono stati rimossi."},
                {"countMessage", "Numero di elementi della raccolta con un valore di fatturato annuo pari a "},
                {"historyMessage", "5 ultimi comandi "},
                {"infoTitle", "Informazioni sulla raccolta:\n"},
                {"infoType", "Tipo"},
                {"infoNumber", "Numero di elementi"},
                {"infoOrgsTable", "\nTabella con le organizzazioni"},
                {"infoUsersTable", "\nTabella dati utente"},
                {"removeIDDBError", "Elementi non rimossi - errore durante l'Accesso alla tabella."},
                {"removeIDMessage", "elemento rimosso."},
                {"removeIDError", "Elemento non rimosso - elemento con tale id non esiste" +
                        "o non appartiene all'utente."},
                {"removeGreaterDBError","Elementi non rimossi - errore durante l'Accesso alla tabella."},
                {"removeGreaterMessage", "Elementi rimossi."},
                {"updateDBError", "Elemento non aggiornato - errore durante l'Accesso alla tabella."},
                {"updateError", "Elemento non aggiornato - No" +
                        "un elemento con tale id o non appartiene all'utente."},
                {"updateMessage", "elemento aggiornato."}
        };
    }
}
