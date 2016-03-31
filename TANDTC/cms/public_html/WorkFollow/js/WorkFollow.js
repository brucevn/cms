function addWorkFollow(){
    $('#method').val("form");
    $('#workfollow').submit();
}
function updateWorkFollow(id){
    $('#method').val("form");
    $('#id').val(id);
    $('#workfollow').submit();
}
function deleteWorkFollow(id){
    var mess=confirm("Bạn thực sự muốn xóa bỏ quy trình này?");
    if(mess==true){
        $('#method').val("delete");
        $('#id').val(id);
        $('#workfollow').submit();
    }
}
function cancel(){
    $('#method').val("Search");    
    $('#workfollow').submit();
}