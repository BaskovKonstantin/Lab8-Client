package please.help.resources.commands;

import java.util.ListResourceBundle;

public class CommandsResources_es_NI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"invalidCommand", "Comando introducido Incorrectamente."},
                {"countValidateError", "Ee debe introducir un valor de tipo double."},
                {"countScriptValidateError", "El Comando debe ingresarse junto con un valor de tipo double."},
                {"emptyLoginError", "El Inicio de Sesión no puede ser un campo vacío"},
                {"spacesInLoginError", "No debe haber espacios en el Inicio de sesión"},
                {"shortPasswordError", "La Contraseña debe tener al menos 5 caracteres"},
                {"spacesInPasswordError", "No debe haber espacios en la contraseña"},
                {"differentPasswordsError", "Las Contraseñas son diferentes"},
                {"averageTitle", "Promedio de facturación anual"},
                {"countTitle", "Recuento por valor de facturación anual"},
                {"historyTitle", "Historia"},
                {"infoTitle", "Información de la colección"},
                {"typeTitle", "Tipos de objetos en orden ascendente"},
                {"removeValidateError", "El Comando debe ingresarse junto con un valor de tipo long."},
                {"showError", "No se puede imprimir la colección - respuesta incorrecta del servidor."},
                {"helpText", "lista de comandos disponibles para el script:\n" +
                        "help: muestra ayuda sobre los comandos disponibles\n"+
                        "info: Mostrar en el flujo de salida estándar información sobre" +
                        "colecciones (tipo, número de elementos, etc.)\n"+
                        "show: muestra todos los elementos de la colección\n en el flujo de salida estándar" +
                        "add {element}: añadir nuevo elemento a la colección\n"+
                        "update id {element}: actualizar el valor de un elemento de colección cuyo id es igual al especificado\n"+
                        "remove_by_id id: eliminar un elemento de la colección por su id \n"+
                        "borrar: eliminar de la colección todos los elementos que pertenecen al usuario\n"+
                        "execute_script file_name: Leer y ejecutar el script desde el archivo especificado\n"+
                        "add_if_max {element}: añadir un nuevo elemento a la colección si" +
                        "su valor excede el valor del elemento más grande" +
                        "esta colección (ordenar por annualTurnover)\n"+
                        "remove_greater {element}: eliminar todos los elementos de la colección," +
                        "excediendo el especificado (ordenar por el campo annualTurnover)\n"+
                        "history: muestra los últimos 5 comandos (sin sus argumentos)\n"+
                        "average_of_annual_turnover: muestra el valor medio del campo annualTurnover" +
                        "para todos los elementos de la colección\n"+
                        "count_by_annual_turnover annualTurnover : Mostrar el número de elementos," +
                        "el valor del campo annualTurnover es igual al especificado\n"+
                        "print_field_ascending_type : muestra los valores del campo type de todos los elementos en" +
                        "orden ascendente\n"}
        };
    }
}
