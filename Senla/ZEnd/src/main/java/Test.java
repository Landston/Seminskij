import com.senla.di.annotation.Auttowared;
import com.senla.di.appconfig.ApplicationContext;
import com.senla.di.exception.ConfigurationError;
import com.senla.model.dao.BookDAO;
import com.senla.model.model.Book;
import com.senla.ui.MenuController;
import lombok.Setter;
import org.apache.logging.log4j.Level;

import java.applet.AppletContext;
import java.io.File;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Test {

    public static void main(String[] args) throws NoSuchFieldException {

        ApplicationContext context = ApplicationContext.getInstance();

        context.getObject(MenuController.class).run();
    }

}
