package com.spring.batch.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;


@ConfigurationProperties("batch-properties")
public class BatchProperties {
	@NestedConfigurationProperty
	private Reader reader;
	
	@NestedConfigurationProperty
	private Writer writer;
	
	public Reader getReader() {
		return reader;	
	}
	
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	
	public Writer getWriter() {
		return writer;
	}
	
	public void setWriter(Writer writer) {
		this.writer = writer;
	}
	
	public static class Reader {
		private String fileName;
		private String filePath;
		private String delimiter;
		private String[] fields;
		
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		public String getDelimiter() {
			return delimiter;
		}
		public void setDelimiter(String delimiter) {
			this.delimiter = delimiter;
		}
		public String[] getFields() {
			return fields;
		}
		public void setFields(String[] fields) {
			this.fields = fields;
		}	
	}
	
	public static class Writer {
		private String fileName;
		private String filePath;
		private String format;
		private String[] fields;
		
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		public String getFormat() {
			return format;
		}
		public void setFormat(String format) {
			this.format = format;
		}
		public String[] getFields() {
			return fields;
		}
		public void setFields(String[] fields) {
			this.fields = fields;
		}
		
	}

}
