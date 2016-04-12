package org.xsnake.web.upload;

public interface IUploadFile {

	String getRealPath();

	String getName();

	long getSize();

	String getFieldName();

	String getWebPath();
	
	String getContentType();
	
	String getSuffix();
}
