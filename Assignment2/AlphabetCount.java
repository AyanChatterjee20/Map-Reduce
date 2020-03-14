import java.io.*;
import org.apache.hadoop.io.IntWritable;
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

public class AlphabetCount {

	public static class Mapp extends Mapper<LongWritable,Text,IntWritable,Text>
	{
		private IntWritable key = new IntWritable();
		private Text val =new Text();
		
		public void map(LongWritable k, Text v,Context con) throws IOException, InterruptedException
		{
			int i;
			String s[] = v.toString().trim().split(" ");
			for(String va: s)
			{
				val.set(va);
				i = va.length();
				key.set(i);
				con.write(key, val);
			}
		}
	}
	
	public static class Reduce extends Reducer<IntWritable,Text,IntWritable,Text>
	{
		private Text value = new Text();
		public void reduce(IntWritable key,Iterable<Text> val,Context con) throws IOException,InterruptedException
		{
			String s ="The same size list of word is: ";
			for(Text word : val)
			{
				String temp = new String();
				temp = word.toString();
				s = s+" "+temp;
			}
			value.set(s);
			con.write(key, value);
		}
	}
	
	public static void main (String args[]) throws Exception 
	{
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf,"Alphabet count wise grouping");
	
		job.setJarByClass(AlphabetCount.class);
		job.setMapperClass(Mapp.class);
		job.setReducerClass(Reduce.class);
		
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
