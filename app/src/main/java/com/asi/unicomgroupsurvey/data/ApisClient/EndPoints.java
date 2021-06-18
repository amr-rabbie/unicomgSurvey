package com.asi.unicomgroupsurvey.data.ApisClient;


// this class define to hold all server constants and end points
public class EndPoints {
 // base url  "http://unicomtrade.com/survey/api/"
    // http://192.168.3.236:88/survey
   public static final String BASE_URL = "http://unicomtrade.com/survey/api/";
    public static final String GET_ALL_ITEMS = "1bnsyj";

    //public static final String BASE_URL = "http://192.168.3.70:8080/api/";
   // get user projects
    public static final String GET_USER_PROJECTS = "user_projects/{user_id}";

    // login api
    public static final String GET_LOGin = "login";

    // get all governates
    public static final String GET_GOVERNOR = "get_governorates";
    // get cities
    public static final String GET_CITIES = "get_cities";
    // ge
    public static final String GET_DISTRICTS = "get_districts";

    // by Belal
    public static final String GET_POSITIONS = "positions";
    public static final String GET_OFFICES = "offices";
    public static final String GET_COMMENTS = "has_feedback_offices/{user_id}";
    public static final String SYNC_SURVEYS = "add_office_data";

    // by elamary
    public static final String add_office_data_room = "add_office_data/room";
    public static final String add_office_data_sketch = "add_office_data/sketch";
    public static final String add_office_data_out_door_image = "add_office_data/out_door_image";
    public static final String add_office_data_in_door_image = "add_office_data/in_door_image";

    public static final String add_office_data_done = "add_office_data/done/{officeId}";

}
