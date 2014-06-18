package mmap;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-3-31
 * Time: 下午4:11
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuf = ByteBuffer.allocate(1024 * 14 * 1024);

        byte[] bbb = new byte[14 * 1024 * 1024];

        FileInputStream fis = new FileInputStream("d:/edw_gemini_page_stat_20140331114005.csv");

        FileOutputStream fos = new FileOutputStream("d:/edw_gemini_page_stat_20140331114005_out.csv");

        FileChannel fc = fis.getChannel();

//        FileChannel fileChannel = new RandomAccessFile("d:/edw_gemini_page_stat_20140331114005.csv","rw").getChannel();



        long timeStar = System.currentTimeMillis();//得到当前的时间


        fc.read(byteBuf);//1 读取   Read time :214ms   37

//        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()); // Read time :20ms
//
//        mbb.load();
        long timeEnd = System.currentTimeMillis();//得到当前的时间


        System.out.println("Read time :" + (timeEnd - timeStar) + "ms");


//        for (int i = 0; i < 2000; i++) {
//                mbb.put(i, new Integer(i).byteValue());
//        }

        timeStar = System.currentTimeMillis();


        fos.write(bbb);//2 写入     Write time :14ms  17

//        mbb.flip();          //Write time :0ms


        timeEnd = System.currentTimeMillis();

        System.out.println("Write time :" + (timeEnd - timeStar) + "ms");

        fos.flush();

        fc.close();

        fis.close();





//        FileChannel fileChannel = new RandomAccessFile("c:/edw_gemini_page_stat_20140331114005.csv","r").getChannel();
//        long size = fileChannel.size();
//        ByteBuffer buf = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L,size);
//
//
//        byte[] bytes = new byte[1000];
//
//
//
//        InputStreamReader read = new InputStreamReader(new FileInputStream( new File("c:/edw_gemini_page_stat_20140331114005.csv")),"utf-8");
//        BufferedReader bufferedReader = new BufferedReader(read);
//
//        String lineTxt = "";
//        while((lineTxt = bufferedReader.readLine()) != null){
//            String[] v = lineTxt.split(",");
//            if( "47".equals(v[0]) ){
//                System.out.print(v[2].trim());
//                System.out.print("   ");
//                System.out.print(v[3].trim());
//                System.out.print("   ");
//                System.out.print(v[4].trim());
//                System.out.print("   ");
//                System.out.print(v[5].trim());
//                System.out.print("   ");
//                System.out.print(v[6].trim());
//                System.out.print("   ");
//                System.out.print(v[7].trim());
//                System.out.print("   ");
//                System.out.print(v[8].trim());
//                System.out.print("   ");
//                System.out.print(v[9].trim());
//                System.out.print("   ");
//                System.out.println(v[10].trim());
//            }
//
//
//        }


//        System.out.println(bytes ="\n".getBytes());// 10

    }

}
