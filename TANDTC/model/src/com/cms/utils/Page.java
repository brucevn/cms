package com.cms.utils;

public class Page {
    public Page() {        
    }
    public static String pageNavigator(int page,int size,int itemsNumber){
        if(itemsNumber>size){
          StringBuffer buffer = new StringBuffer();
          int start=(page-1)*size+1;
          int end = page*size;
          if(end > itemsNumber) end=itemsNumber;
          int maxPage = itemsNumber%size==0?itemsNumber/size:itemsNumber/size+1;
          buffer.append("<div class='navigator'>");
          if(page>2){
            buffer.append("<a href='javascript:gotoPage(1);' class='pageAction'>« &#272;&#7847;u</a>");
          }
          if(page>1){       
            buffer.append("<a href='javascript:gotoPage("+(page-1)+");' class='pageAction'>‹ Tr&#432;&#7899;c</a>");
          }
          buffer.append("<span>"+start+" - "+end+" | "+itemsNumber+"</span>");
          if(page<maxPage){
            buffer.append("<a href='javascript:gotoPage("+(page+1)+");' class='pageAction'>Sau ›</a>");       
          }
          if(page<maxPage-1){
            buffer.append("<a href='javascript:gotoPage("+maxPage+");' class='pageAction'>Cu&#7889;i »</a>");
          }
          buffer.append("</div>");
          return buffer.toString();
        }else
          return "";
      }
}
