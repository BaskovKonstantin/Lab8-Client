package please.help.gui;

import please.help.ScriptExecutorService;
import please.help.commands.*;
import please.help.network.ChangeEvent;
import please.help.network.Client;
import please.help.organizationBuilding.Organization;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainFrame extends JFrame{
    private JTable orgTable;
    private JPanel rootPanel;
    private JScrollPane tableScrollPane;
    private JLabel loginLabel;
    private JLabel commandInfo;
    private JTabbedPane tabbedPane;
    private JScrollPane visualScrollPane;
    private JPanel visualPanel;
    private JPanel tablePanel;
    private JComboBox columnFilterChooser;
    private JComboBox valueFilterChooser;
    private JLabel filterLabel;
    private JCheckBox filterCheckBox;

    private final JPanel square0 = new JPanel();
    private final JPanel square1 = new JPanel();
    private final JPanel square2 = new JPanel();
    private final JPanel square3 = new JPanel();

    private final ArrayList<VisualizingPanel.Pair> square0List = new ArrayList<>();
    private final ArrayList<VisualizingPanel.Pair> square1List = new ArrayList<>();
    private final ArrayList<VisualizingPanel.Pair> square2List = new ArrayList<>();
    private final ArrayList<VisualizingPanel.Pair> square3List = new ArrayList<>();

    private final HashMap<Long, VisualizingPanel> orgCircles = new HashMap<>();

    private final HashMap<Integer, StringBuilder> scriptResults = new HashMap<>();

    private final JMenuBar menuBar;
    private final ClientLocalData data;
    private final OrgTableModel orgTableModel;
    private final JPopupMenu popupMenu;
    private final JMenuItem updateItem;
    private final JMenuItem removeItem;
    private AuthForm authForm;
    private final AddDialog addDialog;
    private final ScriptResultsDialog scriptResultsDialog = new ScriptResultsDialog();
    private final TableRowSorter<OrgTableModel> rowSorter;

    private Organization chosenOrg;

    private final ScheduledExecutorService collectionUpdaterService = Executors.newSingleThreadScheduledExecutor();
    private final ExecutorService commandSenderService = Executors.newSingleThreadExecutor();

    public static Locale currentLocale;
    private static final Locale[] supportedLocales = new Locale[]{new Locale("ru", "RU"),
            new Locale("sr", "RS"), new Locale("it", "IT"),
            new Locale("es", "NI")};
    private ResourceBundle resourceBundle;

    private final JRadioButtonMenuItem ruLang = new JRadioButtonMenuItem(supportedLocales[0]
            .getDisplayName(supportedLocales[0]));
    private final JRadioButtonMenuItem srLang = new JRadioButtonMenuItem(supportedLocales[1]
            .getDisplayName(supportedLocales[1]));
    private final JRadioButtonMenuItem itLang = new JRadioButtonMenuItem(supportedLocales[2]
            .getDisplayName(supportedLocales[2]));
    private final JRadioButtonMenuItem esLang = new JRadioButtonMenuItem(supportedLocales[3]
            .getDisplayName(supportedLocales[3]));

    public MainFrame(){
        super("Labka 8");
        for (Locale locale : supportedLocales){
            if (locale.equals(Locale.getDefault())) {
                currentLocale = locale;
            }
        }
        if (currentLocale == null) currentLocale = supportedLocales[0];

        setContentPane(rootPanel);

        data = new ClientLocalData();
        orgTableModel = new OrgTableModel(data);
        orgTable.setModel(orgTableModel);

        rowSorter = (TableRowSorter<OrgTableModel>) orgTable.getRowSorter();
        columnFilterChooser.setEnabled(false);
        valueFilterChooser.setEnabled(false);

        filterCheckBox.addActionListener(e -> {
            if (filterCheckBox.isSelected()){
                valueFilterChooser.setEnabled(true);
                columnFilterChooser.setEnabled(true);
                updateValueFilterChooser();
            }
            else{
                valueFilterChooser.setEnabled(false);
                columnFilterChooser.setEnabled(false);
                rowSorter.setRowFilter(null);
            }
        });

        columnFilterChooser.addActionListener(e ->{
            if (columnFilterChooser.getItemCount() != 0){
                updateValueFilterChooser();
            }
        });

        valueFilterChooser.addActionListener(e -> {
            if (valueFilterChooser.getItemCount() != 0){
                filterRows();
            }
        });

        popupMenu = new JPopupMenu();
        updateItem = new JMenuItem("update");
        removeItem = new JMenuItem("remove");

        popupMenu.add(updateItem);
        popupMenu.add(removeItem);

        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(ruLang);
        radioGroup.add(itLang);
        radioGroup.add(esLang);
        radioGroup.add(srLang);
        ruLang.addActionListener(e -> languageRadioListener());
        itLang.addActionListener(e -> languageRadioListener());
        esLang.addActionListener(e -> languageRadioListener());
        srLang.addActionListener(e -> languageRadioListener());

        addDialog = new AddDialog(this);

        orgTable.setComponentPopupMenu(popupMenu);

        orgTable.addMouseListener(new TablePopUpHelper(this));
        orgTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orgTable.getTableHeader().setReorderingAllowed(false);

        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        CommandSender.setFrame(this);
        localize();

        visualPanel.setLayout(new GridLayout(2, 2));
        square0.setLayout(new GridLayout(1, 0));
        square1.setLayout(new GridLayout(1, 0));
        square2.setLayout(new GridLayout(1, 0));
        square3.setLayout(new GridLayout(1, 0));
        visualPanel.add(square1);
        visualPanel.add(square0);
        visualPanel.add(square2);
        visualPanel.add(square3);

        VisualizingPanel.setFrame(this);
        VisualizingPanel.setCirclesFont(orgTable.getFont());
        ScriptExecutorService.setFrame(this);
    }

    public void localize(){
        resourceBundle = ResourceBundle.getBundle("please.help.resources.mainFrame.MainFrameResources"
                , currentLocale);
        tabbedPane.setTitleAt(0, resourceBundle.getString("tableTab"));
        tabbedPane.setTitleAt(1, resourceBundle.getString("visualizationTab"));
        updateItem.setToolTipText(resourceBundle.getString("updateTip"));
        removeItem.setToolTipText(resourceBundle.getString("removeTip"));
        filterLabel.setText(resourceBundle.getString("filterLabel"));
        filterCheckBox.setText(resourceBundle.getString("filterCheckBox"));
        orgTableModel.localize();
        orgTableModel.fireTableStructureChanged();

        rowSorter.setComparator(4, (Comparator<String>) (o1, o2) -> {
            NumberFormat format = NumberFormat.getCurrencyInstance(currentLocale);
            format.setCurrency(Currency.getInstance("USD"));
            try {
                return Double.compare(format.parse(o1).doubleValue(), format.parse(o2).doubleValue());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        });

        rowSorter.setComparator(8, (Comparator<String>) (o1, o2) -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT
                    , FormatStyle.SHORT).withLocale(currentLocale);
            return LocalDateTime.parse(o1, dateTimeFormatter).compareTo(LocalDateTime.parse(o2, dateTimeFormatter));
        });

        data.updateFilteringFields();
        updateValueFilterChooser();
        commandSenderService.submit(new CommandSender(null, (mainFrame, feedback) -> Client.localize()));
        addDialog.localize();
        Command.localize();
        columnFilterChooser.removeAllItems();
        for (String column : orgTableModel.getColumnNames()) columnFilterChooser.addItem(column);
        menuBar.removeAll();
        initMenus();
    }

    public void initAuth(){
        authForm = new AuthForm(this);
        authForm.setVisible(true);
    }

    public void showConnectionErrorDialog(){
        String[] options;
        if (authForm.isVisible()) options = (String[]) resourceBundle.getObject("connectionErrorOptions1");
        else options = (String[]) resourceBundle.getObject("connectionErrorOptions2");
        String title = resourceBundle.getString("connectionErrorTitle");
        String message = resourceBundle.getString("connectionErrorMessage");

        int result = JOptionPane.showOptionDialog(this, message, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                null, options, null);

        if (result == JOptionPane.CLOSED_OPTION || result == 0) Client.unpause();
        else {
            if (authForm.isVisible()){
                Client.setStop(true);
                Client.unpause();
            }
            else exitProcedure();
        }
    }

    public void showScriptErrorDialog(ScriptExecutorService executorService, String errorMessage, String[] options){
        String title = resourceBundle.getString("scriptErrorTitle");
        int result = JOptionPane.showOptionDialog(this, errorMessage, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);
        if (result == -1) result = 2;
        executorService.setDialogResult(result);
        executorService.unpause();
    }

    public void showFatalScriptErrorMessage(String message){
        String info = resourceBundle.getString("fatalScriptErrorMessage1") + message +
                resourceBundle.getString("fatalScriptErrorMessage2");
        JOptionPane.showMessageDialog(this, info, resourceBundle.getString("fatalScriptErrorTitle")
                , JOptionPane.ERROR_MESSAGE);
    }

    public void showScriptResults(int id){
        //System.out.println(scriptResults.get(id));
        if (scriptResults.get(id).length() > 0){
            scriptResultsDialog.setResults(scriptResults.get(id).toString());
            scriptResultsDialog.setTitle(resourceBundle.getString("scriptResultsTitle"));
            scriptResultsDialog.setVisible(true);
        }
        scriptResults.remove(id);
    }

    public void showClearDialog(){
        int result = JOptionPane.showConfirmDialog(this, resourceBundle.getString("clearMessage")
                , resourceBundle.getString("clearTitle")
                , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) commandSenderService.submit(new Clear().getCommandSender());
    }

    public void showRemoveDialog(Organization org){
        int result = JOptionPane.showConfirmDialog(this,
                resourceBundle.getString("removeMessage") + org.getId(),
                resourceBundle.getString("removeTitle"),
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION){
            Command command = new Remove_by_id();
            command.validateCommand(org.getId().toString());
            commandSenderService.submit(command.getCommandSender());
        }
    }

    public void showInfo(String info, String title){
        JOptionPane.showMessageDialog(this, info, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void showCountDialog(){
        String result = JOptionPane.showInputDialog(this, resourceBundle.getString("countMessage1"),
                resourceBundle.getString("countTitle"), JOptionPane.QUESTION_MESSAGE);
        Command command = new Count_by_annual_turnover();
        if (result != null) command.validateCommand(result);

        while (result != null && !command.isValid()){
            result = JOptionPane.showInputDialog(this, resourceBundle.getString("countMessage2"),
                    resourceBundle.getString("countTitle"), JOptionPane.QUESTION_MESSAGE);
            if (result != null) command.validateCommand(result);
        }
        if (result != null) commandSenderService.submit(command.getCommandSender());
    }

    public void exitProcedure(){
        authForm.dispose();
        dispose();
        System.exit(0);
    }

    public void startUpdate(){
        collectionUpdaterService.scheduleWithFixedDelay(() -> {
            CommandSender sender = new CommandSender(new CheckEvents(data.getLastHash()), (frame, feedback) -> {
                if (feedback instanceof List){
                    Object lastEvent = ((List<?>) feedback).get(((List<?>) feedback).size() - 1);
                    frame.getData().setLastHash(((ChangeEvent)lastEvent).getHash());
                    for (Object e : (List<?>)feedback){
                        ChangeEvent event = (ChangeEvent)e;
                        if ((event.getType()).equals(ChangeEvent.Type.ADDED)){
                            frame.getData().getCollection().add(event.getOrg());
                            VisualizingPanel panel = new VisualizingPanel(event.getOrg());
                            frame.getOrgCircles().put(event.getOrg().getId(), panel);
                            EventQueue.invokeLater(() -> frame.addVisualizingPanel(panel));
                        }
                        else if ((event.getType()).equals(ChangeEvent.Type.UPDATED)){
                            for (int i = 0; i < frame.getData().getCollection().size(); i++){
                                if (frame.getData().getCollection().get(i).getId().equals(event.getOrg().getId())){
                                    frame.getData().getCollection().set(i, event.getOrg());
                                    EventQueue.invokeLater(() -> frame.getOrgCircles().get(event.getOrg().getId())
                                            .updatePanel(event.getOrg()));
                                    break;
                                }
                            }
                        }
                        else if ((event.getType()).equals(ChangeEvent.Type.DELETED)){
                            frame.getData().getCollection().remove(event.getOrg());
                            EventQueue.invokeLater(() -> frame.getOrgCircles().get(event.getOrg().getId())
                                    .deletePanel());
                        }
                    }
                    EventQueue.invokeLater(() -> {
                        frame.getOrgTableModel().fireTableDataChanged();
                        frame.getData().updateFilteringFields();
                        frame.updateValueFilterChooser();
                    });

                    if (frame.getPopupMenu().isVisible() && frame.getChosenOrg() != null){
                        boolean chosenExist = false;
                        for (int i = 0; i < frame.getData().getCollection().size(); i++){
                            if (frame.getData().getCollection().get(i).equals(frame.getChosenOrg())){
                                chosenExist = true;
                                int lambdaI = i;
                                EventQueue.invokeLater(() -> frame
                                        .getOrgTable().setRowSelectionInterval(
                                                frame.getOrgTable().convertRowIndexToView(lambdaI)
                                                , frame.getOrgTable().convertRowIndexToView(lambdaI)));
                                break;
                            }
                        }
                        if (!chosenExist) EventQueue.invokeLater(() -> frame.getPopupMenu().setVisible(false));
                    }

                }
                else if (feedback instanceof String) EventQueue.invokeLater(() -> frame
                        .setCommandInfo(feedback.toString()));
            });
            commandSenderService.submit(sender);
        }, 1, 1, TimeUnit.SECONDS);
    }

    private void initMenus(){
        JMenu addMenu = new JMenu(resourceBundle.getString("addMenu"));
        menuBar.add(addMenu);

        JMenu deleteMenu = new JMenu(resourceBundle.getString("deleteMenu"));
        menuBar.add(deleteMenu);

        JMenuItem add = new JMenuItem("add");
        add.setToolTipText(resourceBundle.getString("addTip"));
        add.addActionListener(e -> {
            addDialog.setCommand(AddDialog.AddCommands.ADD);
            addDialog.setVisible(true);
        });
        addMenu.add(add);

        JMenuItem add_if_max = new JMenuItem("add_if_max");
        add_if_max.setToolTipText(resourceBundle.getString("addIfMaxTip"));
        add_if_max.addActionListener(e -> {
            addDialog.setCommand(AddDialog.AddCommands.ADD_IF_MAX);
            addDialog.setVisible(true);
        });
        addMenu.add(add_if_max);

        JMenuItem remove_greater = new JMenuItem("remove_greater");
        remove_greater.setToolTipText(resourceBundle.getString("removeGreaterTip"));
        remove_greater.addActionListener(e -> {
            addDialog.setCommand(AddDialog.AddCommands.REMOVE_GREATER);
            addDialog.setVisible(true);
        });
        deleteMenu.add(remove_greater);

        updateItem.addActionListener(e ->
                addDialog.setCommand(AddDialog.AddCommands.UPDATE).setFieldsFromOrg(chosenOrg).setVisible(true));

        JMenuItem clear = new JMenuItem("clear");
        clear.setToolTipText(resourceBundle.getString("clearTip"));
        clear.addActionListener(e -> showClearDialog());
        deleteMenu.add(clear);

        removeItem.addActionListener(e -> showRemoveDialog(chosenOrg));

        JMenu funFactsMenu = new JMenu(resourceBundle.getString("funFactsMenu"));
        menuBar.add(funFactsMenu);

        JMenu otherThingsMenu = new JMenu(resourceBundle.getString("otherThingsMenu"));
        menuBar.add(otherThingsMenu);

        JMenuItem history = new JMenuItem("history");
        history.setToolTipText(resourceBundle.getString("historyTip"));
        history.addActionListener(e -> commandSenderService.submit(new History().getCommandSender()));
        otherThingsMenu.add(history);

        JMenuItem average_of_annual_turnover = new JMenuItem("average_of_annual_turnover");
        average_of_annual_turnover.setToolTipText(resourceBundle.getString("averageOfAnnualTurnoverTip"));
        average_of_annual_turnover.addActionListener(e -> commandSenderService.
                submit(new Average_of_annual_turnover().getCommandSender()));
        funFactsMenu.add(average_of_annual_turnover);

        JMenuItem print_field_ascending_type = new JMenuItem("print_field_ascending_type");
        print_field_ascending_type.setToolTipText(resourceBundle.getString("printFieldAscendingTypeTip"));
        print_field_ascending_type.addActionListener(e -> commandSenderService.
                submit(new Print_field_ascending_type().getCommandSender()));
        funFactsMenu.add(print_field_ascending_type);

        JMenuItem info = new JMenuItem("info");
        info.setToolTipText(resourceBundle.getString("infoTip"));
        info.addActionListener(e -> commandSenderService.submit(new Info().getCommandSender()));
        funFactsMenu.add(info);

        JMenuItem count_by_annual_turnover = new JMenuItem("count_by_annual_turnover");
        count_by_annual_turnover.setToolTipText(resourceBundle.getString("countByAnnualTurnoverTip"));
        count_by_annual_turnover.addActionListener(e -> showCountDialog());
        funFactsMenu.add(count_by_annual_turnover);

        JMenuItem execute_script = new JMenuItem("execute_script");
        execute_script.setToolTipText(resourceBundle.getString("executeScriptTip"));
        execute_script.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle(resourceBundle.getString("scriptChooserTitle"));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION){
                scriptResults.put(ScriptExecutorService.seeNextId(), new StringBuilder());
                new Execute_script().validateCommand(fileChooser.getSelectedFile().getAbsolutePath());
            }

        });
        otherThingsMenu.add(execute_script);

        JMenu languagesMenu = new JMenu(resourceBundle.getString("languagesMenu"));
        menuBar.add(languagesMenu);


        if (currentLocale.equals(supportedLocales[0])) ruLang.setSelected(true);
        languagesMenu.add(ruLang);

        if (currentLocale.equals(supportedLocales[1])) srLang.setSelected(true);
        languagesMenu.add(srLang);

        if (currentLocale.equals(supportedLocales[2])) itLang.setSelected(true);
        languagesMenu.add(itLang);

        if (currentLocale.equals(supportedLocales[3])) esLang.setSelected(true);
        languagesMenu.add(esLang);

        JMenuItem help = new JMenuItem("help");
        help.setToolTipText(resourceBundle.getString("helpTip"));
        help.addActionListener(e -> showInfo(Help.getHelpText(), resourceBundle.getString("helpTitle")));
        otherThingsMenu.add(help);
    }

    private void languageRadioListener(){
        if (ruLang.isSelected()){
            //System.out.println("ruLang");
            currentLocale = supportedLocales[0];
        }
        else if (srLang.isSelected()){
            //System.out.println("srLang");
            currentLocale = supportedLocales[1];
        }
        else if (itLang.isSelected()){
            //System.out.println("itLang");
            currentLocale = supportedLocales[2];
        }
        else {
            //System.out.println("esLang");
            currentLocale = supportedLocales[3];
        }
        localize();
    }

    public void addVisualizingPanel(VisualizingPanel panelToAdd){
        if (panelToAdd.getCoordX() >= 0 && panelToAdd.getCoordY() >= 0){
            addVisualToSquare(square0List, square0, panelToAdd);
        }
        else if (panelToAdd.getCoordX() >= 0 && panelToAdd.getCoordY() < 0){
            addVisualToSquare(square3List, square3, panelToAdd);
        }
        else if (panelToAdd.getCoordX() < 0 && panelToAdd.getCoordY() >= 0){
            addVisualToSquare(square1List, square1, panelToAdd);
        }
        else {
            addVisualToSquare(square2List, square2, panelToAdd);
        }
    }

    private void addVisualToSquare(ArrayList<VisualizingPanel.Pair> squareList
            , JPanel square, VisualizingPanel panelToAdd) {
        if (squareList.isEmpty()){
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1));
            squareList.add(new VisualizingPanel.Pair(panel, panelToAdd.getCoordX()));
            square.add(panel);
            panel.add(panelToAdd);
        }
        else{
            boolean flag = false;
            int i = 0;
            for (; i < squareList.size(); i++){
                if (panelToAdd.getCoordX() < squareList.get(i).coord - 100){
                    break;
                }
                else if (panelToAdd.getCoordX() <= squareList.get(i).coord + 100){
                    int j = 0;
                    while (j < squareList.get(i).panel.getComponentCount() &&
                            ((VisualizingPanel) squareList.get(i).panel.getComponent(j)).getCoordY()
                            >= panelToAdd.getCoordY()) j++;
                    squareList.get(i).panel.add(panelToAdd, j);
                    flag = true;
                    break;
                }
            }
            if (!flag){
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(0, 1));
                squareList.add(i, new VisualizingPanel.Pair(panel, panelToAdd.getCoordX()));
                square.add(panel, i);
                panel.add(panelToAdd);
            }
        }
    }

    public void deleteVisualizingPanel(Organization org){
        JPanel parent = (JPanel) orgCircles.get(org.getId()).getParent();
        parent.remove(orgCircles.get(org.getId()));
        orgCircles.remove(org.getId());
        if (parent.getComponentCount() == 0){
            square0List.removeIf(p -> p.panel.equals(parent));
            square1List.removeIf(p -> p.panel.equals(parent));
            square2List.removeIf(p -> p.panel.equals(parent));
            square3List.removeIf(p -> p.panel.equals(parent));
            parent.getParent().remove(parent);
        }
        visualPanel.revalidate();
    }

    public void updateValueFilterChooser(){
        Object selectedItem = valueFilterChooser.getSelectedItem();
        valueFilterChooser.removeAllItems();
        for (String value : data.getFieldsForFiltering().get(columnFilterChooser.getSelectedIndex())){
            valueFilterChooser.addItem(value);
        }
        valueFilterChooser.setSelectedItem(selectedItem);
        filterRows();
    }

    public void filterRows(){
        if (data.getCollection().size() != 0 && filterCheckBox.isSelected()){
            String regex = (String) valueFilterChooser.getSelectedItem();
            if (regex == null) regex = "^$";
            rowSorter.setRowFilter(RowFilter.regexFilter(regex, columnFilterChooser.getSelectedIndex()));
        }
        else rowSorter.setRowFilter(null);
    }

    public ClientLocalData getData() {
        return data;
    }

    public AuthForm getAuthForm() {
        return authForm;
    }

    public OrgTableModel getOrgTableModel() {
        return orgTableModel;
    }

    public ExecutorService getCommandSenderService() {
        return commandSenderService;
    }

    public void setLoginToLabel(String login){
        loginLabel.setText(login);
        loginLabel.setForeground(VisualizingPanel.getUserColor(login));
    }

    public void setCommandInfo(String info){
        commandInfo.setText(info);
    }

    public JMenuItem getUpdateItem() {
        return updateItem;
    }

    public JMenuItem getRemoveItem() {
        return removeItem;
    }

    public JTable getOrgTable() {
        return orgTable;
    }

    public void setChosenOrg(Organization chosenOrg) {
        this.chosenOrg = chosenOrg;
    }

    public Organization getChosenOrg() {
        return chosenOrg;
    }

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }

    public HashMap<Long, VisualizingPanel> getOrgCircles() {
        return orgCircles;
    }

    public AddDialog getAddDialog() {
        return addDialog;
    }

    public HashMap<Integer, StringBuilder> getScriptResults() {
        return scriptResults;
    }

    public static Locale[] getSupportedLocales() {
        return supportedLocales;
    }
}
