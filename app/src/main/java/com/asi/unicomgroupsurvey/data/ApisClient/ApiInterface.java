package com.asi.unicomgroupsurvey.data.ApisClient;



import android.support.annotation.ArrayRes;

import com.asi.unicomgroupsurvey.data.models.FlagResponse;
import com.asi.unicomgroupsurvey.data.models.GetAllItemsResponse;
import com.asi.unicomgroupsurvey.data.models.Login.Login;
import com.asi.unicomgroupsurvey.data.models.comments.Comment;
import com.asi.unicomgroupsurvey.data.models.comments.CommentsResponse;
import com.asi.unicomgroupsurvey.data.models.getCities.GetCitiesResponse;
import com.asi.unicomgroupsurvey.data.models.getDistricts.GetDistrictsResponse;
import com.asi.unicomgroupsurvey.data.models.getGovernors.GetGovernorsResponse;
import com.asi.unicomgroupsurvey.data.models.get_user_projects.GetUserProjectsResponse;
import com.asi.unicomgroupsurvey.data.models.offices.OfficeResponse;
import com.asi.unicomgroupsurvey.data.models.positions.PositionResponse;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.InDoorDetails;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.OutDoorDetails;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.Room;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.RoomDetails;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.RoomImageDetails;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.SketchDetails;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.SyncOfficeDataRequest;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.SyncOfflineDataResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


// this class creating for register all endpoints implementation
public interface ApiInterface {

    @GET(EndPoints.GET_ALL_ITEMS)
    Observable<GetAllItemsResponse> getAllItems();


    // get projects (belal)
    @GET(EndPoints.GET_USER_PROJECTS)
    Observable<GetUserProjectsResponse> getUserProjects(@Header("authorization") String token , @Path("user_id") String user_id);

    //login amr rabie
    @FormUrlEncoded
    @POST(EndPoints.GET_LOGin)
    Observable<Login> getLogin(
            @Field("email") String email,
            @Field("password") String pw
    );


    //By Elamary
    @GET(EndPoints.GET_GOVERNOR)
    Observable<GetGovernorsResponse> getGovernors();

    @GET(EndPoints.GET_CITIES)
    Observable<GetCitiesResponse> getCities();

    @GET(EndPoints.GET_DISTRICTS)
    Observable<GetDistrictsResponse> getDistricts();


    @GET(EndPoints.GET_POSITIONS)
    Observable<PositionResponse> getPositions();

    @GET(EndPoints.GET_OFFICES)
    Observable<OfficeResponse> getOffices();


    @GET(EndPoints.GET_COMMENTS)
    Observable<CommentsResponse> getComments(@Header("authorization") String token , @Path("user_id") String user_id);


    @POST(EndPoints.SYNC_SURVEYS)
    Observable<SyncOfflineDataResponse> syncOfflineDataObservable(@Header("authorization") String token  , @Body SyncOfficeDataRequest syncOfficeDataRequest);



    @POST(EndPoints.add_office_data_room)
    Observable<SyncOfflineDataResponse> saveRoom(@Header("authorization") String token  , @Body RoomDetails roomDetails);

    @POST(EndPoints.add_office_data_sketch)
    Observable<SyncOfflineDataResponse> saveSketch(@Header("authorization") String token  , @Body SketchDetails sketchDetails);

    @POST(EndPoints.add_office_data_out_door_image)
    Observable<SyncOfflineDataResponse> saveOutDoor(@Header("authorization") String token  , @Body OutDoorDetails outDoorDetails);

    @POST(EndPoints.add_office_data_in_door_image)
    Observable<SyncOfflineDataResponse> saveIndoor(@Header("authorization") String token  , @Body InDoorDetails inDoorDetails);

    @POST(EndPoints.add_office_data_done)
    Observable<FlagResponse> is_done(@Header("authorization") String token  , @Path("officeId") String  officeId);


}
