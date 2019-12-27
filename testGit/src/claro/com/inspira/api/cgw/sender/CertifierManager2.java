// 

// 

package claro.com.inspira.api.cgw.sender;

import microsoft.exchange.webservices.data.core.EwsSSLProtocolSocketFactory;
import claro.com.inspira.exception.CGWException;
import java.io.FileNotFoundException;
import javax.net.ssl.HttpsURLConnection;
import java.io.FileInputStream;
import org.apache.commons.lang3.StringUtils;
import javax.net.ssl.TrustManager;
import java.io.InputStream;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import java.security.KeyStore;
import javax.net.ssl.TrustManagerFactory;

public class CertifierManager2
{
    public static void loadCert() {
        try {
            System.setProperty("javax.net.ssl.trustStore", "/usr/java/jdk1.8.0_144/jre/lib/security/cacerts");
            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
            System.out.println("seted trustStore");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void loadCert(final boolean local) {
        try {
            System.setProperty("javax.net.ssl.trustStore", "C:/tmp/DemoTrust.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "DemoTrustKeyStorePassPhrase");
            System.out.println("seted trustStore");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void setTrustStore(final String trustStore, final String password) throws Exception {
        System.out.println("loading trust store");
        final TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
        final KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        final InputStream keystoreStream = CertifierManager2.class.getResourceAsStream(trustStore);
        keystore.load(keystoreStream, password.toCharArray());
        trustManagerFactory.init(keystore);
        final TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        final SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustManagers, null);
        SSLContext.setDefault(sc);
        System.out.println("loaded trust store");
    }
    
    public static SSLContext setSecureContext(final String RESOURCE_PATH_TO_KEYSTORE, final String MY_KEYSTORE_PASSWORD) throws FileNotFoundException {
        final char[] keystorepass = MY_KEYSTORE_PASSWORD.toCharArray();
        if (StringUtils.isBlank((CharSequence)new String(keystorepass))) {
            System.err.println("Could not read password for configured keystore!");
        }
        final InputStream keystoreFile = new FileInputStream("/var/certificados/DemoTrust.jks");
        if (keystoreFile == null) {
            System.err.println("Could not read the configured keystore file at " + RESOURCE_PATH_TO_KEYSTORE);
        }
        try {
            final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(keystoreFile, keystorepass);
            final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, tmf.getTrustManagers(), new SecureRandom());
            SSLContext.setDefault(sslContext);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            return sslContext;
        }
        catch (Exception e) {
            System.err.println("Error creating context for SSLSocket!");
            return null;
        }
    }
    
    public SSLContext createEwsContextSSL(final String RESOURCE_PATH_TO_KEYSTORE, final String MY_KEYSTORE_PASSWORD) throws Exception {
        final char[] keystorepass = MY_KEYSTORE_PASSWORD.toCharArray();
        if (StringUtils.isBlank((CharSequence)new String(MY_KEYSTORE_PASSWORD))) {
            System.err.println("Could not read password for configured keystore!");
        }
        final InputStream keystoreFile = CertifierManager2.class.getResourceAsStream(RESOURCE_PATH_TO_KEYSTORE);
        if (keystoreFile == null) {
            System.err.println("Could not read the configured keystore file at " + RESOURCE_PATH_TO_KEYSTORE);
            throw new CGWException("Could not read the configured keystore file at " + RESOURCE_PATH_TO_KEYSTORE);
        }
        try {
            final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(keystoreFile, keystorepass);
            final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);
            final SSLContext sslContext = EwsSSLProtocolSocketFactory.build(tmf.getTrustManagers()[0]).getContext();
            SSLContext.setDefault(sslContext);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            return sslContext;
        }
        catch (Exception e) {
            System.err.println("Error creating context for SSLSocket!");
            throw e;
        }
    }
}
