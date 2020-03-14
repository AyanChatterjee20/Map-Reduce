import java.io.*;

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

public class AllTimeAvg {
	
	public static class MapClass extends Mapper<LongWritable, Text , Text , DoubleWritable>
	{
		private Text key_t= new Text();
		private DoubleWritable val_t = new DoubleWritable();
		
		public void map(LongWritable key , Text val, Context con)
		{
			try{
				String[] str = val.toString().split(",");
				double avg = Double.parseDouble(str[6]);
				key_t.set(str[1]);
				val_t.set(avg);
				con.write(key_t, val_t);
			}catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static class ReduceClass extends Reducer<Text,DoubleWritable,Text,DoubleWritable>
	{
		private DoubleWritable avg = new DoubleWritable();
		public void reduce(Text key,Iterable<DoubleWritable> val,Context con) throws IOException, InterruptedException
		{
			double i=0;
			int count = 0;
			for(DoubleWritable v: val)
			{
				i = v.get()+i;
				count = count + 1;
			}
			i = i/count;
			avg.set(i);
			con.write(key, avg);
		}
		
	}
	
	public static void main(String[] args) throws Exception
	{
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf,"All Time Average Stock price");
		job.setJarByClass(AllTimeAvg.class);
		job.setMapperClass(MapClass.class);
		job.setReducerClass(ReduceClass.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(DoubleWritable.class);
		job.setOutputValueClass(DoubleWritable.class);
		job.setOutputKeyClass(Text.class);
		FileInputFormat.addInputPath(job,new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 :1);
	}

}

