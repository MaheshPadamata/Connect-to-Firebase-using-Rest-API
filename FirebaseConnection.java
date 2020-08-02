package com.service;


import java.io.FileInputStream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseConnection {

    @PostConstruct
    public void initialize() {
        try {
        FileInputStream serviceAccount =
                  new FileInputStream("./serviceAccountKey.json");

                FirebaseOptions options = new FirebaseOptions.Builder()
                  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                  .setDatabaseUrl("https://queuemanagement-7b1bc.firebaseio.com")
                  .build();
                if(FirebaseApp.getApps().isEmpty()) {
                	FirebaseApp.initializeApp(options);
                }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}