/**
 * The abbr dialog definition.
 *
 * Created out of the CKEditor Plugin SDK:
 * http://docs.ckeditor.com/#!/guide/plugin_sdk_sample_1
 */

// Our dialog definition.
CKEDITOR.dialog.add( 'abbrDialog', function( editor ) {
	return {

		// Basic properties of the dialog window: title, minimum size.
		title: 'Chèn ảnh nâng cao',
		minWidth: 400,
		minHeight: 200,

		// Dialog window contents definition.
		contents: [
			{
				// Definition of the Basic Settings dialog tab (page).
				id: 'tab-basic',
				label: 'Ảnh',

				// The tab contents.
				elements: [
                                        {
                                            type: 'hbox',
                                            id:'himage',
                                            widths: [ '75%', '25%'],
                                            children: [
                                                {
                                                    // Text input field for the abbreviation text.
                                                    type: 'text',
                                                    id: 'img',
                                                    label: 'Ảnh',

                                                    // Validation checking whether the field is not empty.
                                                    validate: CKEDITOR.dialog.validate.notEmpty( "Bạn cần nhập thông tin ảnh!" )
                                                },
                                                {
                                                    type: 'button',
                                                    hidden: true,
                                                    id: 'browse',                                                    
                                                    filebrowser: 'tab-basic:img',
                                                    label: editor.lang.common.browseServer,
                                                    style: 'float:right'
                                                },
                                            ]
                                        },
                                        {
                                            type: 'hbox',
                                            id:'hproperty',
                                            widths: [ '50%', '50%'],
                                            children: [
                                                {
                                                    // Text input field for the abbreviation text.
                                                    type: 'text',
                                                    id: 'imgHight',
                                                    label: 'Cao'
                                                },
                                                {
                                                    type: 'text',                                                    
                                                    id: 'imgWidth',                                                                                                        
                                                    label:'Rộng'
                                                },
                                            ]
                                        },
					{
						// Text input field for the abbreviation title (explanation).
						type: 'text',
						id: 'description',
						label: 'Mô tả',
						validate: CKEDITOR.dialog.validate.notEmpty( "Bạn cần nhập trường mô tả!" )
					}
				]
			},

			// Definition of the Advanced Settings dialog tab (page).
			{
				id: 'tab-adv',
				label: 'Thuộc tính',
				elements: [
					{
						// Another text field for the abbr element id.
						type: 'text',
						id: 'id',
						label: 'id'
					},
                                        {
						// Another text field for the abbr element id.
						type: 'text',
						id: 'class',
						label: 'class'
					}
				]
			}
		],

		// This method is invoked once a user clicks the OK button, confirming the dialog.
		onOk: function() {

			// The context of this function is the dialog object itself.
			// http://docs.ckeditor.com/#!/api/CKEDITOR.dialog
			var dialog = this;
                        
                        var imgSrc=dialog.getValueOf( 'tab-basic', 'img' );
                        var imgH=dialog.getValueOf( 'tab-basic', 'imgHight' );
                        var imgW=dialog.getValueOf( 'tab-basic', 'imgWidth' );
                        var imgDes=dialog.getValueOf( 'tab-basic', 'description' );			
			var id = dialog.getValueOf( 'tab-adv', 'id' );
                        var _class=dialog.getValueOf( 'tab-adv', 'class' );   
                        
                        var html= '<table cellpadding="0" cellspacing="0" width="100%" border="0"';
                        if(id){
                            html+=' id="'+id+'"';
                        }
                        if(_class){
                            html+=' class="'+_class+'"';
                        }else{
                            html+=' class="image_holder"';
                        }                        
                        html+='/><tr><td align="center">';
                        html+='<img src="'+imgSrc+'"';
                        if(imgH){
                            html+=' height="'+imgH+'"';
                        }
                        if(imgW){
                            html+=' width="'+imgW+'"';
                        }
                       html+='/></td></tr><tr><td align="center">'+imgDes+'</td></tr></table>';
                       editor.insertHtml(html,'unfiltered_html');
		}
	};
});