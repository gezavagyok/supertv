package tek.gezacsorba.supertv.main;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tek.gezacsorba.supertv.use_case.LoadChannelsUseCase;

/**
 * Created by geza on 11/4/17.
 */

public class MainPresenter implements MainContract.Presenter {

    MainContract.View view;
    LoadChannelsUseCase useCase;
    CompositeDisposable compositeDisposable;

    @Inject
    public MainPresenter(MainContract.View view, LoadChannelsUseCase useCase) {
        this.view = view;
        this.useCase = useCase;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadProgramme() {
        view.showLoading();
        compositeDisposable.add(useCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::displayChannels, view::showError, view::hideLoading));
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }

}
