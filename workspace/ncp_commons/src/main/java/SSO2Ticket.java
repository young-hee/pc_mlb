

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.plgrim.ncp.commons.AuthenticationComponent;

import lombok.extern.slf4j.Slf4j;

/**
 * EP SSO Ticket
 *
 * <p>
 *
 * <ul>
 * </ul>.
 *
 * @author 
 * @since 2016. 7. 22
 */
@Slf4j
public class SSO2Ticket   {
	
	@Autowired
    static AuthenticationComponent authenticationComponent;

	public static final int ISSUER_CERT_SUBJECT    = 0;
    public static final int ISSUER_CERT_ISSUER     = 1;
    public static final int ISSUER_CERT_SERIALNO   = 2;
    public static final int ISSUER_CERT_SUMMARY    = 3;
    public static final int ISSUER_CERT_SERIAL     = 4;
    public static final int ISSUER_CERT_VALIDITY   = 5;
    public static final int ISSUER_CERT_FINGERPRINT= 6;
    public static final int ISSUER_CERT_ALGID      = 7;
    public static final int ISSUER_CERT_ALL        = 8;
		
    private static boolean initialized = false;
    public static String SECLIBRARY ;
    public static String SSO2TICKETLIBRARY = "erpssoext";
    
    
    
    static {
        if (System.getProperty("os.name").startsWith("Win"))  {
            SECLIBRARY = "erpsecu.dll";       /* Windows */
        } else if (System.getProperty("os.name").startsWith("Mac"))  {
            SECLIBRARY = "liberpcrypto.jnilib"; /* Mac */
        } else if (System.getProperty("os.name").startsWith("HP"))  {
            if (false == System.getProperty("os.arch").startsWith("IA64")) {
                SECLIBRARY = "liberpcrypto.sl"; /* HP RISC */
            } else {
            	SECLIBRARY = "liberpcrypto.so"; /* HP IA64 */
            }
        } else {
            SECLIBRARY = "liberpcrypto.so";     /* Default for Linux/Unix */
        }
        try {
            System.loadLibrary(SSO2TICKETLIBRARY);  
            log.debug("ERPSSOEXT loaded.");
        } catch (Throwable e) {
            log.debug ("Error during initialization of SSO2TICKET:\n" + e.getMessage());
        }
        log.debug("static part ends.\n");
    }
    
    /**
     * SSO2Ticket class constructor
     */
    public SSO2Ticket()
    {
        init(SECLIBRARY);
    }

    /**
     * SSO2Ticket class constructor
     * 
     * @param seclib location of PLGRIM-implementation
     * 
     */
    public SSO2Ticket(String seclib)
    {
        init(SECLIBRARY);
    }

    
    /**
     * Initialization
     * 
     * @param seclib location of plgrim-implemenation
     * 
     * @return true/false whether initailisation was ok
     */
    public static native synchronized boolean init(String seclib);

    /**
     * Returns internal version.
     * 
     * @return version
     */
    public static native synchronized String getVersion();
    
    /**
     * eval logon ticket
     * 
     * @param ticket        the ticket
     * @param pab           location of pab
     * @param pab_password  password for access the pab
     * 
     * @return Object array with:
     *         [0] = (String)user, [1] = (String)sysid, [2] = (String)client , [3] = (byte[])certificate
     *         [4] = (String)portalUser, [5] = (String)authSchema, [6] = validity
     *  
     */
    public static native synchronized Object[] evalLogonTicket(
        String ticket,
        String pab,
        String pab_password)
        throws Exception;
    
    /**
     * eval assertion ticket
     * 
     * @param ticket        the ticket
     * @param pab           location of pab
     * @param pab_password  password for access the pab
     * @param my_sysid      Own System Id
     * @param my_client     Own System Client
     * 
     * @return Object array with:
     *         [0] = (String)user, [1] = (String)sysid, [2] = (String)client , [3] = (byte[])certificate
     *         [4] = (String)portalUser, [5] = (String)authSchema, [6] = validity
     *  
     */
    public static native synchronized Object[] evalAssertionTicket(
        String ticket,
        String pab,
        String pab_password,
        String my_sysid,
        String my_client)
        throws Exception;    

    /**
     * create a new assertion ticket
     * @param my_sysid      own system id
     * @param my_client     own system client
     * @param pab           location of pab
     * @param pab_password  password for access the pab
     * @param ext_sysid     Recipient System Id
     * @param ext_client    Recipient System Client
     * @param erp_user      ERP user in recipient system
     * @param language      ERP language
     * @param portal_user   Portal user in recipient system
     * @param auth_schema   Authentication Schema
     * 
     * @return String of new Assetion Ticket
     *  
     */
    public static native synchronized String createAssertionTicket(
        String my_sysid,
        String my_client,
        String pab,
        String pab_password,
        String ext_sysid,
        String ext_client,
        String erp_user,
        String language,
        String portal_user,
        String auth_schema)
        throws Exception;

    /**
     * Load a key file into the memory of ERPSSOEXT
     * @param keyFile   	 byte array of key
     * @param key_password	 password of key
     * @param index              index of key if several keys in keyFile
     * @param type               type of key
     * 
     * @return true/false whether load key was ok
     *  
     */
    public static native synchronized boolean loadKey( byte[] keyFile, String key_password, int index , int type );
        

    /**
     * Parse certificate
     * @param cert 			Certificate received from evalLogonTicket
     * @param info_id       One of the requst id큦
     * 
     * @return Info string from certificate
     *  
     */
    public static native synchronized String parseCertificate(
        byte[] cert,
        int info_id);
        
    /**
     * Get ERPSSOEXT property
     * @param name   property name to be retrieved
     * 
     * @return property string from ERPSSOEXT
     *  
     */
    public static native synchronized String getProperty( String name );
    
    /**
     * Set ERPSSOEXT property
     * @param name   property name to be set
     * @param value  property value to be set
     * 
     * @return true/false whether set was ok
     *  
     */
    public static native synchronized boolean setProperty( String name, String value );
    
        
    public static void main(String[] args) throws Exception
    {
		byte[] certificate;
		byte[] keyfile;
		String ticket;
		String pab;
		String pwd;
		String crt;
		String plgrim_library;
		
		try {			
			// plausi check
			if(getCommandParam(args,"-i") == null)
			{
				PrintHelp();
				return;
			}

	        log.debug("Start SSO2TICKET main");        
	        log.debug("-------------- test version --------------");
	        String version =SSO2Ticket.getVersion();
	        log.debug("Version of ERPSSOEXT: " + version);
	        // read ticket into a String
        	ticket = getTicket(getCommandParam(args,"-i"));
        	// get PAB (public key) of issuing system
        	pab = getFullFilePath(getCommandParam(args,"-p"));
        	crt = getCommandParam(args,"-crt");
        	if(pab == null && crt == null) {
        	   PrintHelp();
        	   return;	
        	} else {
        	   if(pab != null)
        	      keyfile = getBytesFromFile(pab);
        	   else
        	      keyfile = getBytesFromFile(crt);
        	}
        	// get PSE password
        	pwd = getCommandParam(args,"-pwd");
	        // init erpsecu library
	        plgrim_library = getCommandParam(args,"-L");
	        if(plgrim_library==null)
	          plgrim_library = SECLIBRARY;
		
		if(getCommandParam(args,"-t")!=null) {
	           setProperty("ERP_EXT_TRC", getCommandParam(args,"-t"));
	           setProperty("ERP_EXT_TRL", getCommandParam(args,"-l"));
		}	   
	        if( !init(plgrim_library)) {
	        	log.debug ("Could not load library: " + plgrim_library);
	        	return;
	        }
	        // load ticket key
	        loadKey(keyfile,pwd,0,pab!=null?0:1);
	        // create assertion ticket
	        if(getCommandParam(args,"-c")!=null) {
	           ticket = createAssertionTicket("EXT", "999", null, null,
	                                          getProperty("ERP_EXT_SYSID"),
	                                          getProperty("ERP_EXT_CLIENT"),
	                                          "ERPUSER", "E", "PORTALUSER", "basicauthentication");
	           BufferedWriter out = new BufferedWriter(new FileWriter(getCommandParam(args,"-c")));	  
	           out.write(ticket);
	           out.close();                                        
	        }	        
		// evaluate the ticket
		Object o[] = evalLogonTicket(ticket, null , null);

		// use 3rd object to analyse the certificate
                if (o[3] != null &&
                    o[3] instanceof byte[]
               ) {     
                   certificate = (byte[])o[3];          		
                   //log.debug("Certificate length     : " + certificate.length + " bytes");
                   /*
                    * remark: The "certificate" object is a DER encoded X.509 certificate
                    *         of the issuing system, which can be parsed/analysed with JAVA  
                    *         funtionality e.g. Java Cryptography Architecture API, IAIK and so on.
                    */
            }// or
            
            // print out all parameters received from ERPSSOEXT
            PrintResults((String)o[0],
            			 (String)o[1],
            			 (String)o[2],
						  parseCertificate((byte[])o[3],ISSUER_CERT_SUBJECT),
						  parseCertificate((byte[])o[3],ISSUER_CERT_ISSUER),
						  ticket,
						  (String)o[4],
						  (String)o[5],
						  (String)o[6]);
            
        } catch (Exception e) {
            log.debug("", e);
        } catch (Throwable te) {
              log.debug("", te);
        }
    }
    
	// print the parameters from ticket
	static void PrintResults(String user, String sysid, String client, 
	String subject, String issuer, String ticket, String prtUsr, String authS, String validity) 
	{
		log.debug("***********************************************");
		log.debug(" Output of program:");
		log.debug("***********************************************");
		log.debug("\n");
		log.debug("The ticket\n\n" + ticket + "\n");
		log.debug("was successfully validated.");
		log.debug("User     : " + user);
		log.debug("Ident of ticket issuing system:");
		log.debug("Sysid    : " + sysid);
		log.debug("Client   : " + client);
		log.debug("External ident of user:");
		log.debug("PortalUsr: " + prtUsr);
		log.debug("Auth     : " + authS);
		log.debug("Ticket validity in seconds:");
		log.debug("Valid (s): " + validity);
		log.debug("Certificate data of issuing system:");
		log.debug("Subject  : " + subject);
		log.debug("Issuer   : " + issuer);
		log.debug("\n");
	}
	
	// read the ticket string from a File
	public static String getTicket(String filename) 
	throws FileNotFoundException
	{
		try {
		   BufferedReader in = new BufferedReader(new FileReader(filename));
		   String str;
		   StringBuffer strBuffer = new StringBuffer();
		   while ((str = in.readLine()) != null) {
		   	strBuffer.append(str);
		   }
		   in.close(); 
		   return strBuffer.toString();
		}
		catch (Exception e) 
		{
			// Let the user know what went wrong.
			log.debug("The file could not be read:");
			log.debug(e.getMessage());
			throw new FileNotFoundException("File "+ filename +" could not be read");
		}
            
	}
		
	// read the key file 
	public static byte[] getBytesFromFile(String filename) throws IOException {
	        File file = new File(filename);
	        InputStream is = new FileInputStream(file);
	    
	        // Get the size of the file
	        long length = file.length();
	    
	        if (length > Integer.MAX_VALUE) {
	            // File is too large
	        }
	    
	        // Create the byte array to hold the data
	        byte[] bytes = new byte[(int)length];
	    
	        // Read in the bytes
	        int offset = 0;
	        int numRead = 0;
	        while (offset < bytes.length
	               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	            offset += numRead;
	        }
	    
	        // Ensure all the bytes have been read in
	        if (offset < bytes.length) {
	            throw new IOException("Could not completely read file "+file.getName());
	        }
	    
	        // Close the input stream and return bytes
	        is.close();
	        return bytes;
	}	
	
	// parse the arguments for an option
	static String getCommandParam(String[] args, String option)
	{
		for(int i=0; i<args.length; i++) 
		{
			if(args[i].equals(option) && args.length > i+1)
			{
				return args[i+1];
			}
		}
		return null;
	}
	
	// print help to console
	static void PrintHelp()
	{
		log.debug("   java SSO2Ticket -i <ticket_file> [-c ticket (create assertion ticket)] ");
		log.debug("   [-L <PLGRIM_LIB>] [-p <file containing PSE>] [-pwd <PSE password>]");
		log.debug("   [-crt <file containing public certificate>]  "); 
		log.debug("   [-t <developer trace file>] [-l <level of trace (1, 2 or 3)>]");         
	}
	
	// get the full path to a file
	static String getFullFilePath(String filename) 
	throws FileNotFoundException
	{
		if(filename==null)
		   return null;
		String path;
		File file = new File(filename);

		if( file.getAbsolutePath().toLowerCase().indexOf(".pse") > 0 )
		{
			path = file.getAbsolutePath();
		}
		else 
		{
			path = file.getAbsolutePath() + ".pse";
		}
		if( ! new File(path).exists() )
			throw new FileNotFoundException("File "+ filename +" does not exists");
		return path;            
	}
}