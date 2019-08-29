package com.ruiyun.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.ruiyun.http.HttpServlet;

/**
 * HTTP/1.1 200 OK Bdpagetype: 1 Bdqid: 0x84aaba060004bcbc Cache-Control:
 * private Connection: Keep-Alive Content-Encoding: gzip Content-Type: text/html
 * Cxy_all: baidu+c617e639bb268d7ec184877eb2bd9dcf Date: Sat, 24 Aug 2019
 * 04:28:57 GMT Expires: Sat, 24 Aug 2019 04:28:08 GMT Server: BWS/1.1
 * Set-Cookie: delPer=0; path=/; domain=.baidu.com Set-Cookie: BDSVRTM=6; path=/
 * Set-Cookie: BD_HOME=0; path=/ Set-Cookie:
 * H_PS_PSSID=1457_21082_18559_29522_29518_29098_29568_29221_26350_22158;
 * path=/; domain=.baidu.com Strict-Transport-Security: max-age=172800 Vary:
 * Accept-Encoding X-Ua-Compatible: IE=Edge,chrome=1 Transfer-Encoding: chunked
 */
public class Server {
	private static final Log log = LogFactory.getLog(Server.class);
	private static final Object Object = null;
	private static String WEBROOT = System.getProperty("user.dir") + "\\WebContent";
	private static String url = "";
	private static Map<String,Object> ServletMap = new HashMap<>();
	/**
	 * 加载servlet
	 */
	static{
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(new File(WEBROOT+"\\web.properties"));
			prop.load(in);
			Set<Entry<Object,Object>> entrySet = prop.entrySet();
			for (Entry<Object, Object> entry : entrySet) {
				String clazzName = (String)entry.getValue();
				String aliaName  = (String)entry.getKey();
				Class<?> class1 = Class.forName(clazzName);	
				Object newInstance = class1.newInstance();
				if(newInstance instanceof HttpServlet){
					Object object = ServletMap.get(entry.getKey());
					if(object == null){
						ServletMap.put(aliaName,newInstance);
						log.info(entry.getKey()+"已经被加载了...");
						log.info(entry.getValue()+"已经被加载了...");
					}else{
						throw new RuntimeException("容器中已经有了一个"+aliaName+"实例了");
					}
				}
			}
		} catch (Exception e) {
			log.error("加载web.properties文件失败");
		}
	
	}
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		Socket socket = null;
		

		try {
			serverSocket = new ServerSocket(8080);
			while (true) {
				socket = serverSocket.accept();
				inputStream = socket.getInputStream();
				outputStream = socket.getOutputStream();
				String content = getReqContent(inputStream);
				log.info(content);
				url = getUrl(content);
				if (!StringUtils.isEmpty(url)) {
					if(url.contains(".")){
						sendStaticResource(outputStream );
					}else{
						sendDynasticResource(outputStream);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				inputStream.close();
				inputStream = null;
			}
			if (outputStream != null) {
				outputStream.close();
				outputStream = null;
			}
			if (socket != null) {
				socket.close();
				socket = null;
			}
		}

	}
	/**
	 * 发送动态资源
	 * @param inputStream
	 * @param outputStream
	 */
	private static void sendDynasticResource(OutputStream outputStream) {
		
		if(url.indexOf("?")>-1){
			int indexOf = url.indexOf("?");
			log.info("url="+url+" indexOf="+indexOf);
			String servletStr = url.substring(1, indexOf);
			log.info("servletStr=="+servletStr);
			HttpServlet servlet = (HttpServlet)ServletMap.get(servletStr);
			servlet.service(outputStream);
		}
	}

	/**
	 * 发送静态内容
	 * 
	 * @param ops
	 * @throws IOException
	 */
	private static void sendStaticResource(OutputStream ops ) throws IOException {
		byte[] byt = new byte[2048];
		File file = new File(WEBROOT, url);
		InputStream fis = null;
		
		try {
			// 文件存在
			StringBuffer sb = new StringBuffer();
			if (!file.isFile() && file.exists()) {
				sb.append("HTTP/1.1 200 OK\n").append("Server:myserver 1.1\n")
				.append("Content-Type:text/html,charset=utf-8\n").append("\n");
				fis = new FileInputStream(file);
				ops.write(sb.toString().getBytes());
				int len = fis.read(byt);
				while (len != -1) {
					ops.write(byt, 0, len);
					len = fis.read(byt);
				}

			} else {// 文件不存在
				String errorMessage = "file not found";
				
				sb.append("HTTP/1.1 404 not found\n").append("Server:apache-Coyote/1.1\n")
				.append("Content-Type:text/html,charset=utf-8\n").append("\n").append(errorMessage);
				
				ops.write(sb.toString().getBytes());
			}

		} catch (Exception e) {
			log.error("发送静态内容失败", e);
		} finally {
			
			if (fis != null) {
				fis.close();
				fis = null;
			}
		}
	}

	/**
	 * 获取请求体中的url
	 * 
	 * @param content
	 */
	private static String getUrl(String content) {
		int index1, index2 = 0;
		if (StringUtils.isEmpty(content)) {
			log.error("请求内容为空，请重新请求！");
		} else {
			index1 = content.indexOf(" ");
			if (index1 != -1) {
				index2 = content.indexOf(" ", index1 + 1);
			}
			if (index2 != 0 && index1 != index2) {
				return content.substring(index1 + 1, index2);
			}
		}
		return null;
	}

	/**
	 * 获取请求体的内容
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	private static String getReqContent(InputStream inputStream) throws IOException {
		byte[] bye = new byte[1024];
		int read = 0;
		StringBuffer sb = new StringBuffer();
		while ((read = inputStream.read(bye)) != -1) {
			sb.append(new String(bye));
		}
		return sb.toString();
	}
}
