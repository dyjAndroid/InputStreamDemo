package dyj.com.inputstreamdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.file_output_stream:
                writeDataByFileOutputStream("\nI am a good coder", "a.txt");
                break;
            case R.id.file_input_stream:
                String str = readDataByFileInputStream(getExternalFilesDir(null));
                Log.d("dyj", str);
                break;
            case R.id.buffered_input_stream:
                String result = readDataByBufferedInputStream(getExternalFilesDir(null));
                Log.d("dyj",result);
            case R.id.buffered_output_stream:
                writeDataByBufferedOutputStream("I am a coder","a.txt");
            default:
                break;
        }
    }

    public void writeDataByBufferedOutputStream(String data, String fileName){
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bos = null;
        try {
            File parentFile = getExternalFilesDir(null);
            if (parentFile != null) {
                fileOutputStream = new FileOutputStream(new File(parentFile.getAbsolutePath() + File.separator + fileName));
                bos = new BufferedOutputStream(fileOutputStream);
                //转换编码格式
//              String result = new String(test.getBytes(),"UTF-8");
                bos.write(data.getBytes(),0,data.getBytes().length);
                bos.flush();
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
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public String readDataByBufferedInputStream(File fileDirectory) {
        StringBuilder result = new StringBuilder();
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        if (fileDirectory != null) {
            try {
                fis = new FileInputStream(new File(fileDirectory.getAbsolutePath() + File.separator + "a.txt"));
                bis = new BufferedInputStream(fis);
                byte[] buffer = new byte[4096];
                int len;
                while ((len = bis.read(buffer)) != -1) {
                    result.append(new String(buffer, 0, len));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result.toString();
    }

    public void writeDataByFileOutputStream(String data, String fileName) {
        FileOutputStream fileOutputStream = null;
        try {
            File parentFile = getExternalFilesDir(null);
            if (parentFile != null) {
                fileOutputStream = new FileOutputStream(new File(parentFile.getAbsolutePath() + File.separator + fileName), true);
                //转换编码格式
//              String result = new String(test.getBytes(),"UTF-8");
                fileOutputStream.write(data.getBytes());
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
    }

    public String readDataByFileInputStream(File fileDirectory) {
        FileInputStream fis = null;
        //1.使用StringBuilder临时存储读取的数据
//        StringBuilder sb = new StringBuilder();
        if (fileDirectory != null) {
            try {
                fis = new FileInputStream(new File(fileDirectory.getAbsolutePath() + File.separator + "a.txt"));
                //2.使用ByteArrayOutputStream临时存储
                ByteArrayOutputStream bas = new ByteArrayOutputStream();
                int a = -1;
                //缓冲数组
                byte[] bytes = new byte[4096];
                //a:读取到的数据长度
                while ((a = fis.read(bytes)) != -1) {
//                    sb.append(new String(bytes, 0, a));
                    bas.write(bytes,0,a);
                }
                bas.flush();
                return new String(bas.toByteArray());
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
//        return sb.toString();
        return "";
    }
}
