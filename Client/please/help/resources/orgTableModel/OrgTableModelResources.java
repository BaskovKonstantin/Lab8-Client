package please.help.resources.orgTableModel;

import java.util.ListResourceBundle;

public class OrgTableModelResources extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"columnNames", new String[]{"id", "Название", "Координата X", "Координата Y", "Годовой оборот"
                        , "Тип", "Улица", "Индекс", "Дата и время создания", "Владелец"}},
        };
    }
}
