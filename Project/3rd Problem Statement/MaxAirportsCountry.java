import java.io.*;
//import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
//import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
//import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

@SuppressWarnings("deprecation")
public class MaxAirportsCountry {
	
	public static class MapAirports extends Mapper<Object,Text,Text,IntWritable>
	{
		Text key = new Text();
		IntWritable Value = new IntWritable();
		public void map (Object i,Text Val,Context con) throws IOException, InterruptedException
		{
			String s[] = Val.toString().trim().split(",");
			if(!s[3].isEmpty())
			{
				key.set(s[3]);
				if(!s[1].isEmpty())
				{
					Value.set(1);
				}
			}
			con.write(key, Value);
		}
	}
	
	public static class ReduceAirport extends Reducer<Text,IntWritable,Text,IntWritable>
	{
		//IntWritable value = new IntWritable();
		//Text k = new Text();
		private TreeMap<Long,String> tmap;
		public void setup(Context con)
		{
			tmap = new TreeMap<Long,String>();
		}
		
		public void reduce (Text k, Iterable<IntWritable> val, Context con) throws IOException, InterruptedException
		{
			int counter = 0;
			for(IntWritable values : val)
			{
				counter = counter + values.get();
			}
			//value.set(counter);
			tmap.put((long) counter, k.toString());
			//con.write(k, value);
			if (tmap.size() > 1)
			{
				tmap.remove(tmap.firstKey());
			}
		}
		public void cleanup(Context con) throws IOException, InterruptedException
		{
			for(Map.Entry<Long, String> entry : tmap.entrySet())
			{
				long v = entry.getKey();
				int values = (int)v;
				String s = entry.getValue();
				con.write(new Text(s), new IntWritable(values));
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException
	{
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "Max no. Airport of the country");
		
		job.setJarByClass(MaxAirportsCountry.class);
		job.setMapperClass(MapAirports.class);
		job.setReducerClass(ReduceAirport.class);
		job.setCombinerClass(ReduceAirport.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		//DistributedCache.addCacheFile(new URI(args[0]), conf);
		//job.addFileToClassPath(new Path(args[0]));
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
