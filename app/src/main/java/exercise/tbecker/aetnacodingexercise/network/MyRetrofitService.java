package exercise.tbecker.aetnacodingexercise.network;

import exercise.tbecker.aetnacodingexercise.models.ResponseItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Android on 8/7/2017.
 */

public interface MyRetrofitService {

    @GET("photos_public.gne")
    Call<ResponseItem> getData(@Query("noJsonCallback") int noJsonCallback,
                               @Query("tagmode") String tagMode,
                               @Query("format") String format,
                               @Query("tags") String input);
}
