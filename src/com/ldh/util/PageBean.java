package com.ldh.util;

import java.util.List;


	public class PageBean<T> {
		private int pageNum;//当前页数
		private int pageCount;//总页数
		private int rowCount;//数据库总记录数
		private int pageSize;//每页显示记录数
		private int rowStart;//每页起始记录数

		public PageBean(int rowCount, int pageNum, int pageSize){
			this.pageSize = pageSize;
			this.rowCount = rowCount;
			
			this.pageCount = rowCount / pageSize +((rowCount % pageSize) > 0 ? 1 : 0);
			if(pageNum > pageCount){
				this.pageNum = pageCount;
			}else if(pageNum < 1){
				this.pageNum = 1;
			}else{
				this.pageNum = pageNum;
			}
			
			this.rowStart = (this.pageNum - 1) * this.pageSize;
		}
		
		
		public int getRowCount() {
			return rowCount;
		}
		
		public void setRowCount(int rowCount) {
			this.rowCount = rowCount;
		}
		public int getPageNum() {
			return pageNum;
		}
		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}
		public int getPageCount() {
			return pageCount;
		}
		public void setPageCount(int pageCount) {
			this.pageCount = pageCount;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getRowStart() {
			return rowStart;
		}
		public void setRowStart(int rowStart) {
			this.rowStart = rowStart;
		}
		
		
	}


