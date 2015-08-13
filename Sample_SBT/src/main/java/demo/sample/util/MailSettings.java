package demo.sample.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="mail.smtp")
@PropertySource(value={StringConstants.CONFIG_FILE_LOCATION})
public class MailSettings {
	
	private String HOST;
	private String PORT;
	private String USER;
	private String PASSWORD;
	
	private boolean AUTH;
	private boolean STARTTTLS_ENABLE;
	private boolean DEBUG;
	
	
	public String getHOST() {
		return HOST;
	}
	public void setHOST(String hOST) {
		HOST = hOST;
	}
	public String getPORT() {
		return PORT;
	}
	public void setPORT(String pORT) {
		PORT = pORT;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getUSER() {
		return USER;
	}
	public void setUSER(String uSER) {
		USER = uSER;
	}
	public boolean isAUTH() {
		return AUTH;
	}
	public void setAUTH(boolean aUTH) {
		AUTH = aUTH;
	}
	public boolean isSTARTTTLS_ENABLE() {
		return STARTTTLS_ENABLE;
	}
	public void setSTARTTTLS_ENABLE(boolean sTARTTTLS_ENABLE) {
		STARTTTLS_ENABLE = sTARTTTLS_ENABLE;
	}
	public boolean isDEBUG() {
		return DEBUG;
	}
	public void setDEBUG(boolean dEBUG) {
		DEBUG = dEBUG;
	}

}
