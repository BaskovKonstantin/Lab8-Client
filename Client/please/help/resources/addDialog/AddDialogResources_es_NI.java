package please.help.resources.addDialog;

import java.util.ListResourceBundle;

public class AddDialogResources_es_NI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"creationDateLabel", "Fecha de creación:"},
                {"ownerLabel", "Propietario:"},
                {"nameLabel", "Nombre (String, no null)"},
                {"coordinatesLabel", "Coordenadas"},
                {"annualTurnoverLabel", "Facturación anual (double, mayor que 0)"},
                {"typeLabel", "Tipo de organización:"},
                {"addressLabel", "Dirección"},
                {"streetLabel", "Calle (String, puede ser null):"},
                {"zipCodeLabel", "Índice (String, longitud de cadena al menos 4, puede ser null):"},
                {"okButton", "Listo"},
                {"cancelButton", "Cancelar"},
                {"FIELDS_FOR_ERRORS", new String [] {"Nombre: ", "coordenada X:", " Coordenada Y: "
                        , "Facturación anual:", "Tipo de organización:", "Calle:", "Índice: "}},
                {"show", "Información del objeto"},
                {"add", "Creación de objetos"},
                {"update", "Actualizar objeto"}
        };
    }
}
