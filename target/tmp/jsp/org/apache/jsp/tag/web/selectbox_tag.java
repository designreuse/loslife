package org.apache.jsp.tag.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.Iterator;

public final class selectbox_tag
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
  private java.lang.String name;
  private java.lang.String value;
  private java.util.Map map;

  public java.lang.String getName() {
    return this.name;
  }

  public void setName(java.lang.String name) {
    this.name = name;
  }

  public java.lang.String getValue() {
    return this.value;
  }

  public void setValue(java.lang.String value) {
    this.value = value;
  }

  public java.util.Map getMap() {
    return this.map;
  }

  public void setMap(java.util.Map map) {
    this.map = map;
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
    if( getName() != null ) {
      _jspx_page_context.setAttribute("name", getName());
}
    if( getValue() != null ) {
      _jspx_page_context.setAttribute("value", getValue());
}
    if( getMap() != null ) {
      _jspx_page_context.setAttribute("map", getMap());
}

    try {
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<select name=\"");
      out.print(name);
      out.write("\" class=\"form-control ");
      out.print(name);
      out.write("\" id=\"");
      out.print(name);
      out.write("\">\n");
      out.write("\t");

		Iterator iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<Integer,String> entry = (Map.Entry<Integer,String>)iterator.next();
			String selected = "";
			if(StringUtils.isNoneBlank(value) && value.equals(String.valueOf(entry.getKey()))){
				selected="selected=\"selected\"";
			}
	
      out.write("\n");
      out.write("\t\t\t<option value=\"");
      out.print(entry.getKey() );
      out.write('"');
      out.write(' ');
      out.print(selected );
      out.write(' ');
      out.write('>');
      out.print(entry.getValue() );
      out.write("</option>\n");
      out.write("\t");

		}
	
      out.write("\n");
      out.write("</select>");
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
