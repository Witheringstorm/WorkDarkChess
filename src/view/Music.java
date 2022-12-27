package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Music{
        public static  Clip bg_clip;
        public static  Clip music1_clip;
        public static  Clip fanqi_clip;
        public static  Clip chizi_clip;
        public static  Clip win_clip;



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
                                fanqi_clip = AudioSystem.getClip();
                                fanqi_clip.open(audioInput);
                                FloatControl gainControl=(FloatControl) fanqi_clip.getControl(FloatControl.Type.MASTER_GAIN);
                                gainControl.setValue(-20.0f);//设置音量，范围为 -60.0f 到 6.0f
                                fanqi_clip.start();
                                //clip.loop(Clip.LOOP_CONTINUOUSLY);
                        }
                        else {}
                }
                catch(Exception ex)
                {
                        ex.printStackTrace();
                }}


        public static void playMusic3(File musicPath){
                try {
                        //File musicPath=new File("");
                        if(musicPath.exists()) {
                                AudioInputStream audioInput= AudioSystem.getAudioInputStream(musicPath);
                                chizi_clip = AudioSystem.getClip();
                                chizi_clip.open(audioInput);
                                FloatControl gainControl=(FloatControl) chizi_clip.getControl(FloatControl.Type.MASTER_GAIN);
                                gainControl.setValue(-20.0f);//设置音量，范围为 -60.0f 到 6.0f
                                chizi_clip.start();
                                //clip.loop(Clip.LOOP_CONTINUOUSLY);
                        }
                        else {}
                }
                catch(Exception ex)
                {
                        ex.printStackTrace();
                }}

        public static void playMusic4(File musicPath){
                try {
                        //File musicPath=new File("");
                        if(musicPath.exists()) {
                                AudioInputStream audioInput= AudioSystem.getAudioInputStream(musicPath);
                                win_clip = AudioSystem.getClip();
                                win_clip.open(audioInput);
                                FloatControl gainControl=(FloatControl) win_clip.getControl(FloatControl.Type.MASTER_GAIN);
                                gainControl.setValue(-20.0f);//设置音量，范围为 -60.0f 到 6.0f
                                win_clip.start();
                                //clip.loop(Clip.LOOP_CONTINUOUSLY);
                        }
                        else {}
                }
                catch(Exception ex)
                {
                        ex.printStackTrace();
                }}

}
