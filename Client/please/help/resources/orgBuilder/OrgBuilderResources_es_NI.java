package please.help.resources.orgBuilder;

import java.util.ListResourceBundle;

public class OrgBuilderResources_es_NI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"spaceError", "No debe haber espacios en los valores de los campos."},
                {"nullFieldError", "El Campo no puede estar vacío"},
                {"tooLongError", "La Longitud de los valores de cadena está limitada a 250 caracteres."},
                {"maxXError", "Valor máximo del campo: 765"},
                {"maxYError", "Valor máximo del campo: 450"},
                {"annualTurnoverError", "El valor del campo debe ser mayor que 0"},
                {"shortZipCodeError", "La longitud de la cadena debe ser al menos 4"},
                {"finishedError", "Los valores de todos los campos ya están ingresados"},
                {"illegalArgument", "Error: el valor no coincide con el tipo de campo."}
        };
    }
}
