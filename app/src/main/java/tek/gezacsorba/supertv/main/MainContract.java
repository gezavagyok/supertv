package tek.gezacsorba.supertv.main;

import java.util.List;

import tek.gezacsorba.supertv.network.Channel;

/**
 * Created by geza on 11/4/17.
 */

public interface MainContract {

    interface Presenter {

        void loadProgramme();

        void onDestroy();
    }

    interface View {

        void showLoading();

        void hideLoading();

        void showError(Throwable throwable);

        void displayChannels(List<Channel> channels);
    }
}
