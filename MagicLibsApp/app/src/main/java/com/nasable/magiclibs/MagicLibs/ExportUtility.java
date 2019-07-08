package com.nasable.magiclibs.MagicLibs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExportUtility {

    String exportData = "";
    Activity activity;

    public ExportUtility(Activity activity) {
        this.activity = activity;
    }

    public void setExportData(String exportData) {
        this.exportData = exportData;
    }

    public void save() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "DATA_" + timeStamp + ".csv";

        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT)
                .addCategory(Intent.CATEGORY_OPENABLE)
                .setType("text/csv")
                .putExtra(Intent.EXTRA_TITLE, fileName);

        activity.startActivityForResult(intent, 0x01);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0x01 && resultCode == Activity.RESULT_OK && data.getData() != null) {
            Uri uri = data.getData();
            write(uri, exportData);
        }
    }

    public void write(Uri uri, String data) {
        try {
            ParcelFileDescriptor pfd = activity.getContentResolver().openFileDescriptor(uri, "w");
            FileOutputStream fos = new FileOutputStream(pfd.getFileDescriptor());
            fos.write(data.getBytes());
            fos.close();
            pfd.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
