/**
 * 
 */

$(function(){
	
	
	KE.show({
		id:'content',
		imageUploadJson : '__PUBLIC__/editor/upload_json.php',
		fileManagerJson : '__PUBLIC__/editor/file_manager_json.php',
		allowFileManager : true,
		resizeMode:0
	});
	
	
	
	$("#taginput").click(function(){
		$("#taginput").val("");
	});
	
	var taginput = $("#taginput");
	var tagdiv = $("#tagdiv");
	$("#addtag").click(function(){
		if(taginput.val()==""){
		}else if(tagdiv.children().length>=5){
			alert("超过最大标签数!!!");
		}else{
			var tags = $("<span><a class='canceltag' href=''>,</a>"+taginput.val()+"</span>");
			tags.appendTo(tagdiv);
			taginput.val("添加新标签");
			
			/*点击取消删除标签*/
			$(".canceltag").click(function(){
				$(this).removeAttr("href");
				$(this).parent().remove();
			});
		}
	});
	
	
	$("#addarticle").click(function(){
		$("#atpidhidden").val($("#atpid").val())
		$("#digesthidden").val($("#tagdiv").children("span").text());
		$("#articleForm").submit();
	});
	
	$(".mybutton").button();

});
