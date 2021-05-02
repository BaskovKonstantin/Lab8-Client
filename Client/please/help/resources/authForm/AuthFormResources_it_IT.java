package please.help.resources.authForm;

import java.util.ListResourceBundle;

public class AuthFormResources_it_IT extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"header1", "Server"},
                {"ipLabel", "ip"},
                {"portLabel", "Port"},
                {"authorization", "Autorizzazione"},
                {"registration", "Deposito"},
                {"loginLabel", "Login"},
                {"passwordLabel", "Password"},
                {"passwordCheckerLabel", "Ripeti password"},
                {"SIGN IN", "SIGN IN"},
                {"SIGN UP", "SIGN UP"},
                {"portError", "Valore della porta non valido"},
                {"textForGuide", "- Il login non può essere una stringa vuota, non ci " +
                        "dovrebbero essere spazi al suo interno\n" +
                        "- Non ci sono spazi all'interno della password, la lunghezza è di almeno 5 caratteri"},
                {"languageLabel", "Lingua:"}
        };
    }
}
