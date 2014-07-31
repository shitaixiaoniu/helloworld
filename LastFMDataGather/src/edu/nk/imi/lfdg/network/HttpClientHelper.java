package edu.nk.imi.lfdg.network;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

public class HttpClientHelper {
	public static String get(String url) throws Exception {
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().build();
		try {
			httpclient.start();
			// Execute request
			HttpGet request1 = new HttpGet(url);
			Future<HttpResponse> future = httpclient.execute(request1, null);
			// and wait until a response is received
			HttpResponse response1 = future.get();
			System.out.println(request1.getRequestLine() + "->"
					+ response1.getStatusLine());
			int statusCode = response1.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) // SC_OK = 200
			{
				String resultStr = EntityUtils.toString(response1.getEntity());
				System.out.println("result:" + resultStr);
				return resultStr;
			}
			

		} finally {
			httpclient.close();
		}

		return null;
	}

	@Deprecated
	public static void getCallBack(String url) throws Exception {
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().build();
		try {
			httpclient.start();
			HttpGet request = new HttpGet(url);
			final CountDownLatch latch = new CountDownLatch(1);
			httpclient.execute(request, new FutureCallback<HttpResponse>() {

				public void completed(final HttpResponse response) {
					latch.countDown();
					System.out.println(response.getStatusLine());
					int statusCode = response.getStatusLine().getStatusCode();
					if (statusCode == HttpStatus.SC_OK) // SC_OK = 200
					{

						try {
							String resultStr = EntityUtils.toString(response
									.getEntity());

						} catch (ParseException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// JsonObject jsonObject = new JsonObject(resultStr);
					}
				}

				public void failed(final Exception ex) {
					latch.countDown();
					System.out.println(ex);
				}

				public void cancelled() {
					latch.countDown();
					System.out.println(" cancelled");
				}

			});
			latch.await();
			System.out.println("Shutting down");

		}

		finally {
			httpclient.close();
		}
		System.out.println("Done");
	}
}
