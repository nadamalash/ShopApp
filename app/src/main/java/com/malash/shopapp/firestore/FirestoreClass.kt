package com.malash.shopapp.firestore

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.malash.shopapp.R
import com.malash.shopapp.models.User
import com.malash.shopapp.utils.backAfterTwoSec
import com.malash.shopapp.utils.showErrorSnackBar

class FirestoreClass {

    private val db = FirebaseFirestore.getInstance()

    fun inputUserData(user: User, activity: AppCompatActivity, view: View) {
        // "users" is the collection name. if the collection is already created before it will not create the same one again
        db.collection("users")
            // document is the Unique ID for each user object
            .document(user.id)
            // set used to input the user object itself,
            // SetOptions.merge() means pass merge the new data with any existing user have the same id instead of replacing it.
            .set(user, SetOptions.merge())
            .addOnSuccessListener {
                showErrorSnackBar(
                    activity.resources.getString(R.string.register_successful),
                    false,
                    view,
                    activity
                )
                backAfterTwoSec(activity)
            }
            .addOnFailureListener {
                showErrorSnackBar(
                    activity.resources.getString(R.string.register_failed),
                    true,
                    view,
                    activity
                )
            }
    }
}