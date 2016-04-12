package org.xsnake.web.upload;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadUtils {

	public static List<IUploadFile> uploadFile(HttpServletRequest request,String path){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
		Map<String, MultipartFile>  map = multipartRequest.getFileMap();
		Set<String> keys = map.keySet();
		Iterator<String> it = keys.iterator();
		List<IUploadFile> result = new ArrayList<IUploadFile>();
		while (it.hasNext()) {
			String filedName = it.next();
			MultipartFile file = map.get(filedName);
			if(file.getSize()>0){
				String fileName = file.getOriginalFilename();
				String fileType = "";
				if(fileName.lastIndexOf(".") > 0){
					fileType = fileName.substring(fileName.lastIndexOf("."));
				}
				String newName = new StringBuffer(path).append("/").append(UUID.randomUUID().toString()).append(fileType).toString(); 
				File dir = new File(request.getSession().getServletContext().getRealPath("/")+path);
				if(!dir.exists()){
					dir.mkdirs();
				}
				File f = new File(request.getSession().getServletContext().getRealPath("/")+newName);
				if(!f.exists()){
					OutputStream out = null;
					try {
						f.createNewFile();
						out = new BufferedOutputStream(new FileOutputStream(f));
						out.write(file.getBytes());
						out.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}finally{
						try {
							out.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				UploadFileImpl uf = new UploadFileImpl();
				uf.setName(file.getOriginalFilename());
				uf.setFieldName(filedName);
				uf.setRealPath(f.getPath());
				uf.setSize(file.getSize());
				uf.setWebPath(newName);
				uf.setContentType(file.getContentType());
				uf.setSuffix(fileType);
				result.add(uf);
			}
		}
		return result;
	}
	
}
