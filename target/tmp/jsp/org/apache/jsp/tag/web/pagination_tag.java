package org.apache.jsp.tag.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class pagination_tag
    extends javax.servlet.jsp.tagext.SimpleTagSupport
    implements org.apache.jasper.runtime.JspSourceDependent {


  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private JspContext jspContext;
  private java.io.Writer _jspx_sout;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_end_begin;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public void setJspContext(JspContext ctx) {
    super.setJspContext(ctx);
    java.util.ArrayList<String> _jspx_nested = null;
    java.util.ArrayList<String> _jspx_at_begin = null;
    java.util.ArrayList<String> _jspx_at_end = null;
    this.jspContext = new org.apache.jasper.runtime.JspContextWrapper(ctx, _jspx_nested, _jspx_at_begin, _jspx_at_end, null);
  }

  public JspContext getJspContext() {
    return this.jspContext;
  }
  private com.asgab.core.pagination.Page page;
  private java.lang.Integer paginationSize;

  public com.asgab.core.pagination.Page getPage() {
    return this.page;
  }

  public void setPage(com.asgab.core.pagination.Page page) {
    this.page = page;
  }

  public java.lang.Integer getPaginationSize() {
    return this.paginationSize;
  }

  public void setPaginationSize(java.lang.Integer paginationSize) {
    this.paginationSize = paginationSize;
  }

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  private void _jspInit(ServletConfig config) {
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_c_forEach_var_end_begin = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_c_forEach_var_end_begin.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_c_when_test.release();
  }

  public void doTag() throws JspException, java.io.IOException {
    PageContext _jspx_page_context = (PageContext)jspContext;
    HttpServletRequest request = (HttpServletRequest) _jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse) _jspx_page_context.getResponse();
    HttpSession session = _jspx_page_context.getSession();
    ServletContext application = _jspx_page_context.getServletContext();
    ServletConfig config = _jspx_page_context.getServletConfig();
    JspWriter out = jspContext.getOut();
    _jspInit(config);
    if( getPage() != null ) {
      _jspx_page_context.setAttribute("page", getPage());
}
    if( getPaginationSize() != null ) {
      _jspx_page_context.setAttribute("paginationSize", getPaginationSize());
}

    try {
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

	int current = page.getPageNumber();
	int begin = Math.max(1, current - paginationSize / 2);
	int end = Math.min(begin + (paginationSize - 1), page.getPageCount());
	request.setAttribute("current", current);
	request.setAttribute("begin", begin);
	request.setAttribute("end", end);

      out.write("\n");
      out.write("\n");
      out.write("\t\t<ul class=\"pagination pull-left\" style=\"margin-top: 10px;margin-bottom: 0px;\">\n");
      out.write("\t\t\t");

			String lang = request.getLocale().getLanguage();
			if("zh".equals(lang)){
				
      out.write("\n");
      out.write("\t\t\t\t<li>当前第");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${current}", java.lang.String.class, (PageContext)this.getJspContext(), null));
      out.write("页，共");
      out.print(page.getPageCount());
      out.write('页');
      out.write('，');
      out.print(page.getTotal());
      out.write("条记录</li>\n");
      out.write("\t\t\t\t");

			}else{
				
      out.write("\n");
      out.write("\t\t\t\t<li>Page ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${current}", java.lang.String.class, (PageContext)this.getJspContext(), null));
      out.write(" of ");
      out.print(page.getPageCount());
      out.write(", Total ");
      out.print(page.getTotal());
      out.write(" entries</li>\n");
      out.write("\t\t\t\t");

			}
			
      out.write("\n");
      out.write("\t\t</ul>\n");
      out.write("\n");
      out.write("\t\t<ul class=\"pagination pull-right\" style=\"margin-top: 10px;margin-bottom: 0px;\">\n");
      out.write("\t\t\t");

				if (page.hasPrevious()) {
			
      out.write("\n");
      out.write("\t\t\t<li><a href=\"?pageNumber=1&sort=");
      out.print(page.getSort());
      out.write('&');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${search}", java.lang.String.class, (PageContext)this.getJspContext(), null));
      out.write("\">&lt;&lt;</a></li>\n");
      out.write("\t\t\t<li><a href=\"?pageNumber=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${current-1}", java.lang.String.class, (PageContext)this.getJspContext(), null));
      out.write("&sort=");
      out.print(page.getSort());
      out.write('&');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${search}", java.lang.String.class, (PageContext)this.getJspContext(), null));
      out.write("\">&lt;</a></li>\n");
      out.write("\t\t\t");

				} else {
			
      out.write("\n");
      out.write("\t\t\t<li class=\"disabled\"><a href=\"#\">&lt;&lt;</a></li>\n");
      out.write("\t\t\t<li class=\"disabled\"><a href=\"#\">&lt;</a></li>\n");
      out.write("\t\t\t");

				}
			
      out.write("\n");
      out.write("\n");
      out.write("\t\t\t");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_end_begin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
      _jspx_th_c_forEach_0.setParent(null);
      _jspx_th_c_forEach_0.setVar("i");
      _jspx_th_c_forEach_0.setBegin(((java.lang.Integer) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${begin}", java.lang.Integer.class, (PageContext)this.getJspContext(), null)).intValue());
      _jspx_th_c_forEach_0.setEnd(((java.lang.Integer) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${end}", java.lang.Integer.class, (PageContext)this.getJspContext(), null)).intValue());
      int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
      try {
        int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
        if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\n");
            out.write("\t\t\t\t");
            //  c:choose
            org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_choose_0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _jspx_tagPool_c_choose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
            _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
            _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_forEach_0);
            int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
            if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\n");
                out.write("\t\t\t\t\t");
                //  c:when
                org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_when_0 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _jspx_tagPool_c_when_test.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
                _jspx_th_c_when_0.setPageContext(_jspx_page_context);
                _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
                _jspx_th_c_when_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${i == current}", java.lang.Boolean.class, (PageContext)this.getJspContext(), null)).booleanValue());
                int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
                if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  do {
                    out.write("\n");
                    out.write("\t\t\t\t\t\t<li class=\"active\"><a\n");
                    out.write("\t\t\t\t\t\t\thref=\"?pageNumber=");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${i}", java.lang.String.class, (PageContext)this.getJspContext(), null));
                    out.write("&sort=");
                    out.print(page.getSort());
                    out.write('&');
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${search}", java.lang.String.class, (PageContext)this.getJspContext(), null));
                    out.write('"');
                    out.write('>');
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${i}", java.lang.String.class, (PageContext)this.getJspContext(), null));
                    out.write("</a></li>\n");
                    out.write("\t\t\t\t\t");
                    int evalDoAfterBody = _jspx_th_c_when_0.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                }
                if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
                  throw new SkipPageException();
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
                out.write("\n");
                out.write("\t\t\t\t\t");
                //  c:otherwise
                org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_otherwise_0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
                _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
                _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
                int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
                if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  do {
                    out.write("\n");
                    out.write("\t\t\t\t\t\t<li><a href=\"?pageNumber=");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${i}", java.lang.String.class, (PageContext)this.getJspContext(), null));
                    out.write("&sort=");
                    out.print(page.getSort());
                    out.write('&');
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${search}", java.lang.String.class, (PageContext)this.getJspContext(), null));
                    out.write('"');
                    out.write('>');
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${i}", java.lang.String.class, (PageContext)this.getJspContext(), null));
                    out.write("</a></li>\n");
                    out.write("\t\t\t\t\t");
                    int evalDoAfterBody = _jspx_th_c_otherwise_0.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                }
                if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                  throw new SkipPageException();
                }
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                out.write("\n");
                out.write("\t\t\t\t");
                int evalDoAfterBody = _jspx_th_c_choose_0.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
            }
            if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
              throw new SkipPageException();
            }
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
            out.write("\n");
            out.write("\t\t\t");
            int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          throw new SkipPageException();
        }
      } catch (Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_forEach_0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_forEach_0.doFinally();
        _jspx_tagPool_c_forEach_var_end_begin.reuse(_jspx_th_c_forEach_0);
      }
      out.write("\n");
      out.write("\t\t\t");

				if (page.hasNext()) {
			
      out.write("\n");
      out.write("\t\t\t<li><a href=\"?pageNumber=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${current+1}", java.lang.String.class, (PageContext)this.getJspContext(), null));
      out.write("&sort=");
      out.print(page.getSort());
      out.write('&');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${search}", java.lang.String.class, (PageContext)this.getJspContext(), null));
      out.write("\">&gt;</a></li>\n");
      out.write("\t\t\t<li><a\n");
      out.write("\t\t\t\thref=\"?pageNumber=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${page.pageCount}", java.lang.String.class, (PageContext)this.getJspContext(), null));
      out.write("&sort=");
      out.print(page.getSort());
      out.write('&');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${search}", java.lang.String.class, (PageContext)this.getJspContext(), null));
      out.write("\">&gt;&gt;</a></li>\n");
      out.write("\t\t\t");

				} else {
			
      out.write("\n");
      out.write("\t\t\t<li class=\"disabled\"><a href=\"#\">&gt;</a></li>\n");
      out.write("\t\t\t<li class=\"disabled\"><a href=\"#\">&gt;&gt;</a></li>\n");
      out.write("\t\t\t");

				}
			
      out.write("\n");
      out.write("\n");
      out.write("\t\t</ul>\n");
      out.write("\n");
      out.write("\n");
    } catch( Throwable t ) {
      if( t instanceof SkipPageException )
          throw (SkipPageException) t;
      if( t instanceof java.io.IOException )
          throw (java.io.IOException) t;
      if( t instanceof IllegalStateException )
          throw (IllegalStateException) t;
      if( t instanceof JspException )
          throw (JspException) t;
      throw new JspException(t);
    } finally {
      ((org.apache.jasper.runtime.JspContextWrapper) jspContext).syncEndTagFile();
      _jspDestroy();
    }
  }
}
