/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.8
 * Generated at: 2019-12-18 00:56:02 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.errors;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class _404_005fnotFounded_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(14);
    _jspx_dependants.put("/WEB-INF/views/include/jsp-header.jspf", Long.valueOf(1575853134310L));
    _jspx_dependants.put("/WEB-INF/tld/functions.tld", Long.valueOf(1575853133336L));
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
    _jspx_dependants.put("/WEB-INF/tld/taglib.tld", Long.valueOf(1575853133348L));
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

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody;

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
    _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody.release();
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

      out.write("<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("$(function () {\n");
      out.write("\tvar state = 0;\n");
      out.write("\tsetInterval(function(){\n");
      out.write("      $('.error').css('background-position-x', state);\n");
      out.write("\t  ( state >= 1920 ) ? state = 0 : state++;\n");
      out.write("\t},170);\n");
      out.write("});\n");
      out.write("</script>\n");
      out.write("<link href=\"https://fonts.googleapis.com/earlyaccess/notosanskr.css\" rel=\"stylesheet\">\n");
      out.write("<style>\n");
      out.write("html {height:100%;}\n");
      out.write("body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, code, form, fieldset, legend, textarea, p, blockquote, th, td, input, select, textarea, button {margin:0; padding:0;}\n");
      out.write("fieldset, img {border:0 none;}\n");
      out.write("dl, ul, ol, menu, li {list-style:none;}\n");
      out.write("blockquote, q {quotes:none;}\n");
      out.write("blockquote:before, blockquote:after, q:before, q:after {content:''; content: none;}\n");
      out.write("input, select, textarea, button {font-size:100%; vertical-align:middle;}\n");
      out.write("button {border:0 none; background-color:transparent; cursor:pointer;}\n");
      out.write("table {border-collapse:collapse; border-spacing:0; width:100%;}\n");
      out.write("body {-webkit-text-size-adjust:none;}/* 뷰포트 변환시 폰트크기 자동확대 방지 */\n");
      out.write("input[type='text'], input[type='password'],\n");
      out.write("input[type='submit'], input[type='search'], textarea { border-radius:0; -webkit-appearance: none;}\n");
      out.write("input:checked[type='checkbox'] {background-color:#666; -webkit-appearance:checkbox;}\n");
      out.write("button, input[type='button'], input[type='submit'],\n");
      out.write("input[type='reset'], input[type='file'] {-webkit-appearance:button; border-radius:0;}\n");
      out.write("input[type='search']::-webkit-search-cancel-button {-webkit-appearance:none;}\n");
      out.write("body {background:#fff; width:100%; height:100%;}\n");
      out.write("body, th, td, input, select, textarea, button {font-size:17px; line-height:1.2; font-weight:400; letter-spacing:-0.8px; font-family:\"Noto Sans KR\", sans-serif;}\n");
      out.write("textarea {resize: none;}\n");
      out.write("a {color:#6e6e6e; text-decoration:none;}\n");
      out.write("a:active, a:hover {text-decoration:none;}\n");
      out.write("address, caption, cite, code, dfn, em, var {font-style:normal; font-weight:400;}\n");
      out.write("legend {position:absolute; left:-9999px; width:1px; height:1px; font-size:0; line-height:0; overflow:hidden;}\n");
      out.write("caption {height:0; font-size:0; line-height:0; overflow:hidden;}\n");
      out.write("img {vertical-align:top; max-width:100%; height:auto;}\n");
      out.write("\n");
      out.write(".error-wrap {height:100%;}\n");
      out.write(".error {width:100%; height:100%; background:url(https://img.fnf.co.kr/shop6/error/img_bg.jpg) no-repeat; overflow:hidden; background-size:cover; background-position:left; background-repeat:repeat-x;}\n");
      out.write(".error .logo {display:block; margin-top:30vw; text-align:center;}\n");
      out.write(".error .txt-wrap {margin-top:6.66vw; height:26.66vw; background:url(https://img.fnf.co.kr/shop6/error/m_img_num.png) no-repeat center top; background-size:cover; text-align:center;}\n");
      out.write(".error .txt-wrap p {margin:6.4vw 0; color:#fff; font-size:3.2vw; line-height:5.8vw;}\n");
      out.write(".error .txt-wrap .btn-back {margin:0 auto; text-align:center;}\n");
      out.write(".error .txt-wrap .btn-back a {display:inline-block; width:41.06vw; height:10.4vw; line-height:10.4vw; border:1px solid #fff; border-radius:5.2vw; color:#fff; font-size:2.66vw; font-weight:500;}\n");
      out.write(".error .txt-wrap .img {\n");
      out.write("\tpadding-left:42vw;\n");
      out.write("\tdisplay:block;\n");
      out.write("\twidth: 15.6vw;\n");
      out.write("\theight: 26.53vw;\n");
      out.write("\tanimation-name: action;\n");
      out.write("\t-webkit-animation-name: action;\n");
      out.write("\t-moz-animation-name: action;\n");
      out.write("\n");
      out.write("\tanimation-duration: 4s;\n");
      out.write("\t-webkit-animation-duration: 4s;\n");
      out.write("\t-moz-animation-duration: 4s;\n");
      out.write("\n");
      out.write("\tanimation-iteration-count: infinite;\n");
      out.write("\t-webkit-animation-iteration-count: infinite;\n");
      out.write("\t-moz-animation-iteration-count: infinite;\n");
      out.write("}\n");
      out.write("\n");
      out.write("@keyframes action {\n");
      out.write("\t0% {\n");
      out.write("\t\ttransform: translateY(0%);\n");
      out.write("\t}\n");
      out.write("\t50% {\n");
      out.write("\t\ttransform: translateY(7%);\n");
      out.write("\t}\n");
      out.write("\t100% {\n");
      out.write("\t\ttransform: translateY(0%);\n");
      out.write("\t}\n");
      out.write("}\n");
      out.write("\n");
      out.write("@-webkit-keyframes action {\n");
      out.write("\t0% {\n");
      out.write("\t\t-webkit-transform: translateY(0%);\n");
      out.write("\t}\n");
      out.write("\t50% {\n");
      out.write("\t\t-webkit-transform: translateY(7%);\n");
      out.write("\t}\n");
      out.write("\t100% {\n");
      out.write("\t\t-webkit-transform: translateY(0%);\n");
      out.write("\t}\n");
      out.write("}\n");
      out.write("\n");
      out.write("@-moz-keyframes action {\n");
      out.write("\t0% {\n");
      out.write("\t\t-moz-transform: translateY(0%);\n");
      out.write("\t}\n");
      out.write("\t50% {\n");
      out.write("\t\t-moz-transform: translateY(7%);\n");
      out.write("\t}\n");
      out.write("\t100% {\n");
      out.write("\t\t-moz-transform: translateY(0%);\n");
      out.write("\t}\n");
      out.write("}\n");
      out.write("</style>\n");
      out.write("<title>Discovery</title>\n");
      out.write("\n");
      out.write("<div class=\"error-wrap\">\n");
      out.write("\t<div class=\"error\">\n");
      out.write("\t\t<span class=\"logo\"><img src=\"https://img.fnf.co.kr/shop6/error/img_logo.png\" alt=\"discovery-expedition\"></span>\n");
      out.write("\t\t<div class=\"txt-wrap\">\n");
      out.write("\t\t\t<span class=\"img\"><img src=\"https://img.fnf.co.kr/shop6/error/m_img_men.png\" alt=\"\"></span>\n");
      out.write("\t\t\t<p>");
      if (_jspx_meth_spring_005fmessage_005f0(_jspx_page_context))
        return;
      out.write("</p>\n");
      out.write("\t\t\t<div class=\"btn-back\"><a href=\"/\">");
      if (_jspx_meth_spring_005fmessage_005f1(_jspx_page_context))
        return;
      out.write("</a></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<input type=\"hidden\" name=\"errMessage\" \tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${message}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" />\n");
      out.write("<input type=\"hidden\" name=\"errStacktrace\" \tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${stacktrace}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" />\n");
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

  private boolean _jspx_meth_spring_005fmessage_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  spring:message
    org.springframework.web.servlet.tags.MessageTag _jspx_th_spring_005fmessage_005f0 = (org.springframework.web.servlet.tags.MessageTag) _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody.get(org.springframework.web.servlet.tags.MessageTag.class);
    boolean _jspx_th_spring_005fmessage_005f0_reused = false;
    try {
      _jspx_th_spring_005fmessage_005f0.setPageContext(_jspx_page_context);
      _jspx_th_spring_005fmessage_005f0.setParent(null);
      // /WEB-INF/views/errors/404_notFounded.jsp(110,6) name = code type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_spring_005fmessage_005f0.setCode("common.no.requst.page");
      int[] _jspx_push_body_count_spring_005fmessage_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_spring_005fmessage_005f0 = _jspx_th_spring_005fmessage_005f0.doStartTag();
        if (_jspx_th_spring_005fmessage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_spring_005fmessage_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_spring_005fmessage_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_spring_005fmessage_005f0.doFinally();
      }
      _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody.reuse(_jspx_th_spring_005fmessage_005f0);
      _jspx_th_spring_005fmessage_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_spring_005fmessage_005f0, _jsp_getInstanceManager(), _jspx_th_spring_005fmessage_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_spring_005fmessage_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  spring:message
    org.springframework.web.servlet.tags.MessageTag _jspx_th_spring_005fmessage_005f1 = (org.springframework.web.servlet.tags.MessageTag) _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody.get(org.springframework.web.servlet.tags.MessageTag.class);
    boolean _jspx_th_spring_005fmessage_005f1_reused = false;
    try {
      _jspx_th_spring_005fmessage_005f1.setPageContext(_jspx_page_context);
      _jspx_th_spring_005fmessage_005f1.setParent(null);
      // /WEB-INF/views/errors/404_notFounded.jsp(111,37) name = code type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_spring_005fmessage_005f1.setCode("common.go.main");
      int[] _jspx_push_body_count_spring_005fmessage_005f1 = new int[] { 0 };
      try {
        int _jspx_eval_spring_005fmessage_005f1 = _jspx_th_spring_005fmessage_005f1.doStartTag();
        if (_jspx_th_spring_005fmessage_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_spring_005fmessage_005f1[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_spring_005fmessage_005f1.doCatch(_jspx_exception);
      } finally {
        _jspx_th_spring_005fmessage_005f1.doFinally();
      }
      _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody.reuse(_jspx_th_spring_005fmessage_005f1);
      _jspx_th_spring_005fmessage_005f1_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_spring_005fmessage_005f1, _jsp_getInstanceManager(), _jspx_th_spring_005fmessage_005f1_reused);
    }
    return false;
  }
}