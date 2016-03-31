function search(parentId){
    $('#method').val('Search');
    $('#categoryId').val('');
    if(parentId>0)
        $('#parentId').val(parentId);
    else{
        $('#parentId').val('');
    }
    $('#category-search-form').submit();
}
function createNewCategory(){
    $('#method').val('ShowForm');
    $('#categoryId').val('');
    $('#category-search-form').submit();
}
function updateCategory(id){
    $('#method').val('ShowForm');
    $('#categoryId').val(id);    
    $('#category-search-form').submit();
}
function deleteCategory(id){
    $('#method').val('Delete');
    $('#categoryId').val(id);
    $('#category-search-form').submit();
}
function showSubCategories(id){
    $('#method').val('Search');
    $('#parentId').val(id);
    $('#category-search-form').submit();
}
function back(){
    $('#method').val('Search');
    $('#category-form').submit();
}
function update(){
    if($('#categoryId').val()==0)
        $('#searchText').val('');
    $('#category-form').submit();
}
function gotoPage(page){
    $('#method').val('Search');
    $('#page').val(page);
    $('#category-search-form').submit();
}