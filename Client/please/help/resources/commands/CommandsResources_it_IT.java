package please.help.resources.commands;

import java.util.ListResourceBundle;

public class CommandsResources_it_IT extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"invalidCommand", "Comando inserito in modo errato."},
                {"countValidateError", "Deve essere immesso un valore di tipo double."},
                {"countScriptValidateError", "Il comando deve essere immesso insieme a un valore di tipo double."},
                {"emptyLoginError", "Login non può essere un campo vuoto"},
                {"spacesInLoginError", "Non ci dovrebbero essere spazi nel login"},
                {"shortPasswordError", "La password deve essere di almeno 5 caratteri"},
                {"spacesInPasswordError", "La password non deve contenere spazi"},
                {"differentPasswordsError", "Le password sono diverse"},
                {"averageTitle", "Media del fatturato annuo"},
                {"countTitle", "Conteggio del fatturato annuo"},
                {"historyTitle", "Storia"},
                {"infoTitle", "Informazioni sulla raccolta"},
                {"typeTitle", "Tipi di oggetti in ordine crescente"},
                {"removeValidateError", "Il comando deve essere immesso insieme a un valore di tipo long."},
                {"showError", "Impossibile stampare la raccolta - risposta errata dal server."},
                {"helpText", "elenco dei comandi disponibili per lo script:\n" +
                        "help: visualizza la guida per i comandi disponibili\n" +
                        "info: visualizza nel flusso di output standard le informazioni su" +
                        "collezioni (tipo, numero di elementi, ecc.) \n" +
                        "show: Visualizza tutti gli elementi della raccolta\n Nel flusso di output standard" +
                        "add {element}: aggiungi un nuovo elemento alla raccolta\n" +
                        "update id {element}: aggiorna il valore dell'elemento della raccolta il cui id è uguale a\n" +
                        "remove_by_id id: rimuovere un elemento dalla raccolta dal suo id\n" +
                        "clear: Rimuovi Dalla raccolta tutti gli elementi che appartengono all'utente\n" +
                        "execute_script file_name: leggere ed eseguire lo script dal file specificato\n" +
                        "add_if_max {element}: aggiungi un nuovo elemento alla raccolta se" +
                        "il suo valore supera il valore dell'elemento più grande" +
                        "questa raccolta (Ordina per campo annualTurnover)\n" +
                        "remove_greater {element}: rimuovi tutti gli elementi dalla raccolta," +
                        "superiore a quello specificato (ordinamento per campo annualTurnover)\n" +
                        "history: Visualizza gli ultimi 5 comandi (senza i loro argomenti)\n" +
                        "average_of_annual_turnover: visualizza la media del campo annualTurnover" +
                        "per tutti gli elementi della raccolta\n" +
                        "count_by_annual_turnover annualTurnover: visualizza il numero di elementi," +
                        "il valore del campo annualTurnover è uguale a\n" +
                        "print_field_ascending_type: visualizza i valori del campo type di tutti gli elementi in" +
                        "ordine crescente\n"}
        };
    }
}
