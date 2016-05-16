package org.apache.jsp.tag.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class sort_tag
    extends javax.servlet.jsp.tagext.SimpleTagSupport
    implements org.apache.jasper.runtime.JspSourceDependent {


  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private JspContext jspContext;
  private java.io.Writer _jspx_sout;
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
  private java.lang.String column;

  public com.asgab.core.pagination.Page getPage() {
    return this.page;
  }

  public void setPage(com.asgab.core.pagination.Page page) {
    this.page = page;
  }

  public java.lang.String getColumn() {
    return this.column;
  }

  public void setColumn(java.lang.String column) {
    this.column = column;
  }

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void doTag() throws JspException, java.io.IOException {
    PageContext _jspx_page_context = (PageContext)jspContext;
    HttpServletRequest request = (HttpServletRequest) _jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse) _jspx_page_context.getResponse();
    HttpSession session = _jspx_page_context.getSession();
    ServletContext application = _jspx_page_context.getServletContext();
    ServletConfig config = _jspx_page_context.getServletConfig();
    JspWriter out = jspContext.getOut();
    if( getPage() != null ) {
      _jspx_page_context.setAttribute("page", getPage());
}
    if( getColumn() != null ) {
      _jspx_page_context.setAttribute("column", getColumn());
}

    try {
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

	String sortStr ="";
	if(page.getSort()!=null && page.getSort()!="" && page.getSort().startsWith(column)){
		if(page.getSort().indexOf("desc")>0){
			sortStr = column;
		}else{
			sortStr = column +" desc";
		}
	}else{
		sortStr = column;
	}

      out.write("\n");
      out.write("onclick = 'javascript:window.location.href=\"?sort=");
      out.print(sortStr);
      out.write('&');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${search}", java.lang.String.class, (PageContext)this.getJspContext(), null));
      out.write("\"' class=\"sorting\"\n");
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
    }
  }
}
