/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.8
 * Generated at: 2018-06-18 10:59:50 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member.popup;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class certification_nice_complete_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(14);
    _jspx_dependants.put("/WEB-INF/views/include/jsp-header.jspf", Long.valueOf(1528789763693L));
    _jspx_dependants.put("/WEB-INF/tld/functions.tld", Long.valueOf(1527236566433L));
    _jspx_dependants.put("jar:file:/C:/work/libs/jstl-1.2.jar!/META-INF/fmt.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("jar:file:/C:/work/libs/jstl-1.2.jar!/META-INF/c-1_0-rt.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("jar:file:/C:/work/libs/spring-security-taglibs-4.0.0.RELEASE.jar!/META-INF/security.tld", Long.valueOf(1427282480000L));
    _jspx_dependants.put("jar:file:/C:/work/libs/tiles-jsp-3.0.5.jar!/META-INF/tld/tiles-jsp.tld", Long.valueOf(1411309932000L));
    _jspx_dependants.put("jar:file:/C:/work/libs/spring-webmvc-4.1.6.RELEASE.jar!/META-INF/spring-form.tld", Long.valueOf(1427241226000L));
    _jspx_dependants.put("jar:file:/C:/work/libs/spring-webmvc-4.1.6.RELEASE.jar!/META-INF/spring.tld", Long.valueOf(1427241226000L));
    _jspx_dependants.put("/WEB-INF/lib/spring-security-taglibs-4.0.0.RELEASE.jar", Long.valueOf(1526264865197L));
    _jspx_dependants.put("/WEB-INF/lib/spring-webmvc-4.1.6.RELEASE.jar", Long.valueOf(1526264955085L));
    _jspx_dependants.put("/WEB-INF/lib/tiles-jsp-3.0.5.jar", Long.valueOf(1526264975933L));
    _jspx_dependants.put("jar:file:/C:/work/libs/jstl-1.2.jar!/META-INF/fn.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1526264945904L));
    _jspx_dependants.put("/WEB-INF/tld/taglib.tld", Long.valueOf(1527236566433L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, false, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      out = pageContext.getOut();
      _jspx_out = out;


	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	if (request.getProtocol().equals("HTTP/1.1")) {
		response.setHeader("Cache-Control", "no-cache");
	}
	com.plgrim.ncp.framework.utils.JSPEnvHelper.setDomainVariables(request, pageContext);

      out.write("<script language=\"JavaScript\">\n");
      out.write("\t//window.onload 는 $(document).ready 보다 뒤에 실행됨.\n");
      out.write("\twindow.onload = function() {\n");
      out.write("\t\tend();\n");
      out.write("\t}\n");
      out.write("    function end() {\n");
      out.write("        //휴대폰인증 실패의 경우 result 가 'N'\n");
      out.write("        var certResult = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${certificationResult}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("';\n");
      out.write("        if (\"Y\" != certResult) {\n");
      out.write("            self.close();\n");
      out.write("            return;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        var checkCertMbr = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${checkCertMbr}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("';\n");
      out.write("        checkCertMbr = (checkCertMbr != '' ? $.parseJSON(checkCertMbr) : '');\n");
      out.write("\n");
      out.write("        var checkMemberName = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${checkMemberName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("';\n");
      out.write("        checkMemberName = (checkMemberName != '' ? $.parseJSON(checkMemberName) : '');\n");
      out.write("\n");
      out.write("        var srv = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${srv}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("';\n");
      out.write("        srv = (srv != '' ? $.parseJSON('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${srv}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("') : '');\n");
      out.write("        var is14 = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${is14}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("';\n");
      out.write("        is14 = (is14 != '' ? $.parseJSON('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${is14}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("') : false);\n");
      out.write("\n");
      out.write("        // 회원 정보 전화번호와 인증받은 전화번호가 다를 경우 false\n");
      out.write("        var mobileNoCheck = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mobileNoCheck}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("';\n");
      out.write("        mobileNoCheck = (mobileNoCheck != '' ? $.parseJSON(mobileNoCheck) : true);\n");
      out.write("\n");
      out.write("        var pccMobile = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pccMobile}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("';\n");
      out.write("        pccMobile = (pccMobile != '' ? $.parseJSON(pccMobile) : '');\n");
      out.write("\n");
      out.write("        var memberFailERPIF = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${memberFailERPIF}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("';\n");
      out.write("        var joinPossibility = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${joinPossibility}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("';\n");
      out.write("        var possibilityCode = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${possibilityCode}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("';\n");
      out.write("        \n");
      out.write("        debugger;\n");
      out.write("        \n");
      out.write("        if(memberFailERPIF != undefined && memberFailERPIF == \"true\") {\n");
      out.write("        \talert( \"현재 연동 서비스 제공이 원활하지 못해\\n일시적으로 진행이 불가합니다.\\n이용에 불편을 드려 죄송합니다.\" );\n");
      out.write("        }\n");
      out.write("        else {\n");
      out.write("\t        try {\n");
      out.write("\t\t\t\tif('newjoin' === srv) {\n");
      out.write("\t\t\t\t\twindow.opener.callbackCertifyJoinMember(checkCertMbr, is14, joinPossibility, possibilityCode);\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t\telse if('find' === srv) {\n");
      out.write("\t\t\t\t\twindow.opener.callbackCertifyFindMember(checkCertMbr);\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t} catch (e) {\n");
      out.write("\t\t\t\talert(\"※보안 수준 변경 필요\\n->인터넷 옵션 설정\\n->보안 탭 이동\\n->인터넷 또는 로컬 인트라넷 아이콘 click\\n->보호 모드 사용 check or uncheck\\n->브라우져 재시작\");\n");
      out.write("\t\t\t}\n");
      out.write("\t    }\n");
      out.write("\n");
      out.write("\t\twindow.opener = 'self';\n");
      out.write("\t\twindow.open('', '_parent', '');\n");
      out.write(" \t\twindow.close();\n");
      out.write("    }\n");
      out.write("</script>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}