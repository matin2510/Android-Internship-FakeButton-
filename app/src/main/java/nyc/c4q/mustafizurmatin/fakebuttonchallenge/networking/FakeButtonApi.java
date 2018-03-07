package nyc.c4q.mustafizurmatin.fakebuttonchallenge.networking;

import java.util.List;

import nyc.c4q.mustafizurmatin.fakebuttonchallenge.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by c4q on 3/6/18.
 */

public interface FakeButtonApi {
    @POST("/user")
    Call<User> createUser(
            @Body User user
    );
    @GET("/user")
    // callng a list of user
    Call<List<User>> getUser(
            @Query("candidate") String candidate
    );


    //http://fake-button.herokuapp.com/
}
