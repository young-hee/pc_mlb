/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.8
 * Generated at: 2019-12-17 04:27:40 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

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
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\t<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script>\n");
      out.write("\t<style type=\"text/css\">\n");
      out.write("\t\t@charset \"utf-8\";\n");
      out.write("\t\ta.btn { display: inline-block; border-radius: 3px; line-height: 21px; height: 25px; padding: 0 10px; padding-top: 1px\\9\\0 !important; box-shadow: 1px 1px 0 rgba(0, 0, 0, 0.1); box-sizing: border-box; text-align: center; border: solid 1px #b5b5b5; background: #fff; color: #333; vertical-align: middle; cursor: pointer }\n");
      out.write("\t\ta.btn:hover { border: solid 1px #23324d; color: #44597f; text-decoration: none }\n");
      out.write("\t\ta.btn:active { box-shadow: none; position: relative; top: 1px; }\n");
      out.write("\t\ta.btn.toggled { background: #44597f; border-color: #233450; color: #fff; }\n");
      out.write("\t\ta.btn.bold { font-weight: 600; }\n");
      out.write("\t\ta.btn.btn-xlg { }\n");
      out.write("\t\ta.btn.btn-lg { line-height: 32px; height: 34px; letter-spacing: 2px; padding: 0 22px; }\n");
      out.write("\t\ta.btn.btn-sm { line-height: 22px; height: 24px; padding: 0 5px; }\n");
      out.write("\t\ta.btn.btn-xsm { line-height: 16px; height: 20px; padding: 0 10px; }\n");
      out.write("\t\ta.btn.btn-white { background: #fff; border-color: #b5b5b5; color: #333; }\n");
      out.write("\t\ta.btn.btn-white:hover { border-color: #23324d; color: #44597f; }\n");
      out.write("\t\ta.btn.btn-dnavy { background: #31425f; border-color: #1a2732; color: #fff; }\n");
      out.write("\t\ta.btn.btn-dnavy:hover { background: #6482b5; color: #fff; }\n");
      out.write("\t\ta.btn.btn-navy { background: #44597f; border-color: #233450; color: #fff; }\n");
      out.write("\t\ta.btn.btn-navy:hover { background: #526e9d; color: #fff; }\n");
      out.write("\t\ta.btn.btn-navy.toggled { background-color: #bfc400; }\n");
      out.write("\t\ta.btn.btn-bgray { background: #f5f5f5; border-color: #b5b5b5; color: #666; }\n");
      out.write("\t\ta.btn.btn-bgray:hover { border-color: #23324d; color: #44597f; }\n");
      out.write("\t\ta.btn.btn-dgray { background: #4f4f4f; border-color: #1a1a1a; color: #fff; }\n");
      out.write("\t\ta.btn.btn-dgray:hover { border-color: #1a1a1a; color: #fff; background: #666; }\n");
      out.write("\t\ta.btn.btn-ico-mute { }\n");
      out.write("\t\t\n");
      out.write("\t\t/* ie line height fix */\n");
      out.write("\t\ta.btn { line-height: 25px\\9\\0; height: 25px\\9\\0; font-size: 12px; }\n");
      out.write("\t\ta.btn.btn-lg { line-height: 34px\\9\\0; height: 34px\\9\\0; }\n");
      out.write("\t\ta.btn.btn-sm { line-height: 24px\\9\\0; height: 24px\\9\\0; }\n");
      out.write("\t\ta.btn.btn-xsm { line-height: 20px\\9\\0; height: 20px\\9\\0; }\n");
      out.write("\t\t@media screen and (-ms-high-contrast:active), (-ms-high-contrast:none) {\n");
      out.write("\t\t  a.btn { line-height: 25px; height: 25px; }\n");
      out.write("\t\t  a.btn.btn-lg { line-height: 34px; height: 34px; }\n");
      out.write("\t\t  a.btn.btn-sm { line-height: 24px; height: 24px; }\n");
      out.write("\t\t  a.btn.btn-xsm { line-height: 20px; height: 20px; }\n");
      out.write("\t\t}\n");
      out.write("\t\t\n");
      out.write("\t\t/* folding btn */\n");
      out.write("\t\ta.btn.folding-button, a.btn.form-folding-btn { background-image: url(../images/common_btn_folding.png); background-repeat: no-repeat; background-position: 100% 10px; padding-right: 25px; height: 25px; line-height: 21px; }\n");
      out.write("\t\ta.btn.toggled.folding-button, a.btn.toggled.form-folding-btn { background-position: 100% -40px; }\n");
      out.write("\t\ta.close-btn-xsm { background-image: url(../images/common_icons.png); display: inline-block; overflow: hidden; width: 15px; height: 16px; vertical-align: middle; position: relative; text-indent: -9999px; background-position: 0 -100px; }\n");
      out.write("\t\ta.close-btn-xsm:hover img { background-position: -20px -100px; }\n");
      out.write("\t\t\n");
      out.write("\t\t/* ie line height fix */\n");
      out.write("\t\ta.btn.folding-button, a.btn.form-folding-btn { line-height: 25px\\9\\0; }\n");
      out.write("\t\t@media screen and (-ms-high-contrast:active), (-ms-high-contrast:none) {\n");
      out.write("\t\t  a.btn.folding-button, a.btn.form-folding-btn { line-height: 25px; }\n");
      out.write("\t\t}\n");
      out.write("\t\ta.btn-copy { background: url(../images/common_icons.png); background-repeat: no-repeat; background-position: -50px -125px; width: 21px; height: 20px; display: inline-block; text-indent: -9999px; vertical-align: middle; cursor: pointer; }\n");
      out.write("\t\ta.btn-history { background: url(../images/common_icons.png); background-repeat: no-repeat; background-position: -0px -125px; width: 21px; height: 20px; display: inline-block; text-indent: -9999px; vertical-align: middle; cursor: pointer; }\n");
      out.write("\t\ta.btn-call { background: url(../images/common_icons.png); background-repeat: no-repeat; background-position: -25px -125px; width: 21px; height: 20px; display: inline-block; text-indent: -9999px; vertical-align: middle; cursor: pointer; }\n");
      out.write("\t\t.containerTableStyle table td.standartTreeImage { display: none; }\n");
      out.write("\t\t.containerTableStyle table td.standartTreeImage:first-child { display: block; }\n");
      out.write("\t\t.containerTableStyle > table > tbody > tr > td > table > tbody > tr:first-child > td { font-weight: 600 }\n");
      out.write("\t\t.input-reset-btn { display: inline-block; background: url(../images/ers.png); background-color: #fff; background-repeat: no-repeat; background-position: 4px 3px; text-indent: -9999px; width: 24px; height: 20px; border-radius: 3px; box-shadow: 1px 1px 0 rgba(0, 0, 0, 0.1); box-sizing: border-box; border: solid 1px #b5b5b5; cursor: pointer; vertical-align: middle; }\n");
      out.write("\t\t.input-reset-btn:hover { border: solid 1px #23324d; text-decoration: none;\n");
      out.write("\t</style>\n");
      out.write("\t\n");
      out.write("\t<script type=\"text/javascript\">\n");
      out.write("\t\t$(\"#btnBegin\").click(function() {\n");
      out.write("\t\t\t$.ajax({\n");
      out.write("\t\t\t      async       : false,\n");
      out.write("\t\t\t      type        : 'POST',\n");
      out.write("\t\t\t      url         : 'URL',\n");
      out.write("\t\t\t      data        : '&a=1&b=2',\n");
      out.write("\t\t\t      cache       : false,\n");
      out.write("\t\t\t      error:function(xhr, sataus, e){\n");
      out.write("\t\t\t        alert('실패.');\n");
      out.write("\t\t\t        return;\n");
      out.write("\t\t\t      },\n");
      out.write("\t\t\t      success:function(html){\n");
      out.write("\t\t\t    \talert('성공.');\n");
      out.write("\t\t\t        return;\n");
      out.write("\t\t\t      }\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t$(\"#btnBeginAll\").click(function() {\n");
      out.write("\t\t\t$.ajax({\n");
      out.write("\t\t\t      async       : false,\n");
      out.write("\t\t\t      type        : 'POST',\n");
      out.write("\t\t\t      url         : 'URL',\n");
      out.write("\t\t\t      data        : '&a=1&b=2',\n");
      out.write("\t\t\t      cache       : false,\n");
      out.write("\t\t\t      error:function(xhr, sataus, e){\n");
      out.write("\t\t\t        alert('실패.');\n");
      out.write("\t\t\t        return;\n");
      out.write("\t\t\t      },\n");
      out.write("\t\t\t      success:function(html){\n");
      out.write("\t\t\t    \talert('성공.');\n");
      out.write("\t\t\t        return;\n");
      out.write("\t\t\t      }\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t$(\"#btnStop\").click(function() {\n");
      out.write("\t\t\t$.ajax({\n");
      out.write("\t\t\t      async       : false,\n");
      out.write("\t\t\t      type        : 'POST',\n");
      out.write("\t\t\t      url         : 'URL',\n");
      out.write("\t\t\t      data        : '&a=1&b=2',\n");
      out.write("\t\t\t      cache       : false,\n");
      out.write("\t\t\t      error:function(xhr, sataus, e){\n");
      out.write("\t\t\t        alert('실패.');\n");
      out.write("\t\t\t        return;\n");
      out.write("\t\t\t      },\n");
      out.write("\t\t\t      success:function(html){\n");
      out.write("\t\t\t    \talert('성공.');\n");
      out.write("\t\t\t        return;\n");
      out.write("\t\t\t      }\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t$(\"#btnStopAll\").click(function() {\n");
      out.write("\t\t\t$.ajax({\n");
      out.write("\t\t\t      async       : false,\n");
      out.write("\t\t\t      type        : 'POST',\n");
      out.write("\t\t\t      url         : 'URL',\n");
      out.write("\t\t\t      data        : '&a=1&b=2',\n");
      out.write("\t\t\t      cache       : false,\n");
      out.write("\t\t\t      error:function(xhr, sataus, e){\n");
      out.write("\t\t\t        alert('실패.');\n");
      out.write("\t\t\t        return;\n");
      out.write("\t\t\t      },\n");
      out.write("\t\t\t      success:function(html){\n");
      out.write("\t\t\t    \talert('성공.');\n");
      out.write("\t\t\t        return;\n");
      out.write("\t\t\t      }\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t\n");
      out.write("\t</script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<h2>Hello World!</h2>\n");
      out.write("\n");
      out.write("<!-- \n");
      out.write("* 버튼: Ajax Call(Restful URL) <br/>\n");
      out.write("\t조회, 저장(수정) <br/>\n");
      out.write("* 버튼: Ajax Call(Restful URL) <br/>\n");
      out.write("\t시작  <br/>\n");
      out.write("\t, 중지 <br/>\n");
      out.write("\t, 모두시작/중지 <br/> \n");
      out.write(" <br/>\n");
      out.write("* 리스트(Table:AA_BATCH_JOB) <br/>\n");
      out.write(" <br/>\n");
      out.write("DeadLine : 월요일(5월4일) <br/>\\\n");
      out.write(" -->\n");
      out.write("\n");
      out.write("<div class=\"buttons-wrap left-top\">\n");
      out.write("\t<a class=\"btn btn-navy bold\" id=\"btnBeginAll\">모두시작</a> \n");
      out.write("\t<a class=\"btn btn-navy bold\" id=\"btnStopAll\">모두중지</a>  \n");
      out.write("</div>\n");
      out.write("<!-- \n");
      out.write("<div class=\"buttons-wrap left-top\" style=\"margin-top:20px;\">\n");
      out.write("\t<a class=\"btn\" id=\"btnBegin\">시작</a> <a class=\"btn\" id=\"btnStop\">중지</a> <a class=\"btn\" id=\"btnUpdate\">수정</a>\n");
      out.write("</div>\n");
      out.write(" -->\n");
      out.write(" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${resultList}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("<div>\n");
      out.write("<ul>\n");
      out.write("\t<li><span><input type=\"checkbox\"></span><span>배치목록1</span> <a class=\"btn\" id=\"btnBegin\">시작</a> <a class=\"btn\" id=\"btnStop\">중지</a> <a class=\"btn\" id=\"btnUpdate\">수정</a></li>\n");
      out.write("\t<li><span><input type=\"checkbox\"></span><span>배치목록1</span> <a class=\"btn\" id=\"btnBegin\">시작</a> <a class=\"btn\" id=\"btnStop\">중지</a> <a class=\"btn\" id=\"btnUpdate\">수정</a></li>\n");
      out.write("</ul>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
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
}