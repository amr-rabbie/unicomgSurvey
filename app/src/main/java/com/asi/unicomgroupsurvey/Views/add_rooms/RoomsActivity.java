package com.asi.unicomgroupsurvey.Views.add_rooms;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.Views.comments.CommentsActivity;
import com.asi.unicomgroupsurvey.Views.get_user_projects.GetUserProjectsActivity;
import com.asi.unicomgroupsurvey.Views.login.LoginActivity;
import com.asi.unicomgroupsurvey.Views.navigation_home.NavigationHomeActivity;
import com.asi.unicomgroupsurvey.Views.saved_offline_survey.GetOfflineSavedSurveyActivity;
import com.asi.unicomgroupsurvey.Views.sketch.SketchActivity;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomDetails;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomImage;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Constants;
import com.asi.unicomgroupsurvey.helper.Utility;
import com.asi.unicomgroupsurvey.listClickListener.ItemClickListenerForRoom;
import com.asi.unicomgroupsurvey.listClickListener.RoomItemClick;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RoomsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, AddRoomsView, ItemClickListenerForRoom, RoomItemClick {
    private static final int REQUEST_CAMERA = 0;
    private static final int SELECT_FILE = 1;
    private static final int REQUEST_CAMERA2 = 100;
    private static final int SELECT_FILE2 = 200;
    Uri picUri;
    private String userChoosenTask;
    private TextView addNewRoom;
    private FloatingActionButton floatABtnAddNewRoom;
    RecyclerView recyclerViewImages;
    private LinearLayout mLinearEmptyRoom;
    private RecyclerView recyclerViewRooms;
    List<RoomImage> roomImages = new ArrayList<>();
    private TextView text_next_click,text_back_click ;
    @Inject
    AddRoomsPresenter addRoomsPresenter ;
    private GetUserRoomsAdapter getUserRoomsAdapter ;
    Dialog dialogAddRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView titleBar = findViewById(R.id.text_title_bar);
        titleBar.setText("الغرف");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        floatABtnAddNewRoom = findViewById(R.id.float_button_add_room);
        addNewRoom = findViewById(R.id.add_room_btn);

        text_next_click = findViewById(R.id.text_next_click);
        text_back_click = findViewById(R.id.text_back_click);
        mLinearEmptyRoom = findViewById(R.id.linearLayout_empyt_room);
        recyclerViewRooms = findViewById( R.id.recycleView_rooms);

        ((DaggerApplication)getApplication()).getAppComponent().inject(this);
        addRoomsPresenter.onAttach(this);


        floatABtnAddNewRoom.setOnClickListener(this);
        addNewRoom.setOnClickListener(this);


        text_next_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoomsActivity.this , SketchActivity.class));
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewRooms.setLayoutManager(linearLayoutManager);
        addRoomsPresenter.getUserRooms();


        findViewById(R.id.text_back_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            RoomsActivity.super.onBackPressed();
            /*
            new android.support.v7.app.AlertDialog.Builder(this)
                    .setMessage("هل أنت متاكد من الخروج من الصفحة ؟ ")
                    .setCancelable(false)
                    .setPositiveButton("متابعة", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            RoomsActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("الغاء", null)
                    .show();*/
        }
    }


    /* @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.jobs, menu);
         return true;
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         // Handle action bar item clicks here. The action bar will
         // automatically handle clicks on the Home/Up button, so long
         // as you specify a parent activity in AndroidManifest.xml.
         int id = item.getItemId();

         //noinspection SimplifiableIfStatement
         if (id == R.id.action_settings) {
             return true;
         }

         return super.onOptionsItemSelected(item);
     }
 */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.show_saved_data_list) {
            Intent i=new Intent(RoomsActivity.this,GetOfflineSavedSurveyActivity.class);
            startActivity(i);
        } else if (id == R.id.add_new_survey) {

            Intent intent = new Intent(RoomsActivity.this , NavigationHomeActivity.class);
            intent.putExtra("project_name" , Constants.getProjectName(RoomsActivity.this));
            startActivity(intent);

        } else if (id == R.id.nav_change_project) {
            startActivity(new Intent(this, GetUserProjectsActivity.class));
        } else if (id == R.id.nav_comment) {
            startActivity(new Intent(this, CommentsActivity.class));
        } else if (id == R.id.nav_logout) {
            android.app.AlertDialog.Builder builder;
            builder = new android.app.AlertDialog.Builder(RoomsActivity.this);
            builder.setMessage("هل حقا تريد تسجيل الخروج ؟");
            builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Constants.saveLoginData("", "", "", RoomsActivity.this);
                    Intent i = new Intent(RoomsActivity.this, LoginActivity.class);
                    startActivity(i);
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.float_button_add_room :

                showAddRoomDialog();
                break;

            case R.id.add_room_btn :

                showAddRoomDialog();
                break;
        }
    }

    private void showAddRoomDialog(){
        isEditing = false;
        roomImages.clear();
        dialogAddRoom = new Dialog(this);
        dialogAddRoom.setCancelable(false);
        dialogAddRoom.setContentView(R.layout.add_new_room);
        final EditText edRoomName = dialogAddRoom.findViewById(R.id.edt_room_name);
        final EditText edRoomCount = dialogAddRoom.findViewById(R.id.edt_room_count);
        final EditText edRoomFurnature = dialogAddRoom.findViewById(R.id.edt_room_furnature);
        Button saveRoom = dialogAddRoom.findViewById(R.id.button_save_room_dialog);
        Button cancelRoom = dialogAddRoom.findViewById(R.id.button_cancel_room_dialog);
        /////
        RadioGroup radioGroup = dialogAddRoom.findViewById(R.id.radio_group);
        if (addRoomsPresenter.isMutualedWithOtherOffice(Constants.getProjectId(RoomsActivity.this), Constants.getOfficeId(RoomsActivity.this), Constants.getuserId(RoomsActivity.this))) {
            radioGroup.setVisibility(View.VISIBLE);
        }
        final RadioButton mutual = dialogAddRoom.findViewById(R.id.mutual_radioButton);
        final RadioButton notMutual = dialogAddRoom.findViewById(R.id.not_mutual_radioButton);
        /////////
        recyclerViewImages = dialogAddRoom.findViewById(R.id.recyclerViewRoomPhoto);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(RoomsActivity.this, 2);
        recyclerViewImages.setLayoutManager(linearLayoutManager);

        Button addImage = dialogAddRoom.findViewById(R.id.btn_add);
        // TODO save Room Offline
        saveRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roomname=edRoomName.getText().toString();
                String roomNumber = edRoomCount.getText().toString();

                if(roomname.isEmpty()){
                    Toast.makeText(RoomsActivity.this,"لابد من ادخال اسم الغرفة",Toast.LENGTH_SHORT).show();
                } else if (roomNumber.isEmpty()) {
                    Toast.makeText(RoomsActivity.this, "لابد من ادخال العدد", Toast.LENGTH_SHORT).show();
                }
                ///////
                else if (!mutual.isChecked() && !notMutual.isChecked() && addRoomsPresenter.isMutualedWithOtherOffice(Constants.getProjectId(RoomsActivity.this), Constants.getOfficeId(RoomsActivity.this), Constants.getuserId(RoomsActivity.this))) {
                    Toast.makeText(RoomsActivity.this, "لابد من اختيار مشترك او غير مشترك", Toast.LENGTH_SHORT).show();
                }
                //////////

//                else if (roomImages.size() > 2) {
//                    Toast.makeText(RoomsActivity.this, "لا يجب اخنيار اكثر من صورتين ", Toast.LENGTH_SHORT).show();
//                }
//
                else {

                    /////////////
                    if (mutual.isChecked() && addRoomsPresenter.isMutualedWithOtherOffice(Constants.getProjectId(RoomsActivity.this), Constants.getOfficeId(RoomsActivity.this), Constants.getuserId(RoomsActivity.this))) {
                        addRoomsPresenter.saveRoomsOfflinePresenter(edRoomName.getText().toString(), edRoomCount.getText().toString(), edRoomFurnature.getText().toString(), roomImages, "1");
                    } else {
                        addRoomsPresenter.saveRoomsOfflinePresenter(edRoomName.getText().toString(), edRoomCount.getText().toString(), edRoomFurnature.getText().toString(), roomImages, "0");
                    }
                    ////////////


                }
            }
        });


        cancelRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddRoom.dismiss();
            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        dialogAddRoom.show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showHideAddRoomDialog(Long flag) {
        if (flag > -1) {
            dialogAddRoom.dismiss();
            addRoomsPresenter.getUserRooms();
        }
    }

    @Override
    public void showHideEditRoomDialog(boolean flag) {

        dialogAddRoom.dismiss();
    }

    @Override
    public void showHideDeleteDialog(boolean flag) {

    }

    @Override
    public void showRoomsList(List<RoomDetails> roomDetails) {

        if(roomDetails.size() > 0){
            recyclerViewRooms.setVisibility(View.VISIBLE);
            mLinearEmptyRoom.setVisibility(View.GONE);

            getUserRoomsAdapter = new GetUserRoomsAdapter(addRoomsPresenter , this, roomDetails, this, 0);
            recyclerViewRooms.setAdapter(getUserRoomsAdapter);
        } else {
            recyclerViewRooms.setVisibility(View.GONE);
            mLinearEmptyRoom.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onAttache() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onItemClick(int position, RoomDetails roomDetails, View view) {

        showEditRoomDialog(roomDetails);
    }

    AlertDialog.Builder builder;

    @Override
    public void onItemClickDelete(int position, final RoomDetails roomDetails, View view) {

        builder = new AlertDialog.Builder(RoomsActivity.this);
        builder.setMessage("هل تريد مسح بيانات هذه الغرفة ؟");
        builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addRoomsPresenter.deleteRooms(roomDetails.get_ID());
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        builder.show();


    }

    EditText edRoomName , edRoomCount , edRoomFurnature;
    List<RoomImage> roomsImagesForEditing = new ArrayList<>();
    RoomDetails currentRoom;
    RecyclerView editImagesRecyclerView;
    boolean isEditing = false;

    private void showEditRoomDialog(final RoomDetails roomDetails){
        isEditing = true;
        currentRoom = roomDetails;
        dialogAddRoom = new Dialog(this);
        dialogAddRoom.setContentView(R.layout.edit_exist_room);
        dialogAddRoom.setCancelable(false);
        edRoomName = dialogAddRoom.findViewById(R.id.edt_room_name);
        edRoomCount = dialogAddRoom.findViewById(R.id.edt_room_count);
        edRoomFurnature = dialogAddRoom.findViewById(R.id.edt_room_furnature);
        Button addImage = dialogAddRoom.findViewById(R.id.btn_add);
        Button saveRoom = dialogAddRoom.findViewById(R.id.button_save_room_dialog);
        Button cancelRoom = dialogAddRoom.findViewById(R.id.button_cancel_room_dialog);
        /////
        RadioGroup radioGroup = dialogAddRoom.findViewById(R.id.radio_group);
        if (addRoomsPresenter.isMutualedWithOtherOffice(Constants.getProjectId(RoomsActivity.this),
                Constants.getOfficeId(RoomsActivity.this), Constants.getuserId(RoomsActivity.this))) {
            radioGroup.setVisibility(View.VISIBLE);
        }
        final RadioButton mutual = dialogAddRoom.findViewById(R.id.mutual_radioButton);
        final RadioButton notMutual = dialogAddRoom.findViewById(R.id.not_mutual_radioButton);
        if (roomDetails.getIs_mutual().equals("1")) {
            mutual.setChecked(true);
        }
        if (roomDetails.getIs_mutual().equals("0")) {
            notMutual.setChecked(true);
        }
        /////////
        edRoomName.setText(roomDetails.getRoomName());
        edRoomCount.setText(roomDetails.getRoomCount());
        edRoomFurnature.setText(roomDetails.getRoomFurniture());


        editImagesRecyclerView = dialogAddRoom.findViewById(R.id.recyclerViewRoomPhoto);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(RoomsActivity.this, 2);
        editImagesRecyclerView.setLayoutManager(linearLayoutManager);
        editImagesRecyclerView.setHasFixedSize(true);

        roomsImagesForEditing = addRoomsPresenter.getAllRoomImagesForEditing(roomDetails.getProject_id(), roomDetails.getOffice_id(), roomDetails.getUser_id(), currentRoom.get_ID());

        if (roomsImagesForEditing.size() > 0) {
            editImagesRecyclerView.setVisibility(View.VISIBLE);
            RoomImagesAdapter roomImagesAdapter = new RoomImagesAdapter(RoomsActivity.this, roomsImagesForEditing, this, 1);
            editImagesRecyclerView.setAdapter(roomImagesAdapter);
        } else {
            editImagesRecyclerView.setVisibility(View.GONE);
        }


        // TODO save Room Offline
        saveRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roomname = edRoomName.getText().toString();
                String roomNumber  =  edRoomCount.getText().toString();
                if(roomname.isEmpty()){
                    Toast.makeText(RoomsActivity.this,"لابد من ادخال اسم الغرفة",Toast.LENGTH_SHORT).show();
                    edRoomName.setError("لابد من ادخال اسم الغرفة");
                } else if (roomNumber.isEmpty()) {
                    Toast.makeText(RoomsActivity.this,"لابد من ادخال العدد",Toast.LENGTH_SHORT).show();
                    edRoomCount.setError("لابد من ادخال العدد");

                }

                ///////
                else if (!mutual.isChecked() && !notMutual.isChecked() && addRoomsPresenter.isMutualedWithOtherOffice(Constants.getProjectId(RoomsActivity.this), Constants.getOfficeId(RoomsActivity.this), Constants.getuserId(RoomsActivity.this))) {
                    Toast.makeText(RoomsActivity.this, "لابد من اختيار مشترك او غير مشترك", Toast.LENGTH_SHORT).show();
                }
                //////////
                else {

                    /////////////
                    if (mutual.isChecked() && addRoomsPresenter.isMutualedWithOtherOffice(Constants.getProjectId(RoomsActivity.this), Constants.getOfficeId(RoomsActivity.this), Constants.getuserId(RoomsActivity.this))) {

                        roomDetails.setRoomName(edRoomName.getText().toString());
                        roomDetails.setRoomCount(edRoomCount.getText().toString());
                        roomDetails.setRoomFurniture(edRoomFurnature.getText().toString());

                        //////
                        roomDetails.setIs_mutual("1");
                        /////
                        addRoomsPresenter.updateRooms(roomDetails.get_ID(), roomDetails);
                    } else {

                        roomDetails.setRoomName(edRoomName.getText().toString());
                        roomDetails.setRoomCount(edRoomCount.getText().toString());
                        roomDetails.setRoomFurniture(edRoomFurnature.getText().toString());

                        //////
                        roomDetails.setIs_mutual("0");
                        /////
                        addRoomsPresenter.updateRooms(roomDetails.get_ID(), roomDetails);
                    }
                    ////////////

                }


            }
        });
        cancelRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddRoom.dismiss();
                roomsImagesForEditing.clear();
            }
        });
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage2();
                roomsImagesForEditing.clear();
            }
        });
        dialogAddRoom.show();
    }

    private void selectImage() {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.sketch_dailog);
        TextView pic = dialog.findViewById(R.id.pic);
        ImageButton btnPic = dialog.findViewById(R.id.picImage);
        ImageButton btnChoose = dialog.findViewById(R.id.chooseImage);
        Window window = dialog.getWindow();
        window.setLayout(DrawerLayout.LayoutParams.WRAP_CONTENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        final boolean result = Utility.checkPermission(getApplicationContext());

        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userChoosenTask = "Take Photo";
                if (result) {
                    cameraIntent();
                }
                dialog.cancel();
            }
        });
        dialog.show();

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userChoosenTask = "Choose from Library";
                if (result) {
                    galleryIntent();
                }
                dialog.cancel();

            }
        });
        dialog.show();
    }

    private void selectImage2() {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.sketch_dailog);
        TextView pic = dialog.findViewById(R.id.pic);
        ImageButton btnPic = dialog.findViewById(R.id.picImage);
        ImageButton btnChoose = dialog.findViewById(R.id.chooseImage);
        Window window = dialog.getWindow();
        window.setLayout(DrawerLayout.LayoutParams.WRAP_CONTENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        final boolean result = Utility.checkPermission(getApplicationContext());

        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userChoosenTask = "Take Photo";
                if (result) {
                    cameraIntent2();
                }
                dialog.cancel();
            }
        });
        dialog.show();

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userChoosenTask = "Choose from Library";
                if (result) {
                    galleryIntent2();
                }
                dialog.cancel();

            }
        });
        dialog.show();
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent2() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA2);
    }

    private void galleryIntent2() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE2);
    }

    Bitmap bitmap;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                if (getSizeOfImage(data) <= 1024) {
                    try {
                        picUri = onSelectFromGalleryResult(data);
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);
                        RoomImage roomImage = new RoomImage();
                        roomImage.setImageBitmap(bitmap);
                        roomImages.add(roomImage);
                        if (roomImages.size() > 0) {
                            recyclerViewImages.setVisibility(View.VISIBLE);
                            RoomImagesAdapter getInDoorAdapter = new RoomImagesAdapter(RoomsActivity.this, roomImages, this, 1);
                            recyclerViewImages.setAdapter(getInDoorAdapter);
                        } else {
                            recyclerViewImages.setVisibility(View.GONE);
                        }

                    } catch (Exception e) {

                    }

                } else {
                    Toast.makeText(this, "الصوره حجمها كبير لابد ان لا يزيد عن واحد ميجا ", Toast.LENGTH_SHORT).show();

//                    roomsImagesForEditing = addRoomsPresenter.getAllRoomImagesForEditing(currentRoom.getProject_id(), currentRoom.getOffice_id(), currentRoom.getUser_id(), currentRoom.get_ID());
//                    if (roomsImagesForEditing.size() > 0) {
//                        editImagesRecyclerView.setVisibility(View.VISIBLE);
//                        RoomImagesAdapter roomImagesAdapter = new RoomImagesAdapter(RoomsActivity.this, roomsImagesForEditing, this, 1);
//                        editImagesRecyclerView.setAdapter(roomImagesAdapter);
//                    } else {
//                        editImagesRecyclerView.setVisibility(View.GONE);
//                    }
                }

            } else if (requestCode == REQUEST_CAMERA) {
                try {
                    picUri = onCaptureImageResult(data);
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);
                    RoomImage roomImage = new RoomImage();
                    roomImage.setImageBitmap(bitmap);
                    roomImages.add(roomImage);
                    if (roomImages.size() > 0) {
                        recyclerViewImages.setVisibility(View.VISIBLE);
                        RoomImagesAdapter getInDoorAdapter = new RoomImagesAdapter(RoomsActivity.this, roomImages, this, 1);
                        recyclerViewImages.setAdapter(getInDoorAdapter);
                    } else {
                        recyclerViewImages.setVisibility(View.GONE);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_CAMERA2) {
                try {

                   // if (addRoomsPresenter.getAllRoomImagesForEditing(currentRoom.getProject_id(), currentRoom.getOffice_id(), currentRoom.getUser_id(), currentRoom.get_ID()).size() < 2) {
                        picUri = onCaptureImageResult(data);
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);
                        RoomImage roomImage = new RoomImage();
                        roomImage.setImageBitmap(bitmap);
                        roomImage.setRoomID(currentRoom.get_ID());
                        roomImage.setOffice_id(currentRoom.getOffice_id());
                        roomImage.setUser_id(currentRoom.getUser_id());
                        roomImage.setProject_id(currentRoom.getProject_id());
                        addRoomsPresenter.addTmageForRoom(roomImage);
                        roomsImagesForEditing = addRoomsPresenter.getAllRoomImagesForEditing(currentRoom.getProject_id(), currentRoom.getOffice_id(), currentRoom.getUser_id(), currentRoom.get_ID());
                        if (roomsImagesForEditing.size() > 0) {
                            editImagesRecyclerView.setVisibility(View.VISIBLE);
                            RoomImagesAdapter roomImagesAdapter = new RoomImagesAdapter(RoomsActivity.this, roomsImagesForEditing, this, 1);
                            editImagesRecyclerView.setAdapter(roomImagesAdapter);
                        } else {
                            editImagesRecyclerView.setVisibility(View.GONE);
                        }
//                    } else {
//                        Toast.makeText(this, "لا يجب اضافه اكثر من صورتين", Toast.LENGTH_SHORT).show();
//                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE2) {

                if (getSizeOfImage(data) <= 1024) {

                    try {
                       // if (addRoomsPresenter.getAllRoomImagesForEditing(currentRoom.getProject_id(), currentRoom.getOffice_id(), currentRoom.getUser_id(), currentRoom.get_ID()).size() < 2) {
                            picUri = onSelectFromGalleryResult(data);
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);
                            RoomImage roomImage = new RoomImage();
                            roomImage.setImageBitmap(bitmap);
                            roomImage.setRoomID(currentRoom.get_ID());
                            roomImage.setOffice_id(currentRoom.getOffice_id());
                            roomImage.setUser_id(currentRoom.getUser_id());
                            roomImage.setProject_id(currentRoom.getProject_id());
                            addRoomsPresenter.addTmageForRoom(roomImage);
                            roomsImagesForEditing = addRoomsPresenter.getAllRoomImagesForEditing(currentRoom.getProject_id(), currentRoom.getOffice_id(), currentRoom.getUser_id(), currentRoom.get_ID());
                            if (roomsImagesForEditing.size() > 0) {
                                editImagesRecyclerView.setVisibility(View.VISIBLE);
                                RoomImagesAdapter roomImagesAdapter = new RoomImagesAdapter(RoomsActivity.this, roomsImagesForEditing, this, 1);
                                editImagesRecyclerView.setAdapter(roomImagesAdapter);
                            } else {
                                editImagesRecyclerView.setVisibility(View.GONE);
                            }


//                        } else {
//                            Toast.makeText(this, "لا يجب اضافه اكثر من صورتين", Toast.LENGTH_SHORT).show();
//                        }

                    } catch (Exception e) {

                    }
                }

                else {
                    Toast.makeText(this, "الصوره حجمها كبير لابد ان لا يزيد عن واحد ميجا ", Toast.LENGTH_SHORT).show();
                    roomsImagesForEditing = addRoomsPresenter.getAllRoomImagesForEditing(currentRoom.getProject_id(), currentRoom.getOffice_id(), currentRoom.getUser_id(), currentRoom.get_ID());
                    if (roomsImagesForEditing.size() > 0) {
                        editImagesRecyclerView.setVisibility(View.VISIBLE);
                        RoomImagesAdapter roomImagesAdapter = new RoomImagesAdapter(RoomsActivity.this, roomsImagesForEditing, this, 1);
                        editImagesRecyclerView.setAdapter(roomImagesAdapter);
                    } else {
                        editImagesRecyclerView.setVisibility(View.GONE);
                    }
                }

            }
        }

    }

    @SuppressWarnings("deprecation")
    private Uri onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        picUri = getImageUri(getApplicationContext(), bm);
        return picUri;
    }

    private Uri onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 50, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        picUri = getImageUri(getApplicationContext(), thumbnail);
        //ivImage.setImage_bitmap(thumbnail);
        return picUri;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    @Override
    public void onItemClickDelete(final int position, final RoomImage roomImage, View view) {

        new android.support.v7.app.AlertDialog.Builder(this)
                .setMessage("سوف يتم مسح الصوره هل انت متاكد  ؟ ")
                .setCancelable(false)
                .setPositiveButton("متابعة", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (isEditing) {
                            addRoomsPresenter.deleteImageFromRoom(roomImage);
                            roomsImagesForEditing = addRoomsPresenter.getAllRoomImagesForEditing(currentRoom.getProject_id(), currentRoom.getOffice_id(), currentRoom.getUser_id(), currentRoom.get_ID());
                            if (roomsImagesForEditing.size() > 0) {
                                editImagesRecyclerView.setVisibility(View.VISIBLE);
                                RoomImagesAdapter roomImagesAdapter = new RoomImagesAdapter(RoomsActivity.this, roomsImagesForEditing, RoomsActivity.this, 1);
                                editImagesRecyclerView.setAdapter(roomImagesAdapter);
                            } else {
                                editImagesRecyclerView.setVisibility(View.GONE);
                            }

                        } else {
                            roomImages.remove(position);
                            if (roomImages.size() > 0) {

                                recyclerViewImages.setVisibility(View.VISIBLE);
                                RoomImagesAdapter getInDoorAdapter = new RoomImagesAdapter(RoomsActivity.this, roomImages, RoomsActivity.this, 1);
                                recyclerViewImages.setAdapter(getInDoorAdapter);
                            } else {
                                recyclerViewImages.setVisibility(View.GONE);

                            }
                        }
                    }
                })
                .setNegativeButton("الغاء", null)
                .show();


    }


    public double getSizeOfImage(Intent data) {
        long dataSize = 0;
        Uri uri = data.getData();
        String scheme = uri.getScheme();

        if (scheme.equals(ContentResolver.SCHEME_CONTENT)) {
            try {
                InputStream fileInputStream = getApplicationContext().getContentResolver().openInputStream(uri);
                dataSize = fileInputStream.available();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (scheme.equals(ContentResolver.SCHEME_FILE)) {
            String path = uri.getPath();
            File f;
            try {
                f = new File(path);
                dataSize = f.length();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (dataSize / 1024);
    }
}


