package com.example.ticket;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ticket.ui.dataService.DataService;
import com.example.ticket.ui.entity.TicketDto;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class TicketUpload extends AppCompatActivity {

    DataService dataService = new DataService();

    private TextView upload_finish;
    private EditText et;

    private static final String TAG = "Upload Image";

    private Boolean isPermission = true;

    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 2;

    private File tempFile;
    private File saved_file;

    TextView ticket_name;
    TextView ticket_place;
    TextView ticket_price;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing Member
    public static final String USER_ID_KEY = "user_id_key";

    // variable for shared preferences.
    SharedPreferences sharedPreferences;
    Long user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ticket_upload);

        ticket_name = findViewById(R.id.upload_name);
        ticket_place = findViewById(R.id.upload_place);
        ticket_price = findViewById(R.id.upload_price);

        Intent intent = getIntent();


        // initializing our shared preferences.
        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        user_id = sharedPreferences.getLong(USER_ID_KEY, 3);

        tedPermission();

        et = (EditText) findViewById(R.id.upload_price);
        upload_finish = (TextView) findViewById(R.id.upload_finish);

        et.addTextChangedListener(new NumberTextWatcher(et));


        findViewById(R.id.openGallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ?????? ????????? ???????????? ????????? ?????? ???????????? ????????????.
                if(isPermission) goToAlbum();
                else Toast.makeText(view.getContext(), getResources().getString(R.string.permission_2), Toast.LENGTH_LONG).show();
            }
        });


        //?????? ?????? ?????????
        upload_finish.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"StaticFieldLeak", "NewApi"})
            @Override
            public void onClick(View v) {
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), saved_file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("multipartFile", saved_file.getName(), requestFile);
                Map<String, RequestBody> map = new HashMap<>();
                map.put("ticket.ticket_name", RequestBody.create(MultipartBody.FORM, ticket_name.getText().toString()));
                map.put("ticket.ticket_place", RequestBody.create(MultipartBody.FORM, ticket_place.getText().toString()));
                map.put("ticket.ticket_price", RequestBody.create(MultipartBody.FORM, ticket_price.getText().toString().replaceAll("\\,", "")));
                map.put("ticket.ticket_chatNum", RequestBody.create(MultipartBody.FORM, String.valueOf(0)));
                map.put("ticket.member.id", RequestBody.create(MultipartBody.FORM, String.valueOf(user_id)));
                AsyncTask<Void, Void, TicketDto> listAPI = new AsyncTask<Void, Void, TicketDto>() {
                    @Override
                    protected TicketDto doInBackground(Void... params) {
                        Call<TicketDto> call = dataService.tickets.addTicket(body, map);
                        try {
                            return call.execute().body();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(TicketDto s) {
                        super.onPostExecute(s);
                    }
                }.execute();

                if(body != null && map !=null){
                    Toast.makeText(getApplicationContext(), "???????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
                    ticket_name.setText("");
                    ticket_price.setText("");
                    ticket_place.setText("");
                    Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent2);

                }else{
                    Toast.makeText(getApplicationContext(), "???????????? ?????????????????????. ", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this, "?????? ???????????????.", Toast.LENGTH_SHORT).show();

            if (tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        Log.e(TAG, tempFile.getAbsolutePath() + " ?????? ??????");
                        tempFile = null;
                    }
                }
            }

            return;
        }

        if (requestCode == PICK_FROM_ALBUM) {

            Uri photoUri = data.getData();
            Log.d(TAG, "PICK_FROM_ALBUM photoUri : " + photoUri);

            Cursor cursor = null;

            try {

                /*
                 *  Uri ????????????
                 *  content:/// ?????? file:/// ???  ????????????.
                 */
                String[] proj = {MediaStore.Images.Media.DATA};

                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));

                Log.d(TAG, "tempFile Uri : " + Uri.fromFile(tempFile));

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

            setImage();

        } else if (requestCode == PICK_FROM_CAMERA) {

            setImage();

        }
    }

    /**
     *  ???????????? ????????? ????????????
     */
    private void goToAlbum() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }


    /**
     *  ??????????????? ????????? ????????????
     */
    private void takePhoto() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            tempFile = createImageFile();
        } catch (IOException e) {
            Toast.makeText(this, "????????? ?????? ??????! ?????? ??????????????????.", Toast.LENGTH_SHORT).show();
            finish();
            e.printStackTrace();
        }
        if (tempFile != null) {

            Uri photoUri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, PICK_FROM_CAMERA);
        }
    }

    /**
     *  ?????? ??? ?????? ?????????
     */
    private File createImageFile() throws IOException {

        // ????????? ?????? ?????? ( blackJin_{??????}_ )
        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String imageFileName = "Upload_" + timeStamp + "_";

        // ???????????? ????????? ?????? ?????? ( blackJin )
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/UploadImg/");
        if (!storageDir.exists()) storageDir.mkdirs();

        // ?????? ??????
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        Log.d(TAG, "createImageFile : " + image.getAbsolutePath());

        return image;
    }

    /**
     *  tempFile ??? bitmap ?????? ?????? ??? ImageView ??? ????????????.
     */
    private void setImage() {

        ImageView imageView = findViewById(R.id.upload_img);

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
        Log.d(TAG, "setImage : " + tempFile.getAbsolutePath());
        saved_file = tempFile;
        imageView.setImageBitmap(originalBm);

        /**
         *  tempFile ?????? ??? null ????????? ????????? ?????????.
         *  (resultCode != RESULT_OK) ??? ??? tempFile ??? ???????????? ?????????
         *  ????????? ???????????? ?????? ?????? ?????? ?????? ?????? ????????? ???????????????.
         */
        tempFile = null;

    }

    /**
     *  ?????? ??????
     */
    private void tedPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // ?????? ?????? ??????
                isPermission = true;

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // ?????? ?????? ??????
                isPermission = false;

            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedMessage(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();

    }

}