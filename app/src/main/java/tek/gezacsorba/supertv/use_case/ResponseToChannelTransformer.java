package tek.gezacsorba.supertv.use_case;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;
import tek.gezacsorba.supertv.network.Channel;
import tek.gezacsorba.supertv.network.Response;

/**
 * Created by geza on 11/4/17.
 */

public class ResponseToChannelTransformer implements Function<Response, List<Channel>> {

    private final static ResponseToChannelTransformer instance = new ResponseToChannelTransformer();

    @Override
    public List<Channel> apply(Response response) throws Exception {
        return (response.getChannels() == null || response.getChannels().isEmpty()) ?
                new ArrayList<>() : response.getChannels();
    }

    public static Function<? super Response, ? extends List<Channel>> get() {
        return instance;
    }
}
