package com.asi.unicomgroupsurvey.Views.out_door;

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
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.Views.comments.CommentsActivity;
import com.asi.unicomgroupsurvey.Views.get_user_projects.GetUserProjectsActivity;
import com.asi.unicomgroupsurvey.Views.in_door.InDoorActivity;
import com.asi.unicomgroupsurvey.Views.login.LoginActivity;
import com.asi.unicomgroupsurvey.Views.navigation_home.NavigationHomeActivity;
import com.asi.unicomgroupsurvey.Views.saved_offline_survey.GetOfflineSavedSurveyActivity;
import com.asi.unicomgroupsurvey.data.models.out_door.OutDoorImage;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Constants;
import com.asi.unicomgroupsurvey.helper.Utility;
import com.asi.unicomgroupsurvey.listClickListener.ItemClickListenerForOutDoorImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

public class OutDoorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OutDoorView, ItemClickListenerForOutDoorImage {


    @Inject
    OutDoorPresenter outDoorPresenter;

    Uri picUri;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private FloatingActionButton floatNewImage;
    private RecyclerView recyclerViewOutDoor;
    com.rengwuxian.materialedittext.MaterialEditText editText ;

    private Button nextBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_door1);
        editText = findViewById(R.id.textView8);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView titleBar = findViewById(R.id.text_title_bar);
        titleBar.setText("صور المبني من الخارج ");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerViewOutDoor = findViewById(R.id.recyclerViewOutDoorPhoto);

        nextBtn = findViewById(R.id.btn_next);

        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        outDoorPresenter.onAttach(this);

        floatNewImage = findViewById(R.id.float_out_door_image);

        /*LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OutDoorActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);*/

        GridLayoutManager linearLayoutManager = new GridLayoutManager(OutDoorActivity.this,2);

        recyclerViewOutDoor.setLayoutManager(linearLayoutManager);

        floatNewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        outDoorPresenter.getOutDoorImages();


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(OutDoorActivity.this , InDoorActivity.class));
            }
        });

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
            OutDoorActivity.super.onBackPressed();
            /*new AlertDialog.Builder(this)
                    .setMessage("هل أنت متاكد من الخروج من الصفحة ؟ ")
                    .setCancelable(false)
                    .setPositiveButton("متابعة", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            OutDoorActivity.super.onBackPressed();
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
            Intent i=new Intent(OutDoorActivity.this,GetOfflineSavedSurveyActivity.class);
            startActivity(i);
        } else if (id == R.id.add_new_survey) {

            Intent intent = new Intent(OutDoorActivity.this , NavigationHomeActivity.class);
            intent.putExtra("project_name" , Constants.getProjectName(OutDoorActivity.this));
            startActivity(intent);


        } else if (id == R.id.nav_change_project) {
            startActivity(new Intent(this, GetUserProjectsActivity.class));
        } else if (id == R.id.nav_comment) {
            startActivity(new Intent(this, CommentsActivity.class));
        } else if (id == R.id.nav_logout) {
            android.app.AlertDialog.Builder builder;
            builder = new android.app.AlertDialog.Builder(OutDoorActivity.this);
            builder.setMessage("هل حقا تريد تسجيل الخروج ؟");
            builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Constants.saveLoginData("", "", "", OutDoorActivity.this);
                    Intent i = new Intent(OutDoorActivity.this, LoginActivity.class);
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
                    //code for deny
                }
                break;


        }
    }




    private void selectImage() {
//        final CharSequence[] items = {"التقاط صورة", "اختيار من الاستديو",
//                "إلغاء"};
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("إضافة صورة للمبني من الخارج");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//                boolean result = Utility.checkPermission(OutDoorActivity.this);
//
//                if (items[item].equals("التقاط صورة")) {
//                    userChoosenTask = "Take Photo";
//                    if (result)
//                        cameraIntent();
//
//                } else if (items[item].equals("اختيار من الاستديو")) {
//                    userChoosenTask = "Choose from Library";
//                    if (result)
//                        galleryIntent();
//
//                } else if (items[item].equals("إلغاء")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//        builder.show();



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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {

              //  if(outDoorPresenter.getNimberOfOutDoorImages().size() < 2 ) {
                    if (getSizeOfImage(data) <= 1024) {
                        try {
                            picUri = onSelectFromGalleryResult(data);
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);

                            OutDoorImage outDoorImage = new OutDoorImage();
                            outDoorImage.setOutDoorImageBitmap(bitmap);
                            outDoorImage.setOutDoorImageDesc(editText.getText().toString());
                            outDoorPresenter.saveOutDoorOfflinePresenter(outDoorImage);
                            editText.setText("");
                        } catch (Exception e) {

                        }
                    } else {
                        Toast.makeText(this, "الصوره حجمها كبير لابد الا يذيد عن واحد ميجا ", Toast.LENGTH_SHORT).show();
                    }
//                }
//                else  {
//                    Toast.makeText(this, "يجب ان لا يزيد عدد الصور عن صورتين ", Toast.LENGTH_SHORT).show();
//                }

            } else if (requestCode == REQUEST_CAMERA) {
                try {
                    picUri = onCaptureImageResult(data);

                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);
                    OutDoorImage outDoorImage = new OutDoorImage();
                    outDoorImage.setOutDoorImageBitmap(bitmap);
                    outDoorImage.setOutDoorImageDesc(editText.getText().toString());
                    outDoorPresenter.saveOutDoorOfflinePresenter(outDoorImage);
                    editText.setText("");

                } catch (IOException e) {
                    e.printStackTrace();
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

        picUri=getImageUri(getApplicationContext(),bm);
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

        picUri =getImageUri(getApplicationContext(),thumbnail);
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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showOutDoorImages(List<OutDoorImage> outDoorImages) {
        if(outDoorImages.size() > 0){
            recyclerViewOutDoor.setVisibility(View.VISIBLE);

            GetOutDoorAdapter getOutDoorAdapter = new GetOutDoorAdapter(this, outDoorImages, this);
            recyclerViewOutDoor.setAdapter(getOutDoorAdapter);
        }
        else
        {
            recyclerViewOutDoor.setVisibility(View.GONE);

        }
    }

    @Override
    public void onAttache() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onItemClickDelete(int position, final OutDoorImage outDoorImage, View view) {

        new AlertDialog.Builder(this)
                .setMessage("سوف يتم مسح الصوره هل انت متاكد  ؟ ")
                .setCancelable(false)
                .setPositiveButton("متابعة", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        outDoorPresenter.deleteOutDoorImage(outDoorImage.getOutDoorImage_ID());
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
