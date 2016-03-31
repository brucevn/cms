function PrivilegeGrant(id){
    $('#cate_id').val(id);
	$.ajax({
		   url:'/govportal-sysadmin/htdocs/SystemPrivilege/PrivilegeAssignForm.jsp',
		   type:'post',
		   dataType:'html',
		   data:'p_catid='+id+'&p_cattype='+$('#cate_type').val(),
	       success:(function(html){
									$('#main_page').html(html);
							})
		   });
}
function UrlInit(form){
 var temp="";
 var arr = form.elements;
 for(var i=0;i<arr.length;i++){
 if((arr[i].type=="text"||arr[i].type=="select-one"||arr[i].type=="hidden")&&arr[i].name!="")
 temp = temp+arr[i].name+"="+trim(arr[i].value)+"&";
 if(arr[i].type=="radio"||arr[i].type=="checkbox"){
 if(arr[i].checked==true)
 temp = temp+arr[i].name+"="+trim(arr[i].value)+"&";
 }
 }
 return temp;
} 
// Removes leading whitespaces
 function LTrim( value ) {
 var re = /\s*((\S+\s*)*)/;
 return value.replace(re, "$1");

 }

 // Removes ending whitespaces
 function RTrim( value ) {
 var re = /((\s*\S+)*)\s*/;
 return value.replace(re, "$1");
 }

 // Removes leading and ending whitespaces
 function trim( value ) {
 return LTrim(RTrim(value));
 } 
function addsubmit(){
    $.ajax({
		   url:'/govportal-sysadmin/htdocs/SystemPrivilege/AddPrivilege.jsp',
		   type:'post',
		   dataType:'html',
		   data:UrlInit(document.edit_page)+'p_temp=1',
	       success:(function(html){
								if(parseInt(html)==1){
									window.location.reload();
								}else if(parseInt(html)==2){
									alert('User/Group đã được phân quyền!');
								}else{
									alert('Thực hiện không thành công');
								}                                
                                	
							})
		   });
}

function _revokePrivilege(id){
	var mes = confirm("Bạn có thực sự muốn hủy quyền?");
	if(mes==true)
    $.ajax({
		   url:'/govportal-sysadmin/htdocs/SystemPrivilege/revokePrivilege.jsp',
		   type:'post',
		   dataType:'html',
		   data:'p_id='+id,
	       success:(function(html){
								if(parseInt(html)==1){
									window.location.reload();
								}else{
									alert('Thực hiện không thành công');
								}                                
                                	
							})
		   });
}

function _doBack(){
    var url = location.href;
	var index = url.indexOf("?");
	if(index>0)
		location.href=url.substring(0,index);
	else
		location.href=url;
}
function UserSelect(){
	openWindow(top,'http://trungbx.home.vn/portal/pls/portal/PORTAL.wwsec_ui.redirect_to_lov?p_redirect_url=http%3A%2F%2Ftrungbx.home.vn%3A7777%2Foiddas%2Fui%2Foracle%2Fldap%2Fdas%2Fsearch%2FLOVUserSearch%3Fappid%3DPORTAL%26callbackurl%3Dhttp%3A%2F%2Ftrungbx.home.vn%2Fportal%2Fpls%2Fportal%2FPORTAL.wwsec_ui.das_callback_user%253Fp_formname%253Dedit_page%2526p_name_param%253Dp_grantee_name%2526p_guid_param%253Dp_grantee_guid%2526p_type_param%253Dp_grantee_type%2526p_array_index%253D-1&p_title_type=USER','lovWindow',{width:600,height:650}, true,'dialog',doSelectUser_edit_pagep_grantee_name); return false;
}
function GroupSelect(){
	openWindow(top,'http://trungbx.home.vn/portal/pls/portal/PORTAL.wwsec_ui.redirect_to_lov?p_redirect_url=http%3A%2F%2Ftrungbx.home.vn%3A7777%2Foiddas%2Fui%2Foracle%2Fldap%2Fdas%2Fsearch%2FLOVGroupSearch%3Fappid%3DPORTAL%26base%3Dcn%253Dportal.090922.105152.546000000%252Ccn%253Dgroups%252Cdc%253Dhome%252Cdc%253Dvn%26otype%3Dassign%26callbackurl%3Dhttp%3A%2F%2Ftrungbx.home.vn%2Fportal%2Fpls%2Fportal%2FPORTAL.wwsec_ui.das_callback_group%253Fp_formname%253Dedit_page%2526p_name_param%253Dp_grantee_name%2526p_guid_param%253Dp_grantee_guid%2526p_type_param%253Dp_grantee_type%2526p_array_index%253D-1&p_title_type=GROUP','lovWindow',{width:600,height:650}, true,'dialog',doSelectGroup_edit_pagep_grantee_name); return false;
}
