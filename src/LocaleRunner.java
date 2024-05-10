import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleRunner {
    public static void main(String[] args) {
        Locale locale=new Locale("ru", "Ru");

        var translations = ResourceBundle.getBundle("translations");
        System.out.println(translations.getString("page.login.password"));
    }
}
