import java.io.*;
import java.util.Date;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

public class StockMaxDate {
	
	public static class MapClass extends Mapper<LongWritable,Text,Text,Text>
	{
		private Text key = new Text();
		private Text val = new Text();
		
		public void map(LongWritable k, Text v,Context con)
		{
			try
			{
				String[] str = v.toString().split(",");
				//Date d = new Date();
				String s = new String();
				key.set(str[1]);
				s = str[2]+"  "+str[4];
				val.set(s);
				con.write(key,val);
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static class ReduceClass extends Reducer<Text,Text,Text,Text>
	{
		//private static Text value = new Text();
		public void reduce(Text key,Iterable<Text> val,Context con) throws IOException,InterruptedException
		{
			double temp = 0;
			Text value = new Text();
			String date = new String();
			for(Text t: val)
			{
				String[] s = t.toString().split("  ");
				double d = Double.parseDouble(s[1]);
				if(temp < d)
				{
					temp = d;
					date = s[0];
				}
			}
			date = temp+" "+date;
			value.set(date);
			con.write(key, value);
		}
	}
	
	public static void main (String[] args) throws Exception
	{
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf,"Date wise Max Stock Value for each Stock :");
		
		job.setJarByClass(StockMaxDate.class);
		job.setMapperClass(MapClass.class);
		job.setReducerClass(ReduceClass.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
	}

}
