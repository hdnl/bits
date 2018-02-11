package com.bits;

/**
 * Created by ttle on 2/10/18.
 */

public class UserHandler {
    static String userId;
    static ProfileDO profile;

    public UserHandler(String userId){
        this.userId = userId;
        this.profile = new ProfileDO();
        profile.setUserId(userId);
    }

    public static void createAccount(final String name, final double age, final String bio, final String location){
        profile.setName(name);
        profile.setAge(age);
        profile.setBio(bio);
        profile.setLocation(location);

        new Thread(new Runnable() {
            @Override
            public void run() {
               // dynamoDBMapper.save(newsItem);
            }
        }).start();
    }

    public static void getUserData(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                //ProfileDO profile = new ProfileDO();
                //profile.setUserId(userId);
                //profile.setName(name);
                //profile.setAge(age);
                //profile.setBio(bio);
                //profile.setLocation(location);
            }
        }).start();

    }
}
