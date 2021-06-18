package com.asi.unicomgroupsurvey.Views.comments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.asi.unicomgroupsurvey.baseClass.BasePresenter;
import com.asi.unicomgroupsurvey.data.ApisClient.ApiInterface;
import com.asi.unicomgroupsurvey.data.LocalSqlite.ItemDbHelper;
import com.asi.unicomgroupsurvey.data.models.comments.Comment;
import com.asi.unicomgroupsurvey.data.models.comments.CommentsResponse;
import com.asi.unicomgroupsurvey.data.models.comments.InDoorImageModel;
import com.asi.unicomgroupsurvey.data.models.comments.JobModel;
import com.asi.unicomgroupsurvey.data.models.comments.OutDoorImageModel;
import com.asi.unicomgroupsurvey.data.models.comments.RoomImageModel;
import com.asi.unicomgroupsurvey.data.models.comments.RoomModel;
import com.asi.unicomgroupsurvey.data.models.comments.SketchModel;
import com.asi.unicomgroupsurvey.data.models.in_door.InDoorImage;
import com.asi.unicomgroupsurvey.data.models.jops.Job;
import com.asi.unicomgroupsurvey.data.models.out_door.OutDoorImage;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomDetails;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomImage;
import com.asi.unicomgroupsurvey.data.models.sketch.Sketch;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Utilities;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CommentsPresenter implements BasePresenter<CommentsView> {
    CommentsView mView;
    boolean isLoaded = false;

    //inject api interface object
    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;
    // create sqllit reference
    @Inject
    ItemDbHelper mItemDbHelper;
    @Override
    public void onAttach(CommentsView view) {
        mView = view;
        mView.onAttache();
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    //create Constructor to get reference of api interface object
    public CommentsPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    //this function created to load items from specific endpoint
    public void loadComments(String token , String id  ) {
        if(!Utilities.checkConnection(mContext)){
            checkConnection(false);
            mView.showMessage("لا يوجد اتصال بالانترنت" , 1);
            mView.hideProgress();
            return;
        }
        try {
            mView.showProgress();
            mApiInterface.getComments("Bearer " + token, id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CommentsResponse>() {
                        @Override
                        public final void onCompleted() {
                            mView.hideProgress();
                        }

                        @Override
                        public final void onError(Throwable e) {
                            mView.hideProgress();
                            Log.e(">>>>>>>>>>", e.getMessage());
                            Toast.makeText(mContext, "جوده الانترنت سيئه ", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public final void onNext(CommentsResponse commentsResponse) {
                            isLoaded = true;
                            List<Comment> comments = commentsResponse.getComments();
                            try {
                                mView.getComments(comments);
                            } catch (Exception e) {
                                Toast.makeText(mContext, "جودة الانترنت سيئه حاول مره اخري ", Toast.LENGTH_SHORT).show();
                                Log.e("Exception", e.getMessage());
                            }

//                        try {
//                            if (!commentsResponse.getComments().isEmpty()) {
////                                for (Comment comment : comments) {
////                                    if (isFound(comment.getUser_id(), comment.getOffice().getProject_id(), comment.getOffice_id())) {
////
////                                    } else {
////                                        Log.e("comment" , comment.toString());
////                                        Log.e("office" , comment.getOffice().toString());
////                                        Log.e("district" , comment.getOffice().getDistrict().toString());
////                                        Log.e("city" ,comment.getOffice().getDistrict().getCity().toString());
////                                        String oth = comment.getOffice().getDistrict().getCity().getCity_name() ;
////                                        Log.e("other" , oth) ;
////
////                                        if(oth.equals("اخرى")){
////                                            addSurvey(comment.getOffice().getDistrict().getCity().getGovernorate().getGov_id(),
////                                                    comment.getOffice().getDistrict().getCity().getGovernorate().getGov_name(),
////                                                    comment.getOffice().getDistrict().getCity().getCity_id(),
////                                                    comment.getOther_city(),
////                                                    comment.getOffice().getDistrict_id(),
////                                                    comment.getOther_district(),
////                                                    comment.getAddress(),
////                                                    comment.getPhone(),
////                                                    comment.getIs_internet(),
////                                                    comment.getIs_network(),
////                                                    comment.getInternet_speed(),
////                                                    comment.getOffice_id(),
////                                                    comment.getOffice().getOffice_name(),
////                                                    "1",
////                                                    comment.getShift_type(),
////                                                    comment.getOwnership_type(),
////                                                    comment.getMorning_shift_from(),
////                                                    comment.getMorning_shift_to(),
////                                                    comment.getEvening_shift_from(),
////                                                    comment.getEvening_shift_to(),
////                                                    comment.getComputer_count(),
////                                                    comment.getComputer_notes(),
////                                                    comment.getPrinters_count(),
////                                                    comment.getPrinters_notes(),
////                                                    comment.getScanners_count(),
////                                                    comment.getScanners_notes(),
////                                                    comment.getOther_city(),
////                                                    comment.getOther_district(),
////                                                    comment.getUser_id(),
////                                                    comment.getOffice().getProject_id(),
////                                                    comment.getNet_inside(),
////                                                    comment.getNet_outside(),
////                                                    comment.getIns_out_notes(),
////                                                    comment.getOthers_macs(),
////                                                    comment.getOthers_macs_notes(),
////                                                    comment.getTwo_offices(),
////                                                    comment.getOther_office_id(),
////                                                    comment.getOther_office_name() ,
////                                                    comment.getIs_arabic() , comment.getIs_kurdish() , comment.getBoth());
////                                        }
////                                        else {
////                                            addSurvey(comment.getOffice().getDistrict().getCity().getGovernorate().getGov_id(),
////                                                    comment.getOffice().getDistrict().getCity().getGovernorate().getGov_name(),
////                                                    comment.getOffice().getDistrict().getCity().getCity_id(),
////                                                    comment.getOffice().getDistrict().getCity().getCity_name(),
////                                                    comment.getOffice().getDistrict_id(),
////                                                    comment.getOffice().getDistrict().getDistrict_name(),
////                                                    comment.getAddress(),
////                                                    comment.getPhone(),
////                                                    comment.getIs_internet(),
////                                                    comment.getIs_network(),
////                                                    comment.getInternet_speed(),
////                                                    comment.getOffice_id(),
////                                                    comment.getOffice().getOffice_name(),
////                                                    "1",
////                                                    comment.getShift_type(),
////                                                    comment.getOwnership_type(),
////                                                    comment.getMorning_shift_from(),
////                                                    comment.getMorning_shift_to(),
////                                                    comment.getEvening_shift_from(),
////                                                    comment.getEvening_shift_to(),
////                                                    comment.getComputer_count(),
////                                                    comment.getComputer_notes(),
////                                                    comment.getPrinters_count(),
////                                                    comment.getPrinters_notes(),
////                                                    comment.getScanners_count(),
////                                                    comment.getScanners_notes(),
////                                                    comment.getOther_city(),
////                                                    comment.getOther_district(),
////                                                    comment.getUser_id(),
////                                                    comment.getOffice().getProject_id(),
////                                                    comment.getNet_inside(),
////                                                    comment.getNet_outside(),
////                                                    comment.getIns_out_notes(),
////                                                    comment.getOthers_macs(),
////                                                    comment.getOthers_macs_notes(),
////                                                    comment.getTwo_offices(),
////                                                    comment.getOther_office_id(),
////                                                    comment.getOther_office_name() ,
////                                                    comment.getIs_arabic() , comment.getIs_kurdish() , comment.getBoth());}
////
////                                        addJobs(generateJobList(comment.getUser_id(), comment.getOffice().getProject_id(), comment.getOffice_id(), comment.getJobs()));
////
////                                        addRooms(generateJobList2(comment.getUser_id(), comment.getOffice().getProject_id(), comment.getOffice_id(), comment.getRooms()));
////
////                                        if(comment.getSketchModels() != null) {
////                                            //TODO
////                                            for (SketchModel sketchModel:comment.getSketchModels()) {
////                                                addSketch(new Sketch(comment.getOffice_id(), comment.getUser_id(), comment.getOffice().getProject_id(), sketchModel.getImg_desc() ,  StringToBitMap(sketchModel.getImage())));
////                                            }
////                                        }
////                                        if(comment.getOutDoorImages() != null) {
////                                            addOutDoorImages(comment.getUser_id(),
////                                                    comment.getOffice().getProject_id(),
////                                                    comment.getOffice_id(),
////                                                    getOutDoorImages22(comment.getOutDoorImages()) );
////                                        }
////                                        if(comment.getInDoorImages() != null) {
////                                            addInDoorImages(comment.getUser_id(), comment.getOffice().getProject_id(),
////                                                    comment.getOffice_id(), getIndoorImages(comment.getInDoorImages()));
////                                        }
////
////                                        addUserNotes(comment.getOffice_id(), comment.getGeneral_notes(),
////                                                String.format("%s -- %s", comment.getLatitude(),
////                                                        comment.getLongitude()), comment.getUser_id(),
////                                                comment.getOffice().getProject_id() , comment.getVisit_date() ,
////                                                comment.getElectricty() , comment.getWater() , comment.getBill_end() ,comment.getColunms() , comment.getDoors() , comment.getSecondary() , comment.getCamersnet());
////
////
////                                    }
////                                }
//                            }
//                        }
//                        catch (Exception e ) {
//                            Toast.makeText(mContext, "جودة الانترنت سيئه ", Toast.LENGTH_SHORT).show();
//                        }

                        }
                    });

        }
        catch (Exception e) {
            Toast.makeText(mContext, " جوده الانتر نت سيئه حاول مره اخري ", Toast.LENGTH_SHORT).show();
        }

    }

    private List<IMGMODEL> getIndoorImages(List<InDoorImageModel> inDoorImages) {
        List<IMGMODEL> strings = new ArrayList<>();
        for (InDoorImageModel inDoorImageModel : inDoorImages) {
            strings.add(new IMGMODEL(inDoorImageModel.getImage() , inDoorImageModel.getImg_desc()));
        }
        return strings;
    }

    private List<IMGMODEL> getOutDoorImages22(List<OutDoorImageModel> outDoorImages) {
        List<IMGMODEL> strings = new ArrayList<>();
        for (OutDoorImageModel outDoorImageModel : outDoorImages) {
            strings.add(new IMGMODEL(outDoorImageModel.getImage() , outDoorImageModel.getImg_desc()));
        }
        return strings;
    }

    public class IMGMODEL {
        String img ;
        String des ;

        public IMGMODEL(String img, String des) {
            this.img = img;
            this.des = des;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }

    public boolean isFound(String user_id, String project_id, String office_id) {
        boolean asd;
        if (mItemDbHelper.getOneSurveyForUserForLastScreenLogic(user_id, project_id, office_id).getProject_id() != null) {
            asd = true;
        } else {
            asd = false;
        }
        return asd;
    }
    private List<RoomDetails> generateJobList2(String user_id, String project_id, String office_id, List<RoomModel> rooms) {
        List<RoomDetails> rooms1 = new ArrayList<>();
        for (RoomModel roomModel : rooms) {
            rooms1.add(new RoomDetails(user_id, project_id, office_id, roomModel.getRoom_name(), roomModel.getRoom_count(), roomModel.getFurniture(), setRoomImagesList(roomModel.getRoomImagesList()), roomModel.getIs_mutual()));
        }
        return rooms1;
    }

    private List<RoomImage> setRoomImagesList(List<RoomImageModel> roomImagesList) {
        List<RoomImage> roomImages  = new ArrayList<>() ;
        for (RoomImageModel roomImageModel:roomImagesList) {
            RoomImage roomImage = new RoomImage(roomImageModel.getUser_id(), roomImageModel.getProject_id(), roomImageModel.getOffice_id(), StringToBitMap(roomImageModel.getImageBitmap()));
            roomImage.setMinimumStatus(1);
            roomImages.add(roomImage);
        }
        return roomImages ;
    }



    private List<Job> generateJobList(String user_id, String project_id, String office_id, List<JobModel> jobs) {
        List<Job> jobs1 = new ArrayList<>();
        for (JobModel job : jobs) {
            jobs1.add(new Job(user_id, project_id, office_id, job.getPosition().getPosition_id(), job.getCount(), job.getNote(), job.getPosition().getPosition_name()));
        }
        return jobs1;
    }

    public Bitmap StringToBitMap(String encodedString) {
        Bitmap bitmap = null ;

        try {
            if(encodedString != null) {
                byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inSampleSize = 4;
                 bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length );
                return bitmap;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return bitmap;

    }

    void checkConnection(boolean isConnected) {
        //check internet and  data not loaded
        if(isConnected  && !isLoaded){
            isLoaded = false;
            //   mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }if(!isConnected && isLoaded){
            //offline check and  data loaded
            //  mView.showMessage(mContext.getString(R.string.offline),Color.WHITE);

        }else if(!isConnected && !isLoaded){
            //get offline  data using realm
            //mView.showMessage(mContext.getString(R.string.get_data_from_local),Color.WHITE);
            //     mView.updateList(mItemDbHelper.getAllItems());
        }else if(isConnected && isLoaded){
            //mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }
    }

    public void addSurvey(String gov_id, String gov_name, String city_id, String city_name, String district_id, String district_name
            , String address, String phone, String hasInternet, String isNetwork, String internetSeed, String office_id, String office_name
            , String office_visit, String shiftType, String OwnerShipType, String morning_shift_from, String morning_shift_to, String evening_shift_from
            , String evening_shift_to, String computer_count, String computer_notes, String printers_count, String printers_notes
            , String scanners_count, String scanners_notes, String other_city, String other_district, String user_id, String project_id,
                          String net_inside, String net_outside, String ins_out_notes, String others_macs, String others_macs_notes
            , String two_offices, String other_office_id, String other_office_name , String is_arabic , String is_kurdish , String both
    ) {
        mItemDbHelper.addSurvey2(gov_id, gov_name, city_id, city_name, district_id, district_name
                , address, phone, hasInternet, isNetwork, internetSeed, office_id, office_name
                , office_visit, shiftType, OwnerShipType, morning_shift_from, morning_shift_to, evening_shift_from
                , evening_shift_to, computer_count, computer_notes, printers_count, printers_notes
                , scanners_count, scanners_notes, other_city, other_district, user_id, project_id
                , net_inside, net_outside, ins_out_notes, others_macs, others_macs_notes
                , two_offices, other_office_id, other_office_name , is_arabic , is_kurdish , both
        );
    }

    public void addJobs(List<Job> jobs) {
        for (Job job : jobs) {
            mItemDbHelper.addJob(job);
        }
    }

    public void addUserNotes(String office_id, String notes, String location, String user_id, String project_id , String visit_date ,String electricty , String water, String bill_end , String colunms
            ,String doors , String secondary ,String camersnet) {
        mItemDbHelper.addNotes2(office_id, notes, location, user_id, project_id , visit_date , electricty ,  water,  bill_end ,  colunms
                , doors ,  secondary , camersnet  );
    }

    public void addRooms(List<RoomDetails> rooms) {
        for (RoomDetails room : rooms) {
            mItemDbHelper.addRooms(room);
        }
    }

    public void addSketch(Sketch sketch) {
        sketch.setMinimumStatus(1);
        mItemDbHelper.addSketch(sketch);
    }

    public void addOutDoorImages(String user_id, String project_id, String office_id, List<IMGMODEL> outDoorImages) {
        for (int i = 0; i < outDoorImages.size(); i++) {
            OutDoorImage outDoorImage = new OutDoorImage(
                    user_id,
                    project_id,
                    office_id,
                    outDoorImages.get(i).getDes(),
                    StringToBitMap(outDoorImages.get(i).getImg()));
            outDoorImage.setMimimumStatus(1);
            mItemDbHelper.addOutDoorImage(outDoorImage);
        }
    }

    public void addInDoorImages(String user_id, String project_id, String office_id, List<IMGMODEL> inDoorImages) {

        for (int i = 0; i < inDoorImages.size(); i++) {
            InDoorImage inDoorImage = new InDoorImage(
                    user_id,
                    project_id,
                    office_id,
                    inDoorImages.get(i).getDes(),
                    StringToBitMap(inDoorImages.get(i).getImg()));
            inDoorImage.setMimimumStatus(1);
            mItemDbHelper.addInDoorImage(inDoorImage);
        }
    }

    public void saveOfficeData(Comment comment) {
        mView.showProgress();
        if (isFound(comment.getUser_id(), comment.getOffice().getProject_id(), comment.getOffice_id())) {

        } else {
            Log.e("comment", comment.toString());
            Log.e("office", comment.getOffice().toString());
            Log.e("district", comment.getOffice().getDistrict().toString());
            Log.e("city", comment.getOffice().getDistrict().getCity().toString());
            String oth = comment.getOffice().getDistrict().getCity().getCity_name();
            Log.e("other", oth);

            if (oth.equals("اخرى")) {
                addSurvey(comment.getOffice().getDistrict().getCity().getGovernorate().getGov_id(),
                        comment.getOffice().getDistrict().getCity().getGovernorate().getGov_name(),
                        comment.getOffice().getDistrict().getCity().getCity_id(),
                        comment.getOther_city(),
                        comment.getOffice().getDistrict_id(),
                        comment.getOther_district(),
                        comment.getAddress(),
                        comment.getPhone(),
                        comment.getIs_internet(),
                        comment.getIs_network(),
                        comment.getInternet_speed(),
                        comment.getOffice_id(),
                        comment.getOffice().getOffice_name(),
                        "1",
                        comment.getShift_type(),
                        comment.getOwnership_type(),
                        comment.getMorning_shift_from(),
                        comment.getMorning_shift_to(),
                        comment.getEvening_shift_from(),
                        comment.getEvening_shift_to(),
                        comment.getComputer_count(),
                        comment.getComputer_notes(),
                        comment.getPrinters_count(),
                        comment.getPrinters_notes(),
                        comment.getScanners_count(),
                        comment.getScanners_notes(),
                        comment.getOther_city(),
                        comment.getOther_district(),
                        comment.getUser_id(),
                        comment.getOffice().getProject_id(),
                        comment.getNet_inside(),
                        comment.getNet_outside(),
                        comment.getIns_out_notes(),
                        comment.getOthers_macs(),
                        comment.getOthers_macs_notes(),
                        comment.getTwo_offices(),
                        comment.getOther_office_id(),
                        comment.getOther_office_name(),
                        comment.getIs_arabic(), comment.getIs_kurdish(), comment.getBoth());
            } else {
                addSurvey(comment.getOffice().getDistrict().getCity().getGovernorate().getGov_id(),
                        comment.getOffice().getDistrict().getCity().getGovernorate().getGov_name(),
                        comment.getOffice().getDistrict().getCity().getCity_id(),
                        comment.getOffice().getDistrict().getCity().getCity_name(),
                        comment.getOffice().getDistrict_id(),
                        comment.getOffice().getDistrict().getDistrict_name(),
                        comment.getAddress(),
                        comment.getPhone(),
                        comment.getIs_internet(),
                        comment.getIs_network(),
                        comment.getInternet_speed(),
                        comment.getOffice_id(),
                        comment.getOffice().getOffice_name(),
                        "1",
                        comment.getShift_type(),
                        comment.getOwnership_type(),
                        comment.getMorning_shift_from(),
                        comment.getMorning_shift_to(),
                        comment.getEvening_shift_from(),
                        comment.getEvening_shift_to(),
                        comment.getComputer_count(),
                        comment.getComputer_notes(),
                        comment.getPrinters_count(),
                        comment.getPrinters_notes(),
                        comment.getScanners_count(),
                        comment.getScanners_notes(),
                        comment.getOther_city(),
                        comment.getOther_district(),
                        comment.getUser_id(),
                        comment.getOffice().getProject_id(),
                        comment.getNet_inside(),
                        comment.getNet_outside(),
                        comment.getIns_out_notes(),
                        comment.getOthers_macs(),
                        comment.getOthers_macs_notes(),
                        comment.getTwo_offices(),
                        comment.getOther_office_id(),
                        comment.getOther_office_name(),
                        comment.getIs_arabic(), comment.getIs_kurdish(), comment.getBoth());
            }

            addJobs(generateJobList(comment.getUser_id(), comment.getOffice().getProject_id(), comment.getOffice_id(), comment.getJobs()));

            addRooms(generateJobList2(comment.getUser_id(), comment.getOffice().getProject_id(), comment.getOffice_id(), comment.getRooms()));

            if (comment.getSketchModels() != null) {
                //TODO
                for (SketchModel sketchModel : comment.getSketchModels()) {
                    addSketch(new Sketch(comment.getOffice_id(), comment.getUser_id(), comment.getOffice().getProject_id(), sketchModel.getImg_desc(), StringToBitMap(sketchModel.getImage())));
                }
            }
            if (comment.getOutDoorImages() != null) {
                addOutDoorImages(comment.getUser_id(),
                        comment.getOffice().getProject_id(),
                        comment.getOffice_id(),
                        getOutDoorImages22(comment.getOutDoorImages()));
            }
            if (comment.getInDoorImages() != null) {
                addInDoorImages(comment.getUser_id(), comment.getOffice().getProject_id(),
                        comment.getOffice_id(), getIndoorImages(comment.getInDoorImages()));
            }

            addUserNotes(comment.getOffice_id(), comment.getGeneral_notes(),
                    String.format("%s -- %s", comment.getLatitude(),
                            comment.getLongitude()), comment.getUser_id(),
                    comment.getOffice().getProject_id(), comment.getVisit_date(),
                    comment.getElectricty(), comment.getWater(), comment.getBill_end(), comment.getColunms(), comment.getDoors(), comment.getSecondary(), comment.getCamersnet());


        }
        mView.hideProgress();
    }

}
