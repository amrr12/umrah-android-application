package com.example.amrproject.fireStoreRepository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.amrproject.models.MootamarAR;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FirebaseQueries {
    FirebaseFirestore db;

    public FirebaseQueries() {
        db = FirebaseFirestore.getInstance();
    }


    public int add_to_ar(MootamarAR mootamarAR) {
        int[] isCreated = {-1};
        Map<String, Object> map = new HashMap<>();
        map.put("fullname",mootamarAR.getFullname());
        map.put("phoneNumber",mootamarAR.getPhoneNumber());
        map.put("gender",mootamarAR.getGender());

        db.collection("Mootamar").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                isCreated[0] = 1;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                isCreated[0] = -1;
            }
        });

        return isCreated[0];

    }


    public List<MootamarAR> get_MootamarAR(OnDataFetchedListener listener){
        List<MootamarAR> mootamarARList = new ArrayList<>();

        db.collection("Mootamar").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                       List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot doc : documents) {
                            mootamarARList.add(doc.toObject(MootamarAR.class));
                        }

                        // Notify the listener that data has been fetched
                        listener.onDataFetched(mootamarARList);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("MootamaList","empty");
                    }
                });
        return mootamarARList;
    }

    public interface OnDataFetchedListener {
        void onDataFetched(List<MootamarAR> mootamarARList);
    }
}

