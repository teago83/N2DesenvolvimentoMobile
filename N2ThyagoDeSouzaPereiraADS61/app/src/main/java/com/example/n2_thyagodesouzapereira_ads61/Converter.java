package com.example.n2_thyagodesouzapereira_ads61;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Converter {

    public UserGit GetInfo (String End) {
        String JSON = APIConnection.GetJSONFromAPI(End);
        UserGit Return = ParseJSON(JSON);
        return Return;
    }

    public FollowerGit GetSomeInfo (String End) {
        String JSON = APIConnection.GetJSONFromAPI(End);
        FollowerGit Return = ParseSomeJSON(JSON);
        return Return;
    }

    private UserGit ParseJSON(String JSON) {

        try {
            UserGit UserGit = new UserGit();

            JSONObject JSONObject = new JSONObject(JSON);
            UserGit.SetUsername(JSONObject.getString("login"));
            UserGit.SetID(JSONObject.getString("id"));

            return UserGit;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private FollowerGit ParseSomeJSON(String JSON) {

        try {
            FollowerGit FollowerGit = new FollowerGit();

            JSONArray Array = new JSONArray(JSON);
            JSONObject Object = Array.getJSONObject(0);

            FollowerGit.SetUsername(Object.getString("login"));
            FollowerGit.SetID(Object.getString("id"));

            return FollowerGit;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
