package vidmot.goldrush;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ViewSwitcher {

    private static final Map<View, Parent> cache = new HashMap<>();
    private static Scene scene;
    private static View lastView = View.START;
    private static View currentView;

    public static void setScene(Scene scene){
        ViewSwitcher.scene = scene;
    }

    public static void switchTo(View view){
        if (scene == null){
            System.out.println("No scene set");
            return;
        }

        try{
            Parent root;
            if (cache.containsKey(view)){
                System.out.println("Loading from cache");
                root = cache.get(view);
            } else {
                System.out.println("Loading from FXML");
                root = FXMLLoader.load(Objects.requireNonNull(ViewSwitcher.class.getResource(view.getFileName())));
                cache.put(view, root);
            }
            lastView = currentView;
            currentView = view;
            scene.setRoot(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static View getLastView() {
        return lastView;
    }
}
