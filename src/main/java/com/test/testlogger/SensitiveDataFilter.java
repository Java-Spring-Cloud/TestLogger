package com.test.testlogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class SensitiveDataFilter extends Filter<ILoggingEvent> {

	private static final String ELEMENT_FIELD_VALUE = "\":";

	private static final Logger LOG = LoggerFactory.getLogger(MaskAspect.class);

	private static final String[] secureFields = {
            "ChargeAccountNumber",
            "ChargeCVN",
            "accountNumber",
            "cardNumber",
            "DebitAccountNumber",
            "DebitCVN",
            "CheckAcountNumber",
            "Password",
            "cvv"
  };
	
	@Override
	public FilterReply decide(ILoggingEvent event) {
		String inboundLogMessage = event.getMessage();
		FilterReply defaultFilterReply = FilterReply.ACCEPT;
		for (int i = secureFields.length - 1; i >= 0; i--) {
			String currentElement = secureFields[i].toString() + ELEMENT_FIELD_VALUE;
			int index = inboundLogMessage.indexOf(currentElement);
			if (index > 0) {
				defaultFilterReply = FilterReply.DENY;
			}
		}
		return defaultFilterReply;

	}
}