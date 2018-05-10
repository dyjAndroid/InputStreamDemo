package dyj.com.inputstreamdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.file_output_stream:
                FileOutputStream fileOutputStream = null;
                try {
                    File parentFile = getExternalFilesDir(null);
                    if (parentFile != null) {
                        fileOutputStream = new FileOutputStream(new File(parentFile.getAbsolutePath() + File.separator + "a.txt"));
                        String test = "I am a coder";
                        fileOutputStream.write(test.getBytes());
                        fileOutputStream.flush();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case R.id.file_input_stream:
                File parentFile = getExternalFilesDir(null);
                FileInputStream fis = null;
                if (parentFile != null) {
                    try {
                        fis = new FileInputStream(new File(parentFile.getAbsolutePath() + File.separator + "a.txt"));
                        int a = -1;
                        StringBuilder sb = new StringBuilder();
                        //缓冲数组
                        byte[] bytes = new byte[4096];
                        //a:读取到的数据长度
                        while ((a = fis.read(bytes)) != -1) {
                            sb.append(new String(bytes,0,a));
                        }
                        Log.d("dyj", sb.toString());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (fis != null) {
                            try {
                                fis.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                break;
        }
    }
}
