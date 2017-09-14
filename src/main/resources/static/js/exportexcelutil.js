var listForExcel = new Array();
var table="<table id='test'><tr>";

function exportExcel(fileName, fieldNames, excel) {
	for(var i = 0; i < fieldNames.length; i++){
		table += '<td>';
		table += fieldNames[i];
		table += '</td>';
	}
	table += '</tr>';
	table += excel;
	table += '</table>';
	
	testexcel(fileName);
}


function testexcel(fileName) {

	var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel"+"' xmlns='http://www.w3.org/TR/REC-html40'>";
    excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">';
    excelFile += '<meta http-equiv="content-type" content="application/';
    excelFile += 'vnd.ms-excel';
    excelFile += '; charset=UTF-8">';
    excelFile += "<head>";
    
    excelFile += "<!--[if gte mso 9]>";
    excelFile += "<xml>";
    excelFile += "<x:ExcelWorkbook>";
    excelFile += "<x:ExcelWorksheets>";
    excelFile += "<x:ExcelWorksheet>";
    excelFile += "<x:Name>";
    excelFile += "测试";
    excelFile += "</x:Name>";
    excelFile += "<x:WorksheetOptions>";
    excelFile += "<x:DisplayGridlines/>";
    excelFile += "</x:WorksheetOptions>";
    excelFile += "</x:ExcelWorksheet>";
    excelFile += "</x:ExcelWorksheets>";
    excelFile += "</x:ExcelWorkbook>";
    excelFile += "</xml>";
    excelFile += "<![endif]-->";
    
    excelFile += "</head>";
    excelFile += "<body>";
    excelFile += table;
    excelFile += "</body>";
    excelFile += "</html>";
    
    var base64data = base64encode(excelFile);

    try {
      var blob = new Blob([excelFile], {type: 'application/vnd.ms-excel'});
      saveAs (blob, '测试.xls');
    }
    catch (e) {
      downloadFile(fileName+'.xls', 'data:application/vnd.ms-excel;base64,' + base64data);
    }
}

function base64encode(input) {
    var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    var output = "";
    var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
    var i = 0;
    input = utf8Encode(input);
    while (i < input.length) {
      chr1 = input.charCodeAt(i++);
      chr2 = input.charCodeAt(i++);
      chr3 = input.charCodeAt(i++);
      enc1 = chr1 >> 2;
      enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
      enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
      enc4 = chr3 & 63;
      if (isNaN(chr2)) {
        enc3 = enc4 = 64;
      } else if (isNaN(chr3)) {
        enc4 = 64;
      }
      output = output +
        keyStr.charAt(enc1) + keyStr.charAt(enc2) +
        keyStr.charAt(enc3) + keyStr.charAt(enc4);
    }
    return output;
}
  
function utf8Encode(string) {
    string = string.replace(/\x0d\x0a/g, "\x0a");
    var utftext = "";
    for (var n = 0; n < string.length; n++) {
      var c = string.charCodeAt(n);
      if (c < 128) {
        utftext += String.fromCharCode(c);
      }
      else if((c > 127) && (c < 2048)) {
        utftext += String.fromCharCode((c >> 6) | 192);
        utftext += String.fromCharCode((c & 63) | 128);
      }
      else {
        utftext += String.fromCharCode((c >> 12) | 224);
        utftext += String.fromCharCode(((c >> 6) & 63) | 128);
        utftext += String.fromCharCode((c & 63) | 128);
      }
    }
    return utftext;
}
  var DownloadEvt = null;
function downloadFile(filename, data){
    var DownloadLink = document.createElement('a');

    if ( DownloadLink ){
      document.body.appendChild(DownloadLink);
      DownloadLink.style = 'display: none';
      DownloadLink.download = filename;
      DownloadLink.href = data;

      if ( document.createEvent ){
        if ( DownloadEvt == null )
          DownloadEvt = document.createEvent('MouseEvents');

        DownloadEvt.initEvent('click', true, false);
        DownloadLink.dispatchEvent(DownloadEvt);
      }
      else if ( document.createEventObject )
        DownloadLink.fireEvent('onclick');
      else if (typeof DownloadLink.onclick == 'function' )
        DownloadLink.onclick();

      document.body.removeChild(DownloadLink);
    }
}