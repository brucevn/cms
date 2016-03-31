// jQuery File Tree Plugin
//
// Version 1.01
//
// Cory S.N. LaViska
// A Beautiful Site (http://abeautifulsite.net/)
// 24 March 2008
//
// Visit http://abeautifulsite.net/notebook.php?article=58 for more information
//
// Usage: $('.fileTreeDemo').fileTree( options, callback )
//
// Options:  root           - root folder to display; default = /
//           script         - location of the serverside AJAX file to use; default = jqueryFileTree.php
//           folderEvent    - event to trigger expand/collapse; default = click
//           expandSpeed    - default = 500 (ms); use -1 for no animation
//           collapseSpeed  - default = 500 (ms); use -1 for no animation
//           expandEasing   - easing function to use on expand (optional)
//           collapseEasing - easing function to use on collapse (optional)
//           multiFolder    - whether or not to limit the browser to one subfolder at a time
//           loadMessage    - Message to display while initial tree loads (can be HTML)
//
// History:
//
// 1.01 - updated to work with foreign characters in directory/file names (12 April 2008)
// 1.00 - released (24 March 2008)
//
// TERMS OF USE
// 
// This plugin is dual-licensed under the GNU General Public License and the MIT License and
// is copyright 2008 A Beautiful Site, LLC. 
//
if(jQuery) (function($){
	
	$.extend($.fn, {
		fileTree: function(o, h) {
			// Defaults
			if( !o ) var o = {};
			if( o.root == undefined ) o.root = '0';
			if( o.page == undefined ) o.page = '0';
			if( o.script == undefined ) o.script = 'jqueryFileTree.php';
			if( o.folderEvent == undefined ) o.folderEvent = 'click';
			if( o.expandSpeed == undefined ) o.expandSpeed= 500;
			if( o.collapseSpeed == undefined ) o.collapseSpeed= 500;
			if( o.expandEasing == undefined ) o.expandEasing = null;
			if( o.collapseEasing == undefined ) o.collapseEasing = null;
			if( o.multiFolder == undefined ) o.multiFolder = true;
			if( o.loadMessage == undefined ) o.loadMessage = 'Loading...';
			
			$(this).each( function() {
				
				function showTree(c, t,p) {
					$(c).addClass('wait');
					$(".jqueryFileTree.start").remove();
					$.post(o.script, { p_siteid: t,p_pageid:p}, function(data) {
						$(c).find('.start').html('');
						$(c).removeClass('wait').append(data);
						if( o.root == t ) $(c).find('UL:hidden').show(); else $(c).find('UL:hidden').slideDown({ duration: o.expandSpeed, easing: o.expandEasing });
						bindTree(c);
					});
					showFiles(t,p,1);						
					document.upload.p_site.value=t;
					document.upload.p_folder.value=p;
				}
				
				function bindTree(t) {
					$(t).find('LI A').bind(o.folderEvent, function() {
						if( $(this).parent().hasClass('directory') ) {
							if( $(this).parent().hasClass('collapsed') ) {
								// Expand
								if( !o.multiFolder ) {
									$(this).parent().parent().find('UL').slideUp({ duration: o.collapseSpeed, easing: o.collapseEasing });
									$(this).parent().parent().find('LI.directory').removeClass('expanded').addClass('collapsed');
								}
								$(this).parent().find('UL').remove(); // cleanup
								showTree( $(this).parent(), $(this).attr('rel1'), $(this).attr('rel2'));
								$(this).parent().removeClass('collapsed').addClass('expanded');
							} else {
								// Collapse
								$(this).parent().find('UL').slideUp({ duration: o.collapseSpeed, easing: o.collapseEasing });
								$(this).parent().removeClass('expanded').addClass('collapsed');
							}
						} else {
							h($(this).attr('rel'));
						}
						return false;
					});
					// Prevent A from triggering the # on non-click events
					if( o.folderEvent.toLowerCase != 'click' ) $(t).find('LI A').bind('click', function() { return false; });
				}
				// Loading message
				$(this).html('<ul class="jqueryFileTree start"><li class="wait">' + o.loadMessage + '<li></ul>');
				// Get the initial file list
				showTree( $(this), escape(o.root),0);
			});
		}
	});
	
})(jQuery);
function showFiles(siteid,pageid,page){
	$.post('/portal/pls/portal/trungbx.FILE_MANAGER.listFiles', { p_siteid:siteid,p_pageid:pageid,p_page:page}, function(data) {
		if(data!=null)
			$('#file_tree').html(data);
		else $('#file_tree').html('');
	});
}
function choseFile(id,path){
		window.top.opener.SetUrl(id,path);
		window.close();
}
function createFolder(){
	 var sFolderName ;
	 while ( true ){
		 sFolderName = prompt( 'Nhập tên thư mục cần tạo:', '' ) ;
		
		 if ( sFolderName == null )
		 	return ;
		 else if ( sFolderName.length == 0 )
		 	alert( 'Bạn cần nhập tên thư mục' ) ;
		 else
		 break ;
	 }
	 $.get('http://192.168.11.211:8080/FileUpload/createFolder.jsp', { p_parentid: $('#folder_id').val(),p_name:sFolderName,p_user:$('#user').val()}, function(data) {
        if(parseInt(data)==2){																																				
			alert("Thư mục đã tồn tại");
		}	
		if($('#folder_id').val()!=0){
			$('#'+$('#folder_id').val()+' > ul').remove();
			$('#sub'+$('#folder_id').val()).fileTree({
					root: $('#folder_id').val(),
					script: 'FolderList.jsp',                    
					expandSpeed: 1000,
					collapseSpeed: 1000,
					multiFolder: false
				}, function(file){
					null;
				});	
		}else{
			$('#folder_tree').fileTree({
					root: $('#folder_id').val(),
					script: 'FolderList.jsp',                    
					expandSpeed: 1000,
					collapseSpeed: 1000,
					multiFolder: false
				}, function(file){
					null;
				});	
		}
	});
}