package please.help.resources.mainFrame;

import java.util.ListResourceBundle;

public class MainFrameResources_es_NI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"tableTab", "Tabla"},
                {"visualizationTab", "Visualización"},
                {"updateTip", "actualizar el valor del elemento de colección"},
                {"removeTip", "eliminar elemento de la colección"},
                {"addMenu", "Adición"},
                {"deleteMenu", "Borrar"},
                {"addTip", "añadir nuevo elemento a la colección"},
                {"addIfMaxTip", "añadir un nuevo elemento a la colección si su valor" +
                        "excede el valor del elemento más grande" +
                        "de esta colección (clasificación por campo de facturación anual)"},
                {"removeGreaterTip", "eliminar todos los elementos de la colección," +
                        "excediendo el especificado (clasificación por campo de facturación anual)"},
                {"clearTip", " eliminar todos los objetos de usuario"},
                {"funFactsMenu", "Estadísticas"},
                {"otherThingsMenu", "Otros"},
                {"historyTip", "mostrar los últimos 5 comandos"},
                {"averageOfAnnualTurnoverTip", "mostrar el valor promedio del campo de facturación anual" +
                        "para todos los elementos de la colección"},
                {"printFieldAscendingTypeTip", "mostrar los valores del campo type de todos los elementos en orden ascendente"},
                {"infoTip", "mostrar información de la colección"},
                {"countByAnnualTurnoverTip", "muestra el número de elementos," +
                        "el valor del campo de facturación anual es igual al especificado"},
                {"executeScriptTip", "leer y ejecutar el script desde el archivo especificado"},
                {"scriptChooserTitle", "Seleccione el archivo de script"},
                {"languagesMenu", "Idioma"},
                {"connectionErrorOptions1", new String [] {"Repetir poptyka", "Detener"}},
                {"connectionErrorOptions2", new String [] {"Repetir poptyk", "Cerrar programa"}},
                {"connectionErrorTitle", "Error de conexión"},
                {"connectionErrorMessage", "No se puede conectar al servidor"},
                {"scriptErrorTitle", "Error de script"},
                {"fatalScriptErrorMessage1", "Error fatal durante el análisis del script:\n"},
                {"fatalScriptErrorMessage2", "\nLa escritura no se ejecutará"},
                {"fatalScriptErrorTitle", "Error de script"},
                {"clearMessage", "Se eliminarán todos sus objetos. ¿Está de acuerdo?"},
                {"clearTitle", "Confirmación de eliminación"},
                {"removeMessage", "Se eliminará el objeto con id "},
                {"removeTitle", "Confirmación de eliminación"},
                {"countMessage1", "Introduzca el valor del campo de facturación anual"},
                {"countMessage2", "Valor de facturación anual introducido Incorrectamente" +
                        "(se debe introducir un valor de tipo double).\nInténtalo de nuevo"},
                {"countTitle", "Recuento por valor de facturación anual"},
                {"filterLabel", "Filtrado"},
                {"filterCheckBox", "incl."},
                {"scriptResultsTitle", "Resultados de la ejecución del script"},
                {"helpTip", "muestra la ayuda de los comandos disponibles"},
                {"helpTitle", "Comandos disponibles"}
        };
    }
}
