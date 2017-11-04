package tek.gezacsorba.supertv.use_case;

import java.util.List;

import io.reactivex.Observable;
import tek.gezacsorba.supertv.network.Api;
import tek.gezacsorba.supertv.network.Channel;

/**
 * Created by geza on 11/4/17.
 */

public class LoadChannelsUseCase {

    final Api api;

    public LoadChannelsUseCase(Api api) {
        this.api = api;
    }

    public Observable<List<Channel>> execute() {
        return api.getProgramme().map(ResponseToChannelTransformer.get());
    }
}
