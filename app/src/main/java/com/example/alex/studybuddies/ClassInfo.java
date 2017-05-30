package com.example.alex.studybuddies;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

/**
 * This class will read and write data into the server
 *
 * Created by Alex on 5/19/2017.
 */

public class ClassInfo {

    static String CLASS_KEY = "class_name";
    static String LONGITUDE_KEY = "longitude";
    static String LATITUDE_KEY = "latitude";
    static String START_KEY= "start_time";
    static String END_KEY = "end_time";


    private StorageReference mStorageRef;
    private String TAG = "UPLOADING . . .";
    //Constructor retrieves data from server

    String temp = "";  //Attempt to return a simple string

    public ClassInfo() {

        mStorageRef = FirebaseStorage.getInstance().getReference();
        grabFile();
    }

    public void grabFile() {
        File localFile = null;
        StorageReference riversRef = mStorageRef.child("Upload.txt");
        try {
            localFile = File.createTempFile("Upload", "txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        riversRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                // ...
            }
        });

    }
    public void onUpload() {
        StorageReference riversRef = mStorageRef.child("Upload.txt");
        String text = "CS121";
        byte[] bits = text.getBytes();

        riversRef.putBytes(bits).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Get a URL to the uploaded content
                Log.d(TAG, "SUCK IT NEERDS SUCCESS ");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...

                        Log.d(TAG, "Ahh yess A challenge worth figuring out");
                    }
                });
    }


    public String getTemp() {
        return temp;
    }
}
