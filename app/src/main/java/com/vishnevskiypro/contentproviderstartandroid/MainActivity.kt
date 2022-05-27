package com.vishnevskiypro.contentproviderstartandroid

import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.vishnevskiypro.contentproviderstartandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btn.setOnClickListener{
            getPhoneContacts()
        }
    }


    fun getPhoneContacts(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS), 0)
        }

        val contentResolver: ContentResolver = contentResolver
        val uri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)

        Log.d("AAAAA", "Contacts ${cursor?.count}")

        if (cursor != null) {
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    val phoneIndex: Int = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                    val numberIndex: Int = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                    val contactName: String = cursor.getString(phoneIndex)
                    val contactNumber: String = cursor.getString(numberIndex)
                    Log.d("AAAAA", "Contact name $contactName contact number: $contactNumber")


                }
            }
        }

    }

}