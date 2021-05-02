package please.help.resources.client;

import java.util.ListResourceBundle;

public class ClientResources_en_NI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"invalidServerData", "Datos incorrectos para la conexión con el servidor"},
                {"serverDead", "Se Produjo un error al interactuar con el servidor."},
                {"initError", "Error al inicializar el cliente"},
                {"connectionError", "No se pudo conectar al servidor"}
        };
    }
}
