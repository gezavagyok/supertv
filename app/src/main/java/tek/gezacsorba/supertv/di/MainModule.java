package tek.gezacsorba.supertv.di;

import dagger.Module;
import dagger.Provides;
import tek.gezacsorba.supertv.main.MainContract;
import tek.gezacsorba.supertv.main.MainPresenter;
import tek.gezacsorba.supertv.network.Api;
import tek.gezacsorba.supertv.use_case.LoadChannelsUseCase;

/**
 * Created by geza on 11/4/17.
 */
@Module(includes = NetworkModule.class)
public class MainModule {

    MainContract.View view;

    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @Provides
    LoadChannelsUseCase provideUseCase(Api api) {
        return new LoadChannelsUseCase(api);
    }

    @Provides
    MainContract.Presenter provideView(LoadChannelsUseCase useCase) {
        return new MainPresenter(view, useCase);
    }
}
