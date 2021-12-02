package com.example.spor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyHelper {
    public static ArrayList <String> InTrend;
    public static ArrayList <String>  ForYou;
    public static ArrayList <String> New;
    public static Response response;
    public static String baseURL = "http://cinema.areas.su";
    public static String imageURL = "http://cinema.areas.su/up/images/";

    public static void fillTrend () {
        InTrend = new ArrayList <> ( );
        InTrend.add (imageURL + "umbrella.jpeg");
        InTrend.add (imageURL + "magicians.png");
        InTrend.add (imageURL + "567640.jpg");
        InTrend.add (imageURL + "The_Call_of_the_Wild_(poster).jpg");
        InTrend.add (imageURL + "Sputnik.jpg");
        InTrend.add (imageURL + "74452446f50a11777432dc992576cac1.jpg");
        InTrend.add (imageURL + "567640.jpg");
        InTrend.add (imageURL + "74452446f50a11777432dc992576cac1.jpg");
        InTrend.add (imageURL + "74452446f50a11777432dc992576cac1.jpg");
    }

    public static void fillNew () {
        New = new ArrayList <> ( );
        New.add (imageURL + "umbrella.jpeg");
        New.add (imageURL + "e05c2d133f245ee347e7f96edfd9a0.jpg");
        New.add (imageURL + "138193.jpg");
        New.add (imageURL + "kinopoisk.ru-Memoriseuteu-3483691.jpg");
        New.add (imageURL + "umbrella.jpeg");
        New.add (imageURL + "30891708-1170704.jpg");
        New.add (imageURL + "umbrella.jpeg");
        New.add (imageURL + "30891708-1170704.jpg");
        New.add (imageURL + "30891708-1170704.jpg");
        New.add (imageURL + "30891708-1170704.jpg");
        New.add (imageURL + "umbrella.jpeg");
        New.add (imageURL + "30891708-1170704.jpg");
    }

    public static void fillForyou () {
        ForYou = new ArrayList <> ( );
        ForYou.add (imageURL + "Harry_Potter.jpg");
        ForYou.add (imageURL + "kinopoisk.ru-The-Lion-King-3351468.jpg");
        ForYou.add (imageURL + "200-1000x830.jpg");
        ForYou.add (imageURL + "prostokvashino.jpeg");
        ForYou.add (imageURL + "download.jpeg");
        ForYou.add (imageURL + "kinopoisk.ru-The-Lion-King-3351468.jpg");
        ForYou.add (imageURL + "200-1000x830.jpg");
        ForYou.add (imageURL + "kinopoisk.ru-The-Lion-King-3351468.jpg");
        ForYou.add (imageURL + "200-1000x830.jpg");
        ForYou.add (imageURL + "prostokvashino.jpeg");
        ForYou.add (imageURL + "74452446f50a11777432dc992576cac1.jpg");

    }


    public static boolean checkEmail (String email) {
        Pattern pattern = Pattern.compile ("^[\\w\\.]+@\\w+\\.\\w+$");
        if (! pattern.matcher (email).find ( )) return false;
        else return true;
    }

    public static boolean checkPassword (String password) {
        Pattern HiLet = Pattern.compile ("[A-Z[А-Я]]+");
        Pattern Simb = Pattern.compile ("\\W+");
        Pattern Digit = Pattern.compile ("\\d+");
        if (! HiLet.matcher (password).find ( ) || ! Simb.matcher (password).find ( ) || ! Digit.matcher (password).find ( ) || password.length ( ) < 8) {
            return false;
        } else return true;
    }

    //POST-запрос на вход в аккаунт
    public static Response signInPOST (String email , String password) throws IOException {
        OkHttpClient client = new OkHttpClient ( );

        String jsonData = "{" +
                "\"email:\" \"" + email + "\"," +
                "\"password:\" \"" + password + "\"," +
                "}";
        RequestBody requestBody = RequestBody.create(MediaType.parse ("application/json") , jsonData);


        Request request = new Request.Builder ( )
                .url (baseURL + "/auth/login").addHeader ("Authorization" , "Bearer kalmyck")
                .post (requestBody)
                .build ( );

        Call call = client.newCall (request);
        Response response = call.execute ( );
        MyHelper.response = response;
        return response;
    }

    //POST-запрос на регистрацию аккаунта
    public static Response signUpPOST (String email , String password , String name , String surname) throws IOException {

        String jsonData = "{" +
                "\"email:\" \"" + email + "\"," +
                "\"password:\" \"" + password + "\"," +
                "\"firstName:\" \"" + name + "\"," +
                "\"lastName:\" \"" + surname + "\"" +
                "}";


        OkHttpClient client = new OkHttpClient ( );

        RequestBody requestBody = RequestBody.create(MediaType.parse ("application/json") , jsonData);


        Request request = new Request.Builder ( )
                .url (baseURL + "/auth/register").addHeader ("Authorization" , "Bearer kalmyck")
                .post (requestBody)
                .build ( );

        Call call = client.newCall (request);
        Response response = call.execute ( );
        MyHelper.response = response;
        return response;
    }


    //TODO Обработчик ответов API
}
