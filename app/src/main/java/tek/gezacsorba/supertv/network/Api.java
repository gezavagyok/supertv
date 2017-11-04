package tek.gezacsorba.supertv.network;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by geza on 11/4/17.
 */

public interface Api {

    String GIST_PROGRAMME = "reden87/ad856e7994b8ea93ac27503ecb051347/raw/050c539749f3d253a01ad685983ebc8503ea7872/example.json";

    @GET(GIST_PROGRAMME)
    Observable<Response> getProgramme();

}
