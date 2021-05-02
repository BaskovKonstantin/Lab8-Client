package please.help.resources.orgTableModel;

import java.util.ListResourceBundle;

public class OrgTableModelResources_it_IT extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"columnNames", new String [] {"id", "Titolo", "Coordinata X", "Coordinata Y", "Fatturato annuo"
                        , "Tipo", "Via", "Indice", "Data e ora di creazione", "Proprietario"}}
        };
    }
}
