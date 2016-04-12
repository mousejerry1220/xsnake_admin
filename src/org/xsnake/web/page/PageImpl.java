package org.xsnake.web.page;

import java.util.ArrayList;
import java.util.List;

public class PageImpl<T> implements IPage<T> {

	private static final long serialVersionUID = 1L;

	int pageNumber = 0;
	
	int pageSize = 0;
	
	int count = 0;
	
	int pageCount = 0;
	
	int pageNext = 0;
	
	int pagePrevious = 0;
	
	int pageCurrent = 0;

	List<T> list = null;
	
	List<Long> pages = new ArrayList<Long>();
	
	public PageImpl(List<T> list,int pageNumber,int pageSize,int count ){
		
		if(list==null){
			
			list = new ArrayList<T>();
			
		}
		
		this.list = list;
		
		
		this.pageNumber = pageNumber;
		
		this.pageSize = pageSize;
		
		this.count = count;
		
		this.pageCount = (count / pageSize) + (count % pageSize == 0 ? 0:1);
		
		if (this.pageCount == 0) {
			
			this.pageCount = 1;
			
			this.pageCurrent = 1;
			
		} else {
			
			this.pageCurrent = (pageNumber <= pageCount) ? (pageNumber > 0 ? pageNumber : 1) : pageCount;
			
		}
		
		
		this.pageNext = (pageCurrent+1>pageCount)?pageCount:(pageCurrent+1);
		
		this.pagePrevious = (pageCurrent-1<1)?1:(pageCurrent-1);
		
		if (pageCurrent < 6) {
			
			for (long i = 1; i < 10 && i <= pageCount ; i++) {
				
				pages.add(i);
				
			}
			
		} else {
			
			for (long i = pageCurrent - 4; i <= pageCurrent + 4 && i <= pageCount; i++) {
				
				pages.add(i);
				
			}
			
		}
		
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public int getCount() {
		return count;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getPageNext() {
		return pageNext;
	}

	public int getPagePrevious() {
		return pagePrevious;
	}

	public int getPageCurrent() {
		return pageCurrent;
	}

	@Override
	public List<Long> getPages() {
		return pages;
	}

	@Override
	public List<T> getList() {
		return list;
	}
	
}
