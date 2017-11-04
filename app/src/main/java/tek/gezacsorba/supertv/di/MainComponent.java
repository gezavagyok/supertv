package tek.gezacsorba.supertv.di;

import dagger.Component;
import tek.gezacsorba.supertv.main.MainActivity;

/**
 * Created by geza on 11/4/17.
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
