package ck;

import java.util.Map;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import com.google.gson.Gson;

/**
 * @author ryq
 *
 *1、sleep3秒
 *2、获取当前时间，所有处方增量，未通过处方增量
 *3、存入redis
 *4、计数器清零
 *
 */
public class PrescriptionIncrementThread implements Runnable
{
	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(1000 * 3);
				RedissonClient myRedissonClient = Redisson.create();
				RMap<Object, Object> prescriptionIncrement = myRedissonClient.getMap("prescriptionIncrement");

				long counterTime = System.currentTimeMillis();

				Map<Object, Object> all = (Map<Object, Object>) prescriptionIncrement.get("all");
				all.put(counterTime, ServerObj.prescriptionIncrementIn3Seconds);
				prescriptionIncrement.replace("all", all);
				ServerObj.prescriptionIncrementIn3Seconds = 0;

				Map<Object, Object> notPassed = (Map<Object, Object>) prescriptionIncrement.get("notPassed");
				notPassed.put(counterTime, ServerObj.notPassPrescriptionIncrementIn3Seconds);
				prescriptionIncrement.replace("notPassed", notPassed);
				
				
				ServerObj.notPassPrescriptionIncrementIn3Seconds = 0;

			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
