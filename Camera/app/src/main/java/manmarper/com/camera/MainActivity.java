package manmarper.com.camera;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> images_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddPhoto = (Button) findViewById(R.id.btn_add_photo);
        btnAddPhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }


    private void selectImage() {
        //take photo with camera or select photo from gallery

        final CharSequence[] options = {"Take Photo", "Pick from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add new Image");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                switch (options[item].toString()) {
                    //verify selected option

                    case "Take Photo"://take photo with device camera

                        //retrieving information related to the application packages that are currently installed on the device
                        PackageManager packageManager1 = getApplicationContext().getPackageManager();

                        if (!packageManager1.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {

                            Toast.makeText(getApplicationContext(), "This device does not have a camera.", Toast.LENGTH_SHORT).show();

                        } else {
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(takePictureIntent, 1);
                            }
                        }

                        break;


                    case "Pick from Gallery"://pick  image from gallery

                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 2);

                        break;


                    case "Cancel": //cancel operation
                        dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 1) { // photo from camera

                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                File photoFile = null;
                try {
                    photoFile = createImageFile(imageBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //allow to show in gallery device
                galleryAddPic(photoFile);

                setImage(Bitmap.createScaledBitmap(imageBitmap, 180, 190, false));
                assert photoFile != null;// Method invocation 'photoFile.getAbsolutePath()'  may produce NullPointerException'
                images_list.add(images_list.size(), "file://" + photoFile.getAbsolutePath());

            } else if (requestCode == 2) { //image from gallery

                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                assert c != null;//Method invocation 'c.moveToFirst()' may produce 'java.lang.NullPointerException'
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                setImage(Bitmap.createScaledBitmap(BitmapFactory.decodeFile(picturePath), 180, 190, false));
                images_list.add(images_list.size(), "file://" + picturePath);

            }
        }
    }


    private void setImage(Bitmap bitmap) {

        LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setPadding(5, 5, 5, 5);
        imageView.setImageBitmap(bitmap);
        layout.addView(imageView);
    }


    private File createImageFile(Bitmap imageBitmap) throws IOException {

        //create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File sdcard0Dir = Environment.getExternalStorageDirectory();
        File storageDir = new File(sdcard0Dir, "MyAppImages");

        //create folder
        if (!storageDir.exists()) {
            storageDir.mkdir();
        }

        //create image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        File image = new File(storageDir + File.separator + timeStamp + ".jpg");
        image.createNewFile();

        //write the bytes in file
        FileOutputStream fo = new FileOutputStream(image);
        fo.write(bytes.toByteArray());

        //close de FileOutput
        fo.close();

        return image;
    }


    private void galleryAddPic(File currentPhotoPath) {
        //Add image to be allowed in device gallery

        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);// intent for present file on device gallery

        Uri contentUri = Uri.fromFile(currentPhotoPath);//path of image in device
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

}
