package please.help.resources;

import java.util.ListResourceBundle;

public class CommandsResources_es_NI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"orgAdded", "Elemento agregado"},
                {"addError", "Elemento no agregado: se produjo un error al agregar el elemento a la tabla."},
                {"smallAddError", " Elemento no agregado"},
                {"averageMessage", "Promedio del campo de facturación anual para todos los elementos de la colección: "},
                {"clearDBError", " Elementos no eliminados: error al acceder a la tabla."},
                {"clearMessage", "Se eliminan todos los elementos que pertenecen al usuario."},
                {"countMessage", "Número de elementos de colección con un valor de facturación anual igual a "},
                {"historyMessage", "5 últimos comandos "},
                {"infoTitle", "Información de la colección:\n"},
                {"infoType", "Tipo"},
                {"infoNumber", "Número de elementos"},
                {"infoOrgsTable", "\nTabla con organizaciones"},
                {"infoUsersTable", "\ntabla con datos de usuario"},
                {"removeIDDBError", "Elementos no eliminados: error al acceder a la tabla."},
                {"removeIDMessage", "Elemento eliminado."},
                {"removeIDError", "el Elemento no se ha eliminado , el elemento con ese id no existe" +
                        "o no pertenece al usuario."},
                {"removeGreaterDBError", " Elementos no eliminados: error al acceder a la tabla."},
                {"removeGreaterMessage", "Elementos eliminados."},
                {"updateDBError", "el Elemento no está actualizado - error al acceder a la tabla."},
                {"updateError", "Elemento no actualizado - no" +
                        "un elemento con ese id o no pertenece al usuario."},
                {"updateMessage", " Elemento actualizado."}
        };
    }
}
