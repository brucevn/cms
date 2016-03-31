<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<%
    String contextPath = request.getContextPath();
%>
<title>jQuery File Upload Example</title>
<script src="<%=contextPath%>/js/jquery-1.9.1.min.js"></script>
 
<script src="<%=contextPath%>/js/jquery.ui.widget.js"></script>
<script src="<%=contextPath%>/js/jquery.iframe-transport.js"></script>
<script src="<%=contextPath%>/js/jquery.fileupload.js"></script>
 
<!-- bootstrap just to have good looking page -->
<script src="<%=contextPath%>/bootstrap/js/bootstrap.min.js"></script>
<link href="<%=contextPath%>/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
 
<!-- we code these -->
<link href="<%=contextPath%>/css/dropzone.css" type="text/css" rel="stylesheet" />
<script src="<%=contextPath%>/js/myuploadfunction.js"></script>
 
<!-- header style ignore it -->
<link href="<%=contextPath%>/css/mystyle.css" rel="stylesheet">
<link href="<%=contextPath%>/css/style.css" rel="stylesheet">
 
</head>
 
<body>
<div id="site-title-content">
        <h3 class="site-header"><a rel="home" title="HMKCode" href="http://hmkcode.com/">
            HMKCode</a>
        </h3>
        <h4 class="site-description">
            Code First!
        </h4>
    </div>
<div id="header">
</div>
<div class="social-button icon">
        <a href="http://www.twitter.com/hmkcode" target="_blank" title="Visit Us On Twitter"><img src="img/twitter.png" style="border:0px;" alt="Visit Us On Twitter"></a>
        <a href="https://www.facebook.com/pages/HMKCode/157443611098005" target="_blank" title="Visit Us On Facebook"><img src="img/facebook.png" style="border:0px;" alt="Visit Us On Facebook"></a>
        <a href="https://plus.google.com/b/113117706677442855053/113117706677442855053" target="_blank" title="Visit Us On Google Plus"><img src="img/googleplus.png" style="border:0px;" alt="Visit Us On Google Plus"></a>
</div>
 
    <h1 style="text-align:center">Servlet jQuery File Upload<br></h1>
 
    <!-- user twitter -->
    <div id="user_twitter">
    <span>Your Twitter</span>
        <div class="input-prepend">
            <span class="add-on">@</span>
            <input class="span2" id="twitter" name="twitter" type="text" placeholder="Username">
        </div>
    </div>
<div style="width:700px;padding:20px;">
 
    <input id="fileupload" type="file" name="files[]" data-url="upload" multiple>
 
    <div id="dropzone" class="fade well">Drop files here</div>
 
    <div id="progress" class="progress">
        <div class="bar" style="width: 0%;"></div>
    </div>
    <h5 style="text-align:center"><i style="color:#ccc"><small>Max File Size: 2 Mb - Display last 20 files</small></i></h5>
 
    <table id="uploaded-files" class="table">
        <tr>
            <th>File Name</th>
            <th>File Size</th>
            <th>File Type</th>
            <th>Download</th>
            <th>Uploaded By</th>
        </tr>
    </table>
</div>
</body>
</html>