package com.example.n2_thyagodesouzapereira_ads61;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIConnection {

    public static String GetJSONFromAPI (String Link) {
        String Return = "";

        try {
            URL APIEnd = new URL(Link);
            HttpURLConnection Connection;

            Connection = (HttpURLConnection) APIEnd.openConnection();
            Connection.setRequestMethod("GET");
            Connection.setReadTimeout(150000);
            Connection.setConnectTimeout(150000);
            Connection.connect();

            int ResponseCode = Connection.getResponseCode();
            InputStream Input;

            if (ResponseCode == HttpURLConnection.HTTP_OK)
                Input = Connection.getInputStream();
            else
                Input = Connection.getErrorStream();

            Return = ConvertISToString(Input);
            Input.close();
            Connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Return;

    }

    private static String ConvertISToString (InputStream Input) {

        StringBuffer Buffer = new StringBuffer();

        try {
            BufferedReader Reader;
            String Line;

            Reader = new BufferedReader(new InputStreamReader(Input));
            while ((Line = Reader.readLine()) != null) {
                Buffer.append(Line);
            }
            Reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Buffer.toString();
    }
}
