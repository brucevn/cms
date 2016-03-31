function remove(id){
    $('#method').val('removeItemRole');
    $('#roleId').val(id);
    $('#itemRole').submit();
}
function selectObj(id,name){
    $('#userGroup').val(id);
    $('#userGroupName').val(name);
}
function selectUserGrop(type){
    if(type=='user'){
        $('#isUser').val(1);
    }else{
        $('#isUser').val(0);
    }
    window.open('/cms/ldap.do?type='+type,'LDAP','width=800,height=600',true);
}
function back(){
    $('#method').val('Search');
    $('form').submit();
}