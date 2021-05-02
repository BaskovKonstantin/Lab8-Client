package please.help.gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TablePopUpHelper extends MouseAdapter {

    private final MainFrame frame;

    public TablePopUpHelper(MainFrame frame){
        this.frame = frame;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point point = e.getPoint();
        int clickedRow = frame.getOrgTable().rowAtPoint(point);
        frame.setChosenOrg(frame.getData().getCollection().get(frame.getOrgTable()
                .convertRowIndexToModel(clickedRow)));
        frame.getOrgTable().setRowSelectionInterval(clickedRow, clickedRow);

        if (frame.getChosenOrg().getOwner().equals(frame.getData().getLogin())){
            frame.getUpdateItem().setEnabled(true);
            frame.getRemoveItem().setEnabled(true);
        }
        else{
            frame.getUpdateItem().setEnabled(false);
            frame.getRemoveItem().setEnabled(false);
        }
    }
}
