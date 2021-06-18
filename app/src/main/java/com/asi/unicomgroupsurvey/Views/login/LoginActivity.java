package com.asi.unicomgroupsurvey.Views.login;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.asi.unicomgroupsurvey.R;

import com.asi.unicomgroupsurvey.Views.get_user_projects.GetUserProjectsActivity;
import com.asi.unicomgroupsurvey.data.models.Item;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.ConnectivityReceiver;
import com.asi.unicomgroupsurvey.helper.Constants;
import com.asi.unicomgroupsurvey.helper.GpsTracker;

import java.util.ArrayList;

import javax.inject.Inject;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class LoginActivity extends AppCompatActivity implements LoginView,ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    LoginPresenter loginPresenter;

    LoginView loginView;

    EditText edit_user_name,edtPass;
    Button btn_login;
    ProgressBar pbar;
    LinearLayout ll;

    private static final int PERMISSION_REQUEST_CODE = 200;
    private Dialog dialog;
    private ProgressBar probar;
    private GpsTracker gpsTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_user_name=findViewById(R.id.edit_user_name);
        edtPass=findViewById(R.id.edtPass);
        btn_login=findViewById(R.id.btn_login);




        pbar=findViewById(R.id.pbar);
        ll=findViewById(R.id.ll);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog);

        probar=dialog.findViewById(R.id.pbar);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        ((DaggerApplication)getApplication()).getAppComponent().inject(this);
        loginPresenter.onAttach(this);
        DaggerApplication.getDaggerApplication().setConnectivityListener(this);


        String userid=Constants.getuserId(this);

//        edit_user_name.setText("ahmed@unicomg.com");
//        edtPass.setText("123456");

        if (!checkPermission()) {
            requestPermission();
        }

        if(! userid.isEmpty()){
            Intent i=new Intent(this,GetUserProjectsActivity.class);
            startActivity(i);
        }else{
            String email=Constants.getUn(this);
            String pw=Constants.getPw(this);
           // edit_user_name.setText(email);
            //edtPass.setText(pw);
        }




        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String email,pw;

                email=edit_user_name.getText().toString();
                pw=edtPass.getText().toString();
                //edit_user_name.setText("ahmed@unicomg.com");
                //edtPass.setText("123456");

                if(email.isEmpty() && pw.isEmpty()){
                    Toast.makeText(LoginActivity.this, "لابد من ادخال جميع الحقول ", Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()){
                    Toast.makeText(LoginActivity.this, "لابد من ادخال بريدك الالكترونى ", Toast.LENGTH_SHORT).show();
                    edit_user_name.setError("لابد من ادخال بريدك الالكترونى ");
                }else if(pw.isEmpty()){
                    Toast.makeText(LoginActivity.this, "لابد من ادخال كلمة السر ", Toast.LENGTH_SHORT).show();
                    edtPass.setError("لابد من ادخال كلمة السر");
                }else{
                    loginPresenter.getLogin(email,pw,pbar,ll,dialog,probar);
                }
            }
        });
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(LoginActivity.this)
                .setMessage(message)
                .setPositiveButton("تم", okListener)
                .setNegativeButton("الغاء", null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readStorageAccepted = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted = grantResults[3] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted && cameraAccepted && readStorageAccepted && writeStorageAccepted)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                showMessageOKCancel("التطبيق يحتاج بعض الصلاحيات",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{ACCESS_FINE_LOCATION, CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    //}
                }


                break;
        }
    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);

        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED &&
                result3 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message, int mColor) {

        Toast.makeText(LoginActivity.this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateList(ArrayList<Item> items) {

    }

    @Override
    public void openProjectsActivity() {
        Intent i=new Intent(LoginActivity.this,GetUserProjectsActivity.class);
        startActivity(i);
    }



/*
    @Override
    public void getLogin( String email,  String pw) {

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String   email=edit_user_name.getText().toString();
                String pw=edtPass.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(LoginActivity.this, "لابد من ادخال اميلك ", Toast.LENGTH_SHORT).show();
                }else if(pw.isEmpty()){
                    Toast.makeText(LoginActivity.this, "لابد من ادخال كلمة السر ", Toast.LENGTH_SHORT).show();
                }else{
                    loginView.getLogin(email,pw);
                }
            }
        });




    }
    */


    @Override
    public void onAttache() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        loginPresenter.checkConnection(isConnected);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
