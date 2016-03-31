function search(){
    $('#method').val('Search');
    $('#propertyId').val('');    
    $('#property-search-form').submit();
}
function createNewProperty(){
    $('#method').val('ShowForm');
    $('#propertyId').val('');
    $('#property-search-form').submit();
}
function updateProperty(id){
    $('#method').val('ShowForm');
    $('#propertyId').val(id);    
    $('#property-search-form').submit();
}
function deleteProperty(id){
    $('#method').val('Delete');
    $('#propertyId').val(id);
    $('#property-search-form').submit();
}
function back(){
    $('#method').val('Search');
    $('#property-form').submit();
}
function update(){
    if($('#propertyId').val()==0)
        $('#searchText').val('');
    $('#property-form').submit();
}
function gotoPage(page){
    $('#method').val('Search');
    $('#page').val(page);
    $('#property-search-form').submit();
}