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


public class ActiveAirlinesUnitedState {
	
	public static class MapActiveAirlineUnitedState extends Mapper<LongWritable,Text,Text,Text>
	{
		Text key = new Text();
		Text Val = new Text();
		public void map(LongWritable inputKey, Text inputVal,Context con) throws IOException, InterruptedException
		{
			String s[] = inputVal.toString().trim().split(",");
			if(s.length == 8)
				if(!s[7].isEmpty() && !s[6].isEmpty())
					if(s[7].equalsIgnoreCase("Y") && s[6].equalsIgnoreCase("United States"))
					{
						if(!s[1].isEmpty())
						{
							key.set(s[6]);
							Val.set(s[1]);
							con.write(key, Val);
						}
					}
			}
	}
	
	public static class ReduceActiveAirlineUnitedState extends Reducer<Text,Text,Text,Text>
	{
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job =Job.getInstance(conf, new String("List of Active Airlines in United State"));
		
		job.setJarByClass(ActiveAirlinesUnitedState.class);
		job.setMapperClass(MapActiveAirlineUnitedState.class);
		job.setReducerClass(ReduceActiveAirlineUnitedState.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}

