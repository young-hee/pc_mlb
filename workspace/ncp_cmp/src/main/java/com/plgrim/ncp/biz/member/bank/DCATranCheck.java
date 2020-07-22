package com.plgrim.ncp.biz.member.bank;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.SimpleTimeZone;

import com.cvs.dcaIche.cipher.SEEDKeyGenerator;
import com.cvs.dcaIche.junmun.Iche6110Msg;
import com.cvs.dcaIche.util.utilCommon;
import com.cvs.dcaIche.util.utilLogger;
import com.cvs.dcaIche.util.utilSocket;

public class DCATranCheck
{
    private utilSocket clientSocket             = null;
    private Hashtable  htArg                    = null;

    private String     m_LOG_FILE_NAME          = "ICHE";
    private String     m_LISTEN_SERVER_IP       = null;
    private int        m_LISTEN_SERVER_PORT     = 0;
    private String     m_ICHE_LOG_FILE_ROOT     = null;
    private String     m_ICHE_MALL_CODE         = null;
    private String     m_ICHE_SUB_MALL_CODE     = null;
    private String     m_ICHE_BANK_CODE         = null;

    private boolean    m_DEBUG                  = false;
    private boolean    m_ENCRYPTION             = false;
    private byte[]     m_bSecKey                = null;

    public DCATranCheck()
    {}

    public void doTransService(Hashtable htArg, String file) throws Exception
    {
        this.htArg = htArg;
        Properties   prop = null;

        /*****************************************
        * 1. �⺻���� �ε�(mall.properties)
        ******************************************/
        try
        {
            prop = new Properties();
            FileInputStream fis = new FileInputStream( file );
            prop.load( fis );
            fis.close();

            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT,   file + " �о���̱� ����..." );

            m_LISTEN_SERVER_IP          = prop.getProperty("LISTEN_ACCOUNT_SERVER");
            m_LISTEN_SERVER_PORT        = Integer.parseInt( prop.getProperty("LISTEN_ACCOUNT_PORT") );
            m_ICHE_MALL_CODE            = prop.getProperty("ICHE_MALL_CODE");
            m_ICHE_SUB_MALL_CODE        = prop.getProperty("ICHE_SUB_MALL_CODE");
            m_ICHE_BANK_CODE            = prop.getProperty("ICHE_BANK_CODE");

            String debug                = prop.getProperty("DEBUG");
            String encryption           = prop.getProperty("ENCRYPTION");
            m_ICHE_LOG_FILE_ROOT        = prop.getProperty("ICHE_LOG_FILE_ROOT");

            if (debug.trim().equals("ON"))
                m_DEBUG = true;

            if (encryption.trim().equals("ON"))
                m_ENCRYPTION = true;

            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT,  file + " �о���̱� ��..." );

            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT,  "SERVER IP       : " + m_LISTEN_SERVER_IP );
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT,  "SERVER PORT     : " + m_LISTEN_SERVER_PORT );
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT,  "ICHE_MALL_CODE  : " +  m_ICHE_MALL_CODE );
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT,  "ICHE_SUB_MALL_CODE: " +  m_ICHE_SUB_MALL_CODE );
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT,  "ICHE_BANK_CODE  : " +  m_ICHE_BANK_CODE );

            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT,  "DEBUG           : " +  debug );
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT,  "ENCRYPTION      : " +  encryption );
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT,  "LOG ���� ���   : " +  m_ICHE_LOG_FILE_ROOT );

        }
        catch( Exception e )
        {
            htArg.put( "respCode",      "F001" );
            htArg.put( "errMsg",    file + "(��)�� �д� �� ���� �߻�" );

            utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doTransService()] Read Properties Exception ==> " + e.getMessage());
            throw e;
        }


        /*****************************************
        * 2. ��ȣȭ Ű��
        ******************************************/
        if (m_ENCRYPTION)
        {
            try
            {
                if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doTransService()] SEED KeyGenerate Try...");

                SEEDKeyGenerator skg = new SEEDKeyGenerator();
                skg.engineInit();
                m_bSecKey = skg.engineGenerateKey();

                if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doTransService()] SEED KeyGenerate Success[" + m_bSecKey + "]...");
            }
            catch( Exception e)
            {
                htArg.put( "respCode",      "K001" );
                htArg.put( "errMsg",        "��ȣȭ KEY �� �� ���� �߻�." );

                utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doTransService()] SEED KeyGenerate Exception ==> " + e.getMessage());
                throw e;
            }
        }

        
        /*****************************************
        * 3. CVS�� ���Ͽ���
        ******************************************/
        try
        {
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doTransService()] Socket Connection Try ...(" + m_LISTEN_SERVER_IP + ":" + m_LISTEN_SERVER_PORT + ")");

            if (m_ENCRYPTION)
                clientSocket = new utilSocket(m_LISTEN_SERVER_IP, m_LISTEN_SERVER_PORT, m_bSecKey, m_DEBUG, m_ICHE_LOG_FILE_ROOT);
            else
                clientSocket = new utilSocket(m_LISTEN_SERVER_IP, m_LISTEN_SERVER_PORT, m_DEBUG, m_ICHE_LOG_FILE_ROOT);

            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doTransService()] Socket Connection Success ...");

            clientSocket.setTimeout( 10 * 1000 ); // TimeOut ==> 10sec
        }
        catch( Exception e )
        {
            htArg.put( "respCode",              "S001" );
            htArg.put( "errMsg",                "CVS Server Socket ���� ����. ����� �ٽ� �õ��ϼ���." );

            utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doTransService()] Socket Connection Exception ==> " + e.getMessage());
            throw e;
        }

        /*****************************************
        * CVS�� ��ۼ���
        ******************************************/
        try
        {
            doSendIcheInfo();
            doRecvIcheInfo();
        }
        catch( Exception e )
        {
            throw e;
        }
        finally
        {
            try
            {
                if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doTransService()] Socket close ...");
                clientSocket.finalize();
            }
            catch(Exception e)
            {}
        }
        
    }

    
    public void doSendIcheInfo() throws Exception
    {
        byte[] bSend_tmp = null;
        byte[] bSend     = new byte[1024];

        if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doSendIcheInfo()] Enter ..." );

        Iche6110Msg in          = new Iche6110Msg();

        String MallCode     = utilCommon.getNullToStr((String)htArg.get( "strMallCode" ),       m_ICHE_MALL_CODE);
        String MallCode2    = utilCommon.getNullToStr((String)htArg.get( "strMallCode2" ),      m_ICHE_SUB_MALL_CODE);

        String outBankCode      = utilCommon.getNullToStr((String)htArg.get( "outBankCode" ),   "00");
        String inBankCode       = utilCommon.getNullToStr((String)htArg.get( "inBankCode" ),    "00");
        String inAccount        = utilCommon.getNullToStr((String)htArg.get( "inAccount" ),     "9999999999999999");
        String sendAmount       = utilCommon.getNullToStr((String)htArg.get( "sendAmount" ),    "0");
        String senderName       = utilCommon.getNullToStr((String)htArg.get( "senderName" ),    " ");
        String receiverName     = utilCommon.getNullToStr((String)htArg.get( "receiverName" ),  " ");

        String BankCode     = utilCommon.getNullToStr((String)htArg.get( "strBankCode" ), m_ICHE_BANK_CODE);

        String TranDate     = utilCommon.getNullToStr((String)htArg.get( "strTranDate" ), utilCommon.getDate());
        String TranTime     = utilCommon.getNullToStr((String)htArg.get( "strTranTime" ), utilCommon.getTime_HHmmss());
        String JunmunNo     = makeJunmunNo();

        
        Connection con      = null;
        Statement stmt      = null;
        ResultSet rs        = null;
        String sql_stmt     = "";  

        /*****************************************
        * 4. ������ȸ ���
        ******************************************/
        try
        {
            
            try
            {
                
                utilLogger.log(m_ICHE_LOG_FILE_ROOT, "��� ����");
                //������
                in.assignString(new String("0300"),         in.cMsg.length);                
                in.assignString(TranDate,                   in.cMsg.date);                  
                in.assignString(TranTime,                   in.cMsg.time);                  
                in.assignString(JunmunNo,                   in.cMsg.serial);                
                in.assignString(new String("0200"),         in.cMsg.type);                  
                in.assignString(new String("6110"),         in.cMsg.code);                  
                in.assignString(utilCommon.fillSpace(4),    in.cMsg.rcode);                 
                in.assignString(MallCode,                   in.cMsg.mallID1);               
                in.assignString(MallCode2,                  in.cMsg.mallID2);               
                in.assignString(BankCode,                   in.cMsg.bankCode);              
                in.assignString(utilCommon.fillSpace(6),    in.cMsg.teller);                
                in.assignString(utilCommon.fillSpace(37),   in.cMsg.filler);            
                
                //�۱��� ������ȸ
                in.assignString(inBankCode,                         in.inBankCode   );              
                in.assignString(inAccount,                          in.inAccount    );              
                in.assignString(utilCommon.fillSpace(20),   in.inCustName   );              
                in.assignString(outBankCode,                in.outBankCode  );              
                in.assignString(sendAmount,                         in.totalAmount  );              
                in.assignString(sendAmount,                 in.cashAmount   );              
                in.assignString("0000000000000",            in.checkAmount  );              
                in.assignString(utilCommon.fillSpace(1),    in.plusOrMinus  );              
                in.assignString("0000000000000",            in.balance              );              
                in.assignString(senderName,                 in.senderName   );              
                in.assignString(utilCommon.fillSpace(37),   in.filler               );
                in.assignString(utilCommon.fillSpace(50),   in.errMsg               );


                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "<<<<<<<<<<<<<<< �ڱݼ۱� ������ȸ �۽� >>>>>>>>>>>>>>>");
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "================= ����� =================");
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "���� = 300");                                                                          
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, " =[" + TranDate + "][" + TranDate.length() + "]");                                     
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, " =[" + TranTime + "][" + TranTime.length() + "]");                                     
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, " =[" + JunmunNo + "][" + JunmunNo.length() + "]");                                     
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "��Ÿ�� = 0200");                                                                     
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "���ڵ� = 6110");                                                                     
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, " =[" + utilCommon.fillSpace(4) + "][" + utilCommon.fillSpace(4).length() + "]");       
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, " =[" + MallCode + "][" + MallCode.length() + "]");                                     
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, " =[" + MallCode2 + "][" + MallCode2.length() + "]");                                   
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, " =[" + BankCode + "][" + BankCode.length() + "]");                                     
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, " =[" + utilCommon.fillSpace(6) + "][" + utilCommon.fillSpace(6).length() + "]");       
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, " =[" + utilCommon.fillSpace(37) + "][" + utilCommon.fillSpace(37).length() + "]");     
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "================= ������ =================");
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "inBankCode       =[" + inBankCode        + "][" + inBankCode.length()    + "]");    
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "inAccount        =[" + inAccount         + "][" + inAccount.length()     + "]");       
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "senderName       =[" + senderName        + "][" + senderName.length()    + "]");    
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "outBankCode      =[" + outBankCode       + "][" + outBankCode.length()   + "]"); 
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "totalAmount      =[" + sendAmount        + "][" + sendAmount.length()    + "]"); 
                if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "senderName       =[" + senderName        + "][" + senderName.length()    + "]");    


                bSend_tmp = in.toByteArray();

                for( int i = 0 ; i < bSend.length ; i++ )
                    bSend[i] = (byte)' ';

                System.arraycopy( bSend_tmp, 0, bSend, 0, bSend_tmp.length );
            }
            catch( Exception e )
            {
                htArg.put( "respCode",      "E001" );
                htArg.put( "errMsg",    "�������Դϴ�. ���ڿ��� �����Ͽ� �ֽʽÿ�." );

                utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doSendIcheInfo()] Exception ==> " + e.getMessage() );

                throw e;
            }


            /***************************************************************************
            * 5. ������ȸ���� DB�� �����ϱ�, ó����-6 ���� ����
            * �ŷ�����-TranDate, ���ȣ-JunmunNo, ����ڵ�-mallID1 �� KEY ������ �ϱ�
            * ����ڵ�� �ڱݼ۱� ��� ����� ����ڵ�(01101020)�� ��.
            * ��ݰ��´� �ӽ÷� "40049085812742"�� ��.
            * �Աݰ��´� �ӽ÷� "36812102441", ������ : �Ѹ��� ������
            ****************************************************************************/
            try
            {
                //DBó��        
                sql_stmt = "";
                //stmt.executeUpdate(sql_stmt);

            }
            catch(Exception e)
            {
                htArg.put( "respCode",      "E002" );
                htArg.put( "errMsg",    "�۽��� DBó���� ����߻�. ���ڿ��� �����Ͽ� �ֽʽÿ�." );

                if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doSendIcheInfo()] Leave ..." );

                throw e;
            }


            /*****************************************
            * 6. ������ȸ �� CVS�� ���
            ******************************************/
            try
            {
                if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doSendIcheInfo()] Send to Biz Try ...");
                utilLogger.junmunLog( m_ICHE_LOG_FILE_ROOT, m_LOG_FILE_NAME, "[DCATranCheck]-[doSendIcheInfo()] Web ===> Biz [" + new String(bSend_tmp) + "]" );
                
                if (m_ENCRYPTION)
                    clientSocket.send_Encript( bSend );
                else
                    clientSocket.send( bSend );
                
                if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doSendIcheInfo()] Send to Biz Success ...");
                if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doSendIcheInfo()] Leave ..." );
            }
            catch( Exception e )
            {
                htArg.put( "respCode",      "E003" );
                htArg.put( "errMsg",    "CVS Biz Server�� �۽� ����. ����� �ٽ� �õ��ϼ���." );

                if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doSendIcheInfo()] Leave ..." );

                throw e;
            }
        }
        catch(SQLException sqle)
        {
            utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doSendIcheInfo()] SQL �� ==> " + sql_stmt);
            utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doSendIcheInfo()] DB ���� Exception ==> " + sqle.getMessage());
        }
        catch( Exception e )
        {

            utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doSendIcheInfo()] Exception ==> " + e );
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doSendIcheInfo()] Leave ..." );

            throw e;
        }finally{

            try
            {
                if (rs   != null) rs.close();
                if (stmt != null) stmt.close();
                if (con  != null) con.close();  
            }
            catch (SQLException ignored) {}
        }
        
    }


    public void doRecvIcheInfo() throws Exception
    {
        if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doRecvIcheInfo()] Enter ..." );

        byte[]  bRecv = null;
        Iche6110Msg out  = new Iche6110Msg();


        Connection con      = null;
        Statement stmt      = null;
        ResultSet rs        = null;
        String sql_stmt     = "";  
       
        /*****************************************
        * 7. ������ȸ �� CVS���� ����
        ******************************************/
        try
        {                       
            try
            {
                if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doRecvIcheInfo()] Read from Biz Try ...");

                if (m_ENCRYPTION)
                    bRecv = clientSocket.read_Encript();
                else
                    bRecv = clientSocket.read();

                if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doRecvIcheInfo()] Read from Biz Success ...");
            }
            catch(Exception e)
            {
                htArg.put( "respCode",      "E004" );
                htArg.put( "errMsg",    "Receive Error : Biz Server�κ��� ���� ����." );

                throw e;
            }


            /*****************************************
            * 8. ������ȸ ������ null üũ
            ******************************************/
            if(bRecv == null)
            {
                utilLogger.junmunLog( m_ICHE_LOG_FILE_ROOT, m_LOG_FILE_NAME, "[DCATranCheck]-[doRecvIcheInfo()] Biz ===> Web : null");

                htArg.put( "respCode",              "E005");
                htArg.put( "errMsg",                "Receive Error : No Data !!!" );

                throw new Exception("[DCATranCheck]-[doRecvIcheInfo()] Read from Biz Data ==> null ");
            }


            utilLogger.junmunLog( m_ICHE_LOG_FILE_ROOT, m_LOG_FILE_NAME, "[DCATranCheck]-[doRecvIcheInfo()] Biz ===> Web [" + new String(bRecv) + "]" );

            out.fromByteArray(bRecv);
                
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "<<<<<<<<<<<<<<< �ڱݼ۱� ������ȸ ���� >>>>>>>>>>>>>>>");
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "================= ����� =================");
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[LENGTH]            = [" + new String(out.cMsg.length       ) + "][" + out.cMsg.length.length        + "]");
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[�ŷ�����]          = [" + new String(out.cMsg.date         ) + "][" + out.cMsg.date.length          + "]");
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[�ŷ��ð�]          = [" + new String(out.cMsg.time         ) + "][" + out.cMsg.time.length          + "]");
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[���Ϸù�ȣ]      = [" + new String(out.cMsg.serial       ) + "][" + out.cMsg.serial.length        + "]");
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[������]          = [" + new String(out.cMsg.type         ) + "][" + out.cMsg.type.length          + "]");
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[�ŷ��ڵ�]          = [" + new String(out.cMsg.code         ) + "][" + out.cMsg.code.length          + "]");
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[�����ڵ�]          = [" + new String(out.cMsg.rcode        ) + "][" + out.cMsg.rcode.length         + "]");
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[���ڵ�]            = [" + new String(out.cMsg.mallID1      ) + "][" + out.cMsg.mallID1.length       + "]");
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[��޾�ü�ڵ�]      = [" + new String(out.cMsg.mallID2      ) + "][" + out.cMsg.mallID2.length       + "]");
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[�����ڵ�]          = [" + new String(out.cMsg.bankCode     ) + "][" + out.cMsg.bankCode.length      + "]");
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[�ڶ�]              = [" + new String(out.cMsg.teller       ) + "][" + out.cMsg.teller.length        + "]");
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[FILLER]            = [" + new String(out.cMsg.filler       ) + "][" + out.cMsg.filler.length        + "]");
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "================= ������ =================");
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "inBankCode   =[" + new String(out.inBankCode )       + "][" + out.inBankCode.length  + "]");    
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "inAccount    =[" + new String(out.inAccount  )       + "][" + out.inAccount.length   + "]");       
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "inCustName   =[" + new String(out.inCustName )       + "][" + out.inCustName.length  + "]");    
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "outBankCode  =[" + new String(out.outBankCode)       + "][" + out.outBankCode.length + "]"); 
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "totalAmount  =[" + new String(out.totalAmount)       + "][" + out.totalAmount.length + "]"); 
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "cashAmount   =[" + new String(out.cashAmount )       + "][" + out.cashAmount.length  + "]");    
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "checkAmount  =[" + new String(out.checkAmount)       + "][" + out.checkAmount.length + "]"); 
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "plusOrMinus  =[" + new String(out.plusOrMinus)       + "][" + out.plusOrMinus.length + "]"); 
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "balance              =[" + new String(out.balance    )       + "][" + out.balance.length     + "]");             
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "senderName   =[" + new String(out.senderName )       + "][" + out.senderName.length  + "]");    
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "errMsg               =[" + new String(out.errMsg )           + "][" + out.errMsg.length              + "]");    
            
            String strErr      = new String(out.cMsg.rcode          );
            String ErrMsg      = new String(out.errMsg              );
            String receiverName= new String(out.inCustName          );
            
            if (strErr.equals("0000"))
            {
                ErrMsg = "����ó��";
            }

            htArg.put( "respCode",          strErr       != null ? strErr       : "" );
            htArg.put( "errMsg",                    ErrMsg       != null ? ErrMsg       : "" );
            htArg.put( "receiverName",      receiverName != null ? new String(out.inCustName )  : "" );


            /*************************************************************************
            * 9. ��� DB�� ������ȸ���� UPDATE �ϱ�
            * �ŷ�����-new String(out.cMsg.date), ���ȣ-new String(out.cMsg.serial), 
            * ����ڵ�-new String(out.cMsg.mallID1) �� KEY ������ �ϱ�
            * ������ȸ ó�����, ó���Ϸ�ð� ��
            **************************************************************************/
            try
            {
                //1. ��ŷ��� ���������Ѵ�  / �ŷ�����, ���ȣ, ����ڵ�
                sql_stmt = "" ;

                //if ( stmt.executeUpdate(sql_stmt) != 1 )
                //{
                //    htArg.put( "respCode",          "E006");
                //    htArg.put( "errMsg",                    "CVS���� ������ �ް�, DB ó���� ��ŷ��� �������� �ʽ��ϴ�. ���ڿ��� ������ �ֽʽÿ�!!!" );

                //    throw new Exception("��ŷ��� �������� ���� ");
                //}
            }
            catch(Exception e)
            {
                htArg.put( "respCode",              "E007");
                htArg.put( "errMsg",                "������ȸ ������� DB ó�� ���� !!!" );
                throw e;        
            }
                
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doRecvIcheInfo()] Leave ..." );
        }
        catch(SQLException sqle)
        {
            utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doRecvIcheInfo()] SQL �� ==> " + sql_stmt);
            utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doRecvIcheInfo()] DB ���� Exception ==> " + sqle.getMessage());
        }
        catch( Exception e )
        {

            utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doRecvIcheInfo()] Exception ==> " + e );
            if (m_DEBUG) utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[doRecvIcheInfo()] Leave ..." );

            throw e;
        }finally{

            try
            {
                if (rs   != null) rs.close();
                if (stmt != null) stmt.close();
                if (con  != null) con.close();
            }
            catch (SQLException ignored) {}
        }
    }



    public synchronized String makeJunmunNo()
    {
        final int  millisPerHour = 60 * 60 * 1000;
        SimpleDateFormat timeHHMMSS = new SimpleDateFormat("HHmmss");
        SimpleTimeZone   timeZone   = new SimpleTimeZone( 9 * millisPerHour, "KST");
        timeHHMMSS.setTimeZone(timeZone);

        Date   d         = new Date();
        String first6    = timeHHMMSS.format(d);
        Long   l         = new Long(d.getTime());
        String strTemp   = l.toString();
        String second5   = strTemp.substring(6,11);
        Double dblRandom = new Double(Math.floor(Math.random() * 10));
        String last1     = Integer.toString(dblRandom.intValue());

        try
        {
            Thread.sleep(10);
        }
        catch(InterruptedException ie)
        {
            if (m_DEBUG) utilLogger.log(m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[makeJunmunNo()] InterruptedException ==> " + ie);
            utilLogger.log( m_ICHE_LOG_FILE_ROOT, "[DCATranCheck]-[makeJunmunNo()] InterruptedException ==> " + ie);
        }

        return first6 + second5 + last1;
    }
}