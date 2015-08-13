package demo.sample.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource(value={StringConstants.CONFIG_FILE_LOCATION})
public class PathSettings {
	
	/** Recaptcha Keys	 */
	private String RECAPTCHA_PRIVATE_KEY;
	private String RECAPTCHA_PUBLIC_KEY;
	/** Recaptcha Keys	 */
	
	/** Cassandra Config */
	private String CASSANDRA_CONTACT_POINTS;
	private int CASSANDRA_PORT;
	private String CASSANDRA_KEYSPACE;
	/** Cassandra Config */
	
	/** OpenTok Keys */
	private int OPENTOK_API_KEY;
	private String OPENTOK_SECRET_KEY;
	/** OpenTok Keys */


	public String getRECAPTCHA_PRIVATE_KEY() {
		return RECAPTCHA_PRIVATE_KEY;
	}
	public void setRECAPTCHA_PRIVATE_KEY(String rECAPTCHA_PRIVATE_KEY) {
		RECAPTCHA_PRIVATE_KEY = rECAPTCHA_PRIVATE_KEY;
	}
	public String getRECAPTCHA_PUBLIC_KEY() {
		return RECAPTCHA_PUBLIC_KEY;
	}
	public void setRECAPTCHA_PUBLIC_KEY(String rECAPTCHA_PUBLIC_KEY) {
		RECAPTCHA_PUBLIC_KEY = rECAPTCHA_PUBLIC_KEY;
	}
	public String getCASSANDRA_CONTACT_POINTS() {
		return CASSANDRA_CONTACT_POINTS;
	}
	public void setCASSANDRA_CONTACT_POINTS(String cASSANDRA_CONTACT_POINTS) {
		CASSANDRA_CONTACT_POINTS = cASSANDRA_CONTACT_POINTS;
	}
	public int getCASSANDRA_PORT() {
		return CASSANDRA_PORT;
	}
	public void setCASSANDRA_PORT(int cASSANDRA_PORT) {
		CASSANDRA_PORT = cASSANDRA_PORT;
	}
	public String getCASSANDRA_KEYSPACE() {
		return CASSANDRA_KEYSPACE;
	}
	public void setCASSANDRA_KEYSPACE(String cASSANDRA_KEYSPACE) {
		CASSANDRA_KEYSPACE = cASSANDRA_KEYSPACE;
	}
	public int getOPENTOK_API_KEY() {
		return OPENTOK_API_KEY;
	}
	public void setOPENTOK_API_KEY(int OPENTOK_API_KEY) {
		this.OPENTOK_API_KEY = OPENTOK_API_KEY;
	}
	public String getOPENTOK_SECRET_KEY() {
		return OPENTOK_SECRET_KEY;
	}
	public void setOPENTOK_SECRET_KEY(String OPENTOK_SECRET_KEY) {
		this.OPENTOK_SECRET_KEY = OPENTOK_SECRET_KEY;
	}
	
	
}
