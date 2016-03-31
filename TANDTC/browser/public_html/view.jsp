<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.cms.utils.PortletUtil" %>
<%
        /*PortletRenderRequest pReq = (PortletRenderRequest)
            request.getAttribute(HttpCommonConstants.PORTLET_RENDER_REQUEST);*/
        String l_user = "trungbx";//pReq.getUser().getName();  
        String type=PortletUtil.getParameterValue(request,"type");
        String strExt="gif|jpe?g|png|swf";
        if(type!=null&&type.length()>0&&type.equalsIgnoreCase("Files")){
            strExt="pdf|doc|docx|xsl|xslx|zip|rar|txt|rft|ppt|pptx|avi|mp3|flv|mpe?g|wav";
        }
%>
<link rel="stylesheet" href="<%= request.getContextPath()%>/sysadmin/css/jqueryFileTree.css" type="text/css" media="screen" />
<link rel="stylesheet" href="<%= request.getContextPath()%>/sysadmin/css/jqueryFileTreeCust.css" type="text/css" media="screen" />
<link rel="stylesheet" href="<%= request.getContextPath()%>/sysadmin/css/jquery.treeview.css" type="text/css" media="screen" />
<link rel="stylesheet" href="<%= request.getContextPath()%>/sysadmin/css/FileBrowserStyle.css" type="text/css" media="screen" />
<link href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="<%= request.getContextPath()%>/css/style.css" rel="stylesheet">
<link href="<%= request.getContextPath()%>/css/jquery.fileupload.css" rel="stylesheet">
<div id="content">
    <div id="foldertree">
        <h2 id="folders_view_label">Thư mục</h2>
        <div id="folder_tree"></div>
    </div>
    <div id="filelist">
        <div id="filelist_header">
            <input type="text" value="" id="txtName" maxlength="50" size="30"/>
            <input type="button" value="Tìm kiếm" id="_search"/>
        <div class="container">
            <br/>
            <span class="btn btn-success fileinput-button">
                <i class="glyphicon glyphicon-plus"></i>
                <span>Thêm...</span>
                <!-- The file input field used as target for the file upload widget -->
                <input id="fileupload" name="files[]" multiple="" type="file">
            </span>
            <br>
            <br>
            <!-- The global progress bar -->
            <div id="progress" class="progress">
                <div class="progress-bar progress-bar-success"></div>
            </div>
            <!-- The container for the uploaded files -->
            <div id="files" class="files"></div>
        </div>  
        </div>
        <div id="filelist_content">            
        </div>
        <div id="filelist_footer">
            <input type="hidden" name="owner" value="<%= l_user%>" id="owner"/>
            <input type="hidden" name="dir" id="folder_id" value="0"/>                
            <input type="button" value="Tạo thư mục" onClick="javascript:createFolder();"/>
            <input type="button" value="Xóa thư mục" onClick="javascript:deleteFolder();"/> 
            <input type="hidden" value="<%= request.getContextPath()%>" id="contextpath"/>
            <input type="hidden" value="<%=request.getParameter("type")!=null?request.getParameter("type"):""%>" id="type">
        </div>
    </div>    
</div>
<script src="<%= request.getContextPath()%>/js/jquery.min.js"></script>
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="<%= request.getContextPath()%>/js/vendor/jquery.ui.widget.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="<%= request.getContextPath()%>/js/load-image.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="<%= request.getContextPath()%>/js/canvas-to-blob.min.js"></script>
<!-- Bootstrap JS is not required, but included for the responsive demo navigation -->
<script src="<%= request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="<%= request.getContextPath()%>/js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="<%= request.getContextPath()%>/js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<script src="<%= request.getContextPath()%>/js/jquery.fileupload-process.js"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="<%= request.getContextPath()%>/js/jquery.fileupload-image.js"></script>
<!-- The File Upload audio preview plugin -->
<script src="<%= request.getContextPath()%>/js/jquery.fileupload-audio.js"></script>
<!-- The File Upload video preview plugin -->
<script src="<%= request.getContextPath()%>/js/jquery.fileupload-video.js"></script>
<!-- The File Upload validation plugin -->
<script src="<%= request.getContextPath()%>/js/jquery.fileupload-validate.js"></script>
<script src="<%= request.getContextPath()%>/sysadmin/js/jqueryFileTreeCust.js"></script>
<script type="text/javascript">
        function showFiles(id,page,name){
            $.post('<%= request.getContextPath()%>/browser.do', {method:'showFiles',dir: id,page:page,name:name,type:$('#type').val()}, function(data) {
                    if(data!=null)
                            $('#filelist_content').html(data);
                    else $('#filelist_content').html('');
            });
        }
        $(document).ready( function() {
            var h = $(window).height();
            var w = $(window).width();
            $('#filelist_content').css('height',h-120);            
            $('#foldertree').css('height',h-45);
            $('#filelist').css('width',w-300);
            $("#folder_tree").fileTree({
                root: "0",
                script: "<%= request.getContextPath()%>/browser.do?method=showFolders",                    
                expandSpeed: 1000,
                collapseSpeed: 1000,
                multiFolder: false
            }, function(file){
            });            
        });
        function choseFile(id,path){
            window.top.opener.mAttach_Item_Insert(id,path);
            window.close();
        }
        function createFolder(){
             var sFolderName ;
             if($('#folder_id').val()==0||$('#'+$('#folder_id').val()).attr('class')=='directory expanded'){
             while ( true ){
                 sFolderName = prompt( 'Nhập tên thư mục cần tạo:', '' ) ;                        
                 if (sFolderName == null )
                        return;
                 else if ( sFolderName.length == 0 )
                        alert( 'Bạn cần nhập tên thư mục' ) ;
                 else
                    break ;
             }
             $.post('<%= request.getContextPath()%>/browser.do', {method:'createFolder',dir:$('#folder_id').val(),owner:$('#owner').val(),folderName:sFolderName}, function(data) {
                if($('#folder_id').val()>0){
                        $('#'+$('#folder_id').val()+' > ul').remove();
                        $('#sub'+$('#folder_id').val()).fileTree({
                                        root: $('#folder_id').val(),
                                        script: '<%= request.getContextPath()%>/browser.do?method=showFolders',                    
                                        expandSpeed: 1000,
                                        collapseSpeed: 1000,
                                        multiFolder: false
                                }, function(file){                                        
                                });	
                }else{
                    window.location.href=window.location.href;
                }
            });
             }else{
                 alert("Bạn cần chọn thư mục")
             }
        }
        function deleteFolder(){            
            if($('#folder_id').val()==0){
                    alert('Bạn cần chọn một thư mục trên cây thư mục');
                    return;
            }
            var ms = confirm('Bạn thực sự muốn xóa thư mục');
            if(ms==true){
                $.post('<%= request.getContextPath()%>/browser.do', {method:'deleteFolder',dir:$('#folder_id').val(),owner:$('#owner').val()}, function(data) {
                    if(parseInt(data)>0){
                        $('#'+parseInt(data)+' > ul').remove();
                        $('#sub'+parseInt(data)).fileTree({
                                root: parseInt(data),
                                script: '<%= request.getContextPath()%>/browser.do?method=showFolders',                    
                                expandSpeed: 1000,
                                collapseSpeed: 1000,
                                multiFolder: false
                        }, function(file){                                
                        });
                    }else if(parseInt(data)==0){
                        $("#folder_tree").fileTree({
                            root: "0",
                            script: "<%= request.getContextPath()%>/browser.do?method=showFolders",                    
                            expandSpeed: 1000,
                            collapseSpeed: 1000,
                            multiFolder: false
                        }, function(file){                            
                        });
                    }else{
                        alert('Thực hiện không thành công! Không kết nối được CSDL hoặc thư mục có chứa file!');
                    }
                });
            }
        }
        
        $(document).ready(function(){
            $('#_upload').click(function(){
                if($('#_root').val()==0){
                    alert("Bạn cần chọn thư mục gốc");
                    $('#_root').focus();
                }else{
                    $('#upload').submit();         
                }
            });
            $("#txtName").keyup(function(event){
              if(event.keyCode == 13){
                showFiles($('#folder_id').val(),1,$('#txtName').val());
              }
            });

            $('#_search').click(function(){
                showFiles($('#folder_id').val(),1,$('#txtName').val());
            });
        });
        // Helper function to get parameters from the query string.
        function getUrlParam( paramName ) {
            var reParam = new RegExp( '(?:[\?&]|&)' + paramName + '=([^&]+)', 'i' ) ;
            var match = window.location.search.match(reParam) ;
        
            return ( match && match.length > 1 ) ? match[ 1 ] : null ;
        }
        function selectFile(id,fileName){            
            <%                
                String funcNum = PortletUtil.getParameterValue(request,"CKEditorFuncNum");
                out.println("var fileUrl = '"+request.getContextPath()+"/browser.do?method=image&id='+id;");
                if(funcNum.length()>0&&Integer.parseInt(funcNum)>0){
                    out.println("window.opener.CKEDITOR.tools.callFunction("+funcNum+",fileUrl);");
                }else{
                    String func= PortletUtil.getParameterValue(request,"func");
                    String FieldId1= PortletUtil.getParameterValue(request,"fieldId1");
                    String FieldId2= PortletUtil.getParameterValue(request,"fieldId2");
                    out.println("window.opener."+func+"('"+FieldId1+"','"+FieldId2+"',id,fileName)");
                }
            %>
            window.close();
        }
        /*jslint unparam: true, regexp: true */
        /*global window, $ */
        $(function () {
            'use strict';
            // Change this to the location of your server-side upload handler:
            var url = '<%= request.getContextPath()%>/upload';
            $('#fileupload').fileupload({
                url: url,
                dataType: 'json',
                autoUpload: true,
                acceptFileTypes: /(\.|\/)(<%=strExt%>)$/i,
                maxFileSize: 5000000, // 5 MB
                // Enable image resizing, except for Android and Opera,
                // which actually support image resizing, but fail to
                // send Blob objects via XHR requests:
                disableImageResize: /Android(?!.*Chrome)|Opera/
                    .test(window.navigator.userAgent),
                previewMaxWidth: 100,
                previewMaxHeight: 100,
                previewCrop: true,
                done: function (e, data) {
                    showFiles($('#folder_id').val(),1,$('#txtName').val());
                }
            }).on('fileuploadadd', function (e, data) {
                $('#files').html('');
                data.context = $('<div/>').appendTo('#files');
                $.each(data.files, function (index, file) {
                    var node = $('<p/>')
                            .append($('<span/>').text(file.name));
                    if (!index) {
                        node
                            .append('<br>');
                    }
                    node.appendTo(data.context);
                });
            }).on('fileuploadprocessalways', function (e, data) {
                var index = data.index,
                    file = data.files[index],
                    node = $(data.context.children()[index]);
                if (file.preview) {
                    node
                        .prepend('<br>')
                        .prepend(file.preview);
                }
                if (file.error) {
                    node
                        .append('<br>')
                        .append($('<span class="text-danger"/>').text(file.error));
                }
                if (index + 1 === data.files.length) {
                    data.context.find('button')
                        .text('Upload')
                        .prop('disabled', !!data.files.error);
                }
            }).on('fileuploadprogressall', function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('#progress .progress-bar').css(
                    'width',
                    progress + '%'
                );
            }).on('fileuploadfail', function (e, data) {
                $.each(data.files, function (index, file) {
                    var error = $('<span class="text-danger"/>').text('Tải dữ liệu không thành công!');
                    $(data.context.children()[index])
                        .append('<br>')
                        .append(error);
                });
            }).prop('disabled', !$.support.fileInput)
                .parent().addClass($.support.fileInput ? undefined : 'disabled');
        }).bind('fileuploadsubmit', function (e, data) {
            // The example input, doesn't have to be part of the upload form:
            var id = $('#folder_id').val();        
            var owner = $('#owner').val();
            data.formData = {id:id,owner:owner};       
        });
</script>
