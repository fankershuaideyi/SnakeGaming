
/**
 * @author fanker Yifan Qiu 21012688
 */
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
//引用了部分源自CSDN代码（Some codes come from CSDN(A website)）

public class Music extends Thread {
    private final String fileName;
    public  int count;

    public Music(String wavFile,boolean isLoop) {
        this.fileName = wavFile;
        if(isLoop) {
            count = 99999;
        } else {
            count = 1;
        }
    }

    @Override
    @SuppressWarnings("unused")
    public void run() {
        File soundFile = new File(fileName);

        // 播放音乐的文件名

        if (!soundFile.exists()) {
            System.err.println("Wave file not found:" + fileName);
            return;
        }

        int i = 0;

        while (i < count) {
            // 设置循环播放
            AudioInputStream audioInputStream;
            // 创建音频输入流对象
            try {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                // 创建音频对象
            } catch (UnsupportedAudioFileException | IOException e1) {
                e1.printStackTrace();
                return;
            }
            AudioFormat format = audioInputStream.getFormat();
            // 音频格式
            SourceDataLine Pauline; // 源数据线
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            try {
                Pauline = (SourceDataLine) AudioSystem.getLine(info);
                Pauline.open(format);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            if (Pauline.isControlSupported(FloatControl.Type.PAN)) {
                FloatControl pan = (FloatControl) Pauline.getControl(FloatControl.Type.PAN);
            }
            Pauline.start();
            int nBytesRead = 0;
            int EXTERNAL_BUFFER_SIZE = 524288;
            byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
            try {
                while (nBytesRead != -1) {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0) {
                        Pauline.write(abData, 0, nBytesRead);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } finally {
                Pauline.drain();
            }
            i++;
        }
    }
}