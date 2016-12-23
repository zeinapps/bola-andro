package zein.apps.chelsea7.Client;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import zein.apps.chelsea7.Model.Responsedetil;
import zein.apps.chelsea7.Model.Responsejadwal;
import zein.apps.chelsea7.Model.Responselist;

/**
 * Created by VILLA-PC on 8/15/2016.
 */
public interface ApiInterface {


    @GET("index")
    Call<Responselist> getListIndex();

    @GET("index")
    Call<Responselist> getListIndex(@Query("page") int page);

    @GET("index/{club}")
    Call<Responselist> getListIndexClub(@Path("club") String time);

    @GET("index/{club}")
    Call<Responselist> getListIndexClub(@Path("club") String time, @Query("page") int page);

    @GET("{time}/detil")
    Call<Responsedetil> getDetailData(@Path("time") String time);

    @GET("{tag}/jadwal")
    Call<Responsejadwal> getDataJadwal(@Path("tag") String tag);

    @GET("{tag}/hasil")
    Call<Responsejadwal> getDataHasil(@Path("tag") String tag);
}
