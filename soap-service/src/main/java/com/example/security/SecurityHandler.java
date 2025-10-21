package com.example.security;

import javax.xml.namespace.QName;
import jakarta.xml.soap.*;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Iterator;
import java.util.Set;

public class SecurityHandler implements SOAPHandler<SOAPMessageContext> {

	private static final String AUTH_TOKEN = "SecretToken123";

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (!Boolean.TRUE.equals(outbound)) {
			try {
				SOAPHeader header = context.getMessage().getSOAPHeader();
				if (header == null) {
					throw new SOAPException("Missing header");
				}
				Iterator<?> it = header.examineAllHeaderElements();
				String token = null;
				while (it.hasNext()) {
					SOAPHeaderElement element = (SOAPHeaderElement) it.next();
					if ("Token".equals(element.getLocalName())) {
						token = element.getValue();
						break;
					}
				}
				if (token == null || !token.equals(AUTH_TOKEN)) {
					throw new SOAPException("Invalid token");
				}
			} catch (SOAPException e) {
				generateSOAPFault(context.getMessage(), e.getMessage());
				return false;
			}
		}
		return true;
	}

	private void generateSOAPFault(SOAPMessage message, String reason) {
		try {
			SOAPBody body = message.getSOAPBody();
			body.removeContents();
			SOAPFault fault = body.addFault();
			fault.setFaultString("Authentication failed: " + reason);
		} catch (SOAPException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}

	@Override
	public void close(MessageContext context) {
		// no-op
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}
}
