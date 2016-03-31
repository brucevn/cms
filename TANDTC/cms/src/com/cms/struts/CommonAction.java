package com.cms.struts;

import com.cms.models.StrutsToken;

import com.cms.services.StrutsTokenService;

import com.cms.utils.CmsConstants;
import com.cms.utils.PortletUtil;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.actions.DispatchAction;

public class CommonAction extends DispatchAction {
    public CommonAction() {
        super();
    }
    public void saveToken(HttpServletRequest request){
        StrutsTokenService service = new StrutsTokenService();
        StrutsToken token = new StrutsToken();
        token.setToken(1);
        service.insertToken(token);
        request.setAttribute("token",token);
    }
    public boolean isValidToken(HttpServletRequest request){        
        try{
            StrutsTokenService service = new StrutsTokenService();
            int tokenId = Integer.parseInt(PortletUtil.getParameterValue(request,CmsConstants.TOKEN_ID));
            int token = Integer.parseInt(PortletUtil.getParameterValue(request,CmsConstants.TOKEN_VALUE));
            StrutsToken strutsToken = service.getStrutsToken(tokenId);
            return strutsToken.isValidToken(token);
        }catch(Exception e){
            return false;
        }        
    }
    public void resetToken(HttpServletRequest request){
        try{
            StrutsTokenService service = new StrutsTokenService();
            int tokenId = Integer.parseInt(PortletUtil.getParameterValue(request,CmsConstants.TOKEN_ID));
            service.deleteStrutsToken(tokenId);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
