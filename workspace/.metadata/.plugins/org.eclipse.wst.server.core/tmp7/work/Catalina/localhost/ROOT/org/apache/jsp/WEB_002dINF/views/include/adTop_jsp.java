/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.8
 * Generated at: 2020-07-21 04:45:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.include;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class adTop_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(14);
    _jspx_dependants.put("/WEB-INF/views/include/jsp-header.jspf", Long.valueOf(1575853205981L));
    _jspx_dependants.put("/WEB-INF/tld/functions.tld", Long.valueOf(1575853204655L));
    _jspx_dependants.put("jar:file:/C:/work/libs/jstl-1.2.jar!/META-INF/fmt.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("jar:file:/C:/work/libs/jstl-1.2.jar!/META-INF/c-1_0-rt.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("jar:file:/C:/work/libs/spring-security-taglibs-4.0.0.RELEASE.jar!/META-INF/security.tld", Long.valueOf(1427282480000L));
    _jspx_dependants.put("jar:file:/C:/work/libs/tiles-jsp-3.0.5.jar!/META-INF/tld/tiles-jsp.tld", Long.valueOf(1411309932000L));
    _jspx_dependants.put("jar:file:/C:/work/libs/spring-webmvc-4.1.6.RELEASE.jar!/META-INF/spring-form.tld", Long.valueOf(1427241226000L));
    _jspx_dependants.put("jar:file:/C:/work/libs/spring-webmvc-4.1.6.RELEASE.jar!/META-INF/spring.tld", Long.valueOf(1427241226000L));
    _jspx_dependants.put("/WEB-INF/lib/spring-security-taglibs-4.0.0.RELEASE.jar", Long.valueOf(1575852743335L));
    _jspx_dependants.put("/WEB-INF/lib/spring-webmvc-4.1.6.RELEASE.jar", Long.valueOf(1575852743368L));
    _jspx_dependants.put("/WEB-INF/lib/tiles-jsp-3.0.5.jar", Long.valueOf(1575852743380L));
    _jspx_dependants.put("jar:file:/C:/work/libs/jstl-1.2.jar!/META-INF/fn.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1575852743322L));
    _jspx_dependants.put("/WEB-INF/tld/taglib.tld", Long.valueOf(1575853204665L));
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

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

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
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
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
		response.setHeader("Cache-Control", "no-cach, no-store, must-revalidatee");
	}
	com.plgrim.ncp.framework.utils.JSPEnvHelper.setDomainVariables(request, pageContext);

      out.write("<div id=\"targetGateScriptLoader\"></div>\n");
      out.write("<div id=\"criteoScriptLoader\"></div>\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("function fnf_appendTargetGateScript(){\n");
      out.write("\t/* var targetGateTag = document.createElement(\"script\");\n");
      out.write("\ttargetGateTag.type=\"text/javascript\";\n");
      out.write("\ttargetGateTag.async=true;\n");
      out.write("\ttargetGateTag.src=\"//cdn-aitg.widerplanet.com/js/wp_astg_4.0.js\";\n");
      out.write("\t$(\"#targetGateScriptLoader\").append(targetGateTag); */\n");
      out.write("}\n");
      out.write("function fnf_appendCriteoScript(){\n");
      out.write("\tvar criteoTag = document.createElement(\"script\");\n");
      out.write("\tcriteoTag.type=\"text/javascript\";\n");
      out.write("\tcriteoTag.async=true;\n");
      out.write("\tcriteoTag.src=\"//static.criteo.net/js/ld/ld.js\";\n");
      out.write("\t\n");
      out.write("\t$.each(window.criteo_q, function(index, criteodata) {\n");
      out.write("\t\tif(criteodata.event=='viewList'){\n");
      out.write("\t\t\tif(criteodata.item.length==0){\n");
      out.write("\t\t\t\treturn;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\tif(criteodata.item.length>3){\n");
      out.write("\t\t\t\tcriteodata.item = criteodata.item.slice(0,3);\n");
      out.write("\t\t\t}\n");
      out.write("\t\t}\n");
      out.write("\t});\n");
      out.write("\t$(\"#criteoScriptLoader\").append(criteoTag);\n");
      out.write("}\n");
      out.write("function fnf_appendMobonScript(){\n");
      out.write("\t/* var rf = new EN();\n");
      out.write("\trf.setSSL(true);\n");
      out.write("\trf.sendRf(); */\n");
      out.write("}\n");
      out.write("</script>\n");
      out.write("<script>\n");
      out.write("\t!function(f,b,e,v,n,t,s)\n");
      out.write("\t{if(f.fbq)return;n=f.fbq=function(){n.callMethod?\n");
      out.write("\tn.callMethod.apply(n,arguments):n.queue.push(arguments)};\n");
      out.write("\tif(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';\n");
      out.write("\tn.queue=[];t=b.createElement(e);t.async=!0;\n");
      out.write("\tt.src=v;s=b.getElementsByTagName(e)[0];\n");
      out.write("\ts.parentNode.insertBefore(t,s)}(window,document,'script',\n");
      out.write("\t'https://connect.facebook.net/en_US/fbevents.js');\n");
      out.write("\tfbq('init', '2420314948195781');\n");
      out.write("\tfbq('init', '396021361339657'); \n");
      out.write("\tfbq('track', 'PageView');\n");
      out.write("</script>\n");
      out.write("<noscript>\n");
      out.write("\t<img height=\"1\" width=\"1\" src=\"https://www.facebook.com/tr?id=2420314948195781&ev=PageView&noscript=1\"/>\n");
      out.write("\t<img height=\"1\" width=\"1\" src=\"https://www.facebook.com/tr?id=396021361339657&ev=PageView&noscript=1\"/>\n");
      out.write("</noscript>\n");
      out.write("<script type=\"text/javascript\" src=\"//wcs.naver.net/wcslog.js\"></script>\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("function fnf_naverKeywordAdvertisement(val,sumAmount){\n");
      out.write("\tif (!wcs_add) var wcs_add={};\n");
      out.write("\twindow.wcs_add = {wa:'s_1a7fdf177b81'};\n");
      out.write("\n");
      out.write("\tif (!_nasa) var _nasa={};\n");
      out.write("\t\n");
      out.write("\tif(val){\n");
      out.write("\t\t_nasa[\"cnv\"] = wcs.cnv(val,sumAmount); // 전환유형, 전환가치 설정해야함. 설치매뉴얼 참고else{}\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("\twcs.inflow(\"mlb-korea.com\");\n");
      out.write("\twcs_do(_nasa);\n");
      out.write("}\n");
      out.write("</script>\n");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      out.write("<script>\n");
      out.write("\t(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n");
      out.write("\t(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n");
      out.write("\tm=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n");
      out.write("\t})(window,document,'script','//www.google-analytics.com/analytics.js','ga');\n");
      out.write("\tga('create', 'UA-29462318-1', 'mlb-korea.com');\n");
      out.write("\tga('send', 'pageview');\n");
      out.write("\tga('create', 'UA-29462318-4', 'auto', 'newTracker');\n");
      out.write("\tga('newTracker.send', 'pageview');\n");
      out.write("</script>\n");
      out.write("<!-- Global site tag (gtag.js) - Google Ads: 840871019 -->\n");
      out.write("<script async src=\"https://www.googletagmanager.com/gtag/js?id=AW-840871019\"></script>\n");
      out.write("<script>\n");
      out.write("  window.dataLayer = window.dataLayer || [];\n");
      out.write("  function gtag(){dataLayer.push(arguments);}\n");
      out.write("  gtag('js', new Date());\n");
      out.write("\n");
      out.write("  gtag('config', 'AW-840871019');\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("\n");
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

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    boolean _jspx_th_c_005fif_005f0_reused = false;
    try {
      _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f0.setParent(null);
      // /WEB-INF/views/include/adTop.jsp(82,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty goods.godEx.godNo}", boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null)).booleanValue());
      int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
      if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("<iframe id=\"hfrADCheck\" src=\"//adcheck.about.co.kr/mad/prd/view?shopid=mlb\" scrolling=\"no\" frameborder=\"0\" width=\"0\" height=\"0\" style=\"display: none;\"></iframe>\n");
          int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      _jspx_th_c_005fif_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fif_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fif_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    boolean _jspx_th_c_005fif_005f1_reused = false;
    try {
      _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f1.setParent(null);
      // /WEB-INF/views/include/adTop.jsp(85,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty goods.godEx.godNo}", boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null)).booleanValue());
      int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
      if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("<iframe id=\"hfrADCheck\" src=\"//adcheck.about.co.kr/mad/prd/view?shopid=mlb&goodscode=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${goods.godEx.godNo }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
          out.write("\" scrolling=\"no\" frameborder=\"0\" width=\"0\" height=\"0\" style=\"display: none;\"></iframe>\n");
          int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      _jspx_th_c_005fif_005f1_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fif_005f1, _jsp_getInstanceManager(), _jspx_th_c_005fif_005f1_reused);
    }
    return false;
  }
}
