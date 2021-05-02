package please.help.gui;

import please.help.organizationBuilding.Organization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;

public class VisualizingPanel extends JPanel {

    private static final HashMap<String, Color> userColors = new HashMap<>();
    private static MainFrame frame;
    private static Font font;

    private static final JPopupMenu circlesPopUp = new JPopupMenu();
    private static final JMenuItem updateItem = new JMenuItem("update");
    private static final JMenuItem removeItem = new JMenuItem("remove");
    private static Organization chosenOrg;

    static {
        updateItem.addActionListener(e -> frame.getAddDialog()
                .setCommand(AddDialog.AddCommands.UPDATE).setFieldsFromOrg(chosenOrg).setVisible(true));
        circlesPopUp.add(updateItem);
        removeItem.addActionListener(e -> frame.showRemoveDialog(chosenOrg));
        circlesPopUp.add(removeItem);
    }

    public static Color getUserColor(String login){
        if (userColors.containsKey(login)) return userColors.get(login);
        else{
            float r = (float) (Math.random() / 2 + 0.5);
            float g = (float) (Math.random() / 2 + 0.5);
            float b = (float) (Math.random() / 2 + 0.5);

            Color userColor = new Color(r, g, b);
            userColors.put(login, userColor);
            return userColor;
        }
    }

    private static float getRadiusFromAnnual(double annualTurnover){
        if (annualTurnover <= Math.pow(10, 5)) return 50;
        else if (annualTurnover <= 5*Math.pow(10, 5)) return 60;
        else if (annualTurnover <= Math.pow(10, 6)) return 70;
        else if (annualTurnover <= 5*Math.pow(10,6)) return 80;
        else if (annualTurnover <= 5*Math.pow(10,8)) return 90;
        else if (annualTurnover <= Math.pow(10, 9)) return 100;
        else if (annualTurnover <= 5*Math.pow(10, 9)) return 110;
        else if (annualTurnover <= Math.pow(10, 10)) return 120;
        else if (annualTurnover <= Math.pow(10, 11)) return 130;
        else if (annualTurnover <= 5*Math.pow(10, 11)) return 140;
        else if (annualTurnover <= Math.pow(10, 12)) return 150;
        else return 375;
    }

    private final Color color;
    private float radius = 0;
    private float newRadius;
    private float dr;
    private int x;
    private int y;
    private Organization org;
    private boolean delete = false;
    private final Ellipse2D.Float circle = new Ellipse2D.Float();

    private final Timer timer = new Timer(40, e -> {
        if ((dr > 0 && radius < newRadius) || (dr < 0 && radius > newRadius)) {
            radius += dr;
            repaint();
        }
    });

    public VisualizingPanel(Organization org){
        this.color = getUserColor(org.getOwner());
        this.newRadius = getRadiusFromAnnual(org.getAnnualTurnover());
        this.x = org.getCoordinates().getX();
        this.y = org.getCoordinates().getY();
        this.org = org;
        dr = (newRadius - radius) / 60;
        timer.start();

        VisualizingPanel panelForMouse = this;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON1){
                    if (circle.contains(e.getPoint())){
                        frame.getAddDialog().setFieldsFromOrg(org)
                                .setCommand(AddDialog.AddCommands.SHOW)
                                .setVisible(true);
                    }
                    circlesPopUp.setVisible(false);
                }
                else if (e.getButton() == MouseEvent.BUTTON3){
                    if (circle.contains(e.getPoint())){
                        chosenOrg = panelForMouse.org;
                        if (frame.getData().getLogin().equals(org.getOwner())){
                            updateItem.setEnabled(true);
                            removeItem.setEnabled(true);
                        }
                        else{
                            updateItem.setEnabled(false);
                            removeItem.setEnabled(false);
                        }
                        circlesPopUp.show(panelForMouse, e.getX(), e.getY());
                    }
                    else{
                        circlesPopUp.setVisible(false);
                    }
                }
            }
        });
    }

    public void updatePanel(Organization org){
        this.org = org;
        if (org.getCoordinates().getX() != x || org.getCoordinates().getY() != y){
            x = org.getCoordinates().getX();
            y = org.getCoordinates().getY();
            frame.deleteVisualizingPanel(org);
            frame.addVisualizingPanel(this);
        }

        if (getRadiusFromAnnual(org.getAnnualTurnover()) != radius){
            if (timer.isRunning()) timer.stop();
            newRadius = getRadiusFromAnnual(org.getAnnualTurnover());
            dr = (newRadius - radius) / 60;
            timer.start();
        }
    }

    public void deletePanel(){
        if (timer.isRunning()) timer.stop();
        newRadius = 0;
        dr = (newRadius - radius) / 60;
        delete = true;
        timer.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int)(2 * newRadius + 10), (int)(2 * newRadius + 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Dimension size = this.getSize();
        FontMetrics metrics = g2d.getFontMetrics(font);
        float d = 2 * radius;
        float x = (size.width - d) / 2;
        float y = (size.height - d) / 2;
        circle.setFrame(x, y, d, d);
        g2d.setColor(color);
        g2d.fill(circle);
        g2d.setColor(color.darker());
        g2d.draw(new Ellipse2D.Float(x, y, d, d));

        g2d.setColor(Color.WHITE);
        x = (float) ((size.width - metrics.stringWidth(org.getId() + " " + org.getName())) / 2.0);
        y = (float) (size.height / 2.0 - metrics.getHeight());
        g2d.setFont(font);
        g2d.drawString(org.getId() + " " + org.getName(), x, y);
        x = (float) ((size.width - metrics.stringWidth(org.getOwner())) / 2.0);
        y = (float) (size.height / 2.0);
        g2d.drawString(org.getOwner(), x, y);

        if (timer.isRunning() && ((dr > 0 && radius >= newRadius) || (dr < 0 && radius <= newRadius))) {
            timer.stop();
            if (delete) {
                frame.deleteVisualizingPanel(org);
            }
        }
    }

    public static class Pair{
        public final JPanel panel;
        public final int coord;

        public Pair(JPanel panel, int coord){
            this.panel = panel;
            this.coord = coord;
        }
    }

    public int getCoordY() {
        return y;
    }

    public int getCoordX() {
        return x;
    }

    public static void setFrame(MainFrame frame) {
        VisualizingPanel.frame = frame;
    }

    public static void setCirclesFont(Font font) {
        VisualizingPanel.font = font;
    }
}
