$(function () { 
    $('#fileupload').fileupload({ 
        dataType: 'json', 
        done: function (e, data) {
            $.each(data.result, function (index, file) {                
                if(parseInt(file.isImage)==1){
                    $('#first-position').after('<a id="file'+file.fileId+'" class="item file_entry" href="javascript:void(0);" onClick="javascript:selectFile('+file.fileId+',\''+file.fileName+'\');" role="listiem presentation" title="'+file.fileName+'"/>')
                        $('#file'+file.fileId).append('<img src="'+$('#contextpath').val()+'/browser.do?method=image&id='+file.fileId+'" border="0" width="96px" height="96px"/>')
                        .append('<span>'+file.fileName+'</span>')
                        .append('<span>'+file.fileSize+'&nbsp;KB</span>');
                }else{
                    $('#first-position').after('<a id="file'+file.fileId+'" class="item file_entry" href="javascript:void(0);" onClick="javascript:selectFile('+file.fileId+',\''+file.fileName+'\');" role="listiem presentation" title="'+file.fileName+'"/>')
                        $('#file'+file.fileId).append('<img src="'+$('#contextpath').val()+'/sysadmin/images/ext/'+file.fileExt+'_ext.png" border="0" width="96px" height="96px"/>')
                        .append('<span>'+file.fileName+'</span>')
                        .append('<span>'+file.fileSize+'&nbsp;KB</span>');
                }
            });
        }, 
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                'width',
                progress + '%'
            );
        }
    }).bind('fileuploadsubmit', function (e, data) {
        // The example input, doesn't have to be part of the upload form:
        var id = $('#folder_id').val();        
        var owner = $('#owner').val();
        data.formData = {id:id,owner:owner};       
    });
 
});