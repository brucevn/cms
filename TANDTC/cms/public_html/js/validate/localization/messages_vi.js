(function( factory ) {
	if ( typeof define === "function" && define.amd ) {
		define( ["jquery", "../jquery.validate"], factory );
	} else {
		factory( jQuery );
	}
}(function( $ ) {

/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: VI (Vietnamese; Tiếng Việt)
 */
$.extend($.validator.messages, {
	required: "Cần nhập dữ liệu.",
	remote: "Cần sửa cho đúng.",
	email: "Cần nhập địa chỉ email.",
	url: "Cần nhập URL.",
	date: "Cần nhập ngày.",
	dateISO: "Hãy nhập ngày the định dạng (ISO).",
	number: "Hãy nhập số.",
	digits: "Hãy nhập chữ số.",
	creditcard: "Cần nhập số thẻ tín dụng.",
	equalTo: "Cần nhập thêm lần nữa.",
	extension: "Phần mở rộng không đúng.",
	maxlength: $.validator.format("Hãy nhập từ {0} kí tự trở xuống."),
	minlength: $.validator.format("Hãy nhập từ {0} kí tự trở lên."),
	rangelength: $.validator.format("Hãy nhập từ {0} đến {1} kí tự."),
	range: $.validator.format("Hãy nhập từ {0} đến {1}."),
	max: $.validator.format("Hãy nhập từ {0} trở xuống."),
	min: $.validator.format("Hãy nhập từ {1} trở lên.")
});

}));