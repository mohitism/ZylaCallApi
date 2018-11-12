/*
 * =============================================================================
 * Copyright (c) 2015-2016 Novopay Solutions Pvt Ltd. All rights reserved.
 * No part of this artifact may be reproduced or transmitted in any form or by
 * any means, electronic or mechanical, whether now known or later invented,
 * for any purpose without the prior and express written consent of 
 * Novopay Solutions Pvt Ltd
 *
 * =============================================================================
 */
package in.zylahealth.spring.exception;

/**
 * The Class NovopayNonFatalException.
 */
public class ZylaHealthNonFatalException extends RuntimeException implements ZylaHealthException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4721667673880882955L;
	
	/** The error code. */
	private String errorCode;
	
	/**
	 * Instantiates a new novopay non fatal exception.
	 *
	 * @param errorCode the error code
	 */
	public ZylaHealthNonFatalException(String errorCode) {
		super("");
		setErrorCode(errorCode);
	}
	
	/**
	 * Instantiates a new novopay non fatal exception.
	 *
	 * @param errorCode the error code
	 * @param defaultMsg the default msg
	 */
	public ZylaHealthNonFatalException(String errorCode, String defaultMsg) {
		super(defaultMsg);
		setErrorCode(errorCode);
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 *
	 * @param errorCode the new error code
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
