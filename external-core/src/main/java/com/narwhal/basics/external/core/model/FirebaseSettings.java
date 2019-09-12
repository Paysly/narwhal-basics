package com.narwhal.basics.external.core.model;

public interface FirebaseSettings {

    String getFirebaseServerKey();

    String getFirebaseMessagingUrl();

    String getFirebaseAppUrl();

    String getFirebaseIconUrl();

    String getFirebaseIosKey();

    String getFirebaseAndroidKey();

    void checkFirebaseData();
}
