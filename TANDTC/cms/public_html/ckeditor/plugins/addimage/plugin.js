/**
 * Basic sample plugin inserting abbreviation elements into CKEditor editing area.
 *
 * Created out of the CKEditor Plugin SDK:
 * http://docs.ckeditor.com/#!/guide/plugin_sdk_sample_1
 */

// Register the plugin within the editor.
CKEDITOR.plugins.add( 'addimage', {

	// Register the icons.
	icons: 'addimage',

	// The plugin initialization logic goes inside this method.
	init: function( editor ) {

		// Define an editor command that opens our dialog.
		editor.addCommand( 'abbr', new CKEDITOR.dialogCommand( 'abbrDialog' ) );

		// Create a toolbar button that executes the above command.
		editor.ui.addButton( 'Abbr', {

			// The text part of the button (if available) and tooptip.
			label: 'Chèn ảnh nâng cao',

			// The command to execute on click.
			command: 'abbr',

			// The button placement in the toolbar (toolbar group name).
			toolbar: 'insert'
		});

		// Register our dialog file. this.path is the plugin folder path.
		CKEDITOR.dialog.add( 'abbrDialog', this.path + 'dialogs/addimage.js' );
	}
});

