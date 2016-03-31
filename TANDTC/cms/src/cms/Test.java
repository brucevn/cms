package cms;

import java.util.List;
import com.cms.services.CmsItemService;
import com.cms.services.CmsPerspectiveService;

import com.cms.models.*;
import com.cms.services.CmsCategoryService;

public class Test {
    public Test() {
        super();
    }
    public static void main(String[] args){
        CmsCategoryService service = new CmsCategoryService();
        List<CmsCategory> arr = service.findCategories("vi",null,0,0,10);
        System.out.println(arr.size());
    }
}
