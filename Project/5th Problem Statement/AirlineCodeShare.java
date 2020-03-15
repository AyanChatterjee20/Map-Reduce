package training;

import java.io.*;
import java.net.URI;
import java.util.HashMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;


public class AirlineCodeShare {
	
	public static class MapAirLine extends Mapper<LongWritable,Text,Text,Text>
	{
		private Text key = new Text();
		private Text value = new Text();
		private static HashMap<Integer,String> routeMap = new HashMap<Integer,String>();
		private BufferedReader breader ;
		
		protected void setup(Context con) throws IOException
		{
			URI cacheF[] = con.getCacheFiles();
			if(cacheF != null && cacheF.length > 0)
			{
				for (int i=0 ; i< cacheF.length; i++ )
				{
					Path eachPath = new Path(cacheF[i]);
					if(eachPath.getName().toString().trim().equals("routes.txt"))
					{
						loadRoutesHashMap(eachPath,con);
						break;
					}
				}
			}
		}
		
		private void loadRoutesHashMap(Path path,Context con) throws IOException
		{
			String strLineRead = "";
			try
			{
				breader = new BufferedReader(new FileReader(path.getName().toString()));
				while ((strLineRead = breader.readLine()) != null)
				{
					String s[] = strLineRead.split(",");
					if(s.length == 9)
						if(isInteger(s[1]) && !s[6].isEmpty())
						{
							int i = Integer.parseInt(s[1]);
							if (i > 0)
							{
								if(s[6].trim().equalsIgnoreCase("Y"))
								{
									routeMap.put(i, s[6]);
								}
							}
						}
				}
			}catch(IOException e)
			{
				System.out.println(e);
			}finally{
				if(breader != null)
					breader.close();
			}
		}
		
		public void map(LongWritable k, Text v,Context con) throws IOException, InterruptedException
		{
			String s[] = v.toString().trim().split(",");
			if(s.length == 8)
				if(isInteger(s[0]) && !s[1].isEmpty())
				{
					int i = Integer.parseInt(s[0]);
					if (i > 0)
					{
						//String val = routeMap.get(i);
						if("Y".equalsIgnoreCase(routeMap.get(i)))
						{
							String val = routeMap.get(i);
							key.set(s[1]);
							value.set(val);
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
	
	public static class ReduceJoin extends Reducer<Text,Text,Text,Text>
	{
		
	}
	
	public static void main(String args[]) throws Exception
	{
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf,"List of Airlines having CodeShare");

		job.addCacheFile(new URI("hdfs://localhost:8020/InputFiles/Project/routes.txt"));
		
		job.setJarByClass(AirlineCodeShare.class);
		job.setReducerClass(ReduceJoin.class);
		job.setMapperClass(MapAirLine.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
