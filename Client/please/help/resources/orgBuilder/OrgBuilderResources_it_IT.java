package please.help.resources.orgBuilder;

import java.util.ListResourceBundle;

public class OrgBuilderResources_it_IT extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"spaceError", "Non ci dovrebbero essere spazi nei valori dei campi."},
                {"nullFieldError", "Il campo non può essere vuoto"},
                {"tooLongError", "La lunghezza dei valori stringa è limitata a 250 caratteri."},
                {"maxXError", "Valore massimo del campo: 765"},
                {"maxYError", "Valore massimo del campo: 450"},
                {"annualTurnoverError", "Il valore del campo deve essere maggiore di 0"},
                {"shortZipCodeError", "La lunghezza della stringa deve essere almeno 4"},
                {"finishedError", "I valori di tutti i campi sono già stati immessi"},
                {"illegalArgument", "Errore: il valore non corrisponde al tipo di campo."}
        };
    }
}
