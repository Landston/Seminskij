import com.senla.di.appconfig.ApplicationContext;
import com.senla.ui.MenuController;


public class Test {

    public static void main(String[] args) throws NoSuchFieldException {

        ApplicationContext context = ApplicationContext.getInstance();

        context.getObject(MenuController.class).run();
    }

}
