package org.yaezakura.ljq.stuff;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MapLike {
	static String domain = "http://jzsc.mohurd.gov.cn";
	static String stuffListUrl = "/dataservice/query/comp/regStaffList/001607220057206853";
	static int pagesize = 25;
	static int pagecnt = 12;

	static int time = 200; // seconds.

	static String file = "stuff.txt";
	static Map<String, String> paraMap = new HashMap<String, String>();
	static PrintStream out;

	static {
		paraMap.put("$reload", "0");
		paraMap.put("$total", "289");
		paraMap.put("$pgsz", pagesize + "");
		try {
			out = new PrintStream(file);
		} catch (FileNotFoundException e) {
		}
	}

	static Map<String, String> stuff_urlMap = new HashMap<String, String>();
	
	
	public static void main(String[] args) throws IOException {
		fillMap();
		int loopCnt = 0;
		while (stuff_urlMap.size() != 0) {
			System.out.println("loop " + loopCnt++ + " start.");
			detail();
			System.out.println("loop " + loopCnt + " end, left " + stuff_urlMap.size());
		}
	}
	

	public static void fillMap() throws IOException {
		int trycnt = 1;
		for (int i = 1; i <= pagecnt; i++) {
			boolean catched = false;
			while (!catched) {
				try {
					Connection listConn = Jsoup.connect(domain + stuffListUrl);
					paraMap.put("$pg", i + "");
					for (Entry<String, String> entry : paraMap.entrySet())
						listConn.data(entry.getKey(), entry.getValue());
					Document listPage = listConn.post();
					Element tab = listPage.getElementsByTag("tbody").get(0);
					Elements trList = tab.children();
					for (int j = 0; j < trList.size(); j++) {
						Element node = trList.get(j);
						Elements tdList = node.children();
						for (int k = 0; k < tdList.size(); k++) {
							Element info = tdList.get(k);
							if ("姓名".equals(info.attr("data-header"))) {
								Element a = info.getElementsByTag("a").get(0);
								String uri = a.attr("onclick");
								uri = uri.substring(uri.indexOf("'") + 1);
								uri = uri.substring(0, uri.lastIndexOf("'"));
								String detailUrl = domain + uri;
								stuff_urlMap.put(info.text(), detailUrl);
							}
						}
					}
					catched = true;

				} catch (HttpStatusException e) {
					try {
						trycnt++;
						TimeUnit.SECONDS.sleep(time);
						System.out.println("try " + trycnt + " to catch list page " + i);
						continue;
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		}

	}



	public static void detail() throws IOException {
		Iterator<Entry<String, String>> entryItr = stuff_urlMap.entrySet().iterator();
		while (entryItr.hasNext()){
			Entry<String, String> entry = entryItr.next();
			String name = entry.getKey();
			String url = entry.getValue();
			Document detailPage;
			try{
				detailPage = Jsoup.connect(url).get();
			} catch(Exception e){
				continue;
			}
			Element div = detailPage.getElementById("regcert_tab");
			Elements dls = div.children();
			for (int i = 0; i < dls.size(); i++) {
				Element e = dls.get(i);
				Elements dds = e.getElementsByTag("dd");
				out.println("-----------------------------");
				for (int j = 0; j < dds.size(); j++) {
					Element dd = dds.get(j);
					Elements spans = dd.children();
					String key = spans.get(0).text();
					String value;
					if (spans.size() != 2) {
						key = "";
						value = dd.text();
					} else {

						value = spans.get(1).text();
					}
					out.println(key + value);
				}
				out.println("-----------------------------");
			}
			entryItr.remove();
		}
	}

}
