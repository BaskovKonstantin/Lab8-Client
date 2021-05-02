package please.help.resources.scriptExecutorService;

import java.util.ListResourceBundle;

public class ScriptExecutorServiceResources_es_NI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"recursionMessage1", " Cadena "},
                {"recursionMessage2", ":\nTrabajo para ejecutar la recursión. ¿Continuar ejecutando el script?"},
                {"recursionOptions", new String [] {"Continuar", " No entrar en recursión"}},
                {"handlingErrorMessage1", "Error durante el análisis del script "},
                {"handlingErrorMessage2",":\nCadena "},
                {"handlingErrorMessage3", "\nPuede:\n"+
                        "- corregir la línea incorrecta en el archivo y continuar con la ejecución"+
                        "(todas las líneas anteriores deben permanecer sin cambios)\n"+
                        "- omita la línea incorrecta y continúe con\n" +
                        "- abortar la ejecución del script (se ejecutarán los comandos que se analizarán) / abortar la adición de\n"+
                        "nuevo objeto si está en modo de creación (el análisis del script continuará)"},
                {"handlingErrorOptions", new String [] {"Actualizar", "Omitir", "Abortar"}},
                {"endOfFileError", "No hay más datos en el archivo,pero el siguiente objeto no se ha creado completamente."}
        };
    }
}
