var BrowserLink='/browser/view.do';
var imgSourceLink='/browser/browser.do?method=image&id=';
var downloadLink='/browser/upload?fileId=';
var NoImageSource='/cms/images/No_Image.jpg';
function gotoPage(page){
    $('#page').val(page);
    $('form').submit();
}