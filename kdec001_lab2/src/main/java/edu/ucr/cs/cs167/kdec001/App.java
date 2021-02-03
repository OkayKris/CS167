package edu.ucr.cs.cs167.kdec001;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        if( args.length != 2){
            System.out.println("[input path] [output path]");
            return;
        }

        Path inputPath = new Path(args[0]);
        Path outputPath = new Path(args[1]);

        Configuration conf = new Configuration();
        conf.addResource(new Path("D:\\CS167\\hadoop-2.10.0\\etc\\hadoop\\core-site.xml"));
        conf.addResource(new Path("D:\\CS167\\hadoop-2.10.0\\etc\\hadoop\\hdfs-site.xml"));
        FileSystem fs = inputPath.getFileSystem(conf);
        FileSystem fso = outputPath.getFileSystem(conf);
        if(!fs.exists(inputPath)){
            System.out.println("Input not found");
            return;
        }

        if(fso.exists(outputPath)){
            System.out.println("Output file exists");
            return;
        }

        long start = System.nanoTime();

        FSDataInputStream inputStream = fs.open(inputPath);
        FSDataOutputStream outputStream = fso.create(outputPath);

        byte[] buffer = new byte[1024];
        int length;
        long total = 0;

        while((length = inputStream.read(buffer)) > 0){
            outputStream.write(buffer, 0, length);
            total += length;
        }

        double estTime = ((double)System.nanoTime() - start) / 1000000000;

        inputStream.close();
        fs.close();
        fso.close();

        System.out.println("Copied " + total + " bytes from '" + inputPath +  "' to '" + outputPath + "' in " + estTime + " seconds");
    }
}
