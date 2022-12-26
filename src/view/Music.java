package view;


//import javax.sound.sampled.*;
//import java.io.File;
//import java.io.IOException;
//public class Music extends Thread {
//    private String fileName;
//    private final int EXTERNAL_BUFFER_SIZE = 524288;
//
//    public Music(String wavFile) {
//        this.fileName = wavFile;
//    }
//
//    public void run() {
//        File soundFile = new File(fileName); // 播放音乐的文件名
//        if (!soundFile.exists()) {
//            System.err.println("Wave file not found:" + fileName);
//        } else {
//            while (true) { // 设置循环播放
//                AudioInputStream audioInputStream = null; // 创建音频输入流对象
//                try {
//                    audioInputStream = AudioSystem.getAudioInputStream(soundFile); // 创建音频对象
//                } catch (UnsupportedAudioFileException e1) {
//                    e1.printStackTrace();
//                    break;
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                    break;
//                }
//                AudioFormat format = audioInputStream.getFormat(); // 音频格式
//                SourceDataLine auline = null; // 源数据线
//                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
//                try {
//                    auline = (SourceDataLine) AudioSystem.getLine(info);
//                    auline.open(format);
//                } catch (LineUnavailableException e) {
//                    e.printStackTrace();
//                    break;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    break;
//                }
//                if (auline.isControlSupported(FloatControl.Type.PAN)) {
//                    FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
//                }
//                auline.start();
//                int nBytesRead = 0;
//                byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
//                try {
//                    while (nBytesRead != -1) {
//                        nBytesRead = audioInputStream.read(abData, 0, abData.length);
//                        if (nBytesRead >= 0)
//                            auline.write(abData, 0, nBytesRead);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    break;
//                } finally {
//                    auline.drain();
//// auline.close();
//                }
//            }
//        }
//    }
//}

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Music{
        public static  Clip bg_clip;
        public static  Clip music1_clip;
        public static  Clip music2_clip;

        public static void playMusic1(File musicPath){
                try {
                //File musicPath=new File("");
                if(musicPath.exists()) {
                AudioInputStream audioInput= AudioSystem.getAudioInputStream(musicPath);
                         bg_clip = AudioSystem.getClip();
                bg_clip.open(audioInput);
                FloatControl gainControl=(FloatControl) bg_clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-20.0f);//设置音量，范围为 -60.0f 到 6.0f
                bg_clip.start();
                //clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                else {}
                }
                catch(Exception ex)
                {
                ex.printStackTrace();
                }}

        public static void playMusic2(File musicPath){
                try {
                        //File musicPath=new File("");
                        if(musicPath.exists()) {
                                AudioInputStream audioInput= AudioSystem.getAudioInputStream(musicPath);
                                music2_clip = AudioSystem.getClip();
                                music2_clip.open(audioInput);
                                FloatControl gainControl=(FloatControl) music2_clip.getControl(FloatControl.Type.MASTER_GAIN);
                                gainControl.setValue(-20.0f);//设置音量，范围为 -60.0f 到 6.0f
                                music2_clip.start();
                                //clip.loop(Clip.LOOP_CONTINUOUSLY);
                        }
                        else {}
                }
                catch(Exception ex)
                {
                        ex.printStackTrace();
                }}

}
