function search(){
    $('#method').val('Search');
    $('#typeId').val('');    
    $('#type-search-form').submit();
}
function createNewType(){
    $('#method').val('ShowForm');
    $('#typeId').val('');
    $('#type-search-form').submit();
}
function updateType(id){
    $('#method').val('ShowForm');
    $('#typeId').val(id);    
    $('#type-search-form').submit();
}
function deleteType(id){
    $('#method').val('Delete');
    $('#typeId').val(id);
    $('#type-search-form').submit();
}
function back(){
    $('#method').val('Search');
    $('#type-form').submit();
}
function update(){
    if($('#typeId').val()==0)
        $('#searchText').val('');
    $('#type-form').submit();
}
function gotoPage(page){
    $('#method').val('Search');
    $('#page').val(page);
    $('#type-search-form').submit();
}
function showProperties(id){
    $('#method').val('showProperties');   
    $('#typeId').val(id);
    $('#type-search-form').submit();
}
function DeleteProperty(id){
    $('#method').val('deleteProperty');   
    $('#typePropertyId').val(id);
    $('#type-properties-form').submit();
}
function up(id){
    $('#method').val('Up');   
    $('#typePropertyId').val(id);
    $('#type-properties-form').submit();
}
function down(id){
    $('#method').val('Down');   
    $('#typePropertyId').val(id);
    $('#type-properties-form').submit();
}
function require(id){
    $('#method').val('require');   
    $('#typePropertyId').val(id);
    $('#type-properties-form').submit();
}
function back(){
    var href = window.location.href;    
    if(href.indexOf("?")>0){
        href=href.substring(0,href.indexOf("?"));
    }
    window.location.href=href+"?method=Search";
}
function addRole(typeId){
    $('#method').val('itemRole');   
    $('#typeId').val(typeId);
    $('#type-search-form').submit();
}