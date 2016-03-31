package com.cms.utils;

import javax.servlet.http.HttpServletRequest;

import oracle.portal.provider.v2.ProviderSession;
import oracle.portal.provider.v2.http.HttpCommonConstants;
import oracle.portal.provider.v2.render.PortletRenderRequest;
import oracle.portal.provider.v2.render.PortletRendererUtil;


public class PortletUtil {
	
	public static String portletParameter(HttpServletRequest request, String param) {
		if (isPortalRequest(request)) {
			PortletRenderRequest pReq = getPortletRendererRequest(request);
			return PortletRendererUtil.portletParameter(pReq, param);
		}
		
		return param;
	}
	
	public static boolean isPortalRequest(HttpServletRequest request)
    {		
	      return (request.getAttribute(HttpCommonConstants.PORTLET_RENDER_REQUEST) != null);  
    }
	
	public static PortletRenderRequest getPortletRendererRequest(HttpServletRequest request)
    {		
	      return (PortletRenderRequest)request.getAttribute(HttpCommonConstants.PORTLET_RENDER_REQUEST);
    }
	
	public static String getPageURL(HttpServletRequest request)
    {		
		PortletRenderRequest pReq = getPortletRendererRequest(request);
		
		if (pReq != null) {
			return pReq.getRenderContext().getPageURL();
		}
		else {
			return request.getRequestURL().toString();
		}
    }
	
	public static String getLoginServerURL(HttpServletRequest request) {
		PortletRenderRequest pReq = getPortletRendererRequest(request);
		
		if (pReq != null) {
			return pReq.getRenderContext().getLoginServerURL();
		}
		else {
			return request.getContextPath();
		}
	}
	
	public static String getParameterValue(HttpServletRequest request, String param) {
		String temp="";
		if (PortletUtil.isPortalRequest(request)) {
			PortletRenderRequest pReq = PortletUtil.getPortletRendererRequest(request);
			if(pReq.getQualifiedParameter(param)!=null) temp=pReq.getQualifiedParameter(param);
		}		
		if(request.getParameter(param)!=null)temp=request.getParameter(param);
                return temp;
	}
	
	public static String[] getParameterValues(HttpServletRequest request, String param) {
		
		if (PortletUtil.isPortalRequest(request)) {
			PortletRenderRequest pReq = PortletUtil.getPortletRendererRequest(request);
			return pReq.getQualifiedParameterValues(param);
		}
		
		return request.getParameterValues(param);
	}
	
	public static String pageNavigator(int page,int size,int itemsNumber,String[] lables){
        if(itemsNumber>size){
          StringBuffer buffer = new StringBuffer();
          int start=(page-1)*size+1;
          int end = page*size;
          if(end > itemsNumber) end=itemsNumber;
          int maxPage = itemsNumber%size==0?itemsNumber/size:itemsNumber/size+1;
          buffer.append("<div class='navigator'>");
          if(page>2){
            buffer.append("<a href='javascript:goToPage(1);' class='pageAction'>"+lables[0]+"</a>");
          }
          if(page>1){       
            buffer.append("<a href='javascript:goToPage("+(page-1)+");' class='pageAction'>"+lables[1]+"</a>");
          }
          buffer.append("<span>"+start+" - "+end+" | "+itemsNumber+"</span>");
          if(page<maxPage){
            buffer.append("<a href='javascript:goToPage("+(page+1)+");' class='pageAction'>"+lables[2]+"</a>");       
          }
          if(page<maxPage-1){
            buffer.append("<a href='javascript:goToPage("+maxPage+");' class='pageAction'>"+lables[3]+"</a>");
          }
          buffer.append("</div>");
          return buffer.toString();
        }else
          return "";
      }
	public static String pageNavigatorFrontEnd(int page,int size,int itemsNumber,String[] lables,String href){
        if(itemsNumber>size){
          StringBuffer buffer = new StringBuffer();
          int maxPage = itemsNumber%size==0?itemsNumber/size:itemsNumber/size+1;
          if(page>maxPage)page=maxPage;
          int start=(page-1)*size+1;
          int end = page*size;
          if(end > itemsNumber) end=itemsNumber;          
          buffer.append("<span class='navigator'>");
          if(page>2){
            buffer.append("<a href='"+href+"1' class='pageAction'>"+lables[0]+"</a>");
          }
          if(page>1){       
            buffer.append("<a href='"+href+(page-1)+"' class='pageAction'>"+lables[1]+"</a>");
          }
          buffer.append("<span>"+start+" - "+end+" | "+itemsNumber+"</span>");
          if(page<maxPage){
            buffer.append("<a href='"+href+(page+1)+"' class='pageAction'>"+lables[2]+"</a>");       
          }
          if(page<maxPage-1){
            buffer.append("<a href='"+href+maxPage+"' class='pageAction'>"+lables[3]+"</a>");
          }
          buffer.append("</span>");
          return buffer.toString();
        }else
          return "";
      }
	public static String pageNavigatorFrontEnd(int page,int size,int itemsNumber,String[] lables){
        if(itemsNumber>size){
          StringBuffer buffer = new StringBuffer();
          int maxPage = itemsNumber%size==0?itemsNumber/size:itemsNumber/size+1;
          if(page>maxPage)page=maxPage;
          int start=(page-1)*size+1;
          int end = page*size;
          if(end > itemsNumber) end=itemsNumber;          
          buffer.append("<span class='navigator'>");
          if(page>2){
	          buffer.append("<a href='javascript:goToPage(1);' class='pageAction'>"+lables[0]+"</a>");
	        }
	        if(page>1){       
	          buffer.append("<a href='javascript:goToPage("+(page-1)+");' class='pageAction'>"+lables[1]+"</a>");
	        }
	        buffer.append("<span>"+start+" - "+end+" | "+itemsNumber+"</span>");
	        if(page<maxPage){
	          buffer.append("<a href='javascript:goToPage("+(page+1)+");' class='pageAction'>"+lables[2]+"</a>");       
	        }
	        if(page<maxPage-1){
	          buffer.append("<a href='javascript:goToPage("+maxPage+");' class='pageAction'>"+lables[3]+"</a>");
	        }
          buffer.append("</span>");
          return buffer.toString();
        }else
          return "";
      }
	public static String encodeHTML(String s)
	{
	    StringBuffer out = new StringBuffer();
	    for(int i=0; i<s.length(); i++)
	    {
	        char c = s.charAt(i);
	        if(c > 127 || c=='"' || c=='<' || c=='>')
	        {
	           out.append("&#"+(int)c+";");
	        }
	        else
	        {
	            out.append(c);
	        }
	    }
	    return out.toString();
	}
	public static void setSessionAttribute(String key,Object value,HttpServletRequest request){
		PortletRenderRequest pReq = getPortletRendererRequest(request);
		ProviderSession session = pReq.getSession();
		if(session!=null)session.setAttribute(key, value);
	}
	public static void removeSessionAttribute(String key,HttpServletRequest request){
		PortletRenderRequest pReq = getPortletRendererRequest(request);
		ProviderSession session = pReq.getSession();
		if(session!=null&&session.getAttribute(key)!=null)
			session.removeAttribute(key);
	}
	public static Object getSessionAttribute(String key,HttpServletRequest request){
		PortletRenderRequest pReq = getPortletRendererRequest(request);
		ProviderSession session = pReq.getSession();
		if(session!=null&&session.getAttribute(key)!=null)
			return session.getAttribute(key);
		else
			return null;
	}
    public static String htmlEsc(String filters){
        String temp=filters.trim();
        temp=temp.replaceAll("--", " ");
        temp=temp.replaceAll(";", " ");
        temp=temp.replaceAll("'", " ");
        temp=temp.replaceAll(",", " ");
        temp=temp.replaceAll("\"", " ");
        if(temp.indexOf("$")>=0){
            int index = temp.indexOf("$");
            while(index>=0){
                if(index==0){
                    temp=temp.substring(1);
                }else{
                    temp = temp.substring(0,index)+" "+temp.substring(index+1,temp.length());
                }                    
                index = temp.indexOf("$");
            }
        }
        if(temp.indexOf("?")>=0){
            int index = temp.indexOf("?");
            while(index>=0){
                if(index==0){
                    temp=temp.substring(1);
                }else{
                    temp = temp.substring(0,index)+" "+temp.substring(index+1,temp.length());
                }                    
                index = temp.indexOf("?");
            }
        }       
        
        temp=temp.replaceAll("#", " ");
        temp=temp.replaceAll("!", " ");
        temp=temp.replaceAll("%", " ");
        temp=temp.replaceAll("&", " ");
        temp=temp.replaceAll("^", " ");
        temp=temp.replaceAll("~", " ");
        temp=temp.replaceAll("<", " ");
        temp=temp.replaceAll(">", " ");        
        temp.replaceAll("script"," ");
        temp.replaceAll("alert"," ");
        temp.replaceAll("delete"," ");
        temp.replaceAll("drop"," ");
        temp.replaceAll("select"," ");
        temp.replaceAll("insert"," ");
        temp.replaceAll("update"," ");
        temp.replaceAll("or"," ");
        temp.replaceAll("and"," ");
        return temp;
    }
    public static boolean isDefaultProperty(int propertyId){
        if(propertyId>=-5&&propertyId<=-1){
            return true;
        }
        return false;
    }
    public static boolean StringCompare(String str1,String str2){
        return str1.equalsIgnoreCase(str2);
    }
}
