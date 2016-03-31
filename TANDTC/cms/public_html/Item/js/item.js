$(document).ready(function(){
    $('.cms-editor').ckeditor({
        filebrowserBrowseUrl: '/browser/view.do?type=Images'
    });
    $('.cms-simpledate').datepicker({ 
        dateFormat: "dd/mm/yy",
        showOn: "both",
        buttonImage: "/cms/images/calendar.gif"
    });
    $('.cms-fulldate').datetimepicker({
        dateFormat: "dd/mm/yy",
        timeFormat: "HH:mm",
        showOn: "both",
        buttonImage: "/cms/images/calendar.gif"
    });
})
function openImageBrowser(fieldId1,fieldId2){
    window.open(BrowserLink+'?type=Images&func=updateImage&fieldId1='+fieldId1+'&fieldId2='+fieldId2,'browser','resizable=no,scrollbars=yes,width=1024,height=800');
}
function updateImage(fieldId1,fieldId2,id,fileName){
    $('#'+fieldId1).val(id);
    $('#'+fieldId2).attr('src',imgSourceLink+id);
}
function removeImage(fieldId1,fieldId2){
    $('#'+fieldId1).val();
    $('#'+fieldId2).attr('src',NoImageSource);
}
function openFileBrowser(fieldId1,fieldId2){
    window.open(BrowserLink+'?type=Files&func=attachFile&fieldId1='+fieldId1+'&fieldId2='+fieldId2,'browser','resizable=no,scrollbars=yes,width=1024,height=800');
}
function attachFile(fieldId1,fieldId2,id,fileName){
    var fieldId1Val = $('#'+fieldId1).val();
    if(fieldId1Val.indexOf(id+',')==-1){
        var temp=fieldId1.substr(0,fieldId1.lastIndexOf('_'));
        var size=$('#'+temp+'_size').val();
        size=parseInt(size)+1;
        var contextPath=$('#contextPath').val();        
        $('#'+fieldId2).append('<li id="'+temp+size+'" class="attach_file"/>');
        $('#'+temp+size).html('<img title="Xóa" onclick="removeAttachFile(\''+fieldId1+'\',\''+temp+size+'\','+id+')" src="'+contextPath+'/images/del.gif" align="middle" class="cms-util-icon"/>'
                        +'<a href="'+downloadLink+id+'">'+fileName+'<\/a>');
        $('#'+temp+'_size').val(size);    
        $('#'+fieldId1).val(fieldId1Val+id+',');
    }
}
function removeAttachFile(holderId,removeId,id){    
    var ids=$('#'+holderId).val();
    ids=ids.replace(id+',','');
    $('#'+holderId).val(ids);
    $('#'+removeId).remove();    
}
function updateDate(temp,value){
    $('#'+value).val($('#'+temp).val());
}
function addNewItems(){
    $('#method').val('ChooseType');
    $('#item-form').submit();
}
function editItem(id){
    $('#method').val('ShowForm');
    $('#itemId').val(id);
    $('#item-form').submit();
}
function deleteItem(id){
    var mes =confirm("Bạn có chắc muốn xóa không?");
    if(mes==true){
        $('#method').val('delete');
        $('#itemId').val(id);
        $('#item-form').submit();
    }
}
function Search(){
    $('#method').val('Search');
    $('#item-form').submit();
}