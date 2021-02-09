package com.example.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public class DirService {

	
	private static ArrayList<String> rmbr = new ArrayList<>();
	private static ArrayList<String> cache = new ArrayList<>();
	private static Location  path;
	public Iterable<String> getFiles(Location location){
		
		ArrayList<String> files = new ArrayList<>();
		try {
			File file = new File(location.getLocation());
			File[] listF = file.listFiles();
			
			for(File f:listF) {
				if(f.isFile()) {
					files.add(f.getName());
				}
			}
			
			return (Iterable<String>) files;
		}
		catch (Exception e) {
			// TODO: handle exception
			files.add("error");
			return files;
		}
		
	}
	
	public ArrayList<String []> getDuplicates(Location location){
		
		rmbr.clear();
		path = location;
		ArrayList<String> cachedFiles = new ArrayList<>();
		ArrayList<String []> results = new ArrayList<>();
		ArrayList<String> size = new ArrayList<>();
		
		try {
			String path = location.getLocation();
			File file = new File(path);
			
			File[] listF = file.listFiles();
			Arrays.sort(listF);
			File tmp;
			int ln = listF.length;
			for(int j = 0; j < ln/2; j++) { 
				tmp = listF[j];
				listF[j] = listF[ln-(j+1)];
				listF[ln-(j+1)] = tmp;
			}
			String name;
			int lw;
			int i = 1;
			String ext;
			Double len=0.0;
			
			for(File f:listF) {
	
			    lw = f.getName().lastIndexOf(".");
			    name = f.getName().substring(0, lw).trim();
			    ext = f.getName().substring(lw);
			    
			    // check if file name ends with (digit)
			    while(true) {
			    	int n1 = name.length();
			    	boolean isD = Character.isDigit(name.charAt(n1- 1));
			    	boolean isUnderscore = name.charAt(name.length() - 2) =='_';
					if(f.isFile() && Pattern.compile("\\(\\d\\)$").matcher(name).find()) {
						int start = name.lastIndexOf("(");
						String strName = name.substring(0,start);
						name = strName.trim();
						
					}
					else if(f.isFile() && Pattern.compile("\\-\\sCopy$").matcher(name).find()) {
						int start = name.lastIndexOf("-");
						String strName = name.substring(0,start);
						name = strName.trim();
					}
					else if((n1 > 1 && isD && isUnderscore )) {
						int start = name.lastIndexOf("_");
						String strName = name.substring(0,start);
						name = strName.trim();
					}
			
					else {
						break;
					}
			    }
				if(cachedFiles.contains(name) && !rmbr.contains(name) && !size.contains(len+"")){
					size.add(len+"");
					rmbr.add(name);
					cache.add(f.getAbsolutePath());
					len =  (double)f.length()/(1024*1024);
					results.add(new String[] {i +"",path+"\\"+(name+ext),(name+ext),String.format("%.2f", len)+" MB" });
					i++;
				}
				else if(cachedFiles.contains(name) && rmbr.contains(name) && size.contains(len+"")) {
					cache.add(f.getAbsolutePath());
				}
				
				if(!cachedFiles.contains(name))
					cachedFiles.add(name);
			}
			
			return results;
		}
		catch (Exception e) {
			// TODO: handle exception
			results.add(new String[] {"error"});
			return results;
		}
	}
	
	public void deleteDuplicates() {
		
		if(!path.equals(null)) {
			File file;
			for(String f:cache) {
				file = new File(f);
				file.delete();
				
			}
		}
	}

	public void setSessionAttr(HttpServletRequest httpServletRequest,Location location,String attr) {
		HttpSession session = httpServletRequest.getSession();
		session.setAttribute(attr, location.getLocation());
		
	}
	public String getSessionAttr(HttpServletRequest httpServletRequest,String attr) {
		HttpSession session = httpServletRequest.getSession();
		String str = "";
		String ssn = (String) session.getAttribute(attr); 
		if(ssn != null) {
			
			str = (String) session.getAttribute(attr);
		}
		return str;
		
	}
	public void clearSession(HttpServletRequest httpServletRequest,String attr) {
		HttpSession session = httpServletRequest.getSession();
		if(session.getAttribute(attr) != null) {
			session.removeAttribute(attr);	
			session.invalidate();
					
		}
		
	}
	

}
