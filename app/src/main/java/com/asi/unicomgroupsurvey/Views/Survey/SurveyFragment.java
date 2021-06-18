package com.asi.unicomgroupsurvey.Views.Survey;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.asi.unicomgroupsurvey.Adapters.CitiesSpinnerAdapter;
import com.asi.unicomgroupsurvey.Adapters.DistricSpinnerAdapter;
import com.asi.unicomgroupsurvey.Adapters.GovSpinnerAdapter;
import com.asi.unicomgroupsurvey.Adapters.OfficeAdapter;
import com.asi.unicomgroupsurvey.R;
//import com.asi.unicomgroupsurvey.Views.add_rooms.AddRoomsFragment;
import com.asi.unicomgroupsurvey.Views.jobs.JobsActivity;
//import com.asi.unicomgroupsurvey.Views.jobs.JobsFragment;
import com.asi.unicomgroupsurvey.Views.navigation_home.NavigationHomeActivity;
import com.asi.unicomgroupsurvey.data.models.Surveys.Survey;
import com.asi.unicomgroupsurvey.data.models.getCities.City;
import com.asi.unicomgroupsurvey.data.models.getDistricts.District;
import com.asi.unicomgroupsurvey.data.models.getGovernors.Governor;
import com.asi.unicomgroupsurvey.data.models.offices.OfficeResponseDetails;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Constants;
import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurveyFragment extends Fragment implements AdapterView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    private View mView;
    @BindView(R.id.spinnerGov)
    Spinner spinnerGov;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.chk_inside)
    CheckBox chk_inside;
    @BindView(R.id.chk_outside)
    CheckBox chk_outside;
    @BindView(R.id.chk_arabic)
    RadioButton chk_arabic;
    @BindView(R.id.chk_kurdish)
    RadioButton chk_kurdish;
    @BindView(R.id.chk_both)
    RadioButton chk_both;
    @BindView(R.id.edit_ins_out_notes)
    EditText edit_ins_out_notes;
    @BindView(R.id.edit_others_num)
    EditText edit_others_num;
    @BindView(R.id.edit_others_notes)
    EditText edit_others_notes;
    @BindView(R.id.chk_two)
    CheckBox chk_two;
    @BindView(R.id.spinnerOffices2)
    Spinner spinnerOffices2;
    @BindView(R.id.spinnerCities)
    Spinner spinnerCities;
    @BindView(R.id.spinnerDistrict)
    Spinner spinnerDistrict;
    @BindView(R.id.spinnerOffices)
    Spinner spinnerOfficeName;
    @BindView(R.id.edit_address)
    EditText edt_address;
    @BindView(R.id.edit_mobile)
    EditText edt_phone;
    @BindView(R.id.text_owner)
    TextView radioOwner;
    @BindView(R.id.text_rental)
    TextView radioRent;
    @BindView(R.id.text_inside_net_yes)
    TextView radioIsNetWorkYes;
    @BindView(R.id.text_inside_net_no)
    TextView radioIsNetWorkNo;
    @BindView(R.id.text_internet_yes)
    TextView radioHasInternetYes;
    @BindView(R.id.text_internet_no)
    TextView radioHasInternetNo;
    @BindView(R.id.edit_internet_speed)
    EditText editInternetSpeed;
    @BindView(R.id.edit_computers_num)
    EditText edt_computer_count;
    @BindView(R.id.edit_computers_notes)
    EditText edt_computer_notes;
    @BindView(R.id.edit_printers_num)
    EditText edt_printers_count;
    @BindView(R.id.edit_printers_notes)
    EditText edt_printers_notes;
    @BindView(R.id.edit_scanners_num)
    EditText edt_scanners_count;
    @BindView(R.id.edit_scanners_notes)
    EditText edt_scanners_notes;
    @BindView(R.id.text_morning_shift)
    TextView morningShift;
    @BindView(R.id.text_night_shift)
    TextView nightShift;
    @BindView(R.id.text_both_shift)
    TextView bothShift;
    @BindView(R.id.text_next_click)
    TextView nextClick;
    @BindView(R.id.text_back_click)
    TextView backClick;
    @BindView(R.id.edit_from)
    TextView editFrom;
    @BindView(R.id.edit_to)
    TextView editTo;
    @BindView(R.id.edit_from_N)
    TextView editFromN;
    @BindView(R.id.edit_to_N)
    TextView editToN;
    @BindView(R.id.edt_other_cities)
    EditText edtOtherCities;
    @BindView(R.id.edt_other_district)
    EditText edtOtherDistrict;

    Dialog dialogAddCity;
    private String strGovId;
    private String strCityId;
    private String strDisrtric;
    private String strProjectName;
    private String strofficeid;
    private String strotherofficeid;
    private String strotherofficename;
    String strOwnerShipType = "1";
    Boolean isOnePressed = false, isSecondOne = false, isThirdOne = false;
    TimePickerDialog timePickerDialog, timePickerDialogMorningFrom, timePickerDialogMorningTo,
            timePickerDialogNightFrom, timePickerDialogNightTo;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    int currentHour1;
    int currentMinute1;
    String amPm;
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
    Date inTime;
    Date outTime;
    String hasInternet = "1", isNetwork = "1";
    String strShiftType = "+";
    String officeVisit;
    String visit;
    Boolean valMor = false;
    Boolean valEve = false;
    boolean clicked = false;
    Boolean impty = false;
    String govName, cityName, disName, officeName;
    Calendar cal;
    Calendar cal1 = Calendar.getInstance();
    Calendar cal12 = Calendar.getInstance();
    String otherCity = "1";
    String otherDistrict = "1";
    String morningShiftTxtFrom, morningShiftTxtTo, nightShiftRxtFrom, nightShiftTextTo;
    @Inject
    SurveyPresenter surveyPresenter;

    SurveyView surveyView;
    List<Governor> govsoffline;
    List<City> citiesoffline;
    List<District> disoffline;
    List<OfficeResponseDetails> offiesoffline;
    private String cityIdOther;
    private String distid;
    private String two;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.activity_survey, container, false);
        ButterKnife.bind(this, mView);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        edtOtherCities.setVisibility(View.GONE);
        edtOtherDistrict.setVisibility(View.GONE);

        iniRadio();
        iniGovSpinner();
        //iniCitiesSpinner();
        //iniDistrictsSpinner();
        //iniOfficesSpinner();

        //getData();
        //getData2();
        setDefault();


        backClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(getActivity())
                        .setMessage("سوف يتم فقد جميع البيانات المسجله هل أنت متاكد من الخروج من الصفحة ؟ ")
                        .setCancelable(false)
                        .setPositiveButton("متابعة", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //startActivity(new Intent(getActivity(), RegistedList.class));
                                //startActivity(new Intent(getActivity(), SurveyActivity.class));
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("الغاء", null)
                        .show();
            }
        });

        nextClick.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        validateOnFields();
                    }
                });



        editFrom.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                cal = Calendar.getInstance();
                currentHour = cal.get(Calendar.HOUR_OF_DAY);
                currentMinute = cal.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    //  @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = " " + " PM";
                        } else {
                            amPm = " " + " AM";
                        }
                        editFrom.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);

                        editTo.setFocusable(true);

                        try {
                            inTime = sdf.parse(editFrom.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (inTime.getHours() == 0 && amPm.equals("  PM")) {
                            //error1.setError(null);
                            //error11.setError(null);

                        } else {
                            if (!isTimeAfter(inTime, outTime)) {
                                editTo.setError("قم بادخال وقت الانتهاء من العمل الصحيح للدوام ");
                                valMor = true;

                            } else {
                                editTo.setError(null);


                            }
                        }




                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
                clicked = true;

            }

            @Override
            public void onDoubleClick(View view) {

            }

        }));


        editTo.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                if (clicked) {

                    cal = Calendar.getInstance();
                    currentHour = cal.get(Calendar.HOUR_OF_DAY);
                    currentMinute = cal.get(Calendar.MINUTE);


                    timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        //    @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                            if (hourOfDay >= 12) {
                                amPm = " " + " PM";
                            } else {
                                amPm = " " + " AM";
                            }
                            editTo.setError(null);
                            editTo.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);


                            try {
                                outTime = sdf.parse(editTo.getText().toString());

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                            Log.e("H---ASI-->", String.valueOf(outTime.getHours()));
                            Log.e("H---ASI-->", String.valueOf(amPm));
                            if (outTime.getHours() == 0 && amPm.equals("  PM")) {
                                //error1.setError(null);
                                //error11.setError(null);

                            } else {
                                if (!isTimeAfter(inTime, outTime)) {

                                    editTo.setError("قم بادخال وقت الانتهاء من العمل الصحيح للدوام ");
                                    valMor = true;

                                } else {
                                    editTo.setError(null);
                                }
                            }


                        }
                    }, currentHour, currentMinute, false);
                    timePickerDialog.show();


                } else {
                    Toast.makeText(getActivity(), "من فضلك ادخل بدايه العمل اولا", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onDoubleClick(View view) {


            }
        }));

        editToN.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                if (clicked) {

                    cal = Calendar.getInstance();
                    currentHour = cal.get(Calendar.HOUR_OF_DAY);
                    currentMinute = cal.get(Calendar.MINUTE);
                    timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        //    @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                            if (hourOfDay >= 12) {
                                amPm = " " + " PM";
                            } else {
                                amPm = " " + " AM";
                            }
                            editToN.setError(null);
                            editToN.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);

                            try {
                                outTime = sdf.parse(editToN.getText().toString());

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            Log.e("H---ASI-->", String.valueOf(outTime.getHours()));
                            Log.e("H---ASI-->", String.valueOf(amPm));
                            if (outTime.getHours() == 0 && amPm.equals("  PM")) {
                                //error1.setError(null);
                                //error11.setError(null);

                            } else {
                                if (!isTimeAfter(inTime, outTime)) {
                                    editToN.setError("قم بادخال وقت الانتهاء من العمل الصحيح للدوام ");
                                    valEve = true;

                                } else {

                                    editToN.setError(null);
                                }
                            }

                        }
                    }, currentHour, currentMinute, false);
                    timePickerDialog.show();


                } else {
                    Toast.makeText(getActivity(), "من فضلك ادخل بدايه العمل اولا", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onDoubleClick(View view) {

            }
        }));


        editFromN.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {

                // Single tap here.
                cal = Calendar.getInstance();
                currentHour = cal.get(Calendar.HOUR_OF_DAY);
                currentMinute = cal.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    //  @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = " " + " PM";
                        } else {
                            amPm = " " + " AM";
                        }
                        editFromN.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);

                        editToN.setFocusable(true);

                        try {
                            inTime = sdf.parse(editFromN.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (inTime.getHours() == 0 && amPm.equals("  PM")) {
                            //error1.setError(null);
                            //error11.setError(null);

                        } else {
                            if (!isTimeAfter(inTime, outTime)) {

                                editToN.setError("قم بادخال وقت الانتهاء من العمل الصحيح للدوام ");
                                valEve = true;

                            } else {
                                editToN.setError(null);
                            }
                        }


                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
                clicked = true;

            }

            @Override
            public void onDoubleClick(View view) {

            }

        }));


        morningShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isOnePressed = true;
                morningShift.setBackgroundResource(R.drawable.sun_active);
                nightShift.setBackgroundResource(R.drawable.moon_nun);
                bothShift.setBackgroundResource(R.drawable.sun_non);
                strShiftType = "1";

                editFrom.setText(null);
                editTo.setText(null);
                editFrom.setVisibility(View.VISIBLE);
                editFrom.setHint("أدخل وقت الحضور صباحا");
                editTo.setVisibility(View.VISIBLE);
                editTo.setHint("أدخل وقت الانصراف صباحا");
                editFromN.setVisibility(View.GONE);
                editToN.setVisibility(View.GONE);
                editFrom.setText(null);
                editTo.setText(null);
                editFromN.setText(null);
                editToN.setText(null);

            }


        });
        nightShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSecondOne = true;
                nightShift.setBackgroundResource(R.drawable.moon_nun);
                strShiftType = "2";
                morningShift.setBackgroundResource(R.drawable.sun_non);
                nightShift.setBackgroundResource(R.drawable.moon_active);
                bothShift.setBackgroundResource(R.drawable.sun_non);
                editFromN.setVisibility(View.GONE);
                editToN.setVisibility(View.GONE);
                editFrom.setText(null);
                editTo.setText(null);
                editFromN.setText(null);
                editToN.setText(null);
                editFrom.setVisibility(View.VISIBLE);
                editFrom.setHint("أدخل وقت الحضور مساء");
                editTo.setVisibility(View.VISIBLE);
                editTo.setHint("أدخل وقت الانصراف مساء");

            }
        });

        bothShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strShiftType = "3";
                isThirdOne = true;

                morningShift.setBackgroundResource(R.drawable.sun_non);
                nightShift.setBackgroundResource(R.drawable.moon_nun);
                bothShift.setBackgroundResource(R.drawable.sun_active);
                editFrom.setVisibility(View.VISIBLE);
                editTo.setVisibility(View.VISIBLE);
                editFromN.setVisibility(View.VISIBLE);
                editToN.setVisibility(View.VISIBLE);
                editFrom.setHint("أدخل وقت الحضور صباحا");
                editTo.setVisibility(View.VISIBLE);
                editTo.setHint("أدخل وقت الانصراف صباحا");
                editFromN.setHint("أدخل وقت الحضور مساء");
                editToN.setHint("أدخل وقت الانصراف مساء");

            }
        });

        return mView;
    }

    private void getData2() {
        String userid = Constants.getuserId(getActivity());
        String officeid = Constants.getOfficeId(getActivity());
        String projectid = Constants.getProjectId(getActivity());
        List<Survey> surveyoffline = surveyPresenter.getdatabyUseridOfficeidProjectid(userid, officeid, projectid);
        for (int i = 0; i < surveyoffline.size(); i++) {
            Survey survey = surveyoffline.get(i);
            Toast.makeText(getActivity(), survey.getCity_name() + " - " + survey.getOther_city() + " - " + survey.getDistrict_name() + " - " + survey.getOther_district(), Toast.LENGTH_SHORT).show();
        }
    }

    private void getData() {
        List<Survey> surveyoffline = surveyPresenter.getdata();

        for (int i = 0; i < surveyoffline.size(); i++) {
            Survey survey = surveyoffline.get(i);
            Toast.makeText(getActivity(), survey.getOffice_id() + " - " + survey.getOffice_name(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setDefault() {
        editFrom.setVisibility(View.GONE);
        editTo.setVisibility(View.GONE);

        editFromN.setVisibility(View.GONE);
        editToN.setVisibility(View.GONE);

        if (strOwnerShipType.equals("1")) {
            strOwnerShipType = "1";
            radioOwner.setBackgroundResource(R.drawable.blue_toggle);
            radioOwner.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
            radioRent.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryText));
            radioRent.setBackgroundResource(R.drawable.white_toggle_left);

        } else {

            strOwnerShipType = "2";
            radioRent.setBackgroundResource(R.drawable.blue_toggle_left);
            radioOwner.setBackgroundResource(R.drawable.white_toggle);
            radioOwner.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryText));
            radioRent.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));

        }

        if (isNetwork.equals("1")) {
            isNetwork = "1";

            radioIsNetWorkYes.setBackgroundResource(R.drawable.blue_toggle);
            radioIsNetWorkYes.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));

            radioIsNetWorkNo.setBackgroundResource(R.drawable.white_toggle_left);
            radioIsNetWorkNo.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryText));
        } else {
            isNetwork = "0";
            radioIsNetWorkYes.setBackgroundResource(R.drawable.white_toggle);
            radioIsNetWorkYes.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryText));
            radioIsNetWorkNo.setBackgroundResource(R.drawable.blue_toggle_left);
            radioIsNetWorkNo.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
        }

        if (hasInternet.equals("1")) {
            hasInternet = "1";
            radioHasInternetYes.setBackgroundResource(R.drawable.blue_toggle);
            radioHasInternetYes.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
            radioHasInternetNo.setBackgroundResource(R.drawable.white_toggle_left);
            radioHasInternetNo.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryText));
            editInternetSpeed.setVisibility(View.VISIBLE);
        } else {
            hasInternet = "0";
            editInternetSpeed.setVisibility(View.GONE);
            editInternetSpeed.setText(null);
            radioHasInternetYes.setBackgroundResource(R.drawable.white_toggle);
            radioHasInternetYes.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryText));
            radioHasInternetNo.setBackgroundResource(R.drawable.blue_toggle_left);
            radioHasInternetNo.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
        }


    }


    private void validateOnFields() {
        int computercount = 0;
        if (!edt_computer_count.getText().toString().isEmpty()) {
            computercount = Integer.parseInt(edt_computer_count.getText().toString());
        } else {
            edt_computer_count.setError("ادخل عدد الاجهزة");
            return;
        }
        String mfrom = editFrom.getText().toString();
        String efrom = editFromN.getText().toString();
        String mto = editTo.getText().toString();
        String eto = editToN.getText().toString();

        if (govName.length() == 0 || govName.equals("اختر المحافظة")) {
            Toast.makeText(getActivity(), "من فضلك اخترالمحافظة", Toast.LENGTH_SHORT).show();

        } else if (cityName.equals("أختر المدينة")) {
            Toast.makeText(getActivity(), "من فضلك اخترالمدينة", Toast.LENGTH_SHORT).show();
        } else if (cityName.contains("اخرى") && edtOtherCities.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "من فضلك اختر او اكتب اسم المدينة ", Toast.LENGTH_SHORT).show();
            edtOtherCities.setError("اكتب اسم المدينة");
        } else if (disName.equals("أختر الحي")) {
            Toast.makeText(getActivity(), "من فضلك اختر الحي", Toast.LENGTH_SHORT).show();
        } else if (disName.contains("اخرى") && edtOtherDistrict.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "من فضلك اختر او اكتب اسم الحى ", Toast.LENGTH_SHORT).show();
            edtOtherDistrict.setError("اكتب اسم الحى ");
        } else if (officeName.length() == 0 || officeName.equals("اختر المكتب")) {
            Toast.makeText(getActivity(), "من فضلك اختر المكتب", Toast.LENGTH_SHORT).show();
        } else if (edt_address.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "من فضلك أدخل العنوان", Toast.LENGTH_SHORT).show();
            edt_address.setError("من فضلك أدخل العنوان");
        } else if (edt_phone.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "من فضلك أدخل رقم الهاتف", Toast.LENGTH_SHORT).show();
            edt_phone.setError("من فضلك أدخل رقم الهاتف");
        } else if (strShiftType.equals("+")) {
            Toast.makeText(getActivity(), "من فضلك أدخل وقت الدوام بشكل صحيح", Toast.LENGTH_SHORT).show();
        } else if (edt_computer_count.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "من فضلك أدخل عدد الاجهزة", Toast.LENGTH_SHORT).show();
            edt_computer_count.setError("من فضلك أدخل عدد الاجهزة");
        } else if (edt_printers_count.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "من فضلك أدخل عدد أجهزة الطباعة", Toast.LENGTH_SHORT).show();
            edt_printers_count.setError("من فضلك أدخل عدد أجهزة الطباعة");
        } else if (edt_scanners_count.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "من فضلك أدخل عدد أجهزة الماسح الضوئي", Toast.LENGTH_SHORT).show();
            edt_scanners_count.setError("من فضلك أدخل عدد أجهزة الماسح الضوئي");
        } else if (hasInternet.equals("1") && editInternetSpeed.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "من فضلك أدخل سرعة الانترنت", Toast.LENGTH_SHORT).show();
            editInternetSpeed.setError("من فضلك أدخل سرعة الانترنت");
        } else if (isNetwork.equals("1") && chk_outside.isChecked() == false && chk_inside.isChecked() == false) {
            Toast.makeText(getActivity(), "من فضلك اختار نوع التوصيلات", Toast.LENGTH_SHORT).show();
        } else if (chk_two.isChecked()  && strotherofficeid.equals("0") &&  strotherofficeid == null ) {
            Toast.makeText(getActivity(), "من فضلك اختار اسم المكتب المشترك", Toast.LENGTH_SHORT).show();
        }
        else if (edt_computer_notes.getText().toString().length() > 100) {
            Toast.makeText(getActivity(), "ملاحظات الاجهزة لابد ان لا تتجاوز 100 حرف ", Toast.LENGTH_SHORT).show();
        } else if (edt_printers_notes.getText().toString().length() > 100) {
            Toast.makeText(getActivity(), "ملاحظات الطابعات لابد ان لا تتجاوز 100 حرف ", Toast.LENGTH_SHORT).show();
            edt_printers_notes.setError("ملاحظات الطابعات لابد ان لا تتجاوز 100 حرف");
        } else if (edt_scanners_notes.getText().toString().length() > 100) {
            Toast.makeText(getActivity(), "ملاحظات الاسكنرز لابد ان لا تتجاوز 100 حرف ", Toast.LENGTH_SHORT).show();
            edt_scanners_notes.setError("ملاحظات الاسكنرز لابد ان لا تتجاوز 100 حرف ");
        }else if (chk_arabic.isChecked() == false && chk_kurdish.isChecked() == false && chk_both.isChecked() == false) {
            Toast.makeText(getContext(), "يجب اختيار لغه ادخال", Toast.LENGTH_SHORT).show();
        }

        else {

            if (!isTimeAfter(inTime, outTime)) {
                //error1.setError("قم بادخال وقت الانتهاء من العمل الصحيح للدوام الصباحى");
                //error11.setError("قم بادخال وقت الانتهاء من العمل الصحيح للدوام الصباحى");

                //editToN.setError("قم بادخال وقت الانتهاء من العمل الصحيح للدوام ");
                Toast.makeText(getActivity(), "قم بادخال وقت الانتهاء من العمل الصحيح للدوام", Toast.LENGTH_SHORT).show();
                //valEve = true;

            } else if (hasInternet.equals("1") && editInternetSpeed.getText().toString().length() == 0) {

                //customToast = new CustomToast(NewUiSurveyScreen .this , "من فضلك أدخل سرعة الانترنت  ");

                Toast.makeText(getActivity(), "من فضلك أدخل سرعة الانترنت", Toast.LENGTH_SHORT).show();
                editInternetSpeed.setError("من فضلك أدخل سرعة الانترنت");
            } else {
                if (!isTimeAfter(inTime, outTime)) {
                    //error1.setError("قم بادخال وقت الانتهاء من العمل الصحيح للدوام الصباحى");
                    //error11.setError("قم بادخال وقت الانتهاء من العمل الصحيح للدوام الصباحى");
                    //editToN.setError("قم بادخال وقت الانتهاء من العمل الصحيح للدوام ");
                    Toast.makeText(getActivity(), "قم بادخال وقت الانتهاء من العمل الصحيح للدوام", Toast.LENGTH_SHORT).show();
                    editFrom.setError("قم بادخال وقت الانتهاء من العمل الصحيح للدوام");
                    //valEve = true;

                } else if (strShiftType.equals("1") && (editFrom.getText().toString().length() == 0 || editTo.getText().toString().length() == 0)) {
                    Toast.makeText(getActivity(), "قم بادخال وقت البداية والانتهاء للدوام الصباحى", Toast.LENGTH_SHORT).show();
                    editTo.setError("قم بادخال وقت الانتهاء");
                    editFrom.setError("قم بادخال وقت البداية");
                } else if (strShiftType.equals("2") && (editFrom.getText().toString().length() == 0 || editTo.getText().toString().length() == 0)) {
                    Toast.makeText(getActivity(), strShiftType + " - " + editFrom.getText().toString() + " - " + editTo.getText().toString(),
                            Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "قم بادخال وقت البداية والانتهاء للدوام المسائى", Toast.LENGTH_SHORT).show();
                    editFrom.setError("قم بادخال وقت البداية ");
                    editTo.setError("قم بادخال وقت والانتهاء");

                } else if (strShiftType.equals("3") && (editFrom.getText().toString().length() == 0 || editTo.getText().toString().length() == 0 ||
                        editFromN.getText().toString().length() == 0 || editToN.getText().toString().length() == 0)) {
                    Toast.makeText(getActivity(), "قم بادخال وقت البداية والانتهاء للدوامين الصباحى والمسائى ", Toast.LENGTH_SHORT).show();
                    editToN.setError("قم بادخال وقت الانتهاء");
                    editFromN.setError("قم بادخال وقت البداية");
                } /*else if (computercount > 65355) {
                    Toast.makeText(getActivity(), "اكبر رقم يمكن ادخله 65000", Toast.LENGTH_SHORT).show();
                }*/  else {
                    if (mfrom.equals(efrom) || mto.equals(eto)) {
                        Toast.makeText(getActivity(), "قم بادخال وقت البداية والانتهاء للدوامين الصباحى والمسائى باوقات مختلفة ", Toast.LENGTH_SHORT).show();
                        editFromN.setError("");
                        editFrom.setError("");
                        editTo.setError("");
                        editToN.setError("");
                    } else {

                        String userid = Constants.getuserId(getActivity());
                        String officeid = Constants.getOfficeId(getActivity());
                        String projectid = Constants.getProjectId(getActivity());
                        List<Survey> surveyoffline = surveyPresenter.getdatabyUseridOfficeidProjectid(userid, officeid, projectid);
                        for (int i = 0; i < surveyoffline.size(); i++) {
                            Survey survey = surveyoffline.get(i);
                            //Toast.makeText(getActivity(),  survey.getOffice_id() + " - " + survey.getOffice_name()   , Toast.LENGTH_SHORT).show();
                            surveyPresenter.deleteSurvy(survey);
                        }


                        saveData();
                        startActivity(new Intent(getActivity(), JobsActivity.class));
                    }
                }

            }

        }
    }

    public void saveData() {
        String userid = Constants.getuserId(getActivity());
        String projectid = Constants.getProjectId(getActivity());
        String address = edt_address.getText().toString();
        String phone = edt_phone.getText().toString();
        String intspeed = editInternetSpeed.getText().toString();
        String mfrom = editFrom.getText().toString();
        String mto = editTo.getText().toString();
        String efrom = editFromN.getText().toString();
        String eto = editToN.getText().toString();
        String computercount = edt_computer_count.getText().toString();
        String computernotes = edt_computer_notes.getText().toString();
        String printercount = edt_printers_count.getText().toString();
        String printernotes = edt_printers_notes.getText().toString();
        String scanercount = edt_scanners_count.getText().toString();
        String scanernotes = edt_scanners_notes.getText().toString();
        officeVisit = "1";

        String net_inside;
        if (chk_inside.isChecked()) {
            net_inside = "1";
        } else {
            net_inside = "0";
        }

        String net_outside;
        if (chk_outside.isChecked()) {
            net_outside = "1";
        } else {
            net_outside = "0";
        }

        if (chk_two.isChecked()) {
            two = "1";
        } else {
            two = "0";
        }

        String arabic;
        if (chk_arabic.isChecked()) {
            arabic = "1";
        } else {
            arabic = "0";
        }

        String kurdish;
        if (chk_kurdish.isChecked()) {
            kurdish = "1";
        } else {
            kurdish = "0";
        }

        String both;
        if (chk_both.isChecked()) {
            both = "1";
        } else {
            both = "0";
        }

        String ins_out_notes = edit_ins_out_notes.getText().toString();

        String others_num = edit_others_num.getText().toString();

        String others_notes = edit_others_notes.getText().toString();


        if (cityName.equals("اخرى")) {
            cityName = edtOtherCities.getText().toString();
        }

        if (disName.equals("اخرى")) {
            disName = edtOtherDistrict.getText().toString();
        }


        //Toast.makeText(getActivity(),otherCity + " - " + otherDistrict , Toast.LENGTH_LONG).show();


        if (strShiftType.equals("1") || strShiftType.equals("3")) {

            surveyPresenter.saveSurveyoffline2(strGovId, govName, strCityId, cityName, strDisrtric, disName, address, phone, hasInternet, isNetwork, intspeed
                    , strofficeid, officeName, officeVisit, strShiftType, strOwnerShipType, mfrom, mto, efrom, eto
                    , computercount, computernotes, printercount, printernotes, scanercount
                    , scanernotes, otherCity, otherDistrict, userid, projectid, net_inside, net_outside, ins_out_notes, others_num, others_notes, two, strotherofficeid, strotherofficename
                    , arabic, kurdish, both
            );

        } else if (strShiftType.equals("2")) {
            surveyPresenter.saveSurveyoffline2(strGovId, govName, strCityId, cityName, strDisrtric, disName, address, phone, hasInternet, isNetwork, intspeed
                    , strofficeid, officeName, officeVisit, strShiftType, strOwnerShipType, efrom, eto, mfrom, mto
                    , computercount, computernotes, printercount, printernotes, scanercount
                    , scanernotes, otherCity, otherDistrict, userid, projectid, net_inside, net_outside, ins_out_notes, others_num, others_notes, two, strotherofficeid, strotherofficename
                    , arabic, kurdish, both
            );
        }


        // //Constants.saveVisitOfficeId(strofficeid,getActivity());

    }

    boolean isTimeAfter(Date startTime, Date endTime) {

        if (startTime != null && endTime != null) {
            if (endTime.before(startTime)) {
                return false;
            } else if (endTime.equals(startTime)) {
                return false;
            } else {
                return true;
            }
        }

        return true;
    }





    public boolean validateEditText(int[] ids) {
        boolean isEmpty = false;

        for (int id : ids) {
            EditText et = (EditText) mView.findViewById(id);

            if (TextUtils.isEmpty(et.getText().toString())) {
                et.setError("برجاء ادخال الحقول المطلوبة");
                isEmpty = true;
            }
        }

        return isEmpty;
    }

    public void iniRadio() {


        radioHasInternetYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasInternet = "1";

                radioHasInternetYes.setBackgroundResource(R.drawable.blue_toggle);
                radioHasInternetYes.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));

                radioHasInternetNo.setBackgroundResource(R.drawable.white_toggle_left);
                radioHasInternetNo.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryText));
                editInternetSpeed.setVisibility(View.VISIBLE);


            }
        });
        radioHasInternetNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasInternet = "0";
                editInternetSpeed.setVisibility(View.GONE);
                editInternetSpeed.setText(null);

                radioHasInternetYes.setBackgroundResource(R.drawable.white_toggle);
                radioHasInternetYes.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryText));

                radioHasInternetNo.setBackgroundResource(R.drawable.blue_toggle_left);
                radioHasInternetNo.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));


            }
        });


        radioIsNetWorkYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNetwork = "1";

                radioIsNetWorkYes.setBackgroundResource(R.drawable.blue_toggle);
                radioIsNetWorkYes.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));

                radioIsNetWorkNo.setBackgroundResource(R.drawable.white_toggle_left);
                radioIsNetWorkNo.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryText));
                ll.setVisibility(View.VISIBLE);
            }
        });

        radioIsNetWorkNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNetwork = "0";

                radioIsNetWorkYes.setBackgroundResource(R.drawable.white_toggle);
                radioIsNetWorkYes.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryText));

                radioIsNetWorkNo.setBackgroundResource(R.drawable.blue_toggle_left);
                radioIsNetWorkNo.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
                ll.setVisibility(View.GONE);
                chk_inside.setChecked(false);
                chk_outside.setChecked(false);
                edit_ins_out_notes.setText("");
            }
        });

        radioOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strOwnerShipType = "1";
                radioOwner.setBackgroundResource(R.drawable.blue_toggle);
                radioOwner.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
                radioRent.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryText));
                radioRent.setBackgroundResource(R.drawable.white_toggle_left);
            }
        });

        radioRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strOwnerShipType = "2";
                radioRent.setBackgroundResource(R.drawable.blue_toggle_left);
                radioOwner.setBackgroundResource(R.drawable.white_toggle);
                radioOwner.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryText));
                radioRent.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));

            }
        });


        chk_two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    spinnerOffices2.setVisibility(View.VISIBLE);
                } else {
                    spinnerOffices2.setVisibility(View.GONE);
                }
            }
        });


    }

    public void iniGovSpinner() {

        // Spinner click listener

        //spinnerGov.setPrompt("أختار المحافظة");


        govsoffline = surveyPresenter.getGovsoffline();

        GovSpinnerAdapter govSpinnerAdapter = new GovSpinnerAdapter(getActivity(), R.layout.spinneritem, govsoffline);
        spinnerGov.setAdapter(govSpinnerAdapter);


        spinnerGov.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                strGovId = govsoffline.get(position).getGov_id();
                govName = govsoffline.get(position).getGov_name();
                Log.e("KEY-->", strGovId);
                //citiesList.clear();


                iniCitiesSpinner();
                //iniCitiesSpinner2();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Please Choose Your Gov", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void iniCitiesSpinner() {

        // Spinner click listener
        //spinnerCities.setPrompt("أختار المدينة");
        spinnerCities.setOnItemSelectedListener(this);


        citiesoffline = surveyPresenter.getCitiesBygovid(strGovId);
        citiesoffline.add(0, new City("-1", "0", "أختر المدينة"));
        //citiesoffline.add(new City("0","0","اخرى"));
        //citiesoffline = surveyPresenter.getCities();

        CitiesSpinnerAdapter citiesSpinnerAdapter = new CitiesSpinnerAdapter(getActivity(), R.layout.spinneritem, citiesoffline);
        spinnerCities.setAdapter(citiesSpinnerAdapter);

        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (citiesoffline.size() != 0) {
                    cityName = citiesoffline.get(position).getCity_name();

                    //String cityIdOther = citiesoffline.get(position).getCity_id();

                    strCityId = citiesoffline.get(position).getCity_id();
                    //districsList.clear();


                    //strCityId="0";

                    if (cityName.contains("اخرى")) {
                        edtOtherCities.setVisibility(View.VISIBLE);
                        //cityIdOther=edtOtherCities.getText().toString();
                        //cityName=edtOtherCities.getText().toString();

                    } else {
                        edtOtherCities.setVisibility(View.GONE);
                        cityName = citiesoffline.get(position).getCity_name();
                        //cityIdOther="1";
                    }


                    //districsList.clear();
                    iniDistrictsSpinner();
                    //iniDistrictsSpinner2();


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Please Choose Your city", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void iniCitiesSpinner2() {

        // Spinner click listener
        //spinnerCities.setPrompt("أختار المدينة");
        spinnerCities.setOnItemSelectedListener(this);


        citiesoffline = surveyPresenter.getCitiesBygovid(strGovId);
        //citiesoffline = surveyPresenter.getCities();

        CitiesSpinnerAdapter citiesSpinnerAdapter = new CitiesSpinnerAdapter(getActivity(), R.layout.spinneritem, citiesoffline);
        spinnerCities.setAdapter(citiesSpinnerAdapter);

        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (citiesoffline.size() != 0) {
                    cityName = citiesoffline.get(position).getCity_name();

                    String cityIdOther = citiesoffline.get(position).getCity_id();

                    strCityId = citiesoffline.get(position).getCity_id();
                    //districsList.clear();


                    //strCityId="0";

                    if (cityName.contains("اخرى")) {
                        edtOtherCities.setVisibility(View.VISIBLE);
                        strCityId = "0";
                        cityIdOther = edtOtherCities.getText().toString();
                        ;
                    } else {
                        edtOtherCities.setVisibility(View.GONE);
                        cityIdOther = "";
                    }


                    //districsList.clear();
                    iniDistrictsSpinner();
                    //iniDistrictsSpinner2();


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Please Choose Your city", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void iniDistrictsSpinner() {


        // Spinner Drop down elements
        //spinnerDistrict.setPrompt("أختار الحي");


        disoffline = surveyPresenter.getDistrictsBycityid(strCityId);
        disoffline.add(0, new District("0", "0", "أختر الحي"));
        //disoffline.add(new District("0","0","اخرى"));

        //disoffline = surveyPresenter.getDistricts();


        DistricSpinnerAdapter disSpinnerAdapter = new DistricSpinnerAdapter(getActivity(), R.layout.spinneritem, disoffline);
        spinnerDistrict.setAdapter(disSpinnerAdapter);


        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (disoffline.size() != 0) {

                    disName = disoffline.get(position).getDistrict_name();

                    edtOtherDistrict.setVisibility(View.GONE);

                    String dist = disoffline.get(position).getDistrict_id();
                    strDisrtric = disoffline.get(position).getDistrict_id();
                    //iniOfficesSpinner();


                    //String distid=disoffline.get(position).getDistrict_id();

                    if (disName.contains("اخرى")) {
                        edtOtherDistrict.setVisibility(View.VISIBLE);
                        //otherDistrict=edtOtherDistrict.getText().toString();
                        //disName=edtOtherDistrict.getText().toString();
                        //iniOfficesSpinner2();
                        strDisrtric = disoffline.get(position).getDistrict_id();

                    } else {
                        edtOtherDistrict.setVisibility(View.GONE);
                        disName = disoffline.get(position).getDistrict_name();
                        //otherDistrict="1";
                        //iniOfficesSpinner();
                        strDisrtric = disoffline.get(position).getDistrict_id();
                    }

                    //iniOfficesSpinner2();

                    distid = disoffline.get(position).getDistrict_id();
                    iniOfficesSpinner();


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Please Choose Your District", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void iniOfficesSpinner2() {
        // Spinner Drop down elements
        //spinnerOfficeName.setPrompt("أختار المكتب");


        String projid = Constants.getProjectId(getActivity());

        //offiesoffline.clear();

        //offiesoffline = surveyPresenter.getOfficessBydistidprojid(strDisrtric,projid);
        //offiesoffline = surveyPresenter.getOfficess();
        offiesoffline = surveyPresenter.getOfficessByprojid(projid, strGovId);
        offiesoffline.add(0, new OfficeResponseDetails("0", "0", "اختر المكتب", "0", "0", "0"));
        //List<OfficeResponseDetails> offiesoffline1 = removeDuplicates(offiesoffline);

        /*List<OfficeResponseDetails> list2 = new ArrayList<OfficeResponseDetails>();
        HashSet<OfficeResponseDetails> lookup = new HashSet<OfficeResponseDetails>();
        for (OfficeResponseDetails item : offiesoffline) {
            if (lookup.add(item)) {

                list2.add(item);
            }
        }
        offiesoffline = list2;*/

        /*for(int i=0;i < offiesoffline.size();i++){
            OfficeResponseDetails officeResponseDetails = offiesoffline.get(i);
            Toast.makeText(getActivity(),officeResponseDetails.getOffice_id() + officeResponseDetails.getOffice_name(),Toast.LENGTH_SHORT).show();
        }*/


        OfficeAdapter officesSpinnerAdapter = new OfficeAdapter(getActivity(), R.layout.spinneritem, offiesoffline);
        spinnerOfficeName.setAdapter(officesSpinnerAdapter);


        spinnerOfficeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (offiesoffline.size() != 0) {
                    strofficeid = offiesoffline.get(position).getOffice_id();
                    officeName = offiesoffline.get(position).getOffice_name();

                    Constants.saveOFFiceid(strofficeid, getActivity());

                    //officeVisit=offiesoffline.get(position).getUser_id();

                    Log.e("OFFICE ID -->", strofficeid);
                    //Log.e("OFFICE Vis -->", officeVisit);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void iniDistrictsSpinner2() {


        // Spinner Drop down elements
        //spinnerDistrict.setPrompt("أختار الحي");


        //disoffline = surveyPresenter.getDistrictsBycityid(strCityId);


        disoffline = surveyPresenter.getDistricts();


        DistricSpinnerAdapter disSpinnerAdapter = new DistricSpinnerAdapter(getActivity(), R.layout.spinneritem, disoffline);
        spinnerDistrict.setAdapter(disSpinnerAdapter);

        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (disoffline.size() != 0) {

                    disName = disoffline.get(position).getDistrict_name();

                    edtOtherDistrict.setVisibility(View.GONE);

                    String dist = disoffline.get(position).getDistrict_id();
                    strDisrtric = disoffline.get(position).getDistrict_id();
                    //iniOfficesSpinner();


                    String distid = disoffline.get(position).getDistrict_id();

                    //iniOfficesSpinner();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Please Choose Your District", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void iniOfficesSpinner() {


        // Spinner Drop down elements
        //spinnerOfficeName.setPrompt("أختار المكتب");


        String projid = Constants.getProjectId(getActivity());

        offiesoffline = surveyPresenter.getOfficessBydistidprojid(distid, projid);
        offiesoffline.add(0, new OfficeResponseDetails("0", "0", "اختر المكتب", "0", "0", "0"));


        OfficeAdapter officesSpinnerAdapter = new OfficeAdapter(getActivity(), R.layout.spinneritem, offiesoffline);
        spinnerOfficeName.setAdapter(officesSpinnerAdapter);

        spinnerOffices2.setAdapter(officesSpinnerAdapter);


        spinnerOfficeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (offiesoffline.size() != 0) {
                    strofficeid = offiesoffline.get(position).getOffice_id();
                    officeName = offiesoffline.get(position).getOffice_name();

                    Constants.saveOFFiceid(strofficeid, getActivity());

                    //officeVisit=offiesoffline.get(position).getUser_id();

                    Log.e("OFFICE ID -->", strofficeid);
                    //Log.e("OFFICE Vis -->", officeVisit);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerOffices2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (offiesoffline.size() != 0) {
                    strotherofficeid = offiesoffline.get(position).getOffice_id();
                    strotherofficename = offiesoffline.get(position).getOffice_name();

                    //Constants.saveOFFiceid(strofficeid,getActivity());

                    //officeVisit=offiesoffline.get(position).getUser_id();

                    //Log.e("OFFICE ID -->", strofficeid);
                    //Log.e("OFFICE Vis -->", officeVisit);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public static <T> ArrayList<T> removeDuplicates(List<OfficeResponseDetails> list) {

        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();

        // Traverse through the first list
        for (OfficeResponseDetails element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add((T) element);
            }
        }

        // return the new list
        return newList;
    }


    public void closeScreen(View view) {
        new AlertDialog.Builder(getActivity())
                .setMessage("سوف يتم فقد بيانات مسجلة بهذه الصفحة هل أنت متاكد من الخروج من الصفحة ؟ ")
                .setCancelable(false)
                .setPositiveButton("متابعة", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().finish();
                    }
                })
                .setNegativeButton("الغاء", null)
                .show();
    }

    public void backback(View view) {
    }


}
