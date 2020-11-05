package com.example.n2_thyagodesouzapereira_ads61;

import org.json.JSONException;
import org.json.JSONObject;

public class Converter {

    public User GetInfo (String End) {
        String JSON = APIConnection.GetJSONFromAPI(End);
        User Return = ParseJSON(JSON);
        return Return;
    }

    private User ParseJSON(String JSON) {

        try {
            User User = new User();

            JSONObject JSONObject = new JSONObject(JSON);
            User.SetUsername(JSONObject.getString("login"));
            User.SetID(JSONObject.getString("id"));

            return User;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
