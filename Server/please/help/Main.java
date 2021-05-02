package please.help;

public class Main {

    public static void main(String[] args) {
        CollectionManager manager = CollectionManagerBuilder.createCollectionManager(new String[]{"db.txt"});
        if (manager != null) manager.start();
    }
}
