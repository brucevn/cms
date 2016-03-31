function search(parentId){
    $('#method').val('Search');
    $('#perspectiveId').val('');
    if(parentId>0)
        $('#parentId').val(parentId);
    else{
        $('#parentId').val('');
    }
    $('#perspective-search-form').submit();
}
function createNewPerspective(){
    $('#method').val('ShowForm');
    $('#perspectiveId').val('');
    $('#perspective-search-form').submit();
}
function updatePerspective(id){
    $('#method').val('ShowForm');
    $('#perspectiveId').val(id);    
    $('#perspective-search-form').submit();
}
function deletePerspective(id){
    $('#method').val('Delete');
    $('#perspectiveId').val(id);
    $('#perspective-search-form').submit();
}
function showSubCategories(id){
    $('#method').val('Search');
    $('#parentId').val(id);
    $('#perspective-search-form').submit();
}
function back(){
    $('#method').val('Search');
    $('#perspective-form').submit();
}
function update(){
    if($('#perspectiveId').val()==0)
        $('#searchText').val('');
    $('#perspective-form').submit();
}
function gotoPage(page){
    $('#method').val('Search');
    $('#page').val(page);
    $('#perspective-search-form').submit();
}