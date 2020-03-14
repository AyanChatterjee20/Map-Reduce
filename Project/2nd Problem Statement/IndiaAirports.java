import java.io.*;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;


public class IndiaAirports {
	
	public static class Map extends Mapper<LongWritable,Text,Text,Text>
	{
		private Text key = new Text();
		private Text value = new Text();
		
		public void map(LongWritable k, Text v,Context con) throws IOException, InterruptedException
		{
			String s[] = v.toString().trim().split(",");
			if(s[3].trim().toUpperCase().equals("INDIA"))
			{
				key.set(s[3]);
				value.set(s[1]);
				
				con.write(key, value);
			}
		}
	}
	
	public static class Reduce extends Reducer<Text,Text,Text,Text>
	{
		public void reduce(Text key,Text val,Context con) throws IOException,InterruptedException
		{
			
		}
	}
	
	public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException
	{
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf,"List of Airports Operating from India");
		
		job.setJarByClass(IndiaAirports.class);
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}

