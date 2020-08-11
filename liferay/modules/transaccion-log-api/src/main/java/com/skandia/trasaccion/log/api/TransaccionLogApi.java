package com.skandia.trasaccion.log.api;

/**
 * @author jrodriguez
 */
public interface TransaccionLogApi {

	void info(Object msg);

	void info(Object msg, Throwable t);

	void error(Object msg);

	void error(Object msg, Throwable t);
}