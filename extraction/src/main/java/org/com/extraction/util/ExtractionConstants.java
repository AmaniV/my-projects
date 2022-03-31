package org.com.extraction.util;

import org.com.extraction.controller.ExtractionController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtractionConstants {
	public static final String RETRIEVAL_ENDPOINT= "/retrieval";
	public static final String CIRCUIT_NAME= "extractService";
    public static final String FALLBACK_METHOD= "extractFallback";
    public static final String FEIGN_RETRIEVAL_ENDPOINT= "/feignRetrieval";
    public static final String MSG= "Service is unavailable / invalid service";
    public static final Logger LOG = LoggerFactory.getLogger(ExtractionController.class);

}
