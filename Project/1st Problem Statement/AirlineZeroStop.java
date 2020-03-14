import java.io.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;


public class AirlineZeroStop {
	
	public static class MapAirLine extends Mapper<LongWritable,Text,IntWritable,Text>
	{
		private IntWritable key = new IntWritable();
		private Text value = new Text();
		
		public void map(LongWritable k, Text v,Context con) throws IOException, InterruptedException
		{
			String s[] = v.toString().trim().split(",");
			if(isInteger(s[0]) && !s[1].isEmpty())
			{
				int i = Integer.parseInt(s[0]);
				if (i > 0)
				{
					key.set(i);
					value.set(s[1]);
					con.write(key, value);
				}
			}
		}
		
		public static boolean isInteger(String num)
		{
			try
			{
				Integer.parseInt(num);
			}catch(Exception e)
			{
				return false;
			}
			return true;
		}
	}
	
	public static class MapRoute extends Mapper<LongWritable,Text,IntWritable,Text>
	{
		private IntWritable key = new IntWritable();
		private Text value = new Text();
		
		public void map(LongWritable k, Text v,Context con) throws IOException, InterruptedException
		{
			String s[] = v.toString().trim().split(",");
			if(isInteger(s[1]) && !s[7].isEmpty())
			{
				int i = Integer.parseInt(s[1]);
				if (i > 0)
				{
					key.set(i);
					if(isInteger(s[7]) && s[7].trim().equalsIgnoreCase("0"))
					{
						value.set(s[7]);
						con.write(key, value);
					}
				}
			}
		}
		
		public static boolean isInteger(String num)
		{
			try
			{
				Integer.parseInt(num);
			}catch(Exception e)
			{
				return false;
			}
			return true;
		}
	}
	
	public static class ReduceJoin extends Reducer<IntWritable,Text,Text,Text>
	{
		public void reduce(IntWritable key, Iterable<Text> values,Context con) throws IOException, InterruptedException
		{
			Text k = new Text();
			Text v = new Text();
			String Val = "is a/an airline having zero stop";
			v.set(Val);
			for (Text key1 : values)
			{
				String s = key1.toString();
				if(s.trim().equalsIgnoreCase("0"))
				{
					for(Text key2 : values)
					{
						String s1 = key2.toString();
						if(!s1.trim().equalsIgnoreCase("0"))
						{
							k.set(s1);
							con.write(k, v);
						}
					}
					break;
				}
			}
		}
	}
	
	public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException
	{
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf,"List of Airlines having zero stop");
		
		job.setJarByClass(AirlineZeroStop.class);
		job.setReducerClass(ReduceJoin.class);
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Text.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		MultipleInputs.addInputPath(job, new Path(args[0]),TextInputFormat.class, MapAirLine.class);
		MultipleInputs.addInputPath(job, new Path(args[1]),TextInputFormat.class, MapRoute.class);
		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
