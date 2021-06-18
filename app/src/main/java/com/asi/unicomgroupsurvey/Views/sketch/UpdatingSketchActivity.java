package com.asi.unicomgroupsurvey.Views.sketch;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.Views.comments.CommentsActivity;
import com.asi.unicomgroupsurvey.Views.get_user_projects.GetUserProjectsActivity;
import com.asi.unicomgroupsurvey.Views.login.LoginActivity;
import com.asi.unicomgroupsurvey.Views.navigation_home.NavigationHomeActivity;
import com.asi.unicomgroupsurvey.Views.out_door.UpdatingOutDoorActivity;
import com.asi.unicomgroupsurvey.Views.saved_offline_survey.GetOfflineSavedSurveyActivity;
import com.asi.unicomgroupsurvey.data.models.sketch.Sketch;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.ConnectivityReceiver;
import com.asi.unicomgroupsurvey.helper.Constants;
import com.asi.unicomgroupsurvey.helper.Utility;
import com.asi.unicomgroupsurvey.listClickListener.ItemClickListenerForSketch;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdatingSketchActivity extends AppCompatActivity implements SketchView, ConnectivityReceiver.ConnectivityReceiverListener
        , NavigationView.OnNavigationItemSelectedListener , ItemClickListenerForSketch {



//    @BindView(R.id.btn_delete_photo)
//    Button btnDeletePhoto;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    @BindView(R.id.text_next_click)
    TextView nextClick;
    @BindView(R.id.text_back_click)
    TextView backClick;

    @BindView(R.id.textView8)
    com.rengwuxian.materialedittext.MaterialEditText editTextDesc;

    @BindView(R.id.recyclerViewSketchPhoto)
    RecyclerView sketchesList;


    @Inject
    SketchPresenter mainPresenter;
    private String userChoosenTask;
    private Uri picUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sketch1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        TextView titleBar = findViewById(R.id.text_title_bar);
        titleBar.setText("اضافة مخطط توضيحي");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ((DaggerApplication) getApplication()).getAppComponent().inject(this);

        mainPresenter.onAttach(this);
//        save_sketch_btn = findViewById(R.id.save_sketch_btn);
//        save_sketch_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectImage();
//            }
//        });

        findViewById(R.id.ivPicAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        nextClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdatingSketchActivity.this, UpdatingOutDoorActivity.class));
            }
        });
        backClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


//        btnDeletePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                save_sketch_btn.setImageResource(R.drawable.addfile);
//                mainPresenter.deleteSketch(Constants.getOfficeId(UpdatingSketchActivity.this)
//                        , Constants.getuserId(UpdatingSketchActivity.this), Constants.getProjectId(UpdatingSketchActivity.this));
//                btnDeletePhoto.setVisibility(View.GONE);
//            }
//        });

        String projectid=Constants.getProjectId(this);
        String officeid=Constants.getOfficeId(this);
        String userid=Constants.getuserId(this);
        List<Sketch> Sketchlist = mainPresenter.getSketch(projectid, officeid, userid);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(UpdatingSketchActivity.this,2);
        sketchesList.setLayoutManager(linearLayoutManager);
        sketchesList.setHasFixedSize(true);
        if(Sketchlist.size() > 0) {
            SketchAdapter sketchAdapter = new SketchAdapter(this , Sketchlist,this);
            sketchesList.setAdapter(sketchAdapter);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            UpdatingSketchActivity.super.onBackPressed();

//            new AlertDialog.Builder(this)
//                    .setMessage("هل أنت متاكد من الخروج من الصفحة ؟ ")
//                    .setCancelable(false)
//                    .setPositiveButton("متابعة", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            UpdatingSketchActivity.super.onBackPressed();
//                        }
//                    })
//                    .setNegativeButton("الغاء", null)
//                    .show();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.show_saved_data_list) {
            Intent i=new Intent(UpdatingSketchActivity.this,GetOfflineSavedSurveyActivity.class);
            startActivity(i);
        } else if (id == R.id.add_new_survey) {

            Intent intent = new Intent(UpdatingSketchActivity.this , NavigationHomeActivity.class);
            intent.putExtra("project_name" , Constants.getProjectName(UpdatingSketchActivity.this));
            startActivity(intent);

        } else if (id == R.id.nav_change_project) {
            startActivity(new Intent(this, GetUserProjectsActivity.class));
        } else if (id == R.id.nav_comment) {
            startActivity(new Intent(this, CommentsActivity.class));
        } else if (id == R.id.nav_logout) {
            android.app.AlertDialog.Builder builder;
            builder = new android.app.AlertDialog.Builder(UpdatingSketchActivity.this);
            builder.setMessage("هل حقا تريد تسجيل الخروج ؟");
            builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Constants.saveLoginData("", "", "", UpdatingSketchActivity.this);
                    Intent i = new Intent(UpdatingSketchActivity.this, LoginActivity.class);
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("التقاط صورة"))
                        cameraIntent();
                    else if (userChoosenTask.equals("اختيار من الاستديو"))
                        galleryIntent();
                } else {
                }
                break;
        }
    }

    private void selectImage() {

        final Dialog dialog  = new Dialog(this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.sketch_dailog);
        TextView pic=dialog.findViewById(R.id.pic);
        ImageButton btnPic = dialog.findViewById(R.id.picImage);
        ImageButton btnChoose=dialog.findViewById(R.id.chooseImage);
        Window window = dialog.getWindow();
        window.setLayout(DrawerLayout.LayoutParams.WRAP_CONTENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        final boolean result = Utility.checkPermission(getApplicationContext());

        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                if (result) {
                    galleryIntent();
                }
                dialog.cancel();

            }
        });
        dialog.show();

    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    Bitmap bitmap;
    Sketch sketch;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                if (getSizeOfImage(data) <= 1024) {
                    try {
                       // if(mainPresenter.getSketch(Constants.getProjectId(this) , Constants.getOfficeId(this) , Constants.getuserId(this) ).size() < 2 ) {
                           // Toast.makeText(this, getSizeOfImage(data) + "", Toast.LENGTH_SHORT).show();
                            Log.e(">>>>>>>", getSizeOfImage(data) + "");
                            picUri = onSelectFromGalleryResult(data);
                            Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);

                            sketch = new Sketch(Constants.getOfficeId(this)
                                    , Constants.getuserId(this), Constants.getProjectId(this), editTextDesc.getText().toString(), bitmap1);
//                    mainPresenter.deleteSketch(Constants.getOfficeId(SketchActivity.this)
//                            , Constants.getuserId(SketchActivity.this), Constants.getProjectId(SketchActivity.this));
                            mainPresenter.saveSketch(sketch);

                            //   save_sketch_btn.setImageURI(picUri);
                            ///   btnDeletePhoto.setVisibility(View.VISIBLE);

//                        }
//                        else {
//                            Toast.makeText(this, "صورتين فقط", Toast.LENGTH_SHORT).show();
//                        }
                    } catch (Exception e) {
                    }

                } else {
                    Toast.makeText(this, "الصوره حجمها كبير لابد الا يذيد عن واحد ميجا ", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == REQUEST_CAMERA) {
                try {
                    picUri = onCaptureImageResult(data);

                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);

                    sketch = new Sketch(Constants.getOfficeId(this)
                            , Constants.getuserId(this), Constants.getProjectId(this), editTextDesc.getText().toString() , bitmap);
                    // mainPresenter.deleteSketch(Constants.getOfficeId(SketchActivity.this) , Constants.getuserId(SketchActivity.this), Constants.getProjectId(SketchActivity.this));
                    mainPresenter.saveSketch(sketch);

                    // save_sketch_btn.setImageURI(picUri);
                    // save_sketch_btn.setImage_bitmap(bitmap);
                    // btnDeletePhoto.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    private Uri onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

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

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    @Override
    public void startOutDoorActivity() {

    }

    @Override
    public void afterAdd(List<Sketch> sketches) {
        GridLayoutManager linearLayoutManager = new GridLayoutManager(UpdatingSketchActivity.this,2);
        sketchesList.setLayoutManager(linearLayoutManager);
        sketchesList.setHasFixedSize(true);
        if(sketches.size() > 0) {
            SketchAdapter sketchAdapter = new SketchAdapter(this , sketches,this);
            sketchesList.setAdapter(sketchAdapter);
        }

    }

    @Override
    public void afterDelete(List<Sketch> sketches) {
        GridLayoutManager linearLayoutManager = new GridLayoutManager(UpdatingSketchActivity.this,2);
        sketchesList.setLayoutManager(linearLayoutManager);
        sketchesList.setHasFixedSize(true);
//        if(sketches.size() > 0) {
            SketchAdapter sketchAdapter = new SketchAdapter(this , sketches,this);
            sketchesList.setAdapter(sketchAdapter);
//        }

    }


    @Override
    public void onAttache() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

    @Override
    public void onItemClickDelete(int position, final Sketch sketch, View view) {

        new AlertDialog.Builder(this)
                .setMessage("سوف يتم مسح الصوره هل انت متاكد  ؟ ")
                .setCancelable(false)
                .setPositiveButton("متابعة", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mainPresenter.deleteSketch(sketch.getOffice_id(), sketch.getUser_id(), sketch.getProject_id(), sketch.get_ID());
                    }
                })
                .setNegativeButton("الغاء", null)
                .show();


    }

//    public void deleteImage(View view) {
//        ConstMethods.saveSketch(SketchPlace.this, "");
//        ivPicImage.setImageDrawable(getResources().getDrawable(R.drawable.addfile));
//
//    }

//    public void goTOBack(View view) {
//        new AlertDialog.Builder(this)
//                .setMessage("سوف يتم فقد بيانات مسجلة بهذه الصفحة هل أنت متاكد من الخروج من الصفحة ؟ ")
//                .setCancelable(false)
//                .setPositiveButton("متابعة", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        finish();
//                    }
//                })
//                .setNegativeButton("الغاء", null)
//                .show();
//    }

//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(this)
//                .setMessage("سوف يتم فقد بيانات مسجلة بهذه الصفحة هل أنت متاكد من الخروج من الصفحة ؟ ")
//                .setCancelable(false)
//                .setPositiveButton("متابعة", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        SketchPlace.super.onBackPressed();
//                    }
//                })
//                .setNegativeButton("الغاء", null)
//                .show();
//    }

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
