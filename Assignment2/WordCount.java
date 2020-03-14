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

public class WordCount {
	
	public static class MapWord extends Mapper<LongWritable , Text, Text, IntWritable>
	{
		private Text key = new Text();
		private IntWritable val = new IntWritable();
		
		public void map(LongWritable k, Text V, Context con)
		{
			try{
				String[] s = V.toString().trim().split(" ");
				for (String word : s)
				{
					key.set(word);
					val.set(1);
					con.write(key, val);
				}
				
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	public static class ReduceWord extends Reducer<Text,IntWritable,Text,IntWritable>
	{
		//private IntWritable  val = new IntWritable();
		public void reduce(Text key, Iterable<IntWritable> value,Context con) throws IOException,InterruptedException
		{
			int sum = 0;
			for (IntWritable i : value)
			{
				sum = sum + i.get();
			}
			//val.set(sum);
			con.write(key,new IntWritable(sum) );
		}
	}
	
	public static void main (String args[]) throws IOException, InterruptedException, ClassNotFoundException 
	{
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf,"WordCount from a given file");
		
		job.setJarByClass(WordCount.class);
		job.setMapperClass(MapWord.class);
		job.setReducerClass(ReduceWord.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job,new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
