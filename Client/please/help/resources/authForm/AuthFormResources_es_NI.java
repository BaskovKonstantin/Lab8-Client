package please.help.resources.authForm;

import java.util.ListResourceBundle;

public class AuthFormResources_es_NI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"header1", "Servidor"},
                {"ipLabel", "ip"},
                {"portLabel", "Puerto"},
                {"authorization", "Autorización"},
                {"registration", "Registro"},
                {"loginLabel", "Inicio de sesión"},
                {"passwordLabel", "Contraseña"},
                {"passwordCheckerLabel", "Repetir contraseña"},
                {"SIGN IN", "SIGN IN"},
                {"SIGN UP", "SIGN UP"},
                {"portError", "Valor de puerto incorrecto"},
                {"textForGuide", "- El Inicio de sesión no puede ser una cadena" +
                        " vacía, no debe haber espacios en su interior\n" +
                        "- No puede haber espacios dentro de la contraseña, la longitud es un mínimo de 5 caracteres"},
                {"languageLabel", "Idioma:"}
        };
    }
}
