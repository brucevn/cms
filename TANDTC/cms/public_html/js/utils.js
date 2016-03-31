function newOrder(list, csv) {
	csv.value = "";
	for (var i = 0; i <= list.length - 1; i++){
		if (list.options[i].value != "") {
		   csv.value += list.options[i].value;
		   if (i < list.length - 1 && list.options[i+1].value != "")
			  csv.value += ",";
		}
	}
 }
function deleteBlankRowIfNotEmpty(list)
{
   var idx = -1;
   var val = "";
// find a blank row in table
   for (i = 0; i < list.length; i++){
		val = list.options[i].value;
		if (val == "") {
		   idx = i;
		   break;
		}
   }
   if (idx >= 0 && (list.length > 1))
	  list.options[idx] = null;
}
function selectAll(list)
{
  for ( i = 0; i <= list.length-1; i++ )
	list.options[i].selected = true;
  return true;
}
function unSelectAll(list)
{
  for ( i = 0; i <= list.length-1; i++ )
	list.options[i].selected = false;
  return true;
}
function clearList(list)
{
  list.length = 0;
}
function copyToList(fromList, toList, direction,obj) {
  for ( i = 0; i <= fromList.length-1;) {
	if (fromList.options[i].selected) {
		txt = fromList.options[i].text;
		val = fromList.options[i].value;
		if ( val != "" ) {
		   // check if value is a spacer or special element
		   if ( (val == "spacer") ) {
				if ( direction == "left" ) {
					 // remove from right and do not add on left
					 fromList.options[i]= null;
				}
				else {
					// add to right but do not remove from left
					fromList.options[i].selected = false;
					toList.options[toList.length] = new Option( txt, val, false, true );
					toList.options[toList.selectedIndex].selected = false;
				}
		   }
		   else {  //only increment when not moving and deleting
			 // create a new row
			 toList.options[toList.length] = new Option( txt, val, false, true );
			 // added these lines
			 // removes from fromList and unselects item in toList
			 fromList.options[i]= null;
			 toList.options[toList.selectedIndex].selected = false;
		   }  //only increment when not moving and deleting
		} else {
			i++; // increment when selected element is empty
		}
	}
	else i++;  //only increment when not moving and deleting
  }
  deleteBlankRowIfNotEmpty(fromList);
  deleteBlankRowIfNotEmpty(toList);  
  //Set the order  
   if(direction=="right"){
		newOrder(toList,obj);
		if(toList.length>=1){
			toList.options[0].selected=true;
		}
	}else{
		 newOrder(fromList,obj);
		 if(fromList.length>=1)
			 fromList.options[0].selected=true;
	}
}
function copyAll(fromList, toList, direction,obj) {
	 indexofspacer = -1;
	 indexofitem = toList.length;
	 for ( i = 0; i <= fromList.length-1; i++ ) {
		 txt = fromList.options[i].text;
		 val = fromList.options[i].value;
		 if (fromList.options[i].selected) {
				fromList.options[i].selected = false;
		 }
		 if ( val != "" ) {
			 // check if we need to copy the spacer too
			 if ( (val == "spacer") ) {
					indexofspacer = i;
			 }
			 else { // Not a spacer
				  toList.options[indexofitem] = new Option( txt, val, false, true );
				  toList.options[indexofitem].selected = false;
				  indexofitem++;
			 }
		 }
	 }
		if (indexofspacer != -1 && direction == "right" ) // let the spacer be on the from list
			fromList.length = indexofspacer + 1;
		else
	 clearList(fromList);
	 deleteBlankRowIfNotEmpty(toList);
	 unSelectAll(toList);	 
	 if(direction=="right"){
		newOrder(toList,obj);
		if(toList.length>=1)
			toList.options[0].selected=true;
	}else{
		 newOrder(fromList,obj);
		 if(fromList.length>=1)
			 fromList.options[0].selected=true;
	}
}
