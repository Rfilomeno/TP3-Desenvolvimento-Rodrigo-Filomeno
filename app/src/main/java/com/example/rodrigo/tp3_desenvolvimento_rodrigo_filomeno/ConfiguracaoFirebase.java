package com.example.rodrigo.tp3_desenvolvimento_rodrigo_filomeno;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by cfilo on 07/12/2017.
 */

public class ConfiguracaoFirebase {

    private static DatabaseReference referenciaFirebase;

    public static DatabaseReference getFirebase(){
        if(referenciaFirebase == null){
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFirebase;
    }

}
